package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employe;

/**
 * Servlet implementation class Accueil
 */
//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
		dispatcher.forward(request, response);

		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");

        try {
			// Si c'est la première fois qu'on essaie de se logguer, ou
		       // d'inscrire quelqu'un
	       if (!AgenceHelper.gestionnairesCrees(session))
	       {
	           AgenceHelper.creerGestionnaire(getServletContext(), session);
	       }

            if(pseudo == null || pseudo.isEmpty() || password== null || password.isEmpty()) {
                    throw new Exception("Le pseudo ou le mot de passe est null");
            }
            Employe connectedEmploye = AgenceHelper.getAgenceInterrogation(session).getGestionEmploye().getEmployeByPseudo(pseudo);

            if(connectedEmploye == null) {
            		throw new Exception("Le pseudo n'est pas correct veuillir le ressaisir");
            }else if(!connectedEmploye.getPassword().equals(password)) {
            	    throw new Exception("Le mot de passe n'est pas correct veuillir le ressaisir");
            }
            
            session.setAttribute("user", pseudo);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
    		dispatcher.forward(request, response);
    		

        } catch(Exception e) {

                request.setAttribute("pseudo", pseudo);
                request.setAttribute("password", password);

                request.setAttribute("erreur", e.getMessage());

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        		dispatcher.forward(request, response);
        }
		
		//doGet(request, response);
	}

}