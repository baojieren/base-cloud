#! /bin/bash
jarName=`cd $(dirname $2); pwd`/$(basename $2)
start(){
 now=`date "+%Y%m%d%H%M%S"`
 exec java -Xms100m -Xmx100m -jar $jarName --spring.profiles.active=test &
 #nohup java -Xms128m -Xmx128m -jar $SpringBoot --spring.profiles.active=test &
 #java -Xms128m -Xmx2048m -jar test2.jar 5 > log.log &
 #tail -f result.log
}
#停止方法
stop(){
 ps -ef|grep java| grep $jarName | awk '{print $2}'| while read pid
 do
    kill -9 $pid
 done
}

case "$1" in
start)
start
;;
stop)
stop
;;
restart)
stop
start
;;
*)
printf 'Usage: %s {start|stop|restart}\n' "$prog"
exit 1
;;
esac
