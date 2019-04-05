package model;

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
public class Activite {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int idActivite;
	private String nomActivite;
	private String description;
	
	/*@OneToMany( mappedBy = "activite", cascade =  CascadeType.ALL )
	private List< Occupation > listeOccupations;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Lieu lieu;
	
	public Activite(String nomActivite, String description, Lieu lieu) {
		super();
		this.nomActivite = nomActivite;
		this.description = description;
		this.lieu = lieu;
		//this.listeOccupations = new LinkedList<Occupation>();
		
	}
	
	public Activite() {
		super();
	
	}
	
	public int getIdActivite() {
		return idActivite;
	}
	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}
	public String getNomActivite() {
		return nomActivite;
	}
	public void setNomActivite(String nomActivite) {
		this.nomActivite = nomActivite;
	}

	/*public List<Occupation> getListeOccupations() {
		return listeOccupations;
	}

	public void setListeOccupations(List<Occupation> listeOccupations) {
		this.listeOccupations = listeOccupations;
	}*/
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	
	/**
	 * Ajoute une occupation à la liste de occupations d'une activite
	 * @param occupation
	 */
	/*public void ajouterOccupation(Occupation occupation) {
		listeOccupations.add(occupation);
	}*/
	
	/**
	 * Supprime une occupation de la liste de occupations d'une activite
	 * @param occupation
	 */
	/*public void supprimerOccupation(Occupation occupation) {
		listeOccupations.remove(occupation);
	}/*

	/**
	 * regarde si cette activite possede une liste d'occupation
	 * @return
	 */
	/*public boolean isActive() {
		boolean testIsActive = true;
		if(this.getListeOccupations().size() == 0) {
			testIsActive = false;
		}
		return testIsActive;
	}*/
	
	@Override
	public String toString() {
		return "Activite [idActivite=" + idActivite + ", nomActivite=" + nomActivite + ", description=" + description + "]";
	}

	
}
