#!/bin/bash
PATH=/usr/sbin:/usr/bin:/sbin:/bin
if [[ ! "$(/usr/sbin/service mysql status)" =~ "active (running)" ]]
then
    echo $(date -u) "MySQL service on the server has been stopped. It has now been restarted."
    sudo service mysql start
fi