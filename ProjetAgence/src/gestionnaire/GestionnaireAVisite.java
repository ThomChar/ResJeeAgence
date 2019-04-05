package gestionnaire;

import java.util.List;
import connexionBD.*;
import model.*;
import table.*;

public class GestionnaireAVisite {

	private TableLieu lieus;
	private TableAVisite aVisites;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireAVisite(TableLieu lieus, TableAVisite aVisites )throws AgencyException {
		this.cx = aVisites.getConnexion();
		if (lieus.getConnexion() == aVisites.getConnexion()) { 
			this.lieus = lieus;
			this.aVisites = aVisites;
		} else {
			throw new AgencyException(
					"Les instances de lieu, d'occupation, d'offreVoyage et/ou de AVisite n'utilisent pas la m�me connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'une nouvelle chose aVisite dans la base de donn�es. Si elle existe d�j�,
	 * une exception est lev�e.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void ajouter(String libelle, String description, Lieu lieu) throws AgencyException, Exception {

		try {
			// V�rifie si la chose a visite existe d�j�
			cx.demarreTransaction();
			if (aVisites.existe(libelle, lieu))
				throw new AgencyException("Cette chose a visiter existe deja ");
			
			if (!lieus.existe(lieu.getNomLieu(), lieu.getPays()))
				throw new AgencyException("Le lieu selectionne n'existe pas");
				
			AVisite tupleAVisite = new AVisite(libelle, description, lieu);
			
			// Ajout de le chose avisite dans la table des choses a visite
			aVisites.creer(tupleAVisite);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Supprime la chose � visiter de la base de donn�es.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void supprime(int idAVisite) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			AVisite typleAVisite = aVisites.getAVisiteById(idAVisite);
			if (typleAVisite == null)
				throw new AgencyException("Chose a visiter inexistante: " + typleAVisite);
			/*if (!tupleLieu.isActive())
				throw new AgencyException("Lieu " + nomLieu + "est encore li�s � des occupations");*/

			// Suppression d'une chose a visite
			boolean testExiste = aVisites.supprimer(typleAVisite);
			
			if (testExiste == false)
				throw new AgencyException("Chose a visite inexistante");
			
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
	 * Affichage d'un Lieu, de ses choses � vister
	 * 
	 * @throws AgencyException,Exception
	 */
	public void affichageLieuAVisites(int idLieu) throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			Lieu tupleLieu = lieus.getLieubyId(idLieu);
			if (tupleLieu == null)
				throw new AgencyException("Lieu inexistante");
			System.out.println(tupleLieu.toString());

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Lecture des choses � visiter d'un lieu (voir des activite)
	 * 
	 * @throws AgencyException, Exception
	 */
	public List<AVisite> lectureLieusAVisite(int idLieu) throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			Lieu tupleLieu = lieus.getLieubyId(idLieu);
			if (tupleLieu == null)
				throw new AgencyException("Lieu inexistante ");

			List<AVisite> listAVisites = aVisites.getLieuAVisites(tupleLieu.getIdLieu());

			// Commit
			cx.commit();
			return listAVisites;
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
	public List<AVisite> affichageAVisites() throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			List<AVisite> listeAVisites = aVisites.getListeAVisites();
			
			// Commit
			cx.commit();
			return listeAVisites;
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
	public AVisite affichageAVisite(int idAVisite) throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			AVisite tupeAVisite = aVisites.getAVisiteById(idAVisite);
			if (tupeAVisite == null)
				throw new AgencyException("Chose a visite inexistante");

			// Commit
			cx.commit();
			return tupeAVisite;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
}
