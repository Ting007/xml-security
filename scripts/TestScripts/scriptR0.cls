#/bin/sh
# The following is an R script type
unset CLASSPATH
CLASSPATH=.:${experiment_root}/apache-xml-security/source:${experiment_root}/apache-xml-security/source/xml-security:./build/classes:../../common/libs

for i in ../../common/libs/*.jar
do
    CLASSPATH=$CLASSPATH:$i
done

export CLASSPATH

echo ">>>>>>>>running test 1"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.c14n.helper.C14nHelperTest  > ${experiment_root}/apache-xml-security/outputs/t1 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 1

echo ">>>>>>>>running test 2"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.c14n.implementations.Canonicalizer20010315Test  > ${experiment_root}/apache-xml-security/outputs/t2 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 2

echo ">>>>>>>>running test 3"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.external.org.apache.xalan.XPathAPI.XalanBug1425Test  > ${experiment_root}/apache-xml-security/outputs/t3 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 3

echo ">>>>>>>>running test 4"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.external.org.apache.xalan.XPathAPI.AttributeAncestorOrSelf  > ${experiment_root}/apache-xml-security/outputs/t4 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 4

echo ">>>>>>>>running test 5"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.signature.XMLSignatureInputTest  > ${experiment_root}/apache-xml-security/outputs/t5 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 5

echo ">>>>>>>>running test 6"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.transforms.implementations.TransformBase64DecodeTest  > ${experiment_root}/apache-xml-security/outputs/t6 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 6

echo ">>>>>>>>running test 7"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.utils.resolver.ResourceResolverSpiTest  > ${experiment_root}/apache-xml-security/outputs/t7 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 7

echo ">>>>>>>>running test 8"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.utils.Base64Test  > ${experiment_root}/apache-xml-security/outputs/t8 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 8

echo ">>>>>>>>running test 9"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.algorithms.implementations.KeyWrapTest  > ${experiment_root}/apache-xml-security/outputs/t9 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 9

echo ">>>>>>>>running test 10"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.algorithms.implementations.BlockEncryptionTest  > ${experiment_root}/apache-xml-security/outputs/t10 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 10

echo ">>>>>>>>running test 11"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.interop.BaltimoreTest  > ${experiment_root}/apache-xml-security/outputs/t11 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 11

echo ">>>>>>>>running test 12"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.interop.IAIKTest  > ${experiment_root}/apache-xml-security/outputs/t12 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 12

echo ">>>>>>>>running test 13"
$JAVA_HOME/bin/java junit.textui.TestRunner org.apache.xml.security.test.interop.RSASecurityTest  > ${experiment_root}/apache-xml-security/outputs/t13 2>&1
${experiment_root}/apache-xml-security/testplans.alt/testscripts/RemoveTime.sh 13
