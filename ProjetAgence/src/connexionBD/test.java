package connexionBD;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import model.*;

public class test {

	public static void main(String[] argv) {
		Connexion cx = new Connexion();
		
		EntityTransaction tx =  cx.getConnection().getTransaction();
		tx.begin();
		
		// 3 : Instanciation Objet métier
		Activite activite = new Activite("Hibernate2");
		Activite activite2 = new Activite("Hibernate3");
		
		// 4 : Persistance Objet/Relationnel : création d'un enregistrement en base
		cx.getConnection().persist(activite);
		cx.getConnection().persist(activite2);
		 System.out.println(activite.toString());
		// 5 : Fermeture transaction 
		 tx.commit();
		Activite activiterecup = cx.getConnection().find(Activite.class, 2);
		System.out.println("Recup :: "+activiterecup.toString());
		/*System.out.println("adresse=" + personneAdresse.getAdresse().getRue() + ", "
				+ personneAdresse.getAdresse().getCodePostal() + " " + personneAdresse.getAdresse().getVille());*/
		cx.fermer();
	}////localhost:8080/agencevoyagedb
	/*<property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
    <property name="hibernate.show_sql" value="true" />
    <property name="hibernate.hbm2ddl.auto" value="create" /> */
}
