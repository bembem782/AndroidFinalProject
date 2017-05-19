<?php

	if(isset($_GET["username"]) && isset($_GET["password"])){
	
	$username = $_GET["username"];
	$password = $_GET["password"];
	//$message = "Invalid input";
	//default username=bae; password=baby
	
	//if(username=="bae"&&password=="baby")
		//$message="Login accepted";
		//using ternary operator
	$message =($username=="bae"&&$password=="baby")?"Login accepted":"Invalid input";
	echo $message;
	
	}
	else echo "Fill all fields"

?>