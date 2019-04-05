package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

import connexionBD.Connexion;
import connexionBD.HibernateUtil;
import model.Activite;
import model.Categorie;
import model.Lieu;
import model.OffreVoyage;
import model.Tarif;

/**
 * Servlet implementation class Accueil
 */
//@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//@PersistenceContext
	//private  EntityManagerFactory em;
	//private Connexion cx;
	
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//cx = new Connexion();
		HttpSession session = request.getSession();
        
        // Si c'est la première fois qu'on essaie de se logguer, ou
        // d'inscrire quelqu'un
		try {
        if (!AgenceHelper.gestionnairesCrees(session))
        {
            System.out.println("Servlet UserLogin : POST Création des gestionnaires");

            AgenceHelper.creerGestionnaire(getServletContext(), session);
        }
/*            AgenceHelper.getAgenceInterrogation(session).getGestionActivite().ajouter("salut6");
            System.out.println("1");
            AgenceHelper.getAgenceInterrogation(session).getGestionActivite().supprime("salut");
            System.out.println("1");
            AgenceHelper.getAgenceInterrogation(session).getGestionActivite().affichageActivite("salut2");
            System.out.println("1");
            AgenceHelper.getAgenceInterrogation(session).getGestionActivite().lectureOcccupationsActivite("salut2");
*/
        /*
            //AgenceHelper.creerGestionnaire(getServletContext(), session);
            //AgenceHelper.getAgenceInterrogation(session).getGestionActivite().ajouter("salut");
            System.out.println("static Lieu");
            //Lieu lieutest = new Lieu("Polytech", "France");
            System.out.println("static Lieu done");
            //AgenceHelper.getAgenceInterrogation(session).getGestionLieu().ajouter("Polytech", "France");
            System.out.println("ajout Lieu");
            //lieutest = AgenceHelper.getAgenceInterrogation(session).getGestionLieu().affichageLieu("Polytech", "France");
            //AgenceHelper.getAgenceInterrogation(session).getGestionOffreVoyage().ajouter("description", lieutest,  "04/04/2019", "24/04/2019");
            System.out.println("ajout offre");
            OffreVoyage test = AgenceHelper.getAgenceInterrogation(session).getGestionOffreVoyage().affichageOffreVoyage(2);
            System.out.println(test.toString());
            Lieu tedgdtdgf = test.getLieu();
            System.out.println(tedgdtdgf.toString());
            //AgenceHelper.getAgenceInterrogation(session).getGestionCategorie().ajouter("c1");
            System.out.println("ajout Categorie BD");
            Categorie c1 = AgenceHelper.getAgenceInterrogation(session).getGestionCategorie().affichageCategorie(4);
            System.out.println("ajout Categorie done");
            //AgenceHelper.getAgenceInterrogation(session).getGestionTarif().ajouter(20.1f, test, c1);
            System.out.println("ajout Tarif BD");
            Tarif t1 =  AgenceHelper.getAgenceInterrogation(session).getGestionTarif().affichageTarif(2, 4);
            System.out.println("ajout Tarif done");
            */
            //AgenceHelper.getAgenceInterrogation(session).getGestionActivite().supprime("salut3");
            //AgenceHelper.getAgenceInterrogation(session).getGestionActivite().getActivite("salut");
            //Activite test = AgenceHelper.getAgenceInterrogation(session).getGestionActivite().getActivite("salut");
            //System.out.println(test.toString());
           // AgenceHelper.getAgenceInterrogation(session).getGestionActivite().affichageActivite("salut");
            System.out.println("1");
            //AgenceHelper.getAgenceInterrogation(session).getGestionActivite().lectureOcccupationsActivite("salut");

            System.out.println("1");
            //AgenceHelper.getAgenceInterrogation(session).getGestionActivite().affichageActivites();
            System.out.println("1");
        
		}catch(Exception e){
			System.out.println("erreur"+e.getMessage());
		}
		//cx = new Connexion();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		dispatcher.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		dispatcher.forward(request, response);*/
		
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("agencedb");
		PrintWriter out = response.getWriter();
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    Activite newActivite = new Activite();
	    newActivite.setNomActivite("Hibernate3");
	    em.persist(newActivite);
	    em.getTransaction().commit();
	    out.write("Entité persistée, id:" + newActivite.getIdActivite() + " "+ newActivite.getNomActivite());
	    em.close();
	    emf.close();*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		dispatcher.forward(request, response);
		//cx.getConnection().createQuery("select a from Activite a");
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("UniversityApplicationPU");
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("agencedb");
		/*PrintWriter out = response.getWriter();
	    //EntityManager em = emf.createEntityManager();
	    cx.getConnection().getTransaction().begin();
	    //create an activite
	    Activite newActivite = new Activite();
	    newActivite.setNomActivite("trololol");
	    cx.getConnection().persist(newActivite);
	    System.out.println(newActivite);
	    cx.getConnection().getTransaction().commit();
	    out.write("Entité persistée, id:" + newActivite.getIdActivite() + " "+ newActivite.getNomActivite());
	    
	    //find an activite
	    Activite activiterecup = cx.getConnection().find(Activite.class, 34);
		System.out.println("Recup : "+activiterecup.toString());
		//remove an activite
		cx.getConnection().remove(activiterecup);*/
	    //System.out.println("remove done");
	     //System.out.println("Recup :: "+cx.getConnection().find(Activite.class, 33).toString());
	     //cx.fermer();
	    //cx.getConnection().close();
	    //emf.close();
		//mettre cascade all
		//doGet(request, response);
	    
	   
	}
	
	/*private void forward(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(page);
		rd.forward(request, response);
	}*/
}
