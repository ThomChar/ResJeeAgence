package gestionnaire;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import connexionBD.AgencyException;
import connexionBD.Connexion;
import model.Activite;
import model.Categorie;
import model.Lieu;
import model.OffreVoyage;
import model.Tarif;
import table.TableCategorie;
import table.TableLieu;
import table.TableOffreVoyage;
import table.TableTarif;

public class GestionnaireTarif {

	private TableTarif tarifs;
	private TableCategorie categories;
	private TableOffreVoyage offreVoyages;
	private Connexion cx;

	/**
	 * Creation d'une instance
	 */
	public GestionnaireTarif(TableTarif tableTarif, TableCategorie tableCategorie, TableOffreVoyage tableOffreVoyage)
			throws AgencyException {
		this.cx = tableTarif.getConnexion();

		if (tableTarif.getConnexion() == tableOffreVoyage.getConnexion()
				&& tableTarif.getConnexion() == tableCategorie.getConnexion()) {
			this.tarifs = tableTarif;
			this.categories = tableCategorie;
			this.offreVoyages = tableOffreVoyage;
		} else {
			throw new AgencyException(
					"Les instances de lieu, de tarif et d'offre de voyage n'utilisent pas la même connexion au serveur");
		}
	}

	/**
	 * Ajout d'un nouveau tarif dans la base de données. Si elle existe déjà , une
	 * exception est levée.
	 * 
	 * @throws AgencyException,
	 *             Exception
	 */
	public void ajouter(float prixUnitaire, OffreVoyage offreVoyage, Categorie categorie)
			throws AgencyException, Exception {

		try {
			cx.demarreTransaction();
			// Vérifie si l'offre de voyage existe déjà

			/*
			 * if (offreVoyages.existeByContent(description, lieu)) throw new
			 * AgencyException("Cette offre de Voyage existe deja ");
			 */

			OffreVoyage tupleOffreVoyage = offreVoyages.getOffreVoyage(offreVoyage.getIdOffre());
			if (tupleOffreVoyage == null)
				throw new AgencyException("Cette offre de Voyage n'existe pas ");
			Categorie tupleCategorie = categories.getCategorieById(categorie.getIdCategorie());
			if (tupleCategorie == null)
				throw new AgencyException("Cette categorie n'existe pas ");

			Tarif tupleTarif = new Tarif(prixUnitaire, offreVoyage, categorie);

			// Ajout du Tarif dans la table
			tarifs.creer(tupleTarif);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Supprime tarif de la base de données.
	 * 
	 * @throws AgencyException,
	 *             Exception
	 */
	public void supprime(int idOffreVoyage, int idCategorie) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			Tarif tupleTarif = tarifs.getTarif(idCategorie, idOffreVoyage);
			if (tupleTarif == null)
				throw new AgencyException("Tarif inexistante : " + tupleTarif);

			// Suppression du Tarif
			boolean testExiste = tarifs.supprimer(tupleTarif); // regarder si lasuppression encascade sinon il faut
																// supprimer dans les listes

			if (testExiste == false)
				throw new AgencyException("Tarif " + tupleTarif + " inexistant");

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Affichage d'un Tarif
	 * 
	 * @throws AgencyException,Exception
	 */
	public Tarif affichageTarif(int idOffreVoyage, int idCategorie) throws AgencyException, Exception {

		// Validation
		try {
			cx.demarreTransaction();
			Tarif tupleTarif = tarifs.getTarif(idCategorie, idOffreVoyage);
			if (tupleTarif == null)
				throw new AgencyException("Tarif inexistante: " + tupleTarif);
			System.out.println(tupleTarif.toString());

			// Commit
			cx.commit();
			return tupleTarif;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}

	/**
	 * Affichage des Offres de Voyage en dessus d'un certain prix
	 * 
	 * @throws AgencyException,Exception
	 */
	public List<Tarif> affichageOffreVoyagesTarif(float prixUnitaire) throws AgencyException, Exception {

		cx.demarreTransaction();

		List<Tarif> list = tarifs.getOffresVoyageTarif(prixUnitaire);

		for (Tarif a : list) {
			System.out.println(a.toString());
		}

		cx.commit();
		return list;

	}
	
	/**
	 * Affichage des Offres de Voyage en dessus d'un certain prix
	 * 
	 * @throws AgencyException,Exception
	 */
	public List<Tarif> affichageTarifs() throws AgencyException, Exception {

		cx.demarreTransaction();

		List<Tarif> list = tarifs.getListeTarifs();

		for (Tarif a : list) {
			System.out.println(a.toString());
		}

		cx.commit();
		return list;

	}
}
