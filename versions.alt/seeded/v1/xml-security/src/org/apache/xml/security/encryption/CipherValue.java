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
package org.apache.xml.security.encryption;



import org.w3c.dom.*;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.utils.EncryptionElementProxy;
import org.apache.xml.security.utils.EncryptionConstants;
import org.apache.xml.security.utils.Base64;


/**
 * This class maps to the <CODE>xenc:CipherValue</CODE> element.
 *
 * @author $Author: dohy $
 */
public class CipherValue extends EncryptionElementProxy {

   /**
    * Constructor CipherValue
    *
    * @param doc
    * @param ciphertext
    * @throws XMLSecurityException
    */
   public CipherValue(Document doc, byte ciphertext[])
           throws XMLSecurityException {

      super(doc);

      this.setCipherText(ciphertext);
   }

   /**
    * Constructor CipherValue
    *
    * @param element
    * @param BaseURI
    * @throws XMLSecurityException
    */
   public CipherValue(Element element, String BaseURI)
           throws XMLSecurityException {
      super(element, BaseURI);
   }

   /**
    * Method getCipherText
    *
    * @return
    * @throws XMLSecurityException
    */
   public byte[] getCipherText() throws XMLSecurityException {

      NodeList nl = this._constructionElement.getChildNodes();

      if ((nl.getLength() != 1)
              || (nl.item(0).getNodeType() != Node.TEXT_NODE)) {
         throw new XMLSecurityException(
            "encryption.structure.CipherTextMustContainText");
      }

      Text t = (Text) nl.item(0);

      return Base64.decode(t.getData());
   }

   /**
    * Method setCipherText
    *
    * @param ciphertext
    * @throws XMLSecurityException
    */
   public void setCipherText(byte ciphertext[]) throws XMLSecurityException {

      while (this._constructionElement.hasChildNodes()) {
         this._constructionElement
            .removeChild(this._constructionElement.getLastChild());
      }

      Text textNode = this._doc.createTextNode(Base64.encode(ciphertext));

      this._constructionElement.appendChild(textNode);
   }

   public String getBaseLocalName() {
      return EncryptionConstants._TAG_CIPHERVALUE;
   }
}
