<?php
	$host = "localhost";
	$usuario="root";
	$senha="159753";
	$banco= "bdlogin";
	
	$dbcon=new MySQLi("$host","$usuario","$senha","$banco");

	if($dbcon->connect_error){
		echo "conexão_erro";
	}
?>
