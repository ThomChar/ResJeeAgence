package gestionnaire;

import java.util.List;
import connexionBD.*;
import model.*;
import table.*;


public class GestionnaireActivite {


	private TableActivite activites;
	private TableLieu lieus;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireActivite(TableActivite activites, TableLieu lieus)throws AgencyException {
		this.cx = activites.getConnexion();
		if (activites.getConnexion() == lieus.getConnexion()) { 
			this.lieus = lieus;
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
	public void ajouter(String nomActivite, String description, Lieu lieu) throws AgencyException, Exception {

		try {
			// Vérifie si l activite existe déjà
			if (lieus.existeById(lieu.getIdLieu()))
				throw new AgencyException("lieu inexistant: ");
			cx.demarreTransaction();
			Activite tupleActivite = new Activite(nomActivite,description, lieu);
			// Ajout de l activite dans la table des activites
			activites.creer(tupleActivite);
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
	public void supprime(int idActivite) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			Activite tupleActivite = activites.getActiviteById(idActivite);
			if (tupleActivite == null)
				throw new AgencyException("Activite inexistant: ");

			// Suppression de l'activite
			//cx.getConnection().remove(tupleActivite);
			boolean testExiste = activites.supprimer(tupleActivite);
			if (testExiste == false)
				throw new AgencyException("Activite inexistante");

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
	public void affichageActivite(int idActivite) throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			Activite tupleActivite = activites.getActiviteById(idActivite);
			if (tupleActivite == null)
				throw new AgencyException("Activite inexistante");

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
	public List<Activite> lectureActivitesLieu(int idLieu) throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			Lieu tuplelieu = lieus.getLieubyId(idLieu);
			if (tuplelieu == null)
				throw new AgencyException("lieu inexistante");

			List<Activite> listActivites = activites.getActivitesLieu(idLieu);

			// Commit
			cx.commit();
			return listActivites;
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
	 
	 /**
		 * Affichage de l'ensemble des activites de la table.
		 * 
		 * @throws AgencyException, Exception
		 */
		 public Activite getActivite(int idActivite) throws AgencyException, Exception {
			
			cx.demarreTransaction();

			Activite activite = activites.getActiviteById(idActivite);
			
			if (activite == null)
				throw new AgencyException("Activite inexistante");
			
			cx.commit();
			return activite;

		}

}