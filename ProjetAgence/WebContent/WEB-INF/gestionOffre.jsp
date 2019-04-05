<!DOCTYPE>
<%@ page import="java.util.*,java.text.*, model.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("activePage", "GestionOffre"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion d'offres de voyages</title>
<jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

       <section class="container">
         <div class="row">
          <div class="col-sm-12 col-md-12">
          <h1>Gestion d'offres de voyages</h1>
          
          <h2>Ajouter une offre</h2>
			<jsp:include page="messageErreur.jsp" />
            <jsp:include page="messageValidation.jsp" />
            
	         <form action="GestionOffre" method="POST">
	         <label for="lieu">Lieu</label>
	         	<select name="lieu" class="form-control">
	          	<%  List<Lieu> listeLieux = (List<Lieu>) request.getAttribute("listeLieux");
	          		if(listeLieux != null) {
	          		for(Lieu lieu : listeLieux)
	          		{
			          	%>
						  <option value='<%= lieu.getIdLieu()%>'><%= lieu.getNomLieu() %></option>
				<% } }%>
				</select>
	          
		          <!-- description -->
		          <label for="lieu">Description</label>
		          <textarea name="description" class="form-control" id="validationTextarea" ><% if(request.getAttribute("description") != null) { out.println(request.getAttribute("description")); }%></textarea>
		          
		          <!-- dateDebut -->
		          <div class="form-group">
		            <label for="dateDebut">Date Debut</label>
		            <input type="text" class="form-control" name="dateDebut" id="dateDebut" placeholder="10/06/2019" <% if(request.getAttribute("dateDebut") != null) { out.println("value='"+request.getAttribute("dateDebut")+"'"); }%>>
		          </div>
		         
		          <!-- dateFin -->
		          <div class="form-group">
		              <label for="dateFin">Date Fin</label>
		              <input type="text" class="form-control" name="dateFin" id="dateFin" placeholder="14/06/2019" <% if(request.getAttribute("dateFin") != null) { out.println("value='"+request.getAttribute("dateFin")+"'"); }%>>
		          </div>
		          
		           <!-- nb place -->
		          <div class="form-group">
		              <label for="dateFin">Nombre de places</label>
		              <input type="number" class="form-control" name="nbPlace" min="10" id="nbPlace" placeholder="50" <% if(request.getAttribute("nbPlace") != null) { out.println("value='"+request.getAttribute("nbPlace")+"'"); }%>>
		          </div>
		          
		          <% 
		          List<Categorie>  listeCategorie = (List<Categorie>) request.getAttribute("listeCategorie");
		            for(Categorie categorie: listeCategorie) {
		          %>
		          <div class="form-group row">
				    <label for="inputEmail3" class="col-sm-2 col-form-label">Prix <%= categorie.getNomCategorie() %> (€)</label>
				    <div class="col-sm-10">
				      <input type="number" class="form-control" min="0" name="<%= categorie.getNomCategorie() %>" id="inputEmail3" placeholder="2" <% if(request.getAttribute(categorie.getNomCategorie()) != null) { out.println("value='"+request.getAttribute(categorie.getNomCategorie())+"'"); }%>>
				    </div>
				  </div>
				  <%
		            }
				  %> 
		          
		          <p class="text-right"><button type="submit" class="btn btn-primary " >Ajouter</button></p>
		      </form>
          
        </div>
        </div>
      </section>

       <jsp:include page="footer.jsp"/>
</body>
</html>