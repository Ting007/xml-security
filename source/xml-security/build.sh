#!/bin/sh

echo “reached build.sh”
#CLASSPATH=/nfs/spectre/u5/aristot/subjects/lib:.
#CLASSPATH=/Users/ting/Doc/ComputerS/Test_objects/apache-xml-security/source:/Users/ting/Doc/ComputerS/Test_objects/apache-xml-security/source/xml-security:.
#CLASSPATH=$CLASSPATH:/Users/Shared/Jenkins/Home/workspace/test_xml_security/apache-xml-security/source/xml-security:/Users/Shared/Jenkins/Home/workspace/test_xml_security/source:.
CLASSPATH=$CLASSPATH:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source:/Users/Shared/Jenkins/Home/workspace/apache-xml-security/source/xml-security:.
#export CLASSPATH
export CLASSPATH
echo $CLASSPATH
echo "reach ant command"
ant ${1}
