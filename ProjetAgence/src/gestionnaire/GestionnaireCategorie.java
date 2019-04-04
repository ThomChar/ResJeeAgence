package gestionnaire;

import java.util.Date;

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
		this.cx = categories.getConnexion();
		
		if (categories.getConnexion() == tarifs.getConnexion() && categories.getConnexion() == participants.getConnexion()) { 
			this.categories = categories;
			this.tarifs = tarifs;
			this.participants = participants;
		} else {
			throw new AgencyException(
					"Les instances de categories, de tarifs et de participants n'utilisent pas la m�me connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'une nouvelle categorie dans la base de donn�es. Si elle existe d�j�,
	 * une exception est lev�e.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void ajouter(String nomCategorie) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// V�rifie si l'offre de voyage existe d�j�

			/*if (offreVoyages.existeByContent(description, lieu))
				throw new AgencyException("Cette offre de Voyage existe deja ");*/
			
			//Categorie tupleCategorie = categories.getCategorie(nomCategorie);
			if (categories.existeByContent(nomCategorie))
				throw new AgencyException("Cette categorie existe deja ");
			
			Categorie tupleCategorie = new Categorie(nomCategorie);
			
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
	 * Supprime categorie de la base de donn�es.
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
				throw new AgencyException("Offre Voyage inexistante: " + tupleCategorie);
			System.out.println(tupleCategorie.toString());
			
			// Commit
			cx.commit();
			return tupleCategorie;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
}
