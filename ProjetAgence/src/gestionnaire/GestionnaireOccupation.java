package gestionnaire;

import connexionBD.*;
import model.*;
import table.*;

public class GestionnaireOccupation {

	private TableActivite activites;
	private TableLieu lieus;
	private TableOccupation occupations;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireOccupation(TableActivite activites, TableLieu lieus, TableOccupation occupations)throws AgencyException {
		this.cx = occupations.getConnexion();
		
		if (activites.getConnexion() == occupations.getConnexion() && lieus.getConnexion() == occupations.getConnexion()) { 
			this.occupations = occupations;
			this.activites = activites;
			this.lieus = lieus;
		} else {
			throw new AgencyException(
					"Les instances d'activite, de lieu et d'occupation n'utilisent pas la même connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'une nouvelle occupation dans la base de données. Si elle existe déjà ,
	 * une exception est levée.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void ajouter(int idLieu, int idActivite) throws AgencyException, Exception {

		try {
			// Vérifie si l occupation existe déjà

			if (occupations.existe(idLieu, idActivite))
				throw new AgencyException("Cette occupations existe deja pour ce Lieu ");

			Activite tupleActivite = activites.getActiviteById(idActivite);
			Lieu tupleLieu = lieus.getLieubyId(idLieu);
			Occupation tupleOccupation = new Occupation(tupleLieu, tupleActivite);
			
			// Ajout de l activite dans la table des activites
			occupations.creer(tupleOccupation);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Supprime occupation de la base de données.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void supprime(int idLieu, int idActivite) throws AgencyException, Exception {
		try {
			
			// Validation
			Occupation tupleOccupation = occupations.getOccupation(idLieu, idActivite);
			if (tupleOccupation == null)
				throw new AgencyException("Occupation inexistant: " + tupleOccupation);
			
			// Suppression de l'occupation
			boolean testExiste = occupations.supprimer(tupleOccupation);	//regarder si lasuppression encascade sinon il faut supprimer dans les listes
			
			if (testExiste == false)
				throw new AgencyException("Occupation " + tupleOccupation + " inexistante");

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Affichage d'une occupation
	 * 
	 * @throws AgencyException,Exception
	 */
	public Occupation affichageOccupation(int idLieu, int idActivite) throws AgencyException, Exception {
		
		// Validation
		try {
			Occupation tupleOccupation = occupations.getOccupation(idLieu, idActivite);
			if (tupleOccupation == null)
				throw new AgencyException("Occupation inexistante: " + tupleOccupation);
			System.out.println(tupleOccupation.toString());
			
			// Commit
			cx.commit();
			return tupleOccupation;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

}
