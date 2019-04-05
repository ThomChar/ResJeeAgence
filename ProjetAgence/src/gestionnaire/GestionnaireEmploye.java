package gestionnaire;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import connexionBD.AgencyException;
import connexionBD.Connexion;
import model.Employe;
import model.Lieu;
import model.OffreVoyage;
import table.TableEmploye;
import table.TableLieu;
import table.TableOffreVoyage;
import table.TableTarif;

public class GestionnaireEmploye {


	private TableEmploye employes;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireEmploye(TableEmploye employes)throws AgencyException {
		this.cx = employes.getConnexion();
		this.employes = employes;
	}
	
	/**
	 * Ajout d'un nouvel employe dans la base de données. Si elle existe déjà ,
	 * une exception est levée.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void ajouter(String nom, String prenom, String adresse, int codePostal, String ville, int age, String contrat, String pseudo, String password) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Vérifie si l'offre de voyage existe déjà
			
			if (employes.existeByContent(pseudo)) {
				throw new AgencyException("Cet employe existe deja ");
			}
			
			Employe tupleEmploye = new Employe(nom, prenom, adresse, codePostal, ville, age, contrat, pseudo, password);
			// Ajout de l'employe dans la table
			employes.creer(tupleEmploye);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Supprime employe de la base de données.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void supprime(int idEmploye) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			Employe tupleEmploye = employes.getEmploye(idEmploye);
			if (tupleEmploye == null)
				throw new AgencyException("Employe inexistant");
			
			// Suppression de l'employe
			boolean testExiste = employes.supprimer(tupleEmploye);	//regarder si lasuppression encascade sinon il faut supprimer dans les listes
			
			if (testExiste == false)
				throw new AgencyException("Employe " + tupleEmploye + " inexistant");

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Affichage d'un Employe
	 * 
	 * @throws AgencyException,Exception
	 */
	public Employe affichageEmploye(int idEmploye) throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			Employe tupleEmploye = employes.getEmploye(idEmploye);
			if (tupleEmploye == null)
				throw new AgencyException("Employe inexistant");
			
			
			System.out.println(tupleEmploye.toString());
			
			// Commit
			cx.commit();
			return tupleEmploye;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
	/**
	 * Affichage d'un Employe
	 * 
	 * @throws AgencyException,Exception
	 */
	public Employe getEmployeByPseudo(String pseudo) throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			Employe tupleEmploye = employes.getEmployebyPseudo(pseudo);
			if (tupleEmploye == null)
				throw new AgencyException("Employe inexistant");
			
			
			System.out.println(tupleEmploye.toString());
			
			// Commit
			cx.commit();
			return tupleEmploye;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
	/**
	 * Affichage de la liste des Employes
	 * 
	 * @throws AgencyException,Exception
	 */
	public List<Employe> getEmployes() throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			List<Employe> Employes = employes.getListeEmployes();
			if (Employes == null)
				throw new AgencyException("Employe inexistante: ");
			
			System.out.println(Employes.toString());
			
			// Commit
			cx.commit();
			return Employes;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
}
