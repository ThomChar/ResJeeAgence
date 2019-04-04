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
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idReservation;
	private String nom;
	private String prenom;
	private String email;
	private String tel;
	
	@OneToMany( mappedBy = "reservation", cascade =  CascadeType.ALL )
	  private List< Participant > listeParticipants;
	
	@ManyToOne(fetch = FetchType.LAZY) // cascade = CascadeType.PERSIST 
	private OffreVoyage offreVoyage;
	
	public Reservation(String nom, String prenom, String email, String tel, OffreVoyage offreVoyage) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.offreVoyage = offreVoyage;
		this.listeParticipants = new LinkedList<Participant>();
	}
	

	public int getIdReservation() {
		return idReservation;
	}



	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public List<Participant> getListeParticipants() {
		return listeParticipants;
	}



	public void setListeParticipants(List<Participant> listeParticipants) {
		this.listeParticipants = listeParticipants;
	}



	public OffreVoyage getOffreVoyage() {
		return offreVoyage;
	}



	public void setOffreVoyage(OffreVoyage offreVoyage) {
		this.offreVoyage = offreVoyage;
	}

	
	/**
	 * Ajoute un participants à la liste des participants d'une reservation
	 * @param tarif
	 */
	public void ajouterParticipant(Participant participant) {
		listeParticipants.add(participant);
	}
	
	/**
	 * Supprime un participant  de la liste des participants d'une reservation
	 * @param tarif
	 */
	public void supprimerParticipant(Participant participant) {
		listeParticipants.remove(participant);
	}


	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", tel=" + tel + ", listeParticipants=" + listeParticipants + ", offreVoyage=" + offreVoyage
				+ "]";
	}

}