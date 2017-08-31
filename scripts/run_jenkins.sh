#!/bin/sh
export JAVA_HOME=`/usr/libexec/java_home -v 1.6`
export experiment_root=/Users/Shared/Jenkins/Home/workspace
CLASSPATH=:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source/xml-security.
export CLASSPATH

./install.sh orig 0

#running the test suits 
cd ../source/xml-security
./runTests.sh
./scriptR0.cls
