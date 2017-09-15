package org.apache.xml.security.utils;

/**
 *
 * @author $Author: dohy $
 */
public class Version {

    /** Version string. */
    public static String fVersion = "Apache-XML-Security-J 1.0.4";

   private Version() {
     // we don't allow instantiation
   }

    public static final String getVersion() {
       return Version.fVersion;
    }

    /**
     * Prints out the version number to System.out. This is needed
     * for the build system.
     */
    public static void main(String argv[]) {
        System.out.println(org.apache.xml.security.utils.Version.getVersion());
    }
}