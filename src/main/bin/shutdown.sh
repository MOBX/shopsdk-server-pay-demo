#!/bin/sh

className=etrade-server-demo

if [ -z "$JAVA_HOME" ] ; then
	export JAVA_HOME=/usr/local/java
fi

pid=`$JAVA_HOME/bin/jps -l | grep $className | awk '{print $1}'`


if [ "$pid" != "" ]
then
        kill -9 $pid
        echo "kill pid "$pid
else

        echo "pid: not found"

fi