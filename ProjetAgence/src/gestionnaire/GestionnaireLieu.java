package gestionnaire;

import java.util.List;
import connexionBD.*;
import model.*;
import table.*;

public class GestionnaireLieu {
	
	private TableLieu lieus;
	private TableOccupation occupations;
	private TableAVisite aVisites;
	private TableOffreVoyage offresVoyage;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireLieu(TableLieu lieus, TableOccupation occupations, TableAVisite aVisites, TableOffreVoyage offresVoyage)throws AgencyException {
		this.cx = lieus.getConnexion();
		if (lieus.getConnexion() == occupations.getConnexion() && lieus.getConnexion() == aVisites.getConnexion() 
				&& lieus.getConnexion() == offresVoyage.getConnexion()) { 
			this.lieus = lieus;
			this.occupations = occupations;
			this.aVisites = aVisites;
			this.offresVoyage = offresVoyage;
		} else {
			throw new AgencyException(
					"Les instances de lieu, d'occupation, d'offreVoyage et/ou de AVisite n'utilisent pas la même connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'un nouveau lieu dans la base de données. Si elle existe déjà ,
	 * une exception est levée.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void ajouter(String nomLieu, String pays) throws AgencyException, Exception {

		try {
			cx.demarreTransaction();
			// Vérifie si le lieu existe déjà

			if (lieus.existe(nomLieu,pays))
				throw new AgencyException("Ce lieu existe deja veuillez selectionner d'autres informations ");

			Lieu tupleLieu = new Lieu(nomLieu,pays);
			
			// Ajout de le lieu dans la table des lieus
			lieus.creer(tupleLieu);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Supprime le lieu de la base de données.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void supprime(String nomLieu, String pays) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			Lieu tupleLieu = lieus.getLieu(nomLieu, pays);
			if (tupleLieu == null)
				throw new AgencyException("Lieu inexistant: " + tupleLieu);
			/*if (!tupleLieu.isActive())
				throw new AgencyException("Lieu " + nomLieu + "est encore liés à des occupations");*/

			// Suppression deu lieu
			boolean testExiste = lieus.supprimer(tupleLieu);
			
			if (testExiste == false)
				throw new AgencyException("Lieu " + nomLieu + " inexistante");
			
			/*List<Occupation> listOccupationActivite = occupations.getOccupationsActivite(tupleLieu.getIdLieu());
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
	 * Affichage d'un Lieu, de ses occupations
	 * 
	 * @throws AgencyException,Exception
	 */
	public Lieu affichageLieu(String nomLieu, String pays) throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			Lieu tupleLieu = lieus.getLieu(nomLieu, pays);
			if (tupleLieu == null)
				throw new AgencyException("Lieu inexistant: " + nomLieu);
			System.out.println(tupleLieu.toString());

			// Commit
			cx.commit();
			
			return tupleLieu;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Lecture des occupations d'un lieu (voir des activite)
	 * 
	 * @throws AgencyException, Exception
	 */
	public List<Occupation> lectureOcccupationsLieu(String nomLieu, String pays) throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			Lieu tupleLieu = lieus.getLieu(nomLieu, pays);
			if (tupleLieu == null)
				throw new AgencyException("Lieu inexistante : " + nomLieu);

			List<Occupation> listOccupations = occupations.getOccupationsLieu(tupleLieu.getIdLieu());

			// Commit
			cx.commit();
			return listOccupations;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
}
