#! /bin/sh

if [ -z "$JAVA_HOME" ] ; then
	export JAVA_HOME=/usr/local/java
fi

SCRIPT="$0"
while [ -h "$SCRIPT" ] ; do
  ls=`ls -ld "$SCRIPT"`
  # Drop everything prior to ->
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    SCRIPT="$link"
  else
    SCRIPT=`dirname "$SCRIPT"`/"$link"
  fi
done

SERVER_HOME=`dirname "$SCRIPT"`
SERVER_HOME=`cd "$SERVER_HOME"; pwd`
export SERVER_HOME

java=$JAVA_HOME/bin/java

JAVA_OPTS="-Xms512m -Xmx512m -XX:PermSize=128m"

nohup $java -jar etrade-server-demo.jar >/dev/null 2>&1 &