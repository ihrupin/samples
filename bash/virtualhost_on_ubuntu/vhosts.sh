# This script needed for creation virtual hosts very fast
# For use this you must execute this script in terminal with sudo
# 
#   PARAMETERS
#
# $1 - IP
# $2 - ServerName
# $3 - ServerAdmin email
# $4 - Your username in System 
# $5 - htdocs directory path 
# 
# EXAMPLE
# IP = 127.0.0.1
# ServerName = example.com
# ServerAdmin email = webmaster@example.com
# Your username in System = user
# htdocs directory path = /home/user/htdocs/
#
# If we located in same directory that vhosts.sh script command in terminal will be:
# sudo ./vhosts.sh 127.0.0.1 example.com webmaster@example.com user /home/user/htdocs/
#
# Now open your browser and type the adress http://example.com .If allright you will see phpinfo();
# hope it's will be useful for you.

# Creation the website directories with test index.php file
mkdir $5/$2
cd $5/$2
mkdir "public_html"
cd $5/$2/public_html
echo "<?php echo '<h1><i>This is</i> $2</h1><br />'; echo phpinfo();?>" > $5/$2/public_html/index.php

chown -R $4:$4 $5$2/
chmod '664' $5/$2/public_html/index.php

# Creation the file with VirtualHost configuration in /etc/apache2/site-available/
echo "<VirtualHost *:80>
	ServerAdmin $3
	ServerName $2

	DocumentRoot $5$2/public_html/
	<Directory />
		Options FollowSymLinks
		AllowOverride All
	</Directory>
	<Directory $5$2/public_html/>
		Options Indexes FollowSymLinks MultiViews
		AllowOverride All
		Order allow,deny
		allow from all
	</Directory>

	ScriptAlias /cgi-bin/ /usr/lib/cgi-bin/
	<Directory "'/usr/lib/cgi-bin'">
		AllowOverride All
		Options +ExecCGI -MultiViews +SymLinksIfOwnerMatch
		Order allow,deny
		Allow from all
	</Directory>

	ErrorLog $5$2/error.log

	# Possible values include: debug, info, notice, warn, error, crit,
	# alert, emerg.
	LogLevel warn

	CustomLog $5$2/access.log combined

    Alias /doc/ "'/usr/share/doc/'"
    <Directory "'/usr/share/doc/'">
        Options Indexes MultiViews FollowSymLinks
        AllowOverride All
        Order deny,allow
        Deny from all
        Allow from 127.0.0.0/255.0.0.0 ::1/128
    </Directory>

</VirtualHost>" > /etc/apache2/sites-available/$2

#add host in /etc/hosts

echo $1 $2 >> /etc/hosts

#add link in /etc/apache2/site-enabled

a2ensite $2

# reload Apache2

/etc/init.d/apache2 reload
