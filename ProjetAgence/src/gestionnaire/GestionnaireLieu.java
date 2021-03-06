package gestionnaire;

import java.util.List;
import connexionBD.*;
import model.*;
import table.*;

public class GestionnaireLieu {
	
	private TableLieu lieus;
	//private TableOccupation occupations;
	private TableAVisite aVisites;
	private TableOffreVoyage offresVoyage;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireLieu(TableLieu lieus, TableAVisite aVisites, TableOffreVoyage offresVoyage)throws AgencyException {
		this.cx = lieus.getConnexion();
		if ( lieus.getConnexion() == aVisites.getConnexion() 
				&& lieus.getConnexion() == offresVoyage.getConnexion()) { 
			this.lieus = lieus;
			this.aVisites = aVisites;
			this.offresVoyage = offresVoyage;
		} else {
			throw new AgencyException(
					"Les instances de lieu, d'occupation, d'offreVoyage et/ou de AVisite n'utilisent pas la m�me connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'un nouveau lieu dans la base de donn�es. Si elle existe d�j�,
	 * une exception est lev�e.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void ajouter(String nomLieu, String pays) throws AgencyException, Exception {

		try {
			cx.demarreTransaction();
			// V�rifie si le lieu existe d�j�

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
	 * Supprime le lieu de la base de donn�es.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void supprime(int idLieu) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			Lieu tupleLieu = lieus.getLieubyId(idLieu);
			if (tupleLieu == null)
				throw new AgencyException("Lieu inexistant: " + tupleLieu);
			/*if (!tupleLieu.isActive())
				throw new AgencyException("Lieu " + nomLieu + "est encore li�s � des occupations");*/

			// Suppression deu lieu
			boolean testExiste = lieus.supprimer(tupleLieu);
			
			if (testExiste == false)
				throw new AgencyException("Lieu inexistante");
			
			/*List<Occupation> listOccupationActivite = occupations.getOccupationsActivite(tupleLieu.getIdLieu());
			for(Occupation occupation :listOccupationActivite) {
				occupations.supprimer(occupation);			//n�cessaire si cascade ?
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
	public Lieu affichageLieu(int idLieu) throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			Lieu tupleLieu = lieus.getLieubyId(idLieu);
			if (tupleLieu == null)
				throw new AgencyException("Lieu inexistant");
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
	 * Affichage la liste des choses a Visite
	 * 
	 * @throws AgencyException,Exception
	 */
	public List<Lieu> affichageLieus() throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			List<Lieu> listeLieus = lieus.getListeLieu();
			
			// Commit
			cx.commit();
			return listeLieus;
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
	/*public List<Occupation> lectureOcccupationsLieu(String nomLieu, String pays) throws AgencyException, Exception {
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
	}*/
}
