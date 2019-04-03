package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categorie;
import model.Lieu;
import model.OffreVoyage;

/**
 * Servlet implementation class Accueil
 */
//@WebServlet("/Accueil")
public class ConsultationOffre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultationOffre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		// on récupère toutes les offres de voyages
			// dans l'attente de la BDD, on les génère.
		ArrayList<OffreVoyage> listeOffresVoyages = new ArrayList<OffreVoyage>();
		ArrayList<Lieu> listeLieux = new ArrayList<Lieu>();
		ArrayList<Categorie> listeCategorie = new ArrayList<Categorie>();
		

		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultationOffre.jsp");
		dispatcher.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//doGet(request, response);
	}

}