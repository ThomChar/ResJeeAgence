package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Categorie;
import model.OffreVoyage;

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
		
		HttpSession session = request.getSession();

		try {
			int idOffreVoyage = Integer.valueOf(request.getParameter("offreVoyage"));
			// Si c'est la première fois qu'on essaie de se logguer, ou
		       // d'inscrire quelqu'un
	       if (!AgenceHelper.gestionnairesCrees(session))
	       {
	           AgenceHelper.creerGestionnaire(getServletContext(), session);
	       }
	       
			// on vérifie que l'offre voyage exite
			OffreVoyage offreVoyage = (OffreVoyage) AgenceHelper.getAgenceInterrogation(session).getGestionOffreVoyage().affichageOffreVoyage(idOffreVoyage);
			
			if(offreVoyage != null)
				System.out.println(offreVoyage.toString());
			else
				throw new Exception("L'offre de voyage passée en paramètre n'existe pas.");
			
			// on récupère toutes les catégories
			ArrayList<Categorie> listeCategories = (ArrayList<Categorie>) AgenceHelper.getAgenceInterrogation(session).getGestionCategorie().affichageCategories();
			
			request.setAttribute("offreVoyage", offreVoyage);
			request.setAttribute("listeCategories", listeCategories);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
			dispatcher.forward(request, response);
		
		} catch(Exception e) {
			
			request.setAttribute("erreur", e.getMessage());
			System.out.println(e.getMessage());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
			dispatcher.forward(request, response);
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		try {
			// Si c'est la première fois qu'on essaie de se logguer, ou
		       // d'inscrire quelqu'un
	       if (!AgenceHelper.gestionnairesCrees(session))
	       {
	           AgenceHelper.creerGestionnaire(getServletContext(), session);
	       }
	       
	       int idOffreVoyage = Integer.valueOf(request.getParameter("idOffreVoyage"));
	       String nom = request.getParameter("nom");
	       String prenom = request.getParameter("prenom");
	       String email = request.getParameter("email");
	       String tel = request.getParameter("tel");
	       
	       OffreVoyage offreVoyage = (OffreVoyage) AgenceHelper.getAgenceInterrogation(session).getGestionOffreVoyage().affichageOffreVoyage(idOffreVoyage);
	       
	       if(offreVoyage != null)
				System.out.println(offreVoyage.toString());
			else
				throw new Exception("L'offre de voyage passée en paramètre n'existe pas.");
			
			// on récupère toutes les catégories
			ArrayList<Categorie> listeCategories = (ArrayList<Categorie>) AgenceHelper.getAgenceInterrogation(session).getGestionCategorie().affichageCategories();
			
			request.setAttribute("offreVoyage", offreVoyage);
			request.setAttribute("listeCategories", listeCategories);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("email", email);
			request.setAttribute("tel", tel);
       
			// on fait les tests
			 if(nom == null || nom.isEmpty())
                 throw new Exception("Le nom est null");
             if(prenom == null || prenom.isEmpty())
                 throw new Exception("Le prenom est null");
             if(email == null || email.isEmpty())
                 throw new Exception("Le mail est null");
             if(tel == null || tel.isEmpty())
                 throw new Exception("Le téléphone est null");
            // implémenter test téléphone ultérieurement 
			
			// on ajoute la réservation
             //AgenceHelper.getAgenceInterrogation(session).getGestionR
             
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
			dispatcher.forward(request, response);


        } catch(Exception e) {
                request.setAttribute("erreur", e.getMessage());

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
        		dispatcher.forward(request, response);
        }
		
		//doGet(request, response);
	}

}