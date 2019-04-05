package gestionnaire;

import java.util.Date;
import java.util.List;

import connexionBD.*;
import model.*;
import table.*;

public class GestionnaireOffreVoyage {
	
	private TableOffreVoyage offreVoyages;
	private TableLieu lieus;
	private TableTarif tarifs;
	private TableReservation reservations;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireOffreVoyage(TableOffreVoyage offreVoyages, TableLieu lieus, TableTarif tarifs,TableReservation reservations)throws AgencyException {
		this.cx = offreVoyages.getConnexion();
		
		if (lieus.getConnexion() == offreVoyages.getConnexion() && lieus.getConnexion() == tarifs.getConnexion() && lieus.getConnexion() == reservations.getConnexion()) { 
			this.offreVoyages = offreVoyages;
			this.lieus = lieus;
			this.tarifs = tarifs;
			this.reservations = reservations;
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
	public void ajouter(String description, Lieu lieu, String dateDebut, String dateFin, int nbPlacesRestantes) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// V�rifie si l'offre de voyage existe d�j�

			/*if (offreVoyages.existeByContent(description, lieu))
				throw new AgencyException("Cette offre de Voyage existe deja ");*/
			
			Lieu tupleLieu = lieus.getLieubyId(lieu.getIdLieu());
			if (tupleLieu == null)
				throw new AgencyException("Ce lieu n'existe pas ");
			
			OffreVoyage tupleOffreVoyage = new OffreVoyage(description, lieu, dateDebut, dateFin, nbPlacesRestantes);
			tupleLieu.ajouterOffreVoyage(tupleOffreVoyage);
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
			cx.demarreTransaction();
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
			cx.demarreTransaction();
			OffreVoyage tupleOffreVoyage = offreVoyages.getOffreVoyage(idOffreVoyage);
			/*if (tupleOffreVoyage == null)
				throw new AgencyException("Offre Voyage inexistante: " + idOffreVoyage);
			System.out.println(tupleOffreVoyage.toString());*/
			
			// Commit
			cx.commit();
			return tupleOffreVoyage;
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
	public List<OffreVoyage> getOffresVoyages() throws AgencyException, Exception {
		// Validation
		try {
			cx.demarreTransaction();
			System.out.println("gestionnaireOfreVoyage getOffresVoyages");
			List<OffreVoyage> OffresVoyages = offreVoyages.getListeOffresVoyage();
			if (OffresVoyages == null)
				throw new AgencyException("Offre Voyage inexistante: ");
			
			System.out.println("after getOffresVoyages");
			//System.out.println(OffresVoyages.toString());
			
			// Commit
			cx.commit();
			return OffresVoyages;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
	/**
	 * Affichage d'une liste de reservation d'une offre de Voyage
	 * 
	 * @throws AgencyException,Exception
	 */
	public List<Reservation> getReservationsOffresVoyage(int idOffre) throws AgencyException, Exception {
		// Validation
				try {
					cx.demarreTransaction();
					List<Reservation>  listeOffreVoyages = reservations.getReservationsOffre(idOffre);
					cx.commit();
					return listeOffreVoyages;
				} catch (Exception e) {
					cx.rollback();
					throw e;
				}
	}
	
	/**
	 * Affichage du nombre de place restante pour un projete offre de voyage
	 * @throws AgencyException,Exception
	 */
	public int getNbPlaceRestante(int idOffre) throws AgencyException, Exception {
		// Validation
				try {
					cx.demarreTransaction();
					OffreVoyage tupleOffreVoyage = offreVoyages.getOffreVoyage(idOffre);
					int nbPlacesRestantes = tupleOffreVoyage.getNbPlacesRestantes();
					int compteur =0;
					List<Reservation>  listeOffreVoyages = reservations.getReservationsOffre(idOffre);
					for(Reservation res : listeOffreVoyages) {
						for(Participant p : res.getListeParticipants()) {
							compteur = compteur + p.getNombreParticipants();
						}
					}
					nbPlacesRestantes=nbPlacesRestantes-compteur;
					cx.commit();
					return nbPlacesRestantes;
				} catch (Exception e) {
					cx.rollback();
					throw e;
				}
	}
}
