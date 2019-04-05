<!DOCTYPE>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("activePage", "Accueil"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        </div>
      </section>

       <jsp:include page="footer.jsp"/>
</body>
</html>