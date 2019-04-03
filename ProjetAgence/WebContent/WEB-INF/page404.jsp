<% session.setAttribute("activePage", "Accueil"); %>
<!DOCTYPE>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
   <head>
      <title>Page d'erreur</title>
      <meta name="description" content="Page d'erreur 404">
      <jsp:include page="head.jsp"/>
  </head>
  <body>
      <jsp:include page="header.jsp"/>

      <section class="container">
        
          
          <div class="row">
            <div class="col-sm-12 col-md-7">
            <h1>Erreur 404</h1>
            <p class="text-justify">Tabarnak ! Nous avons cherché partout votre page, mais elle est introuvable. L'adresse est peut être erronée ou la page n'existe plus.</p>
          </div>
          <div class="col-sm-12 col-md-5">
            <img src="<%=request.getContextPath()%>/images/404.jpg" class="img-fluid" alt="Responsive image">
          </div>
        </div>
      </section>

      <jsp:include page="footer.jsp"/>
  </body>
</html>