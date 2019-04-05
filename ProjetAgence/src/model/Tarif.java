package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tarif {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTarif;
	private float prixUnitaire;
	
	@ManyToOne(fetch = FetchType.LAZY) // cascade = CascadeType.PERSIST 
	private OffreVoyage offreVoyage;
	 
	@ManyToOne(fetch = FetchType.LAZY)
	private Categorie categorie ;
	
	public Tarif() {
		super();
	}
	
	public Tarif(float prixUnitaire, OffreVoyage offreVoyage, Categorie categorie) {
		super();
		this.prixUnitaire = prixUnitaire;
		this.offreVoyage = offreVoyage;
		this.categorie = categorie;
	}
	
	public Tarif() {
		super();
		
	}
	
	public int getIdTarif() {
		return idTarif;
	}
	public void setIdTarif(int idTarif) {
		this.idTarif = idTarif;
	}
	public float getPrixUnitaire() {
		return prixUnitaire;
	}
	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public OffreVoyage getOffreVoyage() {
		return offreVoyage;
	}

	public void setOffreVoyage(OffreVoyage offreVoyage) {
		this.offreVoyage = offreVoyage;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Tarif [idTarif=" + idTarif + ", prixUnitaire=" + prixUnitaire +"]";
	}
}
