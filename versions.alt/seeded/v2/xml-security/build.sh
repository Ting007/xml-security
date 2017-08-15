#!/bin/sh

CLASSPATH=/nfs/spectre/u5/aristot/subjects/lib:.
export CLASSPATH

ant ${1}
