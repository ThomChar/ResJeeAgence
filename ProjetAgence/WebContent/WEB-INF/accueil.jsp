<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("activePage", "Accueil"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'accueil</title>
<jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

       <section class="container">


           <div class="row">
            <div class="col-sm-12 col-md-7">
            <h1>Bienvenu sur notre site !</h1>
            <p class="text-justify">Prêt pour un nouveau voyage ?</p>
          </div>
          <div class="col-sm-12 col-md-5">
            <!--  <img src="<%=request.getContextPath()%>/images/404.jpg" class="img-fluid" alt="Responsive image">-->
          </div>
        </div>
      </section>

       <jsp:include page="footer.jsp"/>
</body>
</html>