package Prosco.Class;


import java.io.IOException;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClassBDEcole {

	String nomEcole;
	
	public ClassBDEcole(String nomEcole) {
		this.nomEcole = nomEcole;
	}
	
	public String getNomEcole () {
		return this.nomEcole;
	}
	
	public String getNumEcole () {
		
		String elem = "";
		try {
        	ConnectBD BD = new ConnectBD();
        	
        	//Récupération des informations propres à l'école
        	ResultSet res = BD.setQuery("SELECT * FROM ROOT.ECOLES WHERE NOM='" + this.getNomEcole() + "'");

        	//Positionnement du curseur
        	res.next();
    		elem = res.getString("ID");

    	} 
		catch ( Exception e ) {
    	        	System.out.println("Problème de connexion à la BD : " + e.getMessage());
    	            e.printStackTrace();       
		}
		
		return elem;
	}
	public void setNomEcole (String newNomEcole) {
		this.nomEcole = newNomEcole;
	}
	
	
	public void chargerEcole (String nomEcole, Object object) throws IOException
	{
		 
	            Node node= (Node) object;
	       		  Stage stage=(Stage) node.getScene().getWindow();
	       		  Parent root = null;
			
					root = FXMLLoader.load(getClass().getResource("/Prosco/Resources/View/ViewInfoEcole.fxml"));

	       		 
	       		  Scene scene = new Scene(root);
	       		  stage.setScene(scene);
	       		 
	       		  stage.show();
		
		
	  }  
	}
	


