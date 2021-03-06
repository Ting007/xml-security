
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
package org.apache.xml.security.c14n.helper;



import org.w3c.dom.*;
import org.apache.log4j.*;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.utils.Constants;


/**
 * Compares two attributes based on the C14n specification.
 *
 * <UL>
 * <LI>Namespace nodes have a lesser document order position than attribute nodes.
 * <LI> An element's namespace nodes are sorted lexicographically by
 *   local name (the default namespace node, if one exists, has no
 *   local name and is therefore lexicographically least).
 * <LI> An element's attribute nodes are sorted lexicographically with
 *   namespace URI as the primary key and local name as the secondary
 *   key (an empty namespace URI is lexicographically least).
 * </UL>
 *
 * @todo Should we implement java.util.Comparator and import java.util.Arrays to use Arrays.sort(intarray);
 * @author Christian Geuer-Pollmann
 */
public class AttrCompare implements java.util.Comparator, sun.misc.Compare {

   /** {@link org.apache.log4j} logging facility */
   static org.apache.log4j.Category cat =
      org.apache.log4j.Category.getInstance(AttrCompare.class.getName());

   /**
    * Same as {@link #compare}.
    * @param obj0
    * @param obj1
    * @return
    */
   public int doCompare(Object obj0, Object obj1) {
      return this.compare(obj0, obj1);
   }

