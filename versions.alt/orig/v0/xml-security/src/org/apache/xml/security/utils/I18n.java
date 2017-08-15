
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
package org.apache.xml.security.utils;



import java.util.Properties;
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.MessageFormat;


/**
 * The Internationalization (I18N) pack.
 *
 *
 *
 * @author Christian Geuer-Pollmann
 */
public class I18n {

   /** Field defaultLanguageCode */
   public static String defaultLanguageCode;    // will be set in static{} block

   /** Field defaultCountryCode */
   public static String defaultCountryCode;    // will be set in static{} block

   /** Field resourceBundle */
   protected static ResourceBundle resourceBundle;

   /** Field alreadyInitialized */
   protected static boolean alreadyInitialized = false;

   /** Field _languageCode */
   protected static String _languageCode = null;

   /** Field _countryCode */
   protected static String _countryCode = null;

   /**
    * Method translate
    *
    * translates a message ID into an internationalized String, see alse
    * <CODE>XMLSecurityException.getExceptionMEssage()</CODE>. The strings are
    * stored in the <CODE>ResourceBundle</CODE>, which is identified in
    * <CODE>exceptionMessagesResourceBundleBase</CODE>
    *
    * @param message
    * @param args is an <CODE>Object[]</CODE> array of strings which are inserted into the String which is retrieved from the <CODE>ResouceBundle</CODE>
    * @return
    */
   public static String translate(String message, Object[] args) {
      return getExceptionMessage(message, args);
   }

   /**
    * Method translate
    *
    * translates a message ID into an internationalized String, see alse
    * <CODE>XMLSecurityException.getExceptionMEssage()</CODE>
    *
    * @param message
    * @return
    */
   public static String translate(String message) {
      return getExceptionMessage(message);
   }

   /**
    * Method getExceptionMessage
    *
    * @param msgID
    * @return
    */
   public static String getExceptionMessage(String msgID) {

      try {
         String s = resourceBundle.getString(msgID);

         return s;
      } catch (Throwable t) {
         String s = "No message with ID \"" + msgID
                    + "\" found in resource bundle \""
                    + Constants.exceptionMessagesResourceBundleBase + "\"";

         return s;
      }
   }

   /**
    * Method getExceptionMessage
    *
    * @param msgID
    * @param originalException
    * @return
    */
   public static String getExceptionMessage(String msgID,
                                            Exception originalException) {

      try {
         Object exArgs[] = { originalException.getMessage() };
         String s = MessageFormat.format(resourceBundle.getString(msgID),
                                         exArgs);

         return s;
      } catch (Throwable t) {
         String s = "No message with ID \"" + msgID
                    + "\" found in resource bundle \""
                    + Constants.exceptionMessagesResourceBundleBase
                    + "\". Original Exception was a "
                    + originalException.getClass().getName() + " and message "
                    + originalException.getMessage();

         return s;
      }
   }

   /**
    * Method getExceptionMessage
    *
    * @param msgID
    * @param exArgs
    * @return
    */
   public static String getExceptionMessage(String msgID, Object exArgs[]) {

      try {
         String s = MessageFormat.format(resourceBundle.getString(msgID),
                                         exArgs);

         return s;
      } catch (Throwable t) {
         String s = "No message with ID \"" + msgID
                    + "\" found in resource bundle \""
                    + Constants.exceptionMessagesResourceBundleBase + "\"";

         return s;
      }
   }

   /**
    * Method init
    *
    * @param _defaultLanguageCode
    * @param _defaultCountryCode
    */
   public static void init(String _defaultLanguageCode,
                           String _defaultCountryCode) {

      I18n.defaultLanguageCode = _defaultLanguageCode;

      if (I18n.defaultLanguageCode == null) {
         I18n.defaultLanguageCode = Locale.getDefault().getLanguage();
      }

      I18n.defaultCountryCode = _defaultCountryCode;

      if (I18n.defaultCountryCode == null) {
         I18n.defaultCountryCode = Locale.getDefault().getCountry();
      }

      initLocale(I18n.defaultLanguageCode, I18n.defaultCountryCode);
   }

   /**
    * Method initLocale
    *
    * @param languageCode
    * @param countryCode
    */
   public static void initLocale(String languageCode, String countryCode) {

      if (alreadyInitialized && languageCode.equals(_languageCode)
              && countryCode.equals(_countryCode)) {
         return;
      }

      if ((languageCode != null) && (countryCode != null)
              && (languageCode.length() > 0) && (countryCode.length() > 0)) {
         _languageCode = languageCode;
         _countryCode = countryCode;
      } else {
         _countryCode = I18n.defaultCountryCode;
         _languageCode = I18n.defaultLanguageCode;
      }

      I18n.resourceBundle =
         ResourceBundle.getBundle(Constants.exceptionMessagesResourceBundleBase,
                                  new Locale(_languageCode, _countryCode));
   }
}
