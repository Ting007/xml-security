#!/bin/sh

CLASSPATH="$CLASSPATH":.
export CLASSPATH
echo ${experiment_root}
cd ${experiment_root}/apache-xml-security/source/xml-security

if [ ${1} -ge 3 ]; then
    build.sh compile.tests
    exit 0
fi

echo "before build.sh"
pwd
./build.sh compile
