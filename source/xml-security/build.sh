#!/bin/sh

echo “reached build.sh”
#CLASSPATH=/nfs/spectre/u5/aristot/subjects/lib:.
#CLASSPATH=/Users/ting/Doc/ComputerS/Test_objects/apache-xml-security/source:/Users/ting/Doc/ComputerS/Test_objects/apache-xml-security/source/xml-security:.
CLASSPATH=$CLASSPATH:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source/xml-security:.
CLASSPATH=$CLASSPATH:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source/xml-security/ant
#export CLASSPATH
echo "right before ant command"
ant ${1}
