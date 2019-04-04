<%@ page import="java.util.*,java.text.*,model.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<title>Commande</title>
</head>
<body>
	<h2>Nouvelle Commande</h2>
	<form action="servlet?op=commande" method="POST" name="donnees">
		Code: <input name="code" type="text"> <br /> Descripritf:
		</td>
		<td><input name="descriptif" type="text"><br /> <input
			type="submit" value="OK"><br />
	</form>
</body>
</html>