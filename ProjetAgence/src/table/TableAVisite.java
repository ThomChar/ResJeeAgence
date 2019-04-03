package table;

import java.util.List;
import javax.persistence.TypedQuery;
import connexionBD.*;
import model.*;

public class TableAVisite {
	
	private TypedQuery<AVisite> stmtExiste;
	private TypedQuery<AVisite> stmtListToutesAVisites;
	private TypedQuery<AVisite> stmtListToutesAVisiteLieu;
	// private TypedQuery<Activite> stmtListToutesOccupationsActivite; //a mettre
	// dans la table des occupations

	private Connexion cx;

	/**
	 * Creation d'une instance.
	 */
	public TableAVisite(Connexion cx) {
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery(
				"select a from AVisite a where a.libelle = :libelle and a.lieu.idLieu = :idLieu",
				AVisite.class);
		stmtListToutesAVisites = cx.getConnection().createQuery("select a from AVisite a", AVisite.class);
		stmtListToutesAVisiteLieu = cx.getConnection()
				.createQuery("select a from AVisite a where a.lieu.idLieu = :idLieu", AVisite.class);
	}

	/**
	 * Retourner la connexion associee.
	 */
	public Connexion getConnexion() {
		return cx;
	}

	/**
	 * Verifie si une chose a visiter existe.
	 * 
	 */
	public boolean existe(String libelle, Lieu lieu) {
		stmtExiste.setParameter("libelle", libelle);
		stmtExiste.setParameter("idLieu", lieu.getIdLieu());
		return !stmtExiste.getResultList().isEmpty();
	}

	/**
	 * Recupere une chose a visiter correspondant au libelle
	 * 
	 */
	public AVisite getAVisite(String libelle) {
		stmtExiste.setParameter("libelle", libelle);
		List<AVisite> aVisites = stmtExiste.getResultList();
		if (!aVisites.isEmpty()) {
			return aVisites.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Recupere liste des choses a visiter pour une ville correspondant au idLieu.
	 * 
	 */
	public List<AVisite> getLieuAVisites(int idLieu) {
		stmtListToutesAVisiteLieu.setParameter("idLieu", idLieu);
		List<AVisite> aVisites = stmtListToutesAVisiteLieu.getResultList();
		if (!aVisites.isEmpty()) {
			return aVisites;
		} else {
			return null;
		}
	}

	/**
	 * Ajout d'une nouvelle chose a visiter non vide.
	 * 
	 */
	public AVisite creer(AVisite aVisite) {
		cx.getConnection().persist(aVisite);
		return aVisite;
	}

	/**
	 * Suppression d'une chose à visiter.
	 */
	public boolean supprimer(AVisite aVisite) {
		if (aVisite != null) {
			cx.getConnection().remove(aVisite);
			return true;
		}
		return false;
	}
	
	/**
     * Retourne l'ensemble des choses à visiter de la base de données
     * @return
     */
    public List<AVisite> getListeAVisites()
    {
        return stmtListToutesAVisites.getResultList();
    }
	
}
