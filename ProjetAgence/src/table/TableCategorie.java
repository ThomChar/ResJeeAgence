package table;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import connexionBD.Connexion;
import model.Categorie;
import model.OffreVoyage;

/*import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;*/

public class TableCategorie {
	
	private TypedQuery<Categorie> stmtExiste;
	private TypedQuery<Categorie> stmtExisteByContent;
	private TypedQuery<Categorie> stmtListToutesCategories;
	
	private Connexion cx;

	/**
	 * Creation d'une instance.
	 */
	public TableCategorie(Connexion cx) {
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery(
				"select c from Categorie c where c.idCategorie = :idCategorie",
				Categorie.class);
		stmtExisteByContent = cx.getConnection().createQuery(
				"select c from Categorie c where c.nomCategorie = :nomCategorie",
				Categorie.class);
		stmtListToutesCategories = cx.getConnection().createQuery("select o from Categorie o", Categorie.class);
		
	}

	/**
	 * Retourner la connexion associee.
	 */
	public Connexion getConnexion() {
		return cx;
	}

	/**
	 * Verifie si une categorie existe.
	 * 
	 */
	public boolean existe(int idCategorie) {
		stmtExiste.setParameter("idCategorie", idCategorie);
		return !stmtExiste.getResultList().isEmpty();
	}
	
	/**
	 * Verifie si une categorie existe.
	 * 
	 */
	public boolean existeByContent(String nomCategorie) {
		stmtExisteByContent.setParameter("nomCategorie", nomCategorie);
		return !stmtExisteByContent.getResultList().isEmpty();
	}

	/**
	 * Recupere une categorie correspondant au nomCategorie.
	 * 
	 */
	public Categorie getCategorie(String nomCategorie) {
		stmtExisteByContent.setParameter("nomCategorie", nomCategorie);
		List<Categorie> categories = stmtExisteByContent.getResultList();
		if (!categories.isEmpty()) {
			return categories.get(0);
		} else {
			return null;
		}
	}
	

	/**
	 * Ajout d'une nouvelle categorie non vide.
	 * 
	 */
	public Categorie creer(Categorie categorie) {
		cx.getConnection().persist(categorie);
		return categorie;
	}

	/**
	 * Suppression d'une categorie.
	 */
	public boolean supprimer(Categorie categorie) {
		if (categorie != null) {
			cx.getConnection().remove(categorie);
			return true;
		}
		return false;
	}
	
	/**
     * Retourne l'ensemble des categories de la base de données
     * @return
     */
    public List<Categorie> getListeCategories()
    {
        return stmtListToutesCategories.getResultList();
    }

	public Categorie getCategorieById(int idCategorie) {
		stmtExiste.setParameter("idCategorie", idCategorie);
		List<Categorie> categories = stmtExiste.getResultList();
		if (!categories.isEmpty()) {
			return categories.get(0);
		} else {
			return null;
		}
	}	
}
