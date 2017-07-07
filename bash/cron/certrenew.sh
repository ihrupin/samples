#!/bin/bash
PATH=/usr/sbin:/usr/bin:/sbin:/bin
sudo service nginx stop
/home/igor/letsencrypt/letsencrypt-auto renew
service nginx start
exit 0