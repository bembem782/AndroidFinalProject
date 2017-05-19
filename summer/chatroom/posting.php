
<html>
	<head>
		<meta http-equiv='refresh' content='3' />
	</head>
	<body>
<?php
//read the messages

	$filename="chat.txt";
		$file=file($filename);
	
		echo "<table style='border-collapse:collapse;border:0px solid #808080'; padding:5px;width:100%'>";
			foreach($file as $record){
				echo "<tr>";
					echo "<td>".$record."</td>";
				echo "</tr>";
			}
		echo "</table>";
	
?>
	</body>
</html>