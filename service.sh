#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
#echo $DIR
# Keep the pwd in mind!
# Example: RUN="java -jar $DIR/target/magic.jar"
RUN="java -jar target/BusRouteService-1.0.jar"
NAME="BusRouteService"

DATA_FILE=$2

PIDFILE="/tmp/$NAME.pid"
LOGFILE="/tmp/$NAME.log"

start() {
    if [ -f $PIDFILE ]; then
	    if kill -0 $(cat $PIDFILE); then
            echo 'Service already running' >&2
            return 1
        else
            rm -f $PIDFILE
        fi
    fi
    echo $RUN $DATA_FILE
    local CMD="$RUN $DATA_FILE &> \"$LOGFILE\" & echo \$!"
    sh -c "$CMD" > $PIDFILE
}

stop() {
    echo "Shutting down"
    if [ ! -f $PIDFILE ] ;then
        echo 'Service not running' >&2
        return 1
    else
          pid = $(cat $PIDFILE) - 1
          echo $pid
          if [ ! kill -0 $(cat $PIDFILE) ] ;then
             echo 'Service not running' >&2
             return 1
          else
             kill -15 $pid && rm -f $PIDFILE
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

