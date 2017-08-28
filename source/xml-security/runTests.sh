#!/bin/sh
CLASSPATH="$CLASSPATH":./build/classes:../../common/libs
echo "CLASSPATH is " $CLASSPATH


for i in ../../common/libs/*.jar
do
    CLASSPATH=$CLASSPATH:$i
done

export CLASSPATH

#$JAVA_HOME/bin/java $* org.apache.xml.security.test.AllTests
echo "JAVA_HOME is " $JAVA_HOME

$JAVA_HOME/bin/java -version
