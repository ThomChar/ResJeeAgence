package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Occupation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idOccupation;
	//private int idLieu;
	//private int idAcivite;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Lieu lieu;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Activite activite;
	
	
	public Occupation(Lieu lieu, Activite activite) {
		super();
		this.lieu = lieu;
		this.activite = activite;
	}
	
	public Occupation() {
		super();
	}
	
	public int getIdOccupation() {
		return idOccupation;
	}
	public void setIdOccupation(int idOccupation) {
		this.idOccupation = idOccupation;
	}
	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public Activite getActivite() {
		return activite;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
	}

	@Override
	public String toString() {
		return "Occupation [idOccupation=" + idOccupation + ", lieu=" + lieu + ", activite=" + activite + "]";
	}
}
