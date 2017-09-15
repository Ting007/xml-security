#i/bin/sh
CLASSPATH=/nfs/spectre/u5/aristot/subjects/lib:./build/classes:../../common/libs

for i in ../../common/libs/*.jar
do
    CLASSPATH=$CLASSPATH:$i
done

export CLASSPATH

$JAVA_HOME/bin/java $* org.apache.xml.security.test.AllTests
