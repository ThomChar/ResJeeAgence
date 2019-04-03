package table;

import java.util.List;
import javax.persistence.TypedQuery;
import connexionBD.*;
import model.*;

public class TableOccupation {

	private TypedQuery<Occupation> stmtExiste;
	private TypedQuery<Occupation> stmtListToutesOccupations;
	private TypedQuery<Occupation> stmtListToutesOccupationsLieu;
	private TypedQuery<Occupation> stmtListToutesOccupationsActivite;
	// private TypedQuery<Activite> stmtListToutesOccupationsActivite; //a mettre
	// dans la table des occupations

	private Connexion cx;

	/**
	 * Creation d'une instance.
	 */
	public TableOccupation(Connexion cx) {
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery(
				"select o from Occupation o where o.lieu.idLieu = :idLieu and o.activite.idActivite = :idActivite",
				Occupation.class);
		stmtListToutesOccupations = cx.getConnection().createQuery("select o from Occupation o", Occupation.class);
		stmtListToutesOccupationsLieu = cx.getConnection()
				.createQuery("select o from Occupation o where o.lieu.idLieu = :idLieu", Occupation.class);
		stmtListToutesOccupationsActivite = cx.getConnection().createQuery(
				"select o from Occupation o where o.activite.idActivite = :idActivite", Occupation.class);
		// stmtListToutesOccupationsActivite = cx.getConnection().createQuery("select e
		// from Equipe e where e.ligue.nomLigue = :nomLigue",Activite.class);
	}

	/**
	 * Retourner la connexion associee.
	 */
	public Connexion getConnexion() {
		return cx;
	}

	/**
	 * Verifie si une occupation existe.
	 * 
	 */
	public boolean existe(int idLieu, int idActivite) {
		stmtExiste.setParameter("idLieu", idLieu);
		stmtExiste.setParameter("idActivite", idActivite);
		return !stmtExiste.getResultList().isEmpty();
	}

	/**
	 * Recupere occupation correspondant au couple idLieu idActivite.
	 * 
	 */
	public Occupation getOccupation(int idLieu, int idActivite) {
		stmtExiste.setParameter("idLieu", idLieu);
		stmtExiste.setParameter("idActivite", idActivite);
		List<Occupation> occupations = stmtExiste.getResultList();
		if (!occupations.isEmpty()) {
			return occupations.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Recupere occupation correspondant au idActivite.
	 * 
	 */
	public List<Occupation> getOccupationsActivite(int idActivite) {
		stmtListToutesOccupationsActivite.setParameter("idActivite", idActivite);
		List<Occupation> occupations = stmtListToutesOccupationsActivite.getResultList();
		if (!occupations.isEmpty()) {
			return occupations;
		} else {
			return null;
		}
	}

	/**
	 * Recupere occupation correspondant au idLieu.
	 * 
	 */
	public List<Occupation> getOccupationsLieu(int idLieu) {
		stmtListToutesOccupationsLieu.setParameter("idLieu", idLieu);
		List<Occupation> occupations = stmtListToutesOccupationsLieu.getResultList();
		if (!occupations.isEmpty()) {
			return occupations;
		} else {
			return null;
		}
	}

	/**
	 * Ajout d'une nouvelle occupation non vide.
	 * 
	 */
	public Occupation creer(Occupation occupation) {
		cx.getConnection().persist(occupation);
		return occupation;
	}

	/**
	 * Suppression d'une occupation.
	 */
	public boolean supprimer(Occupation Occupation) {
		if (Occupation != null) {
			cx.getConnection().remove(Occupation);
			return true;
		}
		return false;
	}
	
	/**
     * Retourne l'ensemble des occupations de la base de données
     * @return
     */
    public List<Occupation> getListeOccupations()
    {
        return stmtListToutesOccupations.getResultList();
    }
}
