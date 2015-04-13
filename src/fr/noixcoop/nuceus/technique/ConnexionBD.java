package fr.noixcoop.nuceus.technique;
import java.sql.* ;
import com.mysql.jdbc.Connection;

public class ConnexionBD {
	private static String dbURL ="jdbc:mysql://localhost:3306/noixcoop" , user ="noixcoop" , password ="noixcoop";
	private static Connection connexion = null;
	private ConnexionBD() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance() ;
			connexion = (Connection) DriverManager.getConnection(dbURL,user,password);
		}
		catch(Exception e){
			System.out.println("Erreur BD") ;
		}
	}

	public static Connection getConnexion(){
		if(connexion == null) 
			new ConnexionBD();
		return connexion;
	}
}

