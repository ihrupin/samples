#!/bin/bash
PATH=/usr/sbin:/usr/bin:/sbin:/bin
if [[ ! "$(/etc/init.d/nginx status)" =~ "active (running)" ]]
then
    echo $(date -u) "NGINX server has been stopped. It has now been restarted."
    service nginx start
fi