package gestionnaire;

import java.util.List;

import connexionBD.AgencyException;
import connexionBD.Connexion;
import model.Activite;
import model.Lieu;
import model.Occupation;
import model.OffreVoyage;
import model.Reservation;
import table.TableActivite;
import table.TableLieu;
import table.TableOccupation;
import table.TableOffreVoyage;
import table.TableParticipant;
import table.TableReservation;

public class GestionnaireReservation {
	
	private TableReservation reservations;
	private TableParticipant participants;
	private TableOffreVoyage offreVoyages;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireReservation(TableReservation tableReservation,TableOffreVoyage tableOffreVoyage, TableParticipant tableParticipant)throws AgencyException {
		this.cx = tableReservation.getConnexion();
		
		if (tableReservation.getConnexion() == tableParticipant.getConnexion() && tableReservation.getConnexion() == tableOffreVoyage.getConnexion()) { 
			this.reservations = tableReservation;
			this.participants = tableParticipant;
			this.offreVoyages = tableOffreVoyage;
		} else {
			throw new AgencyException(
					"Les instances d'activite, de lieu et d'occupation n'utilisent pas la même connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'une nouvelle reservation dans la base de données. Si elle existe déjà ,
	 * une exception est levée.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void ajouter(String nom, String prenom, String email, String tel, OffreVoyage offreVoyage) throws AgencyException, Exception {

		try {
			cx.demarreTransaction();
			// Vérifie si l occupation existe déjà

			if (reservations.existeByContent(nom, offreVoyage))
				throw new AgencyException("Cette occupations existe deja pour ce Lieu ");

			Reservation tupleReservation = new Reservation(nom, prenom, email, tel, offreVoyage);
			
			// Ajout de l activite dans la table des activites
			reservations.creer(tupleReservation);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Supprime reservation de la base de données.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void supprime(int idReservation) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			Reservation tupleReservation = reservations.getReservation(idReservation);
			if (tupleReservation == null)
				throw new AgencyException("reservation");
			
			// Suppression de l'occupation
			boolean testExiste = reservations.supprimer(tupleReservation);	//regarder si lasuppression encascade sinon il faut supprimer dans les listes
			
			if (testExiste == false)
				throw new AgencyException("Occupation " + tupleReservation + " inexistante");

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Affichage d'une reservation
	 * 
	 * @throws AgencyException,Exception
	 */
	public Reservation affichageReservation(int idReservation) throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			Reservation tupleReservation = reservations.getReservation(idReservation);
			if (tupleReservation == null)
				throw new AgencyException("reservation inexistante ");
			System.out.println(tupleReservation.toString());
			
			// Commit
			cx.commit();
			return tupleReservation;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
	/**
	 * Affichage d'une liste de reservation
	 * 
	 * @throws AgencyException,Exception
	 */
	public List<Reservation> affichageReservations() throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			List<Reservation>  listeReservations = reservations.getListeReservations();
			cx.commit();
			return listeReservations;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
	/**
	 * Affichage d'une liste de reservation
	 * 
	 * @throws AgencyException,Exception
	 */
	public List<Reservation> affichageReservationsOffre(int idOffre) throws AgencyException, Exception {
		
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
}
