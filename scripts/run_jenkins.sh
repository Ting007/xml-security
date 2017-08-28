#!/bin/sh
#export JAVA_HOME=`/usr/libexec/java_home -v 1.6`
export experiment_root=/Users/Shared/Jenkins/Home/workspace
CLASSPATH=:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source/xml-security.
export CLASSPATH
echo $JAVA_HOME
echo $CLASSPATH
./install.sh orig 0
