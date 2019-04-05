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
					System.out.println("Connexion refusée ! La connexion est reservé aux employés.");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
					dispatcher.forward(request, response);
				}
				
				if(request.getParameter("offreVoyage") == null)
					throw new Exception("Vous devez choisir une offre à reserver");
				
				int idOffreVoyage = Integer.valueOf(request.getParameter("offreVoyage"));
				// Si c'est la première fois qu'on essaie de se logguer, ou
			       // d'inscrire quelqu'un
			       if (!AgenceHelper.gestionnairesCrees(session))
			       {
			           AgenceHelper.creerGestionnaire(getServletContext(), session);
			       }
		       
				// on vérifie que l'offre voyage exite
				List<Lieu> listeLieux = (List<Lieu>) AgenceHelper.getAgenceInterrogation(session).getGestionLieu().affichageLieus();
				
				request.setAttribute("listeLieux", listeLieux);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reservation.jsp");
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
				
		       List<Tarif> listeTarifs =  offreVoyage.getListeTarifs();
				
				request.setAttribute("offreVoyage", offreVoyage);
				request.setAttribute("listeTarifs", listeTarifs);
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
	             AgenceHelper.getAgenceInterrogation(session).getGestionReservation().ajouter(nom, prenom, email, tel, offreVoyage);
	             System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
	             model.Reservation reservation = AgenceHelper.getAgenceInterrogation(session).getGestionReservation().getLastReservation();
	             System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA" + reservation.getIdReservation());
	             for(Tarif tarif: listeTarifs) {
	            	 
	            	 String nbParticipants = request.getParameter(tarif.getCategorie().getNomCategorie());
	            	 System.out.println(nbParticipants);
	            	 System.out.println(tarif.getCategorie().getNomCategorie());
	            	 
	            	 AgenceHelper.getAgenceInterrogation(session).getGestionParticipant().ajouter(Integer.valueOf(nbParticipants), tarif.getCategorie(), reservation);
	             }
	             /*
	             List<Tarif>  listeTarifs = (List<Tarif>) request.getAttribute("listeTarifs");
		            for(Tarif tarif: listeTarifs) {
		          %>
		          <div class="form-group row">
				    <label for="inputEmail3" class="col-sm-2 col-form-label">nombre de <%= tarif.getCategorie().getNomCategorie() %>(s)</label>
				    <div class="col-sm-10">
				      <input type="number" class="form-control" min="0" name="<%= tarif.getCategorie() %>" id="inputEmail3" placeholder="2" <% if(request.getAttribute(tarif.getCategorie().getNomCategorie()) != null) { out.println("value='"+request.getAttribute(tarif.getCategorie().getNomCategorie())+"'"); }%>>
				    </div>
				  </div>
				  <%
		            }
	             
	             */
	             request.setAttribute("messageSuccess", "La réservation a bien été enregistré.");
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