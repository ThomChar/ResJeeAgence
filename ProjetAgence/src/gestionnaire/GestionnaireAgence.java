package gestionnaire;

import connexionBD.*;
import table.*;
import java.sql.SQLException;

public class GestionnaireAgence
{
    private Connexion cx;
    
    private GestionnaireActivite gestionActivite;
    private GestionnaireOffreVoyage gestionOffreVoyage;
    private GestionnaireEmploye gestionEmploye;
    private GestionnaireLieu gestionLieu;
    private GestionnaireAVisite gestionAVisite;
    private GestionnaireCategorie gestionCategorie;
    private GestionnaireTarif gestionTarif;
    private GestionnaireOccupation gestionOccupation;
    private GestionnaireParticipant gestionParticipant;
    private GestionnaireReservation gestionReservation;
    
    private TableActivite tableActivite;
    private TableOffreVoyage tableOffreVoyage;
    private TableEmploye tableEmploye;
    private TableLieu tableLieu;
    private TableAVisite tableAVisite;
    private TableCategorie tableCategorie;
    private TableTarif tableTarif;
    private TableOccupation tableOccupation;
    private TableParticipant tableParticipant;
    private TableReservation tableReservation;

    public GestionnaireAgence() throws AgencyException, SQLException
    {
        cx = new Connexion();
        tableActivite = new TableActivite(cx);
        tableOffreVoyage = new TableOffreVoyage(cx);
        tableEmploye = new TableEmploye(cx);
        tableLieu = new TableLieu(cx);
        tableAVisite = new TableAVisite(cx);
        tableCategorie = new TableCategorie(cx);
        tableTarif = new TableTarif(cx);
        tableOccupation = new TableOccupation(cx);
        tableParticipant = new TableParticipant(cx);
        tableReservation = new TableReservation(cx);
        
        gestionActivite = new GestionnaireActivite(tableActivite, tableOccupation);
        System.out.println("test1");
        gestionOffreVoyage = new GestionnaireOffreVoyage(tableOffreVoyage, tableLieu, tableTarif);
        System.out.println("test1");
        gestionEmploye = new GestionnaireEmploye(tableEmploye);
        gestionLieu = new GestionnaireLieu(tableLieu, tableOccupation, tableAVisite, tableOffreVoyage);
        System.out.println("test1");
        gestionAVisite = new GestionnaireAVisite(tableLieu, tableAVisite);
        System.out.println("test1");
        gestionCategorie = new GestionnaireCategorie(tableCategorie, tableTarif, tableParticipant);
        System.out.println("test1");
        gestionTarif = new GestionnaireTarif(tableTarif, tableCategorie, tableOffreVoyage);
        System.out.println("test1");
        gestionOccupation = new GestionnaireOccupation(tableActivite, tableLieu, tableOccupation);
        System.out.println("test1");
        gestionParticipant = new GestionnaireParticipant(tableParticipant,tableReservation,tableCategorie);
        gestionReservation = new GestionnaireReservation(tableReservation,tableOffreVoyage,tableParticipant);
    }

    public Connexion getConnexion() {
        return cx;
    }

    public void setConnexion(Connexion cx) {
        this.cx = cx;
    }

	public GestionnaireActivite getGestionActivite() {
		return gestionActivite;
	}

	public void setGestionActivite(GestionnaireActivite gestionActivite) {
		this.gestionActivite = gestionActivite;
	}

	public GestionnaireOffreVoyage getGestionOffreVoyage() {
		return gestionOffreVoyage;
	}

	public void setGestionOffreVoyage(GestionnaireOffreVoyage gestionOffreVoyage) {
		this.gestionOffreVoyage = gestionOffreVoyage;
	}

	public GestionnaireEmploye getGestionEmploye() {
		return gestionEmploye;
	}

	public void setGestionEmploye(GestionnaireEmploye gestionEmploye) {
		this.gestionEmploye = gestionEmploye;
	}

	public GestionnaireLieu getGestionLieu() {
		return gestionLieu;
	}

	public void setGestionLieu(GestionnaireLieu gestionLieu) {
		this.gestionLieu = gestionLieu;
	}

	public GestionnaireAVisite getGestionAVisite() {
		return gestionAVisite;
	}

	public void setGestionAVisite(GestionnaireAVisite gestionAVisite) {
		this.gestionAVisite = gestionAVisite;
	}

	public GestionnaireCategorie getGestionCategorie() {
		return gestionCategorie;
	}

	public void setGestionCategorie(GestionnaireCategorie gestionCategorie) {
		this.gestionCategorie = gestionCategorie;
	}

	public GestionnaireTarif getGestionTarif() {
		return gestionTarif;
	}

	public void setGestionTarif(GestionnaireTarif gestionTarif) {
		this.gestionTarif = gestionTarif;
	}

	public GestionnaireOccupation getGestionOccupation() {
		return gestionOccupation;
	}

	public void setGestionOccupation(GestionnaireOccupation gestionOccupation) {
		this.gestionOccupation = gestionOccupation;
	}

	public TableActivite getTableActivite() {
		return tableActivite;
	}

	public void setTableActivite(TableActivite tableActivite) {
		this.tableActivite = tableActivite;
	}

	public TableOffreVoyage getTableOffreVoyage() {
		return tableOffreVoyage;
	}

	public void setTableOffreVoyage(TableOffreVoyage tableOffreVoyage) {
		this.tableOffreVoyage = tableOffreVoyage;
	}

	public TableEmploye getTableEmploye() {
		return tableEmploye;
	}

	public void setTableEmploye(TableEmploye tableEmploye) {
		this.tableEmploye = tableEmploye;
	}

	public TableLieu getTableLieu() {
		return tableLieu;
	}

	public void setTableLieu(TableLieu tableLieu) {
		this.tableLieu = tableLieu;
	}

	public TableAVisite getTableAVisite() {
		return tableAVisite;
	}

	public void setTableAVisite(TableAVisite tableAVisite) {
		this.tableAVisite = tableAVisite;
	}

	public TableCategorie getTableCategorie() {
		return tableCategorie;
	}

	public void setTableCategorie(TableCategorie tableCategorie) {
		this.tableCategorie = tableCategorie;
	}

	public TableTarif getTableTarif() {
		return tableTarif;
	}

	public void setTableTarif(TableTarif tableTarif) {
		this.tableTarif = tableTarif;
	}

	public TableOccupation getTableOccupation() {
		return tableOccupation;
	}

	public void setTableOccupation(TableOccupation tableOccupation) {
		this.tableOccupation = tableOccupation;
	}


    public void fermer() throws SQLException
    {
        //Fermeture de la connexion
        cx.fermer();
    }
}

