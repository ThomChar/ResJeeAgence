	package servlet;

	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

	import model.Categorie;
import model.Lieu;
import model.OffreVoyage;
	import model.Tarif;

	/**
	 * Servlet implementation class Accueil
	 */
	//@WebServlet("/Login")
	public class GestionOffre extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public GestionOffre() {
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
				if(session.getAttribute("user") == null) {
					 request.setAttribute("erreur", "Connexion refusée ! La connexion est reservé aux employés.");
					 
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
					dispatcher.forward(request, response);
				}
				
				// Si c'est la première fois qu'on essaie de se logguer, ou
			       // d'inscrire quelqu'un
			       if (!AgenceHelper.gestionnairesCrees(session))
			       {
			           AgenceHelper.creerGestionnaire(getServletContext(), session);
			       }
		       
				// on vérifie que l'offre voyage exite
				List<Lieu> listeLieux = (List<Lieu>) AgenceHelper.getAgenceInterrogation(session).getGestionLieu().affichageLieus();
				
				List<Categorie> listeCategorie =  AgenceHelper.getAgenceInterrogation(session).getGestionCategorie().affichageCategories();
				
				request.setAttribute("listeCategorie", listeCategorie);
				request.setAttribute("listeLieux", listeLieux);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gestionOffre.jsp");
				dispatcher.forward(request, response);
			
			} catch(Exception e) {
				
				request.setAttribute("erreur", e.getMessage());
				System.out.println(e.getMessage());
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gestionOffre.jsp");
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
				if(session.getAttribute("user") == null) {
					 request.setAttribute("erreur", "Connexion refusée ! La connexion est reservé aux employés.");
					 
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
					dispatcher.forward(request, response);
				}
				// Si c'est la première fois qu'on essaie de se logguer, ou
			       // d'inscrire quelqu'un
		       if (!AgenceHelper.gestionnairesCrees(session))
		       {
		           AgenceHelper.creerGestionnaire(getServletContext(), session);
		       }

		       int lieu = Integer.valueOf(request.getParameter("lieu"));
		       String description = request.getParameter("description");
		       String dateDebut = request.getParameter("dateDebut");
		       String dateFin = request.getParameter("dateFin");
		       String nbPlace = request.getParameter("nbPlace");
		       
		       List<Lieu> listeLieux = (List<Lieu>) AgenceHelper.getAgenceInterrogation(session).getGestionLieu().affichageLieus();
		       List<Categorie> listeCategorie =  AgenceHelper.getAgenceInterrogation(session).getGestionCategorie().affichageCategories();
				
				request.setAttribute("listeCategorie", listeCategorie);
				request.setAttribute("listeLieux", listeLieux);
		       
				request.setAttribute("lieu", lieu);
				request.setAttribute("description", description);
				request.setAttribute("dateDebut", dateDebut);
				request.setAttribute("dateFin", dateFin);
				request.setAttribute("nbPlace", nbPlace);
	       
				// on fait les tests
				Lieu lieuObj = (Lieu) AgenceHelper.getAgenceInterrogation(session).getGestionLieu().affichageLieu(lieu);
				
				 if(lieuObj == null)
	                 throw new Exception("Le Lieu n'exisste pas");
	             if(description == null || description.isEmpty())
	                 throw new Exception("Le prenom est null");
	             if(dateDebut == null || dateDebut.isEmpty())
	                 throw new Exception("Le mail est null");
	             if(dateFin == null || dateFin.isEmpty())
	                 throw new Exception("Le téléphone est null");
	            // implémenter test téléphone ultérieurement 
				
				// on ajoute la réservation
	             AgenceHelper.getAgenceInterrogation(session).getGestionOffreVoyage().ajouter(description, lieuObj, dateDebut, dateFin);
	             OffreVoyage offreVoayge = AgenceHelper.getAgenceInterrogation(session).getGestionOffreVoyage().getLastOffreVoyage();
	             
	             for(Categorie categorie: listeCategorie) {
	            	 
	            	 String prix = request.getParameter(categorie.getNomCategorie());
	            	 System.out.println(prix);
	            	 System.out.println(categorie.getNomCategorie());
	            	 
	            	 AgenceHelper.getAgenceInterrogation(session).getGestionTarif().ajouter(Integer.valueOf(prix), offreVoayge, categorie);
	             }

	             request.setAttribute("messageSuccess", "L'offre a bien été enregistré.");
	             request.setAttribute("lieu", null);
				request.setAttribute("description", null);
				request.setAttribute("dateDebut", null);
				request.setAttribute("dateFin", null);
				request.setAttribute("nbPlace", null);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gestionOffre.jsp");
				dispatcher.forward(request, response);


	        } catch(Exception e) {
	                request.setAttribute("erreur", e.getMessage());

	                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gestionOffre.jsp");
	        		dispatcher.forward(request, response);
	        }
			
			//doGet(request, response);
		}

	}