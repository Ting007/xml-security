#!/bin/sh
###!/usr/local/bin/bash

if [ $# -lt 1 ]; then
    echo "Must give version number to install"
    exit 1
fi

if [ ! -d ${experiment_root}/apache-xml-security/versions.alt/seeded/v${1} ]; then
    echo "Invalid version number"
    exit 1
fi

if [ -d ${experiment_root}/apache-xml-security/source/xml-security ]; then
    rm -rf ${experiment_root}/apache-xml-security/source/xml-security
fi

cp -rf ${experiment_root}/apache-xml-security/versions.alt/seeded/v${1}/xml-security ${experiment_root}/apache-xml-security/source
if [ ! -d ${experiment_root}/apache-xml-security/common ]; then
    echo copy common...	
    cp -rf ${experiment_root}/apache-xml-security/versions.alt/seeded/common ${experiment_root}/apache-xml-security
fi
echo build.sh...
${experiment_root}/apache-xml-security/scripts/build.sh ${1}
