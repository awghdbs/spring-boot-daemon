@echo off

java -jar -Dloader.main=net.nicoll.boot.daemon.StopSpringBootService ${distJar} ${distJmxPort}
