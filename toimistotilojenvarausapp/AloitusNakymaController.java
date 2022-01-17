package toimistotilojenvarausapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * AloitusNakymaViewController FXML Controller luokka sisältää painikkeiden toiminnot mitkä
 * avaavat näkymät asiakkaiden, toimitilojen, laskujen, varausten ja palvelujen tietojen hallintaan
 * 
 * @author Joni Vepsäläinen, 2006234
 * @version 1.0
 */
public class AloitusNakymaController implements Initializable {
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void showAsiakasView(ActionEvent event) throws Exception {
          Parent root = FXMLLoader.load(getClass().getResource("AsiakasView.fxml"));

        Scene scene = new Scene(root);

        Stage asiakasStage = new Stage();
        asiakasStage.setScene(scene);
        asiakasStage.setResizable(false);
        asiakasStage.setTitle("Asiakas");
        asiakasStage.show();
        
    }

    @FXML
    private void showToimipisteView(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ToimipisteView.fxml"));

        Scene scene = new Scene(root);

        Stage toimipisteStage = new Stage();
        toimipisteStage.setScene(scene);
        toimipisteStage.setResizable(false);
        toimipisteStage.setTitle("Toimipiste");
        toimipisteStage.show();

    }

    @FXML
    private void showLaskuView(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LaskuView.fxml"));

        Scene scene = new Scene(root);

        Stage laskuStage = new Stage();
        laskuStage.setScene(scene);
        laskuStage.setResizable(false);
        laskuStage.setTitle("Lasku");
        laskuStage.show();
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void showVarausView(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("VarausView.fxml"));

        Scene scene = new Scene(root);

        Stage laskuStage = new Stage();
        laskuStage.setScene(scene);
        laskuStage.setResizable(false);
        laskuStage.setTitle("Varaus");
        laskuStage.show();
        
    }

    @FXML
    private void showPalveluView(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PalveluView.fxml"));

        Scene scene = new Scene(root);

        Stage toimipisteStage = new Stage();
        toimipisteStage.setScene(scene);
        toimipisteStage.setResizable(false);
        toimipisteStage.setTitle("Palvelu");
        toimipisteStage.show();

    }

    private void showLaiteView(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LaiteView.fxml"));

        Scene scene = new Scene(root);

        Stage toimipisteStage = new Stage();
        toimipisteStage.setScene(scene);
        toimipisteStage.setResizable(false);
        toimipisteStage.setTitle("Laite");
        toimipisteStage.show();
    }

}