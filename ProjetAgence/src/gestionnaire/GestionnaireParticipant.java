package gestionnaire;

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
			this.participants = participants;
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
	 * Supprime occupation de la base de données.
	 * 
	 * @throws AgencyException, Exception
	 */
	/*public void supprime(int idLieu, int idActivite) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
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
	}*/

	/**
	 * Affichage d'une occupation
	 * 
	 * @throws AgencyException,Exception
	 */
	/*public Occupation affichageOccupation(int idLieu, int idActivite) throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
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
	}*/
}
