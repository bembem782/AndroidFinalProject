<?php
	//receive the message from remote
	//put message to file
	
	if(!empty($_GET['message']) && !empty($_GET['name'])){
		
		$name=$_GET['name'];
		$message=$_GET['message'];
		
		$file=fopen("chat.txt","a");
		fwrite($file,$name);
		fwrite($file,": ");
		fwrite($file,$message);
		fwrite($file,"\n");
		fclose($file);
		
		echo "Message Sent";
	}
	
	

?>