#!/bin/sh
# The following is an R script type
unset CLASSPATH
CLASSPATH=.:/nfs/spectre/u5/dohy/Prior/apache-xml-security/source:/nfs/spectre/u5/dohy/Prior/apache-xml-security/source/xml-security:.:/nfs/spectre/u5/aristot/subjects/lib:./build/classes:../../common/libs/bc-jce-jdk13-114.jar:../../common/libs/commons-logging-api.jar:../../common/libs/commons-logging.jar:../../common/libs/junit3.8.1.jar:../../common/libs/log4j-1.2.8.jar:../../common/libs/style-apachexml.jar:../../common/libs/stylebook-1.0-b3_xalan-2.jar:../../common/libs/xalan.jar:../../common/libs/xercesImpl.jar:../../common/libs/xml-apis.jar:../../common/libs/xmlParserAPIs.jar
export CLASSPATH

echo ">>>>>>>>running test 1"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.c14n.helper.C14nHelperTest  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t1 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 1

echo ">>>>>>>>running test 2"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.c14n.implementations.Canonicalizer20010315Test  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t2 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 2

echo ">>>>>>>>running test 3"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.c14n.implementations.Canonicalizer20010315ExclusiveTest  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t3 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 3

echo ">>>>>>>>running test 4"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.external.org.apache.xalan.XPathAPI.XalanBug1425Test  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t4 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 4

echo ">>>>>>>>running test 5"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.external.org.apache.xalan.XPathAPI.AttributeAncestorOrSelf  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t5 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 5

echo ">>>>>>>>running test 6"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.signature.XMLSignatureInputTest  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t6 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 6

echo ">>>>>>>>running test 7"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.transforms.implementations.TransformBase64DecodeTest  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t7 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 7

echo ">>>>>>>>running test 8"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.utils.resolver.ResourceResolverSpiTest  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t8 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 8

echo ">>>>>>>>running test 9"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.utils.Base64Test  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t9 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 9

echo ">>>>>>>>running test 10"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.interop.BaltimoreTest  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t10 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 10

echo ">>>>>>>>running test 11"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.interop.IAIKTest  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t11 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 11

echo ">>>>>>>>running test 12"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.interop.RSASecurityTest  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t12 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 12

echo ">>>>>>>>running test 13"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.c14n.implementations.ExclusiveC14NInterop  > /nfs/spectre/u5/dohy/Prior/apache-xml-security/outputs/t13 2>&1
/nfs/spectre/u5/dohy/Prior/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 13
