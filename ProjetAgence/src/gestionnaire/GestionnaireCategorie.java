package gestionnaire;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import table.TableCategorie;
import table.TableTarif;

/*import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;*/

public class GestionnaireCategorie {

	private int idCategorie;
	private String nomCategorie;
	
	public GestionnaireCategorie(TableCategorie tableCategorie, TableTarif tableTarif) {
		// TODO Auto-generated constructor stub
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
	
}
