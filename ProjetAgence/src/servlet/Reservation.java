package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Accueil
 */
//@WebServlet("/Login")
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// on r�cup�re la r�servation passer en param�tre
		int idOffreVoyage = Integer.valueOf(request.getParameter("offreVoyage"));
				
		// on v�rifie que l'offre voyage exite
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
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

            if(pseudo == null || pseudo.isEmpty() || password== null || password.isEmpty()) {
                    throw new Exception("Le pseudo ou le mot de passe est null");
            }
            
            //List<UserAccount> user = uamanager.getAllUsers();
            
            if(/*uamanager.validateLogin(email, password)*/ password.equals("password"))
            {
                    // la connexion est �tablie
                    session.setAttribute("user", pseudo);
                    
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
            		dispatcher.forward(request, response);
            }
            else
            {
                    // connexion refus�e
                    throw new Exception("Le pseudo ou le mot de passe n'est pas correcte !");
            }

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