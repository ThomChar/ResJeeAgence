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
	
	@OneToMany( mappedBy = "categorie", cascade =  CascadeType.PERSIST ) //cascade = CascadeType.ALL
	  private List< Tarif > listeTarifs;
	
	public Categorie(String nomCategorie, Lieu lieu) {
		super();
		this.nomCategorie = nomCategorie;
		this.listeTarifs = new LinkedList<Tarif>();
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

	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", listeTarifs="
				+ listeTarifs + "]";
	}
	
}
