<%@ page import="java.util.*,java.text.*, model.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("activePage", "ConsultationOffre"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
           /* ArrayList<OffreVoyage> offresVoyages = request.getAttribute("a");
            for(OffreVoyage offreVoyage: offresVoyages) {
            	
            }
            */
            %>
            <div class="formTitle col-sm-12 col-md-12 col-lg-12">
                    <!--  <h2>General Information</h2>-->
                </div>
            
            
          </div>

        </div>
      </section>

      <jsp:include page="footer.jsp"/>
</body>
</html>