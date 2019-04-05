package gestionnaire;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import connexionBD.AgencyException;
import connexionBD.Connexion;
import model.Categorie;
import model.Lieu;
import model.OffreVoyage;
import table.TableCategorie;
import table.TableLieu;
import table.TableOffreVoyage;
import table.TableParticipant;
import table.TableTarif;

/*import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;*/

public class GestionnaireCategorie {


	private TableCategorie categories;
	private TableTarif tarifs;
	private TableParticipant participants;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireCategorie(TableCategorie tableCategorie, TableTarif tableTarif, TableParticipant tableParticipant)throws AgencyException {
		this.cx = tableCategorie.getConnexion();
		if (tableCategorie.getConnexion() == tableTarif.getConnexion() && tableCategorie.getConnexion() == tableParticipant.getConnexion()) { 
			this.categories = tableCategorie;
			this.tarifs = tableTarif;
			this.participants = tableParticipant;
		} else {
			throw new AgencyException(
					"Les instances de categories, de tarifs et de participants n'utilisent pas la même connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'une nouvelle categorie dans la base de données. Si elle existe déjà ,
	 * une exception est levée.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void ajouter(String nomCategorie) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Vérifie si l'offre de voyage existe déjà

			/*if (offreVoyages.existeByContent(description, lieu))
				throw new AgencyException("Cette offre de Voyage existe deja ");*/
			//Categorie tupleCategorie = categories.getCategorie(nomCategorie);
			if (categories.existeByContent(nomCategorie))
				throw new AgencyException("Cette categorie existe deja ");
			System.out.println("trtolrotorort");
			Categorie tupleCategorie = new Categorie(nomCategorie);
			System.out.println("trtolrotorort2");
			// Ajout de la categorie dans la table
			categories.creer(tupleCategorie);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Supprime categorie de la base de données.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void supprime(int idCategorie) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			Categorie tupleCategorie = categories.getCategorieById(idCategorie);
			if (tupleCategorie == null)
				throw new AgencyException("Categorie inexistante : " + tupleCategorie);
			
			// Suppression de la categorie
			boolean testExiste = categories.supprimer(tupleCategorie);	//regarder si lasuppression encascade sinon il faut supprimer dans les listes
			
			if (testExiste == false)
				throw new AgencyException("Categorie " + tupleCategorie + " inexistante");

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Affichage de Categorie
	 * 
	 * @throws AgencyException,Exception
	 */
	public Categorie affichageCategorie(int idCategorie) throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			Categorie tupleCategorie = categories.getCategorieById(idCategorie);
			if (tupleCategorie == null)
				throw new AgencyException("Categorie inexistante: " + tupleCategorie);
			System.out.println(tupleCategorie.toString());
			
			// Commit
			cx.commit();
			return tupleCategorie;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
	/**
	 * Affichage la liste des Categories
	 * 
	 * @throws AgencyException,Exception
	 */
	public List<Categorie> affichageCategories() throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			List<Categorie> listeCategories = categories.getListeCategories();
			
			// Commit
			cx.commit();
			return listeCategories;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
}
