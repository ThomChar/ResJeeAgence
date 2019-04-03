package gestionnaire;

import connexionBD.*;
import model.*;
import table.*;

public class GestionnaireOffreVoyage {
	
	private TableOffreVoyage offreVoyages;
	private TableLieu lieus;
	private TableTarif tarifs;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireOffreVoyage(TableOffreVoyage offreVoyages, TableLieu lieus, TableTarif tarifs)throws AgencyException {
		this.cx = offreVoyages.getConnexion();
		
		if (lieus.getConnexion() == tarifs.getConnexion() && lieus.getConnexion() == tarifs.getConnexion()) { 
			this.offreVoyages = offreVoyages;
			this.lieus = lieus;
			this.tarifs = tarifs;
		} else {
			throw new AgencyException(
					"Les instances de lieu, de tarif et d'offre de voyage n'utilisent pas la m�me connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'une nouvelle offre de voyage dans la base de donn�es. Si elle existe d�j�,
	 * une exception est lev�e.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void ajouter(String description, Lieu lieu) throws AgencyException, Exception {

		try {
			// V�rifie si l'offre de voyage existe d�j�

			if (offreVoyages.existeByContent(description, lieu))
				throw new AgencyException("Cette offre de Voyage existe deja ");
			
			Lieu tupleLieu = lieus.getLieubyId(lieu.getIdLieu());
			if (tupleLieu == null)
				throw new AgencyException("Cette offre de Voyage existe deja ");
			
			OffreVoyage tupleOffreVoyage = new OffreVoyage(description, lieu);
			
			// Ajout de l'offre de voyage dans la table
			offreVoyages.creer(tupleOffreVoyage);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Supprime offre de Voyage de la base de donn�es.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void supprime(int idOffreVoyage) throws AgencyException, Exception {
		try {
			
			// Validation
			OffreVoyage tupleOffreVoyage = offreVoyages.getOffreVoyage(idOffreVoyage);
			if (tupleOffreVoyage == null)
				throw new AgencyException("OffreVoyage inexistante : " + tupleOffreVoyage);
			
			// Suppression de l'offre de Voyage
			boolean testExiste = offreVoyages.supprimer(tupleOffreVoyage);	//regarder si lasuppression encascade sinon il faut supprimer dans les listes
			
			if (testExiste == false)
				throw new AgencyException("OffreVoyage " + tupleOffreVoyage + " inexistante");

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Affichage d'une offre de Voyage
	 * 
	 * @throws AgencyException,Exception
	 */
	public OffreVoyage affichageOffreVoyage(int idOffreVoyage) throws AgencyException, Exception {
		
		// Validation
		try {
			OffreVoyage tupleOffreVoyage = offreVoyages.getOffreVoyage(idOffreVoyage);
			if (tupleOffreVoyage == null)
				throw new AgencyException("Offre Voyage inexistante: " + tupleOffreVoyage);
			System.out.println(tupleOffreVoyage.toString());
			
			// Commit
			cx.commit();
			return tupleOffreVoyage;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
}