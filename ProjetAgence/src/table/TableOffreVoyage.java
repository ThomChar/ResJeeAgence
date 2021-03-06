package table;

import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import connexionBD.*;
import model.*;

public class TableOffreVoyage {
	
	private TypedQuery<OffreVoyage> stmtExiste;
	private TypedQuery<OffreVoyage> stmtExisteByContent;
	private TypedQuery<OffreVoyage> stmtListToutesOffreVoyages;
	private TypedQuery<OffreVoyage> stmtListToutesOffreVoyagesLieu;
	private TypedQuery<OffreVoyage> stmtLastOffreVoyage;
	
	private Connexion cx;

	/**
	 * Creation d'une instance.
	 */
	public TableOffreVoyage(Connexion cx) {
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery(
				"select o from OffreVoyage o where o.idOffreVoyage = :idOffreVoyage",
				OffreVoyage.class);
		stmtExisteByContent = cx.getConnection().createQuery(
				"select o from OffreVoyage o where o.description = :description and o.lieu.idLieu = :idLieu",
				OffreVoyage.class);
		stmtListToutesOffreVoyages = cx.getConnection().createQuery("select o from OffreVoyage o", OffreVoyage.class);
		stmtListToutesOffreVoyagesLieu = cx.getConnection()
				.createQuery("select o from OffreVoyage o where o.lieu.idLieu = :idLieu", OffreVoyage.class);
		stmtLastOffreVoyage = cx.getConnection().createQuery("select r from OffreVoyage r where r.idOffreVoyage = (SELECT MAX(r.idOffreVoyage) from OffreVoyage)", OffreVoyage.class);
		
	}

	/**
	 * Retourner la connexion associee.
	 */
	public Connexion getConnexion() {
		return cx;
	}

	/**
	 * Verifie si une offre de voyage existe.
	 * 
	 */
	public boolean existe(int idOffreVoyage) {
		stmtExiste.setParameter("idOffreVoyage", idOffreVoyage);
		return !stmtExiste.getResultList().isEmpty();
	}
	
	/**
	 * Verifie si une offre de voyage existe.
	 * 
	 */
	/*public boolean existeByContent(String description, Lieu lieu, Date dateDebut, Date dateFin) {
		stmtExisteByContent.setParameter("description", description);
		stmtExisteByContent.setParameter("idLieu", lieu.getIdLieu());
		return !stmtExisteByContent.getResultList().isEmpty();
	}*/

	/**
	 * Recupere offre  de Voyage correspondant au idOffreVoyage.
	 * 
	 */
	public OffreVoyage getOffreVoyage(int idOffreVoyage) {
		stmtExiste.setParameter("idOffreVoyage", idOffreVoyage);
		List<OffreVoyage> offreVoyages = stmtExiste.getResultList();
		if (!offreVoyages.isEmpty()) {
			return offreVoyages.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Recupere offre de Voyage correspondant au idOffreVoyage.
	 * 
	 */
	public List<OffreVoyage> getOffresVoyageLieu(int idLieu) {
		stmtListToutesOffreVoyagesLieu.setParameter("idLieu", idLieu);
		List<OffreVoyage> offreVoyages = stmtListToutesOffreVoyagesLieu.getResultList();
		if (!offreVoyages.isEmpty()) {
			return offreVoyages;
		} else {
			return null;
		}
	}
	

	/**
	 * Ajout d'une nouvelle offre de voyage non vide.
	 * 
	 */
	public OffreVoyage creer(OffreVoyage offreVoyage) {
		cx.getConnection().persist(offreVoyage);
		return offreVoyage;
	}

	/**
	 * Suppression d'une offre de voyage.
	 */
	public boolean supprimer(OffreVoyage offreVoyage) {
		if (offreVoyage != null) {
			cx.getConnection().remove(offreVoyage);
			return true;
		}
		return false;
	}
	
	/**
     * Retourne l'ensemble des offres de Voyage de la base de donn�es
     * @return
     */
    public List<OffreVoyage> getListeOffresVoyage()
    {
    	System.out.println("tableOfreVoyage getListeOffresVoyage");
        return stmtListToutesOffreVoyages.getResultList();
    }
    
    /**
     * Retourne la derni�re offre de voyage de la BDD
     * @return
     */
    public OffreVoyage getLastOffresVoyages()
    {
		List<OffreVoyage> offresVoyages = stmtLastOffreVoyage.getResultList();
		if (!offresVoyages.isEmpty()) {
			return offresVoyages.get(0);
		} else {
			return null;
		}
    }
}
