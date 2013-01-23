# $1 - Path to dir which contains next .deb files: cnijfilter-common_3.00-1_i386.deb and cnijfilter-common_3.00-1_i386.deb

# Prepare the cnijfilter-common_3.00-1_i386.deb package. Extract .deb file in to two dirs DEBIAN and common.
dpkg-deb -x $1/cnijfilter-common_3.00-1_i386.deb $1/common
dpkg-deb --control $1/cnijfilter-common_3.00-1_i386.deb $1/DEBIAN

# Replace libcupsys2 by libcups2 in DEBIAN/control file. Save it.
sed -i 's/libcupsys2/libcups2/g' $1/DEBIAN/control

# Move whole DEBIAN dir in to common dir
mv $1/DEBIAN $1/common

# Create the .deb from common dir.
dpkg -b $1/common $1/new_cnijfilter-common_3.00-1_i386.deb

# Delete the common dir
rm -r $1/common
#################################################################################
# Prepare the cnijfilter-ip3600series_3.00-1_i386.deb package. Extract .deb file in to two dirs DEBIAN and common.
dpkg-deb -x $1/cnijfilter-ip3600series_3.00-1_i386.deb $1/common
dpkg-deb --control $1/cnijfilter-ip3600series_3.00-1_i386.deb $1/DEBIAN

# Replace libcupsys2 by libcups2 in DEBIAN/control file. Save it.
sed -i 's/libcupsys2/libcups2/g' $1/DEBIAN/control

# Move whole DEBIAN dir in to common dir
mv $1/DEBIAN $1/common

# Create the .deb from common dir.
dpkg -b $1/common $1/new_cnijfilter-ip3600series_3.00-1_i386.deb

# Delete the common dir
rm -r $1/common

#################################################################################
# Next you would install that two new packages by double click on .deb files. First will be installed the new_cnijfilter-common_3.00-1_i386.deb.

# Instalation the packages in terminal:
dpkg -i new_cnijfilter-common_3.00-1_i386.deb
dpkg -i new_cnijfilter-ip3600series_3.00-1_i386.deb

#################################################################################

#If you start print job you have got some issue. If you diagnose this you have got hext messages. 

# Status Messages
# There are status massages associated with this queue.
# The printer's state message is: 'Filter "/usr/lib/cups/filter/pstocanonij" for printer "iP3600-series" not owned by root'.

#igor@igor-laptop:/usr/lib/cups/filter$ ls -l
#-rwxr-xr-x 1 igor igor  21044 2008-11-05 08:06 pstocanonij

# To fix this you must change /usr/lib/cups/filter/pstocanonij file owner to root.
chown root:root /usr/lib/cups/filter/pstocanonij
