<html>
<head>
	<meta charset = "utf-8">
	<title>Canadian Food Guide Nutritional Recommendation</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
  	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
</head>
<body>
<h2>Canadian Food Guide Nutritional Recommendation</h2>
<form action="recommendation" method="post">

	
	<input type="radio" name="gender" value="Male" checked><label id="genM">Male</label>
  	<input type="radio" name="gender" value="Female"> <label id="genF">Female</label><br>
	Age:<input type="text" name ="userAge"><br>
	<input id="en" type="radio" name="language" value="en" checked> English
  	<input id="fr" type="radio" name="language" value="fr"> Francais<br><br>
	<input type="submit"><br>


<script type="text/javascript">
	$("document").ready(function(){
		
		$('#fr, #en').click(function(){
			if ($('#fr').is(':checked')){
				$('[id$=genM]').text('Homme');
				$('[id$=genF]').text('Femme');
			} else if ($('#en').is(':checked')){
				$('[id$=genM]').text('Male');
				$('[id$=genF]').text('Female');
			};
		});
	
	});

</script>
</body>
</html>
