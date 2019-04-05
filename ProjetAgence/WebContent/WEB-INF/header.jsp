<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link href="<%=request.getContextPath()%>/css/header.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<!-- Bandeau -->
<header class="container">
  <div class="row">
    <!-- Logo -->
    <div class="logo col-md-12 col-lg-3 ">
      <a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/images/logo.jpg" alt="logo biblio" class="img" id="logo"></a>
    </div>
    <!-- titre site -->
    <div class="titre col-md-12 offset-lg-1 col-lg-4 text-center" >
      <h1>Agence de voyage</span></h1>
    </div>
    

	<!-- option connexion, deconnexion, profil -->
    <div class="option col-md-12  offset-lg-1  col-lg-3 align-self-center text-center">
      <% if(session.getAttribute("user") != null)
      {
          %>
              <button type="button" class="btn btn-light" onclick="self.location.href='Logout'">D�connexion</button>
          <%
          

      }
      else
      {
         %>
              <button type="button" class="btn btn-light" onclick="self.location.href='<%=request.getContextPath()%>/Login';">Espace employ�</button>
         <%
      }
      %>
    </div>

  </div>
</header>

<!-- Menu de navigation -->
<nav class="container" >
<ul class="nav nav-tabs">
  <li class="nav-item">
    <a <% if(request.getAttribute("activePage") != null && request.getAttribute("activePage").equals("Accueil")) { %> <%="class='nav-link active'"%><% } else { %> class="nav-link" <% } %> href="<%=request.getContextPath()%>">Accueil</a>
  </li>
  <li class="nav-item">
    <a <% if(request.getAttribute("activePage") != null &&request.getAttribute("activePage").equals("ConsultationOffre")) { %> <%="class='nav-link active'"%><% } else { %> class="nav-link" <% } %> href="<%=request.getContextPath()%>/ConsultationOffre">Consulter les offres</a>
  </li>
 <% if(session.getAttribute("user") != null) { %>
  <li class="nav-item">
    <a <% if(request.getAttribute("activePage") != null &&request.getAttribute("activePage").equals("GestionOffre")) { %> <%="class='nav-link active'"%><% } else { %> class="nav-link" <% } %> href="<%=request.getContextPath()%>/GestionOffre">G�rer offres voyages</a>
  </li>
  <li class="nav-item">
    <a <% if(request.getAttribute("activePage") != null &&request.getAttribute("activePage").equals("GestionLieu")) { %> <%="class='nav-link active'"%><% } else { %> class="nav-link" <% } %> href="<%=request.getContextPath()%>#4">G�rer les lieux</a>
  </li>
  <% } %>
</ul>
</nav>
