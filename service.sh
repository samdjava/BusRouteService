#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
#echo $DIR
# Keep the pwd in mind!
# Example: RUN="java -jar $DIR/target/magic.jar"
# Assumption port is 8088
RUN="java -jar target/BusRouteService-1.0.jar"
NAME="BusRouteService"
PORT="8088"
DATA_FILE=$2

#PIDFILE="/tmp/$NAME.pid"
LOGFILE="/tmp/$NAME.log"

start() {
    processId=`netstat -nltp | grep $PORT | awk '{print $7}' | awk '{split($0,x,"/"); print x[1]'}`    
    if [ ! -z $processId ] ; then
	 if [ kill -0 $processId ]; then
            echo 'Service already running' >&2
            return 1
         fi
    fi
    echo $RUN $DATA_FILE
    local CMD="$RUN $DATA_FILE &> \"$LOGFILE\" & echo \$!"
    sh -c "$CMD"
}

stop() {
    echo "Shutting down"
    processId=`netstat -nltp | grep $PORT | awk '{print $7}' | awk '{split($0,x,"/"); print x[1]'}`    
    if [ -z $processId ] ; then
    echo 'Service not running' >&2
    else
        if ! kill -0 $processId ;then
             echo 'Service not running' >&2
             return 1
          else
             echo 'Service is running' >&2  
             kill -15 $processId
          fi
    fi
}


case $1 in
    start)
        start
        ;;
    stop)
        stop
        ;;
    block)
        start
        sleep infinity
        ;;
    *)
        echo "Usage: $0 {start|stop|block} DATA_FILE"
esac

