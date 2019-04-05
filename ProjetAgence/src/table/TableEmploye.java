package table;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import connexionBD.Connexion;
import model.Employe;
import model.OffreVoyage;

public class TableEmploye {

	private TypedQuery<Employe> stmtExiste;
	private TypedQuery<Employe> stmtExisteByContent;
	private TypedQuery<Employe> stmtListTousEmployes;
	
	private Connexion cx;

	/**
	 * Creation d'une instance.
	 */
	public TableEmploye(Connexion cx) {
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery(
				"select e from Employe e where e.matricule = :matricule",
				Employe.class);
		stmtExisteByContent = cx.getConnection().createQuery(
				"select e from Employe e where e.pseudo = :pseudo",
				Employe.class);
		stmtListTousEmployes = cx.getConnection().createQuery("select e from Employe e", Employe.class);
		
	}

	/**
	 * Retourner la connexion associee.
	 */
	public Connexion getConnexion() {
		return cx;
	}

	/**
	 * Verifie si un employe existe.
	 * 
	 */
	public boolean existe(int idEmployee) {
		stmtExiste.setParameter("matricule", idEmployee);
		return !stmtExiste.getResultList().isEmpty();
	}
	
	/**
	 * Verifie si employe existe.
	 * 
	 */
	public boolean existeByContent(String pseudo) {
		stmtExisteByContent.setParameter("pseudo", pseudo);
		return !stmtExisteByContent.getResultList().isEmpty();
	}

	/**
	 * Recupere employe correspondant au idEmployee.
	 * 
	 */
	public Employe getEmploye(int idEmployee) {
		stmtExiste.setParameter("matricule", idEmployee);
		List<Employe> employes = stmtExiste.getResultList();
		if (!employes.isEmpty()) {
			return employes.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Recupere employe correspondant au pseudo.
	 * 
	 */
	public Employe getEmployebyPseudo(String pseudo) {
		stmtExisteByContent.setParameter("pseudo", pseudo);
		List<Employe> employes = stmtExisteByContent.getResultList();
		if (!employes.isEmpty()) {
			return employes.get(0);
		} else {
			return null;
		}
	}
	

	/**
	 * Ajout d'une nouvel employe non vide.
	 * 
	 */
	public Employe creer(Employe employe) {
		cx.getConnection().persist(employe);
		return employe;
	}

	/**
	 * Suppression d'une employe.
	 */
	public boolean supprimer(Employe employe) {
		if (employe != null) {
			cx.getConnection().remove(employe);
			return true;
		}
		return false;
	}
	
	/**
     * Retourne l'ensemble des offres de Voyage de la base de données
     * @return
     */
    public List<Employe> getListeEmployes()
    {
        return stmtListTousEmployes.getResultList();
    }
}
