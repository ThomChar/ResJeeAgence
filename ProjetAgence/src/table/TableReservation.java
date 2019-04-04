package table;

import java.util.List;

import javax.persistence.TypedQuery;

import connexionBD.Connexion;
import model.Categorie;
import model.Participant;
import model.Reservation;

public class TableReservation {
	private TypedQuery<Reservation> stmtExiste;
	private TypedQuery<Reservation> stmtExisteByContent;
	private TypedQuery<Reservation> stmtListToutesReservations;
	private TypedQuery<Reservation> stmtListReservationsOffre;
	
	private Connexion cx;


	/**
	 * Creation d'une instance.
	 */
	public TableReservation(Connexion cx) {
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery(
				"select r from Reservation r where r.idReservation = :idReservation",
				Reservation.class);
		stmtExisteByContent = cx.getConnection().createQuery(
				"select r from Reservation r where r.nom = :nom",
				Reservation.class);
		stmtListToutesReservations = cx.getConnection().createQuery("select r from Reservation r", Reservation.class);
		stmtListReservationsOffre = cx.getConnection().createQuery("select r from Reservation r where r.offreVoyage.idOffreVoyage = : idOffreVoyage", Reservation.class);
		
	}

	/**
	 * Retourner la connexion associee.
	 */
	public Connexion getConnexion() {
		return cx;
	}

	/**
	 * Verifie si une reservation existe.
	 * 
	 */
	public boolean existe(int idReservation) {
		stmtExiste.setParameter("idReservation", idReservation);
		return !stmtExiste.getResultList().isEmpty();
	}
	
	/**
	 * Verifie si une reservation existe.
	 * 
	 */
	public boolean existeByContent(String nomReservation) {
		stmtExisteByContent.setParameter("nom", nomReservation);
		return !stmtExisteByContent.getResultList().isEmpty();
	}

	/**
	 * Recupere une reservation correspondant au idReservation.
	 * 
	 */
	public Reservation getReservation(int idReservation) {
		stmtExiste.setParameter("idReservation", idReservation);
		List<Reservation> reservations = stmtExiste.getResultList();
		if (!reservations.isEmpty()) {
			return reservations.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Recupere les reservations d'une offre de voyage correspondant au idReservation.
	 * 
	 */
	public List<Reservation> getOccupationsActivite(int idReservation) {
		stmtListReservationsOffre.setParameter("idReservation", idReservation);
		List<Reservation> reservations = stmtListReservationsOffre.getResultList();
		if (!reservations.isEmpty()) {
			return reservations;
		} else {
			return null;
		}
	}

	/**
	 * Ajout d'une nouvelle reservation non vide.
	 * 
	 */
	public Reservation creer(Reservation reservation) {
		cx.getConnection().persist(reservation);
		return reservation;
	}

	/**
	 * Suppression d'une reservation.
	 */
	public boolean supprimer(Reservation reservation) {
		if (reservation != null) {
			cx.getConnection().remove(reservation);
			return true;
		}
		return false;
	}
	
	/**
     * Retourne l'ensemble des categories de la base de données
     * @return
     */
    public List<Reservation> getListeCategories()
    {
        return stmtListToutesReservations.getResultList();
    }	
}
