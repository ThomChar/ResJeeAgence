<%@ page import="java.util.*,java.text.*, model.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("activePage", "ConsultationOffre"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/consultationOffre.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/reservation.css" rel="stylesheet">
<title>Page d'accueil</title>
<jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

      <section class="container">
        
          
          <div class="row">
            <div class="col-sm-12 col-md-12">
            <h1>Reservation</h1>
            
            <jsp:include page="messageErreur.jsp" />
            <jsp:include page="messageValidation.jsp" />
            
            <% 
            
            OffreVoyage offreVoyage = (OffreVoyage) request.getAttribute("offreVoyage");
            
            if(offreVoyage != null)
            {
	            %>
	            
	            <!-- Offre de Voyage -->
	            	<div class="formTitle col-sm-12 col-md-12 col-lg-12 OffreVoyage">
	                     <h2><%= offreVoyage.getLieu().getNomLieu() %>,<span class="datesVoyage"> du <%= offreVoyage.getDateDebut() %> au <%= offreVoyage.getDateFin() %></span></h2>
	                     <div class="infos">
	                     
		                     <span><%= offreVoyage.getDescription() %></span><br/>
		                     
		                     <div class="row itemsOffre">
			                     <div class="col-sm-12 col-md-6">
			                     	<h3>À faire :</h3>
			                     	<ul>
				                     	<%
				                     	if(offreVoyage.getLieu().getListeAVisite().size() > 0)
				                     	{
	
					                     	for(AVisite aVisite: offreVoyage.getLieu().getListeAVisite()) {
					                     	%>
					                     		<li><%= aVisite.getLibelle() %></li>
					                     	<%
					                     	}
				                     	}
				                     	else
				                     	{
				                     		%> <span>à venir...</span> <%
				                     	}
				                     	%>
				                     </ul>
			                     </div>
			                     
			                     <div class="col-sm-12 col-md-6">
			                     	<h3>Activités :</h3>
			                     	<ul>
				                     	<%
				                     	if(offreVoyage.getLieu().getListeActivites().size() > 0)
				                     	{
				                     		for(Activite activite: offreVoyage.getLieu().getListeActivites()) {
					                     	%>
					                    		<li><%= activite.getNomActivite() %></li>
					                     	<%
					                     	}
				                     	}
				                     	else
				                     	{
				                     		%> <span>à venir...</span> <%
				                     	}
				                     	%>
				                     </ul>
			                     </div>
		                     </div>
		                     
		                     <span>Les tarifs :</span>
		                     <ul>
		                     
		                     	<%
		                     	if(offreVoyage.getListeTarifs().size() > 0)
		                     	{
			                     	for(Tarif tarif: offreVoyage.getListeTarifs()) {
			                     	%>
			                     		<li><%= tarif.getCategorie().getNomCategorie() %> : <%= tarif.getPrixUnitaire() %>€</li>
			                     	<%
			                     	}
		                     	}
		                     	else
		                     	{
		                     		%> <span>à venir...</span> <%
		                     	}
		                     	%>
		                     </ul>
	                     </div>
	
	                </div>
	            
	            <h2>Information client</h2>
	            <form action="Reservation" method="POST">
		          <!-- Nom -->
		          <div class="form-group">
		            <label for="nom">Nom</label>
		            <input type="text" class="form-control" name="nom" id="pseudo" aria-describedby="emailHelp" placeholder="Dupond" <% if(request.getAttribute("nom") != null) { out.println("value='"+request.getAttribute("nom")+"'"); }%>>
		          </div>
		          
		          <!-- Prénom -->
		          <div class="form-group">
		              <label for="prenom">Prénom</label>
		              <input type="text" class="form-control" name="prenom" id="password" placeholder="Louis" <% if(request.getAttribute("prenom") != null) { out.println("value='"+request.getAttribute("prenom")+"'"); }%>>
		          </div>
		          
		          <!-- Adresse mail -->
		          <div class="form-group">
		              <label for="email">Adresse mail</label>
		              <input type="email" class="form-control" name="email" id="email" placeholder="louis.dupond@mail.com" <% if(request.getAttribute("email") != null) { out.println("value='"+request.getAttribute("email")+"'"); }%>>
		          </div>
		          
		          <!-- Téléphone -->
		          <div class="form-group">
		              <label for="tel">Téléphone</label>
		              <input type="tel" class="form-control" name="tel" id="password" placeholder="0654655454" maxlength="10" <% if(request.getAttribute("tel") != null) { out.println("value='"+request.getAttribute("tel")+"'"); }%>>
		          </div>
		          
		          <input type="text" name="idOffreVoyage" value="<%= String.valueOf(offreVoyage.getIdOffre()) %>" class="hidden" />
		          
		          <% 
		          List<Tarif>  listeTarifs = (List<Tarif>) request.getAttribute("listeTarifs");
		            for(Tarif tarif: listeTarifs) {
		          %>
		          <div class="form-group row">
				    <label for="inputEmail3" class="col-sm-2 col-form-label">nombre de <%= tarif.getCategorie().getNomCategorie() %>(s)</label>
				    <div class="col-sm-10">
				      <input type="number" class="form-control" min="0" name="<%= tarif.getCategorie().getNomCategorie() %>" id="inputEmail3" placeholder="2" <% if(request.getAttribute(tarif.getCategorie().getNomCategorie()) != null) { out.println("value='"+request.getAttribute(tarif.getCategorie().getNomCategorie())+"'"); }%>>
				    </div>
				  </div>
				  <%
		            }
				  %> 
		          <p class="text-right"><button type="submit" class="btn btn-primary " >Enregistrer la réservation</button></p>
		      </form>
            <%
            }
            %>
          </div>
        </div>
      </section>

      <jsp:include page="footer.jsp"/>
</body>
</html>