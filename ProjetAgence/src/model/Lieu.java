package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Lieu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idLieu;
	private String nomLieu;
	private String pays;
	
	@OneToMany( mappedBy = "lieu", cascade =  CascadeType.ALL )
	private List< OffreVoyage > listeOffresVoyage;
	
	@OneToMany( mappedBy = "lieu", cascade =  CascadeType.ALL )
	private List< Occupation > listeOccupations;
	
	@OneToMany( mappedBy = "lieu", cascade =  CascadeType.ALL )
	private List< AVisite > listeAVisite;
	
	public Lieu(String nomLieu, String pays) {
		super();
		this.nomLieu = nomLieu;
		this.pays = pays;
		this.listeOffresVoyage = new LinkedList<OffreVoyage>();
		this.listeOccupations = new LinkedList<Occupation>();
		this.listeAVisite = new LinkedList<AVisite>();
	}
	
	public Lieu() {
		super();
	
	}
	public int getIdLieu() {
		return idLieu;
	}
	public void setIdLieu(int idLieu) {
		this.idLieu = idLieu;
	}
	public String getNomLieu() {
		return nomLieu;
	}
	public void setNomLieu(String nomLieu) {
		this.nomLieu = nomLieu;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}

	public List<OffreVoyage> getListeOffresVoyage() {
		return listeOffresVoyage;
	}

	public void setListeOffresVoyage(List<OffreVoyage> listeOffresVoyage) {
		this.listeOffresVoyage = listeOffresVoyage;
	}

	public List<Occupation> getListeOccupations() {
		return listeOccupations;
	}

	public void setListeOccupations(List<Occupation> listeOccupations) {
		this.listeOccupations = listeOccupations;
	}

	public List<AVisite> getListeAVisite() {
		return listeAVisite;
	}

	public void setListeAVisite(List<AVisite> listeAVisite) {
		this.listeAVisite = listeAVisite;
	}
	
	/**
	 * Ajoute une offre de Voyage à la liste des offres de Voyage d'un Lieu
	 * @param offreVoyage
	 */
	public void ajouterOffreVoyage(OffreVoyage offreVoyage) {
		listeOffresVoyage.add(offreVoyage);
	}
	
	/**
	 * Supprime une offre de Voyage de la liste des offres de Voyage d'un Lieu
	 * @param offreVoyage
	 */
	public void supprimerOffreVoyage(OffreVoyage offreVoyage) {
		listeOffresVoyage.remove(offreVoyage);
	}
	
	/**
	 * Ajoute une occupation à la liste des occupations d'un lieu
	 * @param occupation
	 */
	public void ajouterOccupation(Occupation occupation) {
		listeOccupations.add(occupation);
	}
	
	/**
	 * Supprime un occupation de la liste de occupations d'une lieu
	 * @param occupation
	 */
	public void supprimerOccupation(Occupation occupation) {
		listeOccupations.remove(occupation);
	}
	
	/**
	 * Ajoute une chose a visiter à la liste des choses a visiter d'un lieu
	 * @param aVisite
	 */
	public void ajouterAVisite(AVisite aVisite) {
		listeAVisite.add(aVisite);
	}
	
	/**
	 * Supprime une chose a visiter de la liste des choses a visiter d'un lieu
	 * @param aVisite
	 */
	public void supprimerAVisite(AVisite aVisite) {
		listeAVisite.remove(aVisite);
	}

	@Override
	public String toString() {
		return "Lieu [idLieu=" + idLieu + ", nomLieu=" + nomLieu + ", pays=" + pays + ", listeOffresVoyage="
				+ listeOffresVoyage + ", listeOccupations=" + listeOccupations + ", listeAVisite=" + listeAVisite + "]";
	}
}
