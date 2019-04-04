package gestionnaire;

import java.util.List;
import connexionBD.*;
import model.*;
import table.*;


public class GestionnaireActivite {


	private TableActivite activites;
	private TableOccupation occupations;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireActivite(TableActivite activites, TableOccupation occupations)throws AgencyException {
		this.cx = activites.getConnexion();
		if (activites.getConnexion() == occupations.getConnexion()) { 
			this.occupations = occupations;
			this.activites = activites;
		} else {
			throw new AgencyException(
					"Les instances d'activite et d'occupation n'utilisent pas la même connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'une nouvelle activite dans la base de données. Si elle existe déjà ,
	 * une exception est levée.
	 * 
	 * @throws AgencyException,
	 *             Exception
	 */
	public void ajouter(String nomActivite) throws AgencyException, Exception {

		try {
			// Vérifie si l activite existe déjà
			cx.demarreTransaction();
			if (activites.existe(nomActivite))
				throw new AgencyException("Cette activite existe deja ");
			System.out.println("je suis la "+nomActivite);
			Activite tupleActivite = new Activite(nomActivite);
			
			// Ajout de l activite dans la table des activites
			activites.creer(tupleActivite);
			System.out.println("je suis la maintenant "+nomActivite);
			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Supprime Activite de la base de données.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void supprime(String nomActivite) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			Activite tupleActivite = activites.getActivite(nomActivite);
			System.out.println("sup1");
			if (tupleActivite == null)
				throw new AgencyException("Activite inexistant: " + tupleActivite);
			if (tupleActivite.isActive())
				throw new AgencyException("Activite " + nomActivite + "est encore liés à des occupations");

			// Suppression de l'activite
			//cx.getConnection().remove(tupleActivite);
			boolean testExiste = activites.supprimer(tupleActivite);
			System.out.println("sup2");
			if (testExiste == false)
				throw new AgencyException("Equipe " + nomActivite + " inexistante");
			
			/*List<Occupation> listOccupationActivite = occupations.getOccupationsActivite(tupleActivite.getIdActivite());
			for(Occupation occupation :listOccupationActivite) {
				occupations.supprimer(occupation);			//nécessaire si cascade ?
			}*/

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Affichage d'une activite, de ses occupations
	 * 
	 * @throws AgencyException,Exception
	 */
	public void affichageActivite(String nomActivite) throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			Activite tupleActivite = activites.getActivite(nomActivite);
			if (tupleActivite == null)
				throw new AgencyException("Activite inexistante: " + nomActivite);
			System.out.println(tupleActivite.toString());

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Lecture des occupations d'une activite (voir des lieus)
	 * 
	 * @throws AgencyException, Exception
	 */
	public List<Occupation> lectureOcccupationsActivite(String nomActivite) throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			Activite tupleActivite = activites.getActivite(nomActivite);
			if (tupleActivite == null)
				throw new AgencyException("Activite inexistante : " + nomActivite);

			List<Occupation> listOccupations = occupations.getOccupationsActivite(tupleActivite.getIdActivite());

			// Commit
			cx.commit();
			return listOccupations;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Affichage de l'ensemble des activites de la table.
	 * 
	 * @throws AgencyException, Exception
	 */
	 public List<Activite> affichageActivites() throws AgencyException, Exception {
		
		cx.demarreTransaction();

		List<Activite> list = activites.getListeActivites();

		for (Activite a : list) {
			System.out.println(a.toString());
		}
		
		cx.commit();
		return list;

	}

}