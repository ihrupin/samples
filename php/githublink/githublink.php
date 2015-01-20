<?php
/*
Plugin Name: GithubLink
Plugin URI: http://www.hrupin.com/2014/12/wordpress-plugin-for-showing-link-to-github
Description: This plugin help show source link
Version: 1.0 BETA
Author: Igor Khrupin
Author URI: http://www.hrupin.com
*/

/*
DEMO List Posts (Wordpress Plugin)
Copyright (C) 2014 Igor Khrupin
Contact me at http://www.hrupin.com

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

add_shortcode("githublink", "githublink_function");

function sourcelink_function($atts) {
	$image_link = plugin_dir_url( __FILE__ ).'img/gitHub-download-button.png';
	$alt = 'Download it from github';
	$width = '230';
	$height = '70';
	$url = $atts['url'];

	return '<a href="'.$url.'" target="_blank"><img src="'.$image_link.'" alt="'.$alt.'" width="'.$width.'" height="'.$height.'" class="alignleft size-full wp-image-1595" /></a>';
}
?>