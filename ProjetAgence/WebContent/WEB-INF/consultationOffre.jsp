<%@ page import="java.util.*,java.text.*, model.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("activePage", "ConsultationOffre"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<%=request.getContextPath()%>/css/consultationOffre.css" rel="stylesheet">
	<title>Consulté les Offres</title>
	<jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

      <section class="container">
        
          <div class="row">
            <div class="col-sm-12 col-md-12">
            <h1>Les Offres de voyages</h1>
            
            <% 
            
            ArrayList<OffreVoyage> offresVoyages = (ArrayList<OffreVoyage>) request.getAttribute("listeOffresVoyages");
            for(OffreVoyage offreVoyage: offresVoyages) {
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
	                     
	                     <p class="text-right"><button type="submit" class="btn btn-primary buttonitemsOffre" onclick="location.href='<%=request.getContextPath()%>/Reservation?offreVoyage=<%= offreVoyage.getIdOffre() %>'">Reserver ce voyage</button></p>
                     </div>

                </div>
                
            <%
            }
            
            %>
            
            
            
          </div>

        </div>
      </section>

      <jsp:include page="footer.jsp"/>
</body>
</html>