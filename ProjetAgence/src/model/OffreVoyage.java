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
public class OffreVoyage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idOffreVoyage;
	private String description;
	private String dateDebut;
	private String dateFin;
	
	@OneToMany( mappedBy = "offreVoyage", cascade =  CascadeType.ALL )
	  private List< Tarif > listeTarifs;
	
	@OneToMany( mappedBy = "offreVoyage", cascade =  CascadeType.ALL )
	  private List< Reservation > listeReservations;
	
	@ManyToOne(fetch = FetchType.LAZY) // cascade = CascadeType.PERSIST 
	private Lieu lieu;
	
	public OffreVoyage(String description, Lieu lieu, String dateDebut, String dateFin) {
		super();
		this.description = description;
		this.listeTarifs = new LinkedList<Tarif>();
		this.listeReservations = new LinkedList<Reservation>();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.lieu = lieu;
	}
	
	public OffreVoyage() {
		super();
	
	}
	
	public int getIdOffre() {
		return idOffreVoyage;
	}
	public void setIdOffre(int idOffreVoyage) {
		this.idOffreVoyage = idOffreVoyage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Tarif> getListeTarifs() {
		return listeTarifs;
	}

	public void setListeTarifs(List<Tarif> listeTarifs) {
		this.listeTarifs = listeTarifs;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	
	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	/**
	 * Ajoute un tarif à la liste des tarifs d'une Offre de Voyage
	 * @param tarif
	 */
	public void ajouterTarif(Tarif tarif) {
		listeTarifs.add(tarif);
	}
	
	/**
	 * Supprime un tarif  de la liste des tarifs d'une Offre de Voyage
	 * @param tarif
	 */
	public void supprimerTarif(Tarif tarif) {
		listeTarifs.remove(tarif);
	}
	
	/**
	 * Ajoute une reservation à la liste des reservations d'une Offre de Voyage
	 * @param reservation
	 */
	public void ajouterReservation(Reservation reservation) {
		listeReservations.add(reservation);
	}
	
	
	/**
	 * Supprime un reservation  de la liste des reservations d'une Offre de Voyage
	 * @param reservation
	 */
	public void supprimerReservation(Reservation reservation) {
		listeReservations.remove(reservation);
	}



	/*@Override
	public String toString() {
		return "OffreVoyage [idOffreVoyage=" + idOffreVoyage + ", description=" + description + ", dateDebut="
				+ dateDebut + ", dateFin=" + dateFin + ", listeTarifs=" + listeTarifs + ", listeReservations="
				+ listeReservations + ", lieu=" + lieu.toString() + "]";
	}*/
	
	@Override
	public String toString() {
		return "OffreVoyage [idOffreVoyage=" + idOffreVoyage + ", description=" + description + ", dateDebut="
				+ dateDebut + ", dateFin=" + dateFin +", listeTarifs=" + listeTarifs + ", listeReservations="
						+ listeReservations + "]";
	}
}
