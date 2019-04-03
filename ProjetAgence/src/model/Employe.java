package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employe {

	@Id
    @GeneratedValue
	private int matricule;
	private String nom;
	private String prenom;
	private String adresse;
	private int codePostal;
	private String ville;
	private int age;
	private String contrat;
	/*public enum Contrat {
		  TEMPPLEIN,
		  PARTIEL;
		}*/
	
	public Employe (String nom, String prenom, String adresse, int codePostal, String ville, int age, String contrat) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.age = age;
		this.contrat = contrat;
	}
	
	public int getMatricule() {
		return matricule;
	}
	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getContrat() {
		return contrat;
	}

	public void setContrat(String contrat) {
		this.contrat = contrat;
	}

	@Override
	public String toString() {
		return "Employe [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse
				+ ", codePostal=" + codePostal + ", ville=" + ville + ", age=" + age + ", contrat=" + contrat + "]";
	}
	
}
