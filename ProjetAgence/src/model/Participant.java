package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Participant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idParticipant;
	private int nombreParticipants;
	
	@ManyToOne(fetch = FetchType.LAZY) // cascade = CascadeType.PERSIST 
	private Categorie categorie;
	
	@ManyToOne(fetch = FetchType.LAZY) // cascade = CascadeType.PERSIST 
	private Reservation reservation;
	
	public Participant(int nombreParticipants, Categorie categorie, Reservation reservation) {
		super();
		this.nombreParticipants = nombreParticipants;
		this.categorie = categorie;
		this.reservation = reservation;
		
	}
	
	public Participant() {
		super();
		
	}

	public int getIdParticipant() {
		return idParticipant;
	}

	public void setIdParticipant(int idParticipant) {
		this.idParticipant = idParticipant;
	}

	public int getNombreParticipants() {
		return nombreParticipants;
	}

	public void setNombreParticipants(int nombreParticipants) {
		this.nombreParticipants = nombreParticipants;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Participant [idParticipant=" + idParticipant + ", nombreParticipants=" + nombreParticipants
				+ ", categorie=" + categorie + ", reservation=" + reservation + "]";
	}

}
