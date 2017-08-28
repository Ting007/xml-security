#!/bin/sh
CLASSPATH="$CLASSPATH":./build/classes:../../common/libs

for i in ../../common/libs/*.jar
do
    CLASSPATH=$CLASSPATH:$i
done

$JAVA_HOME/bin/java $* org.apache.xml.security.test.AllTests

