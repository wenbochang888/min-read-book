#!/bin/bash
pid=$(ps -ef | grep com.tianya.HouseApplication | grep 'java' | grep -v grep | awk '{print $2'})
if [ -z "$pid" ]; then
echo 'there are not HouseApplication process. starting will be continue.'
fi
if [ -n "$pid" ]; then
echo 'java process id is '$pid
if ps -p $pid > /dev/null
then
echo $pid' will be kill'
kill -9 $pid
fi
fi
echo 'start HouseApplication wait.'
nohup java -jar /root/work/workspace/gitee/house-new/house/target/house-0.0.1-SNAPSHOT.jar com.tianya.HouseApplication > /dev/null 2>&1 &
echo 'finish starting HouseApplication'

