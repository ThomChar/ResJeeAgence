package servlet;
import gestionnaire.*;
import connexionBD.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AgenceHelper
{
    public static boolean infoBDValide(ServletContext c)
    {
        return c.getAttribute("serveur") != null;
    }
    
    public static boolean peutProceder(ServletContext c, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        if(infoBDValide(c))
        {
            HttpSession session = request.getSession(false);
            if (AgenceHelper.estConnecte(session))
            {
                return true;
            }
            DispatchToLogin(request, response);
            return false;
        }
        else
        {
            DispatchToBDConnect(request, response);
            return false;
        }
    }
    
    public static boolean peutProcederLogin(ServletContext c, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        if(infoBDValide(c))
        {
            HttpSession session = request.getSession(false);
            if (session != null)
            {
                session.invalidate();
            }
            return true;
        }
        else
        {
            DispatchToBDConnect(request, response);
            return false;
        }
    }
    
    public static void DispatchToLoginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession(false);
        if (AgenceHelper.estConnecte(session))
        {
            session.invalidate();
        }
        // Afficher le menu de connexion principal de l'application
        RequestDispatcher dispatcher = request.getRequestDispatcher("/UserLogin");
        dispatcher.forward(request, response);
    }
    
    public static void DispatchToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession(false);
        if (AgenceHelper.estConnecte(session))
        {
            session.invalidate();
        }
        // Afficher le menu de connexion principal de l'application
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/userLogin.jsp");
        dispatcher.forward(request, response);
    }

    public static void DispatchToAccueil(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession(false);
        if (AgenceHelper.estConnecte(session))
        {
            session.invalidate();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/accueil.jsp");
        dispatcher.forward(request, response);
    }
    
    public static void DispatchToBDConnect(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession(false);
        if (AgenceHelper.estConnecte(session))
        {
            session.invalidate();
        }
        // Afficher le menu de connexion principal de l'application
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
    
    public static boolean estConnecte(HttpSession session)
    {
        if(session == null)
            return false;
        return session.getAttribute("etat") != null;
    }
    
    public static boolean gestionnairesCrees(HttpSession session)
    {
        if(session == null)
            return false;
        return session.getAttribute("agenceInterrogation") != null;
    }
    
    public static void creerGestionnaire(ServletContext c, HttpSession s) throws SQLException, AgencyException
    {
        /*String serveur = (String) c.getAttribute("serveur");
        String bd = (String) c.getAttribute("bd");
        String userIdBD = (String) c.getAttribute("user");
        String pass = (String) c.getAttribute("pass");*/
    	
    	System.out.println("test");
        GestionnaireAgence agenceInterrogation = new GestionnaireAgence();
        System.out.println("test1");
        //agenceInterrogation.getConnexion().setIsolationReadCommited();
        s.setAttribute("agenceInterrogation", agenceInterrogation);
        System.out.println("test2");
        GestionnaireAgence agenceUpdate = new GestionnaireAgence();
        System.out.println("test3");
        s.setAttribute("agenceUpdate", agenceUpdate);
        System.out.println("test4");
    }
    
    public static GestionnaireAgence getAgenceInterrogation(HttpSession session)
    {
        return (GestionnaireAgence)session.getAttribute("agenceInterrogation");
    }
    
    public static GestionnaireAgence getAgenceUpdate(HttpSession session)
    {
        return (GestionnaireAgence)session.getAttribute("agenceUpdate");
    }
    
    
    public static int ConvertirInt(String v, String nom) throws AgencyException
    {
        try
        {
            return Integer.parseInt(v);
        }
        catch(Exception e)
        {
            throw new AgencyException(nom + " ne doit être composé que de chiffre.");
        }
    }
    
    public static long ConvertirLong(String v, String nom) throws AgencyException
    {
        try
        {
            return Long.parseLong(v);
        }
        catch(Exception e)
        {
            throw new AgencyException(nom + " ne doit être composé que de chiffre.");
        }
    }
}
