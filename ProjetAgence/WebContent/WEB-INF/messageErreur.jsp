<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%
if(request.getAttribute("erreur") != null) {
        %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert" id="messageErreur">
            <%= request.getAttribute("erreur")%>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <%
}
%>