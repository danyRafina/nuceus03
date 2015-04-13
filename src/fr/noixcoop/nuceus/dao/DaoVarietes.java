package fr.noixcoop.nuceus.dao;

import java.util.* ;
import java.sql.* ;

import fr.noixcoop.nuceus.metier.Variete;
import fr.noixcoop.nuceus.technique.ConnexionBD;

public class DaoVarietes implements IDaoVarietes {

	public List<Variete> consulter() {
		List<Variete> varietes = new ArrayList<Variete>();
		Connection connexion = ConnexionBD.getConnexion() ;
		try {
			Statement requete = connexion.createStatement() ;
			ResultSet resultat = requete.executeQuery("select * from VARIETE ;") ;
			while(resultat.next()){
				boolean aoc = false ;
				if(resultat.getInt("aoc") == 1){
					aoc = true ;
				}
				Variete variete =  new Variete(resultat.getString("libelle"),aoc) ;
				varietes.add(variete);
			}
			resultat.close() ;
			requete.close() ;
		}
		catch(Exception e){
			System.out.println("Erreur dans DaoVarietes::consulter()...") ;
		}

		return varietes ;
	}

	public Variete consulter(String libelle){
		Variete variete = null ;
		Connection connexion = ConnexionBD.getConnexion() ;
		try {
			Statement requete = connexion.createStatement() ;
			ResultSet resultat = requete.executeQuery("select aoc from VARIETE where libelle='" + libelle + "';") ;
			if(resultat.next()){
				boolean aoc = false ;
				if(resultat.getInt("aoc") == 1){
					aoc = true ;
				}
				variete =  new Variete(libelle,aoc) ;
			}
			resultat.close() ;
			requete.close() ;
		}
		catch(Exception e){
			System.out.println("Erreur dans DaoVarietes::consulter(String)...") ;
		}
		return variete ;
	}

	public boolean ajouter(Variete variete){
		Connection connexion = ConnexionBD.getConnexion() ;
		int aoc = 0;
		try {
			Statement requete = connexion.createStatement() ;
			if(variete.isAoc()){
				aoc=1;
			}
			int resultat = requete.executeUpdate("INSERT INTO VARIETE VALUES ('"+variete.getLibelle()+"',"+aoc+") ;");
			if(resultat == 1){
				return true ;
			}
			else {
				return false;
			}
		}
		catch(Exception e){
			System.out.println("Erreur dans DaoVarietes::ajouter(variete)...") ;
			return false ;
		}
	}

	public boolean modifier(Variete variete){
		Connection connexion = ConnexionBD.getConnexion() ;
		int aoc = 0;
		try {
			Statement requete = connexion.createStatement() ;
			if(variete.isAoc()){
				aoc=1;
			}
			int resultat = requete.executeUpdate("UPDATE VARIETE SET aoc = "+aoc+" where libelle = '"+variete.getLibelle()+"' ;");
			if(resultat == 1){
				return true ;
			}
			else {
				return false;
			}
		}
		catch(Exception e){
			System.out.println("Erreur dans DaoVarietes::modifier(variete)...") ;
			return false ;
		}
	}

	public boolean supprimer(String libelle){
		Connection connexion = ConnexionBD.getConnexion() ;
		try {
			Statement requete = connexion.createStatement() ;
			int resultat = requete.executeUpdate("DELETE FROM VARIETE where libelle ='" + libelle + "';") ;
			if(resultat == 1){
				return true ;
			}
			else {
				return false;
			}
			
		}
		catch(Exception e){
			System.out.println("Erreur dans DaoVarietes::supprimer(String)...") ;
			return false ;
		}
	}

	public boolean supprimer(Variete variete){
		return this.supprimer(variete.getLibelle());
	}

}