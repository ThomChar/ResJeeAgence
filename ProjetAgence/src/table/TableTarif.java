package table;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import connexionBD.Connexion;
import model.Lieu;
import model.OffreVoyage;
import model.Tarif;

public class TableTarif {

	private TypedQuery<Tarif> stmtExiste;
	private TypedQuery<Tarif> stmtExisteByContent;
	private TypedQuery<Tarif> stmtListTousTarifs;
	private TypedQuery<Tarif> stmtListToutesOffreVoyagesTarif;
	private TypedQuery<Tarif> stmtListToutesCategoriesTarif;
	
	private Connexion cx;

	/**
	 * Creation d'une instance.
	 */
	public TableTarif(Connexion cx) {
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery(
				"select t from Tarif t where t.offreVoyage.idOffreVoyage = :idOffreVoyage and t.categorie.idCategorie= :idCategorie ",
				Tarif.class);
		stmtExisteByContent = cx.getConnection().createQuery(
				"select t from Tarif t where t.prixUnitaire = :prixUnitaire and t.offreVoyage.idOffreVoyage = :idOffreVoyage and t.categorie.idCategorie= :idCategorie",
				Tarif.class);
		stmtListTousTarifs = cx.getConnection().createQuery("select t from Tarif t", Tarif.class);
		stmtListToutesOffreVoyagesTarif = cx.getConnection()
				.createQuery("select t from Tarif t where prixUnitaire <= :prixUnitaire", Tarif.class);
		stmtListToutesCategoriesTarif = cx.getConnection()
				.createQuery("select t from Tarif t where idTarif = :idTarif", Tarif.class);
		
	}

	/**
	 * Verifie si un Tarif existe.
	 * 
	 */
	public boolean existe(int idCategorie, int idOffreVoyage) {
		stmtExiste.setParameter("idCategorie", idCategorie);
		stmtExiste.setParameter("idOffreVoyage", idOffreVoyage);
		return !stmtExiste.getResultList().isEmpty();
	}
	
	/**
	 * Verifie si un Tarifexiste.
	 * 
	 */
	public boolean existeByContent(float prixUnitaire, int idCategorie, int idOffreVoyage) {
		stmtExisteByContent.setParameter("prixUnitaire", prixUnitaire);
		stmtExisteByContent.setParameter("idCategorie", idCategorie);
		stmtExisteByContent.setParameter("idOffreVoyage", idOffreVoyage);
		return !stmtExisteByContent.getResultList().isEmpty();
	}

	/**
	 * Recupere un Tarif correspondant au couple idCategorie, id Offre.
	 * 
	 */
	public Tarif getTarif(int idCategorie, int idOffreVoyage) {
		stmtExiste.setParameter("idCategorie", idCategorie);
		stmtExiste.setParameter("idOffreVoyage", idOffreVoyage);
		List<Tarif> tarifs = stmtExiste.getResultList();
		if (!tarifs.isEmpty()) {
			return tarifs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Recupere offre de Voyage correspondant au tarif.
	 * 
	 */
	public List<Tarif> getOffresVoyageTarif(float prixUnitaire) {
		stmtListToutesOffreVoyagesTarif.setParameter("prixUnitaire", prixUnitaire);
		List<Tarif> tarifs = stmtListToutesOffreVoyagesTarif.getResultList();
		if (!tarifs.isEmpty()) {
			return tarifs;
		} else {
			return null;
		}
	}
	

	/**
	 * Ajout d'un nouveau tarif non vide non vide.
	 * 
	 */
	public Tarif creer(Tarif tarif) {
		cx.getConnection().persist(tarif);
		return tarif;
	}

	/**
	 * Suppression d'un tarif.
	 */
	public boolean supprimer(Tarif tarif) {
		if (tarif != null) {
			cx.getConnection().remove(tarif);
			return true;
		}
		return false;
	}
	
	/**
     * Retourne l'ensemble des tarifs de la base de données
     * @return
     */
    public List<Tarif> getListeTarifs()
    {
        return stmtListTousTarifs.getResultList();
    }
	
	public Connexion getConnexion() {
		return cx;
	}

}
