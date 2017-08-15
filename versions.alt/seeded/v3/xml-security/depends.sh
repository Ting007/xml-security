#!/usr/local/bin/bash

CLASSPATH=/nfs/spectre/u5/aristot/subjects/lib:./build/classes

for i in ../../common/libs/*.jar
do
    CLASSPATH=$CLASSPATH:$i
done

export CLASSPATH

java junit.textui.SelectiveTestRunner -names ${1} | sed -e 's/^[0-9]*:[  ]*//' -e 's/\.[^.]*$//' | sort -u | 
(
while read LINE
do
   DLIST="${DLIST} ${LINE}"
done
java -mx128m -cp ${CLASSPATH}:/nfs/spectre/u5/aristot/subjects/lib/BCEL listclass -nocontents -dependencies -recurse ${DLIST} -exclude java. javax. sun.
) |
sort -u

