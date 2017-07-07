## Installing crontab job

### For MySQL

- Copy `mysqlfix.sh` to /root/mysqlfix.sh file
- Make it exequtable `chmod +x /root/mysqlfix.sh`
- Add crontab job. For 3 min period job will be next:
`*/3 * * * * /root/mysqlfix.sh >> /var/log/mysqlfix.log 2>&1`

`/var/log/mysqlfix.log` - log file

### For Nginx

- Copy `nginxfix.sh` to /root/nginxfix.sh file
- Make it exequtable `chmod +x /root/nginxfix.sh`
- Add crontab job. For 3 min period job will be next:
`*/3 * * * * /root/nginxfix.sh >> /var/log/nginxfix.log 2>&1`

`/var/log/nginxfix.log` - log file

### For Letsencrypt certificate

- Copy `certrenew.sh` to /root/certrenew.sh file
- Make it exequtable `chmod +x /root/certrenew.sh`
- Add crontab job. Job for running every day at 3:15AM will be next:
`15 3 * * * /root/certrenew.sh >> /var/log/le-renew.log`

`/var/log/nginxfix.log` - log file