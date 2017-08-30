#!/bin/sh
export JAVA_HOME=`/usr/libexec/java_home -v 1.6`
export experiment_root=/Users/Shared/Jenkins/Home/workspace
CLASSPATH=:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source/xml-security.
export CLASSPATH

CLASSPATH="$CLASSPATH":./build/classes:../../common/libs

for i in ../../common/libs/*.jar
do
    CLASSPATH=$CLASSPATH:$i
done

$JAVA_HOME/bin/java $* org.apache.xml.security.test.AllTests

