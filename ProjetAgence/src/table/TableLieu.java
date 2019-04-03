package table;

import java.util.List;

import javax.persistence.TypedQuery;

import connexionBD.*;
import model.*;

public class TableLieu {
	
	private TypedQuery<Lieu> stmtExiste;
	private TypedQuery<Lieu> stmtExisteById;
	private TypedQuery<Lieu> stmtListTousLieus;

	private Connexion cx;

	/**
	 * Creation d'une instance.
	 */
	public TableLieu(Connexion cx) {
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery(
				"select l from Lieu l where nomLieu = :nomLieu and pays = :pays",
				Lieu.class);
		stmtExisteById = cx.getConnection().createQuery(
				"select l from Lieu l where idLieu = :idLieu",
				Lieu.class);
		stmtListTousLieus = cx.getConnection().createQuery("select l from Lieu l", Lieu.class);
	
	}
	
	public Connexion getConnexion() {
		return cx;
	}
	
	/**
	 * Verifie si une activite existe.
	 * 
	 */
	public boolean existe(String nomLieu, String pays) {
		stmtExiste.setParameter("nomLieu", nomLieu);
		stmtExiste.setParameter("pays", pays);
		return !stmtExiste.getResultList().isEmpty();
	}
	
	/**
	 * Recupere lieu correspondant au couple idLieu idActivite.
	 * 
	 */
	public Lieu getLieu(String nomLieu, String pays) {
		stmtExiste.setParameter("nomLieu", nomLieu);
		List<Lieu> lieus = stmtExiste.getResultList();
		if (!lieus.isEmpty()) {
			return lieus.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Recupere lieu correspondant au couple idLieu idActivite.
	 * 
	 */
	public Lieu getLieubyId(int idLieu) {
		stmtExisteById.setParameter("idLieu", idLieu);
		List<Lieu> lieus = stmtExisteById.getResultList();
		if (!lieus.isEmpty()) {
			return lieus.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Ajout d'un nouveau Lieu non vide.
	 * 
	 */
	public Lieu creer(Lieu lieu) {
        cx.getConnection().persist(lieu);       
        return lieu;
	}

	 /**
     * Suppression d'un lieu.
     */
    public boolean supprimer(Lieu lieu)
    {
        if(lieu != null)
        {
            cx.getConnection().remove(lieu);
            return true;
        }
        return false;
    }
    
    /**
     * Retourne l'ensemble des lieus de la base de données
     * @return
     */
    public List<Lieu> getListeLieu()
    {
        return stmtListTousLieus.getResultList();
    }

}
