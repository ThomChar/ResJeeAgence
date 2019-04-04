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
import model.Tarif;

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

		
		// on r�cup�re toutes les offres de voyages
			// dans l'attente de la BDD, on les g�n�re.
		ArrayList<OffreVoyage> listeOffresVoyages = new ArrayList<OffreVoyage>();
		ArrayList<Lieu> listeLieux = new ArrayList<Lieu>();
		ArrayList<Categorie> listeCategorie = new ArrayList<Categorie>();
		Lieu lieu1 = new Lieu("Polytech Tours", "France");
		Categorie cat1 = new Categorie("enfant");
		Categorie cat2 = new Categorie("�tudiant");
		
		OffreVoyage offreVoyage = new OffreVoyage("Un super voyage !", lieu1, "10/06/2019", "16/062019");
		
		Tarif t1 = new Tarif(11, offreVoyage, cat1);
		Tarif t2 = new Tarif(17, offreVoyage, cat2);
		offreVoyage.getListeTarifs().add(t1);
		offreVoyage.getListeTarifs().add(t2);
		
		listeOffresVoyages.add(offreVoyage);
		
		request.setAttribute("listeOffresVoyages", listeOffresVoyages);
		
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