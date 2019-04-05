package gestionnaire;

import java.util.List;

import connexionBD.AgencyException;
import connexionBD.Connexion;
import model.Activite;
import model.Categorie;
import model.Lieu;
import model.Occupation;
import model.Participant;
import model.Reservation;
import table.TableActivite;
import table.TableCategorie;
import table.TableLieu;
import table.TableOccupation;
import table.TableParticipant;
import table.TableReservation;

public class GestionnaireParticipant {

	
	private TableParticipant participants;
	private TableReservation reservations;
	private TableCategorie categories;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireParticipant(TableParticipant participants,TableReservation reservations,TableCategorie categories)throws AgencyException {
		this.cx = participants.getConnexion();
		
		if (participants.getConnexion() == reservations.getConnexion() && participants.getConnexion() == categories.getConnexion()) { 
			this.participants = participants;
			this.reservations = reservations;
			this.categories = categories;
		} else {
			throw new AgencyException(
					"Les instances de participants, de reservations et de categories n'utilisent pas la même connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'un nouveau participant dans la base de données. Si elle existe déjà ,
	 * une exception est levée.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void ajouter(int nombreParticipants, Categorie categorie, Reservation reservation) throws AgencyException, Exception {

		try {
			cx.demarreTransaction();
			// Vérifie si l occupation existe déjà

			if (!reservations.existe(reservation.getIdReservation()))
				throw new AgencyException("Cette reservation n'existe pas pour ce Participant ");
			if (!categories.existe(categorie.getIdCategorie()))
				throw new AgencyException("Cette categorie n'existe pas pour ce Participant ");
			
			Participant tupleParticipant = new Participant(nombreParticipants, categorie, reservation);
			
			// Ajout du participant dans la table des participants
			participants.creer(tupleParticipant);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Supprime participant de la base de données.
	 * 
	 * @throws AgencyException, Exception
	 */
	public void supprime(int idReservation,int idCategorie) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			Participant tupleParticipant = participants.getParticipant(idReservation, idCategorie);
			if (tupleParticipant == null)
				throw new AgencyException("Participant inexistant");
			
			// Suppression du participant
			boolean testExiste = participants.supprimer(tupleParticipant);	//regarder si lasuppression encascade sinon il faut supprimer dans les listes
			
			if (testExiste == false)
				throw new AgencyException("Occupation " + tupleParticipant + " inexistant");

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Affichage d'un participant
	 * 
	 * @throws AgencyException,Exception
	 */
	public Participant affichageParticipant(int idReservation,int idCategorie) throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			Participant tupleParticipant = participants.getParticipant(idReservation, idCategorie);
			if (tupleParticipant == null)
				throw new AgencyException("Participant inexistant ");
			System.out.println(tupleParticipant.toString());
			
			// Commit
			cx.commit();
			return tupleParticipant;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
	/**
	 * Affichage de la liste des participants
	 * 
	 * @throws AgencyException,Exception
	 */
	public List<Participant> affichageParticipants() throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			List<Participant> listeParticipants = participants.getListeParticipants();
						
			// Commit
			cx.commit();
			return listeParticipants;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
	
	/**
	 * Affichage de la liste des participants correspondant à un idReservation
	 * 
	 * @throws AgencyException,Exception
	 */
	public List<Participant> affichageParticipantsReservation(int idReservation) throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			Reservation tupleReservation = reservations.getReservation(idReservation);
			if (tupleReservation == null)
				throw new AgencyException("Reservation inexistant ");
			System.out.println(tupleReservation.toString());
			
			List<Participant> listeParticipants = participants.getParticipantsReservation(idReservation);
						
			// Commit
			cx.commit();
			return listeParticipants;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}
}
