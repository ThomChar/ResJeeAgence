package table;

import java.util.List;

import javax.persistence.TypedQuery;

import connexionBD.Connexion;
import model.Categorie;
import model.Occupation;
import model.Participant;
import model.Reservation;

public class TableParticipant {
	private TypedQuery<Participant> stmtExiste;
	private TypedQuery<Participant> stmtExisteById;
	private TypedQuery<Participant> stmtListTousParticipants;
	private TypedQuery<Participant> stmtListTousParticipantsReservation;
	
	private Connexion cx;

	/**
	 * Creation d'une instance.
	 */
	public TableParticipant(Connexion cx) {
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery(
				"select p from Participant p where p.reservation.idReservation = :idReservation and p.categorie.idCategorie = :idCategorie",
				Participant.class);
		stmtExisteById = cx.getConnection().createQuery(
				"select p from Participant p where p.idParticipant = :idParticipant",
				Participant.class);
		stmtListTousParticipants = cx.getConnection().createQuery("select p from Participant p", Participant.class);
		stmtListTousParticipantsReservation = cx.getConnection().createQuery("select p from Participant p where p.reservation.idReservation = :idReservation", Participant.class);
		
	}

	/**
	 * Retourner la connexion associee.
	 */
	public Connexion getConnexion() {
		return cx;
	}

	/**
	 * Verifie si un participant existe.
	 * 
	 */
	public boolean existe(int idReservation, int idCategorie) {
		stmtExiste.setParameter("idReservation", idReservation);
		stmtExiste.setParameter("idCategorie", idCategorie);
		return !stmtExiste.getResultList().isEmpty();
	}
	
	/**
	 * Verifie si un participant existe.
	 * 
	 */
	public boolean existeById(int idParticipant) {
		stmtExisteById.setParameter("idParticipant", idParticipant);
		return !stmtExisteById.getResultList().isEmpty();
	}

	/**
	 * Recupere un participant correspondant au coupe idResrervation idCategorie.
	 * 
	 */
	public Participant getParticipant(int idReservation, int idCategorie) {
		stmtExiste.setParameter("idReservation", idReservation);
		stmtExiste.setParameter("idCategorie", idCategorie);
		List<Participant> participants = stmtExiste.getResultList();
		if (!participants.isEmpty()) {
			return participants.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Recupere les participants d'une reservation correspondant au idReservation.
	 * 
	 */
	public List<Participant> getParticipantsReservation(int idReservation) {
		stmtListTousParticipantsReservation.setParameter("idReservation", idReservation);
		List<Participant> participants = stmtListTousParticipantsReservation.getResultList();
		if (!participants.isEmpty()) {
			return participants;
		} else {
			return null;
		}
	}

	/**
	 * Ajout d'une nouveau participant non vide.
	 * 
	 */
	public Participant creer(Participant participant) {
		cx.getConnection().persist(participant);
		return participant;
	}

	/**
	 * Suppression d'un participant.
	 */
	public boolean supprimer(Participant participant) {
		if (participant != null) {
			cx.getConnection().remove(participant);
			return true;
		}
		return false;
	}
	
	/**
     * Retourne l'ensemble des participants de la base de données
     * @return
     */
    public List<Participant> getListeParticipants()
    {
        return stmtListTousParticipants.getResultList();
    }	
}
