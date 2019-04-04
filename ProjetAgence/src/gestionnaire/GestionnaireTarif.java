package gestionnaire;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import table.TableCategorie;
import table.TableOffreVoyage;
import table.TableTarif;

public class GestionnaireTarif {

	private int idOffre;
	private int idCategorie;
	private float prixUnitaire;
	
	public GestionnaireTarif(TableTarif tableTarif, TableCategorie tableCategorie, TableOffreVoyage tableOffreVoyage) {
		// TODO Auto-generated constructor stub
	}
	public int getIdOffre() {
		return idOffre;
	}
	public void setIdOffre(int idOffre) {
		this.idOffre = idOffre;
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public float getPrixUnitaire() {
		return prixUnitaire;
	}
	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
}
