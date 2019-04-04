package gestionnaire;

import connexionBD.AgencyException;
import connexionBD.Connexion;
import model.Activite;
import model.Lieu;
import model.Occupation;
import table.TableActivite;
import table.TableLieu;
import table.TableOccupation;
import table.TableOffreVoyage;
import table.TableParticipant;
import table.TableReservation;

public class GestionnaireReservation {
	
	private TableReservation reservations;
	private TableParticipant participants;
	private TableOffreVoyage offreVoyages;
	private Connexion cx;
	
	/**
	 * Creation d'une instance
	 */
	public GestionnaireReservation(TableReservation tableReservation,TableOffreVoyage tableOffreVoyage, TableParticipant tableParticipant)throws AgencyException {
		this.cx = reservations.getConnexion();
		
		if (reservations.getConnexion() == participants.getConnexion() && reservations.getConnexion() == offreVoyages.getConnexion()) { 
			this.reservations = reservations;
			this.participants = participants;
			this.offreVoyages = offreVoyages;
		} else {
			throw new AgencyException(
					"Les instances d'activite, de lieu et d'occupation n'utilisent pas la m�me connexion au serveur");
		}
	}
	
	/**
	 * Ajout d'une nouvelle occupation dans la base de donn�es. Si elle existe d�j�,
	 * une exception est lev�e.
	 * 
	 * @throws AgencyException, Exception
	 */
	/*public void ajouter(int idLieu, int idActivite) throws AgencyException, Exception {

		try {
			cx.demarreTransaction();
			// V�rifie si l occupation existe d�j�

			if (occupations.existe(idLieu, idActivite))
				throw new AgencyException("Cette occupations existe deja pour ce Lieu ");

			Activite tupleActivite = activites.getActiviteById(idActivite);
			Lieu tupleLieu = lieus.getLieubyId(idLieu);
			Occupation tupleOccupation = new Occupation(tupleLieu, tupleActivite);
			
			// Ajout de l activite dans la table des activites
			occupations.creer(tupleOccupation);

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}*/

	/**
	 * Supprime occupation de la base de donn�es.
	 * 
	 * @throws AgencyException, Exception
	 */
	/*public void supprime(int idLieu, int idActivite) throws AgencyException, Exception {
		try {
			cx.demarreTransaction();
			// Validation
			Occupation tupleOccupation = occupations.getOccupation(idLieu, idActivite);
			if (tupleOccupation == null)
				throw new AgencyException("Occupation inexistant: " + tupleOccupation);
			
			// Suppression de l'occupation
			boolean testExiste = occupations.supprimer(tupleOccupation);	//regarder si lasuppression encascade sinon il faut supprimer dans les listes
			
			if (testExiste == false)
				throw new AgencyException("Occupation " + tupleOccupation + " inexistante");

			// Commit
			cx.commit();
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}*/

	/**
	 * Affichage d'une occupation
	 * 
	 * @throws AgencyException,Exception
	 */
	/*public Occupation affichageOccupation(int idLieu, int idActivite) throws AgencyException, Exception {
		
		// Validation
		try {
			cx.demarreTransaction();
			Occupation tupleOccupation = occupations.getOccupation(idLieu, idActivite);
			if (tupleOccupation == null)
				throw new AgencyException("Occupation inexistante: " + tupleOccupation);
			System.out.println(tupleOccupation.toString());
			
			// Commit
			cx.commit();
			return tupleOccupation;
		} catch (Exception e) {
			cx.rollback();
			throw e;
		}
	}*/
}
