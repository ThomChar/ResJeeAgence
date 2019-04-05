<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%
if(request.getAttribute("messageSuccess") != null) {
        %>
        <div class="alert alert-success alert-dismissible fade show" role="alert" id="messageErreur">
            <%= request.getAttribute("messageSuccess")%>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <%
}
%>