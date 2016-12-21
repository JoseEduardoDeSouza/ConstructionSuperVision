<?php

	include_once 'conexao.php';

	$email=$_GET['email'];
	$senha=$_GET['senha'];
	$nome=$_GET['nome'];

	$sql1=$dbcon->query("SELECT * FROM tblogin WHERE email='$email'");

	if(mysqli_num_rows($sql1)>0){
		echo "email_erro";
	} else {
		$sql2 = $dbcon->query("INSERT INTO tblogin(nome,email,senha) VALUES('$nome','$email','$senha')");
		if ($sql2){
			"Registro_ok";
		}else{
			"resgistro_erro";
		}	
	}


?>