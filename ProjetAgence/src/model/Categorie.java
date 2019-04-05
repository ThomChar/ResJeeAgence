package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/*import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;*/

@Entity
public class Categorie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCategorie;
	private String nomCategorie;
	
	@OneToMany( mappedBy = "categorie", cascade =  CascadeType.ALL ) //cascade = CascadeType.ALL
	  private List< Tarif > listeTarifs;
	
	@OneToMany( mappedBy = "categorie", cascade =  CascadeType.ALL )
	  private List< Participant > listeParticipants;
	
	public Categorie(String nomCategorie) {
		super();
		this.nomCategorie = nomCategorie;
		this.listeTarifs = new LinkedList<Tarif>();
	}
	
	public Categorie() {
		super();
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public List<Tarif> getListeTarifs() {
		return listeTarifs;
	}

	public void setListeTarifs(List<Tarif> listeTarifs) {
		this.listeTarifs = listeTarifs;
	}
	
	/**
	 * Ajoute un tarif à la liste des tarifs d'une categorie
	 * @param tarif
	 */
	public void ajouterTarif(Tarif tarif) {
		listeTarifs.add(tarif);
	}
	
	/**
	 * Supprime un tarif de la liste de tarifs d'une categorie
	 * @param tarif
	 */
	public void supprimerTarif(Tarif tarif) {
		listeTarifs.remove(tarif);
	}
	
	/**
	 * Ajoute un participant à la liste des participants d'une categorie
	 * @param tarif
	 */
	public void ajouterParticipant(Participant participant) {
		listeParticipants.add(participant);
	}
	
	/**
	 * Supprime un participant de la liste de participants d'une categorie
	 * @param tarif
	 */
	public void supprimerParticipant(Participant participant) {
		listeParticipants.remove(participant);
	}

	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", listeTarifs="
				+ listeTarifs + "]";
	}

	public List<Participant> getListeParticipants() {
		return listeParticipants;
	}

	public void setListeParticipants(List<Participant> listeParticipants) {
		this.listeParticipants = listeParticipants;
	}
	
}
