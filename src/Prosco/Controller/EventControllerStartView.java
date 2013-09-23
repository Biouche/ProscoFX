package Prosco.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import com.mytdev.javafx.scene.control.AutoCompleteTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Prosco.App.App;
import Prosco.Class.ClassBDEcole;
import Prosco.Class.ConnectBD;


public class EventControllerStartView extends AnchorPane implements Initializable{

	
	public EventControllerStartView () throws IOException {
		
	}
	
	public void chargerVue () throws IOException {
		
		//Charger la fenêtre courante de l'appel du bouton de retour (fonction BackButton ()
		Stage stage1 = App.getCurrentStage();
		
		//Charger la fenêtre d'accueil
		Parent root = FXMLLoader.load(getClass().getResource("/Prosco/Resources/View/StartView.fxml"));
		Scene scene = new Scene (root);
		
		//Afficher la fenêtre d'accueil
        stage1.setScene(scene);
        stage1.show();
	} //chargerVue
	
	@FXML
	public AutoCompleteTextField<String> AutoCompleteField;
	

	@FXML 
	public void getTxtRechercheEcole (ActionEvent event) throws IOException {
			
		
			ClassBDEcole Ecole = new ClassBDEcole(AutoCompleteField.getText());
		
		
			EventControllerViewInfoEcole root = new EventControllerViewInfoEcole();
    		
			root.labelNomEcole.setText(AutoCompleteField.getText());
    		root.textFieldNumEcole.setText(Ecole.getNumEcole());
    		
    		Scene cc = AutoCompleteField.getScene();
    		cc.setRoot(root);
    		
       
		
			
	} //getTxtRechercheEcole
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> List = FXCollections.observableArrayList();
		
		/*************************** Créer le dictionnaire de données pour l'auto Complétion ***************************/
		
        	try {
			ConnectBD BD = new ConnectBD();	
        	//Récupération des informations propres à l'école
        	ResultSet res = BD.setQuery("SELECT * FROM ROOT.ECOLES");
        	
        	String elem = "";
        	
        	while (res.next()){
        		//Récupération de toutes les valeurs de la colonne 'NOM' de ECOLES
    			elem = res.getString("NOM");
    			
    			//Ajout des Valeurs à la ObservableList
    			List.add(elem);
    			}
        		
        		//Ajout du dictionnaire de données au textField
	            AutoCompleteField.setItems(List);
	            
	            System.out.println(List);
        	}
        	catch ( Exception e ) {
    			System.out.println("Problème de connexion à la BD : " + e.getMessage());
    		    e.printStackTrace();       
    		}
      	           
	} //Initialize

}
