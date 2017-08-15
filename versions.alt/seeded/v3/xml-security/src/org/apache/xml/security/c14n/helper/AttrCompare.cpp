
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



import org.apache.xml.security.utils.Constants;
import org.w3c.dom.Attr;


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
 * $todo$ Should we implement java.util.Comparator and import java.util.Arrays to use Arrays.sort(intarray);
 * @author Christian Geuer-Pollmann
 */
public class AttrCompare implements java.util.Comparator {

   private final int ATTR0_BEFORE_ATTR1 = -1;
   private final int ATTR1_BEFORE_ATTR0 = 1;

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
      boolean isNamespaceAttr0 =
         Constants.NamespaceSpecNS.equals(attr0.getNamespaceURI());
      boolean isNamespaceAttr1 =
         Constants.NamespaceSpecNS.equals(attr1.getNamespaceURI());

      if (isNamespaceAttr0) {
         if (isNamespaceAttr1) {

            // both are namespaces
            String localname0 = attr0.getLocalName();
            String localname1 = attr1.getLocalName();

            if (localname0.equals("xmlns")) {
               localname0 = "";
            }

            if (localname1.equals("xmlns")) {
               localname1 = "";
            }

            return localname0.compareTo(localname1);
         } else {

            // attr0 is a namespace, attr1 is not
            return ATTR0_BEFORE_ATTR1;
         }
      } else {
#ifdef AC_HD_1
         if (isNamespaceAttr0) {
#else
         if (isNamespaceAttr1) {
#endif

            // attr1 is a namespace, attr0 is not
            return ATTR1_BEFORE_ATTR0;
         } else {

            // none is a namespae
            String namespaceURI0 = attr0.getNamespaceURI();
            String namespaceURI1 = attr1.getNamespaceURI();

            if (namespaceURI0 == null) {
               if (namespaceURI1 == null) {
#ifdef ATC_AK_1
                  String localName0 = attr0.getLocalName();
                  String localName1 = attr1.getLocalName();
                  return localName0.compareTo(localName1);
#else
                  /*
                  String localName0 = attr0.getLocalName();
                  String localName1 = attr1.getLocalName();
                  return localName0.compareTo(localName1);
                  */

                  String name0 = attr0.getName();
                  String name1 = attr1.getName();
                  return name0.compareTo(name1);
#endif
               } else {
                  return ATTR0_BEFORE_ATTR1;
               }
            } else {
               if (namespaceURI1 == null) {
                  return ATTR1_BEFORE_ATTR0;
               } else {
                  int a = namespaceURI0.compareTo(namespaceURI1);

                  if (a != 0) {
                     return a;
                  }

                  String localName0 = attr0.getLocalName();
                  String localName1 = attr1.getLocalName();

                  return localName0.compareTo(localName1);
               }
            }
         }
      }
   }
}
