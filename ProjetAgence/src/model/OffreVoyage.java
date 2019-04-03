package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OffreVoyage {
	
	@Id
    @GeneratedValue
	private int idOffreVoyage;
	private String description;
	//private int idLieu;
	
	@OneToMany( mappedBy = "offreVoyage", cascade =  CascadeType.PERSIST )
	  private List< Tarif > listeTarifs;
	
	@ManyToOne(fetch = FetchType.LAZY) // cascade = CascadeType.PERSIST 
	private Lieu lieu;
	
	public OffreVoyage(String description, Lieu lieu) {
		super();
		this.description = description;
		this.listeTarifs = new LinkedList<Tarif>();
		this.lieu = lieu;
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

	@Override
	public String toString() {
		return "OffreVoyage [idOffre=" + idOffreVoyage + ", description=" + description + ", listeTarifs=" + listeTarifs
				+ ", lieu=" + lieu + "]";
	}
}