   /**
    * Compares two attributes based on the C14n specification.
    *
    * <UL>
    * <LI>Namespace nodes have a lesser document order position than attribute nodes.
    * <LI> An element's namespace nodes are sorted lexicographically by
    *   local name (the default namespace node, if one exists, has no
    *   local name and is therefore lexicographically least).
    * <LI> An element's attribute nodes are sorted lexicographically with
    *   namespace URI as the primary key and local name as the secondary
    *   key (an empty namespace URI is lexicographically least).
    * </UL>
    *
    * @param obj0 casted Attr
    * @param obj1 casted Attr
    * @return returns a negative integer, zero, or a positive integer as obj0 is less than, equal to, or greater than obj1
    *
    */
   public int compare(Object obj0, Object obj1) {

      Attr attr0 = (Attr) obj0;
      Attr attr1 = (Attr) obj1;

      attr0.normalize();
      attr1.normalize();

      String name0 = attr0.getName();
      String name1 = attr1.getName();
      String prefix0 = attr0.getPrefix();
      String prefix1 = attr1.getPrefix();
      String localName0 = attr0.getLocalName();
      String localName1 = attr1.getLocalName();
      String namespaceURI0 = attr0.getNamespaceURI();
      String namespaceURI1 = attr1.getNamespaceURI();
      boolean definesNS0 = false;
      boolean definesNS1 = false;
      boolean definesDefaultNS0 = false;
      boolean definesDefaultNS1 = false;

      /*
       * For defualt NS definitions, the XML parser gives us
       * - prefix=null
       * - localname=null
       * - name="xmlns"
       *
       * this is changed to
       * - prefix="xmlns"
       * - localname=null
       * - name="xmlns"
       *
       */
      if (name0.equals("xmlns")) {

         // must be "" to allow localName0.compareTo(localName1) to work
         localName0 = "";
         prefix0 = "xmlns";
         definesNS0 = true;
         definesDefaultNS0 = true;

         if (namespaceURI0 == null) {
            namespaceURI0 = "http://www.w3.org/2000/xmlns/";
         }
      }

      if (name1.equals("xmlns")) {

         // must be "" to allow localName0.compareTo(localName1) to work
         localName1 = "";
         prefix1 = "xmlns";
         definesNS1 = true;
         definesDefaultNS1 = true;

         if (namespaceURI1 == null) {
            namespaceURI1 = "http://www.w3.org/2000/xmlns/";
         }
      }

      if (name0.startsWith("xmlns:")) {
         prefix0 = "xmlns";
         localName0 = name0.substring(name0.indexOf("xmlns:")
                                      + "xmlns:".length());
         definesNS0 = true;

         if (namespaceURI0 == null) {
            namespaceURI0 = "http://www.w3.org/2000/xmlns/";
         }
      }

      if (name1.startsWith("xmlns:")) {
         prefix1 = "xmlns";
         localName1 = name1.substring(name1.indexOf("xmlns:")
                                      + "xmlns:".length());
         definesNS1 = true;

         if (namespaceURI1 == null) {
            namespaceURI1 = "http://www.w3.org/2000/xmlns/";
         }
      }

      if (namespaceURI0 == null) {
         namespaceURI0 = "";
         localName0 = name0;
      }

      if (namespaceURI1 == null) {
         namespaceURI1 = "";
         localName1 = name1;
      }

      if (cat.isDebugEnabled()) {    // debug info
         Element ownerElement = attr0.getOwnerElement();
         String elementName = "";

         // During the test cases, there can be Attributes without containing
         // element.
         if (ownerElement != null) {
            elementName = "<"
                          + ((Element) attr0.getOwnerElement()).getNodeName()
                          + "> ";
         }

         cat.debug(elementName + "attr0(" + attr0 + "): prefix(" + prefix0
                   + ") localName(" + localName0 + ") name(" + name0
                   + ") nsURI(" + namespaceURI0 + ") definesNS(" + definesNS0
                   + ") definesDefaultNS(" + definesDefaultNS0 + ")");
         cat.debug(elementName + "attr1(" + attr1 + "): prefix(" + prefix1
                   + ") localName(" + localName1 + ") name(" + name1
                   + ") nsURI(" + namespaceURI1 + ") definesNS(" + definesNS1
                   + ") definesDefaultNS(" + definesDefaultNS1 + ")");
      }    // debug info

      if (attr0 == null) {
         cat.fatal("attr0 == null");

         return (0);
      }

      if (attr1 == null) {
         cat.fatal("attr1 == null");

         return (0);
      }

      if ((localName0 == null) && (name0 == null)) {
         cat.fatal("localName0 == null && name0 == null");

         return (0);
      }

      if ((localName1 == null) && (name1 == null)) {
         cat.fatal("localName1 == null && name1 == null");

         return (0);
      }

      /*
       * namespace definitions can be detected in Xerces v1.3.0 by
       *
       * boolean = namespaceURI.equals("http://www.w3.org/2000/xmlns/")
       *
       * but I don't know how stable this is. And the difference between
       * defaultNS definitions isn't checked by this either.
       *
       * In Xerces v1.3.1, it doesn't work.
       */
      if (definesNS0 && definesNS1) {

         /*
          * - An element's namespace nodes are sorted lexicographically by
          *   local name (the default namespace node, if one exists, has no
          *   local name and is therefore lexicographically least).
          *
          */
         int result = signum(localName0.compareTo(localName1));

         cat.debug("A1 returns " + result);

         return result;
      } else if (!definesNS0 &&!definesNS1) {

         /*
          * - An element's attribute nodes are sorted lexicographically with
          *   namespace URI as the primary key and local name as the secondary
          *   key (an empty namespace URI is lexicographically least).
          *
          */
         int NScomparisonResult =
            signum(namespaceURI0.compareTo(namespaceURI1));

         if (NScomparisonResult != 0) {
            cat.debug("A2 returns " + NScomparisonResult);

            return NScomparisonResult;
         }

         int result = signum(localName0.compareTo(localName1));

         cat.debug("A3 returns " + result);

         return result;
      } else if (definesNS0 &&!definesNS1) {

         /*
          * - Namespace nodes have a lesser document order position
          *   than attribute nodes.
          *
          */
         cat.debug("A4 returns " + -1);

         return -1;
      } else if (!definesNS0 && definesNS1) {

         /*
          * - Namespace nodes have a lesser document order position
          *   than attribute nodes.
          *
          */
         cat.debug("A5 returns " + 1);

         return 1;
      }

      cat.fatal("Oh oh, very bad...");

      return 0;
   }

   /**
    * Mathematical signum() function
    *
    * @param input
    * @return -1 for negative input, 1 for positive input, 0 otherwise
    */
   private static int signum(int input) {

      if (input == 0) {
         return 0;
      } else if (input < 0) {
         return -1;
      } else {
         return 1;
      }
   }

   static {
      org.apache.xml.security.Init.init();
   }
}
