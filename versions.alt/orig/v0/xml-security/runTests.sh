#!/bin/sh

export JAVA_HOME=`/usr/libexec/java_home -v 1.6`
export experiment_root=/Users/Shared/Jenkins/Home/workspace
CLASSPATH=:${experiment_root}/apache-xml-security/source:${experiment_root}/apache-xml-security/source/xml-security.

CLASSPATH="$CLASSPATH":./build/classes/:../../common/libs

for i in ../../common/libs/*.jar
do
    CLASSPATH=$CLASSPATH:$i
done

export CLASSPATH

$JAVA_HOME/bin/java $* org.apache.xml.security.test.AllTests
