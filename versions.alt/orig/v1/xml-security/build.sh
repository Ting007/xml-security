#!/bin/sh

#CLASSPATH=/nfs/spectre/u5/aristot/subjects/lib:.
CLASSPATH=/Users/ting/Doc/ComputerS/Test_objects/apache-xml-security/source:/Users/ting/Doc/ComputerS/Test_objects/apache-xml-security/source/xml-security:.
export CLASSPATH

ant ${1}
