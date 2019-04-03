<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("activePage", "login"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

      <section class="container">
	      <h1>Login</h1>
	      <p>Cet espace est reservé aux employés de l'agence.</p>
	     
	     <jsp:include page="messageErreur.jsp" />
	      
	      <form action="Login" method="POST">
	          <!-- email -->
	          <div class="form-group">
	            <label for="pseudo">Pseudo</label>
	            <input type="text" class="form-control" name="pseudo" id="pseudo" aria-describedby="emailHelp" placeholder="Enter email" <% if(request.getAttribute("pseudo") != null) { out.println("value='"+request.getAttribute("pseudo")+"'"); }%>>
	          </div>
	          
	          <!-- password -->
	          <div class="form-group">
	              <label for="password">Mot de passe</label>
	              <input type="password" class="form-control" name="password" id="password" placeholder="Password" <% if(request.getAttribute("password") != null) { out.println("value='"+request.getAttribute("password")+"'"); }%>>
	          </div>
	          
	          <p class="text-right"><button type="submit" class="btn btn-primary " >Se connecter</button></p>
	      </form>
	
	   </section>

      <jsp:include page="footer.jsp"/>
</body>
</html>