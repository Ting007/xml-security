/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "<WebSig>" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 2001, Institute for
 * Data Communications Systems, <http://www.nue.et-inf.uni-siegen.de/>.
 * The development of this software was partly funded by the European
 * Commission in the <WebSig> project in the ISIS Programme.
 * For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.xml.security.transforms.implementations;



import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.transforms.TransformSpi;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


/**
 * Implements the <CODE>http://www.w3.org/2000/09/xmldsig#enveloped-signature</CODE>
 * transform.
 *
 * @author Christian Geuer-Pollmann
 */
public class TransformEnvelopedSignature extends TransformSpi {

   /** Field implementedTransformURI */
   public static final String implementedTransformURI =
      Transforms.TRANSFORM_ENVELOPED_SIGNATURE;

   //J-
   public boolean wantsOctetStream ()   { return true; }
   public boolean wantsNodeSet ()       { return true; }
   public boolean returnsOctetStream () { return true; }
   public boolean returnsNodeSet ()     { return false; }
   //J+

   /**
    * Method engineGetURI
    *
    *
    */
   protected String engineGetURI() {
      return implementedTransformURI;
   }

   /**
    * This transform performs the Enveloped-Signature-Transform by
    *
    * @param input
    *
    * @throws TransformationException
    */
   protected XMLSignatureInput enginePerformTransform(XMLSignatureInput input)
           throws TransformationException {

      try {

         /**
          * If the actual input is an octet stream, then the application MUST
          * convert the octet stream to an XPath node-set suitable for use by
          * Canonical XML with Comments. (A subsequent application of the
          * REQUIRED Canonical XML algorithm would strip away these comments.)
          *
          * ...
          *
          * The evaluation of this expression includes all of the document's nodes
          * (including comments) in the node-set representing the octet stream.
          */

         /*
         if (input.isOctetStream()) {
            input.setNodesetXPath(Canonicalizer.XPATH_C14N_WITH_COMMENTS);
         }
         */
         Set inputSet = input.getNodeSet();

         if (inputSet.isEmpty()) {
            Object exArgs[] = { "input node set contains no nodes" };

            throw new TransformationException("generic.EmptyMessage", exArgs);
         }

         Element transformElement = this._transformObject.getElement();
         Node signatureElement = transformElement;
         boolean found = false;

         searchSignatureElemLoop: while (true) {
            if ((signatureElement == null)
                    || (signatureElement.getNodeType() == Node.DOCUMENT_NODE)) {
               break searchSignatureElemLoop;
            }

            if (((Element) signatureElement).getNamespaceURI()
                    .equals(Constants
                    .SignatureSpecNS) && ((Element) signatureElement)
                       .getLocalName().equals(Constants._TAG_SIGNATURE)) {
               found = true;

               break searchSignatureElemLoop;
            }

            signatureElement = signatureElement.getParentNode();
         }

         if (!found) {
            throw new TransformationException(
               "envelopedSignatureTransformNotInSignatureElement");
         }

         Document transformDoc = transformElement.getOwnerDocument();
         Document inputDoc = XMLUtils.getOwnerDocument((Node) inputSet.iterator().next());

         if (transformDoc != inputDoc) {
            throw new TransformationException("xpath.funcHere.documentsDiffer");
         }

         Set resultSet = new HashSet();
         Iterator iterator = inputSet.iterator();

         while (iterator.hasNext()) {
            Node inputNode = (Node) iterator.next();

            if (!TransformEnvelopedSignature
                    .isDescendantOrSelf(signatureElement, inputNode)) {
               resultSet.add(inputNode);
            }
         }

         XMLSignatureInput result = new XMLSignatureInput(resultSet,
                                       input.getCachedXPathAPI());

         return result;
      } catch (IOException ex) {
         throw new TransformationException("empty", ex);
      } catch (SAXException ex) {
         throw new TransformationException("empty", ex);
      } catch (ParserConfigurationException ex) {
         throw new TransformationException("empty", ex);
      } catch (CanonicalizationException ex) {
         throw new TransformationException("empty", ex);
      } catch (InvalidCanonicalizerException ex) {
         throw new TransformationException("empty", ex);
      }
   }

   /**
    * Returns true if the descendantOrSelf is on the descendant-or-self axis
    * of the context node.
    *
    * @param ctx
    * @param descendantOrSelf
    *
    */
   static boolean isDescendantOrSelf(Node ctx, Node descendantOrSelf) {

      if (ctx == descendantOrSelf) {
         return true;
      }

      Node parent = descendantOrSelf;

      while (true) {
         if (parent == null) {
            return false;
         }

         if (parent == ctx) {
            return true;
         }

         if (parent.getNodeType() == Node.ATTRIBUTE_NODE) {
            parent = ((Attr) parent).getOwnerElement();
         } else {
            parent = parent.getParentNode();
         }
      }
   }
}
