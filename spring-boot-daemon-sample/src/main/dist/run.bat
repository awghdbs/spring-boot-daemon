@echo off

java -Dspring.application.admin.enabled=true -Dcom.sun.management.jmxremote.port=${distJmxPort} -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -jar -Dloader.main=net.nicoll.boot.daemon.StartSpringBootService ${distJar} ${distStartClass}
