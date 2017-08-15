#!/usr/local/bin/bash

current=`pwd`
CLASSPATH="$CLASSPATH":${current}/build/classes

for i in ${experiment_root}/apache-xml-security/common/libs/*.jar
do
    CLASSPATH=$CLASSPATH:$i
done

export CLASSPATH

$JAVA_HOME/bin/java $* org.apache.xml.security.test.AllTests
