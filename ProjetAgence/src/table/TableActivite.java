package table;

import java.util.List;
import javax.persistence.TypedQuery;
import connexionBD.*;
import model.*;

public class TableActivite {

	//private int idActivite;
	//private String nomActivite;
	
	private TypedQuery<Activite> stmtExiste;
	private TypedQuery<Activite> stmtExisteById;
	private TypedQuery<Activite> stmtListToutesActivites;
	private TypedQuery<Activite> stmtListToutesActivitesLieu;
	//private TypedQuery<Activite> stmtListToutesOccupationsActivite;	//a	 mettre dans la table des occupations
	
	
	private Connexion cx;

	/**
	 * Creation d'une instance.
	 */
	public TableActivite(Connexion cx) {
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery("select a from Activite a where a.nomActivite = :nomActivite",Activite.class);
		stmtExisteById = cx.getConnection().createQuery("select a from Activite a where a.idActivite = :idActivite",Activite.class);
		stmtListToutesActivites = cx.getConnection().createQuery("select a from Activite a",Activite.class);
		stmtListToutesActivitesLieu = cx.getConnection()
				.createQuery("select a from Activite a where a.lieu.idLieu = :idLieu", Activite.class);
		//stmtListToutesOccupationsActivite = cx.getConnection().createQuery("select e from Equipe e where e.ligue.nomLigue = :nomLigue",Activite.class);
	}
	
	
	/**
	 * Retourner la connexion associee.
	 */
	public Connexion getConnexion() {
		return cx;
	}

	/**
	 * Verifie si une activite existe.
	 * 
	 */
	public boolean existe(String nomActivite) {
		stmtExiste.setParameter("nomActivite", nomActivite);
		return !stmtExiste.getResultList().isEmpty();
	}
	
	/**
	 * Verifie si une activite existe.
	 * 
	 */
	public boolean existeById(int idActivite) {
		stmtExiste.setParameter("idActivite", idActivite);
		return !stmtExiste.getResultList().isEmpty();
	}

	/**
	 * Recupere Activite correspondant au nomActivite.
	 * 
	 */
	public Activite getActivite(String nomActivite){
		stmtExiste.setParameter("nomActivite", nomActivite);
		List<Activite> activites = stmtExiste.getResultList();
		if (!activites.isEmpty()) {
			return activites.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Recupere Activite correspondant a idActivite.
	 * 
	 */
	public Activite getActiviteById(int idActivite){
		stmtExiste.setParameter("idActivite", idActivite);
		List<Activite> activites = stmtExiste.getResultList();
		if (!activites.isEmpty()) {
			return activites.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Recupere occupation correspondant au idLieu.
	 * 
	 */
	public List<Activite> getActivitesLieu(int idLieu) {
		stmtListToutesActivitesLieu.setParameter("idLieu", idLieu);
		List<Activite> activites = stmtListToutesActivitesLieu.getResultList();
		if (!activites.isEmpty()) {
			return activites;
		} else {
			return null;
		}
	}

	/**
	 * Ajout d'une nouvelle activite non vide.
	 * 
	 */
	public Activite creer(Activite activite) {
        cx.getConnection().persist(activite);       
        return activite;
	}

	 /**
     * Suppression d'une Activite.
     */
    public boolean supprimer(Activite activite)
    {
        if(activite != null)
        {
            cx.getConnection().remove(activite);
            return true;
        }
        return false;
    }
    
    /**
     * Retourne l'ensemble des activites de la base de données
     * @return
     */
    public List<Activite> getListeActivites()
    {
        return stmtListToutesActivites.getResultList();
    }
}
