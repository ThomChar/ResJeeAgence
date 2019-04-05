package model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//import javax.persistence.CascadeType;
//import javax.persistence.OneToMany;

@Entity
public class AVisite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAVisite;
	private String libelle;
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Lieu lieu;
	
	public AVisite(String libelle,String description, Lieu lieu) {
		super();
		this.libelle = libelle;
		this.setDescription(description);
		this.lieu = lieu;
	}
	
	public int getIdAVisite() {
		return idAVisite;
	}
	public void setIdAVisite(int idAVisite) {
		this.idAVisite = idAVisite;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "AVisite [idAVisite=" + idAVisite + ", libelle=" + libelle + ", description=" + description + ", lieu=" + lieu + "]";
	}
	
}
