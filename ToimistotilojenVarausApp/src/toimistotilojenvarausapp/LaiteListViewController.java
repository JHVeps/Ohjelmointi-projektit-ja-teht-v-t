package toimistotilojenvarausapp;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Leevi Meriläinen
 */
public class LaiteListViewController implements Initializable {
    /**
     * kolumni laitteen laitenimi tiedolle
     */
    @FXML
    private TableColumn<Laite, String> laitteenNimiColumn;
    /**
     * kolumni laitteen kuvaus tiedolle
     */
    @FXML
    private TableColumn<Laite, String> KuvausColumn;
    /**
     * kolumni laitteen hinta tiedolle
     */
    @FXML
    private TableColumn<Laite, Integer> HintaColumn;
    /**
     * TableView komponentti varausten tietojen esittämistä varten
     */
    @FXML
    private TableView<Laite> laiteTblView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Laitteen tietojen sarakkeiden alustaminen
         *
         * @param laitteenNimiColumn laitteen nimi kolumni
         * @param KuvausColumn laitteen kuuvaus kolumni
         * @param HintaColumn varauksen Hinta kolumni
         */
        laitteenNimiColumn.setCellValueFactory(new PropertyValueFactory<>("Laitteen nimi"));
        KuvausColumn.setCellValueFactory(new PropertyValueFactory<>("Kuvaus"));
       HintaColumn.setCellValueFactory(new PropertyValueFactory<>("Hinta"));
        try {
            /*
             * Otetaan yhteys tietokantaan
             */
            Connection conn = Tietokantayhteys.openConnection(
                    ""
                    + "");
            


            /*
             * Määritetään käytettävä tietokanta
             */
            Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
            /*
             * Haetaan tiedot tietokannasta
             */
            ResultSet laitteetResult = Tietokantayhteys.selectLaitteet(conn);
            /*
             * Lisätään uudet laite luokan ilmentymät TableView komponenttiin
             */
            while (laitteetResult.next()) {
                Laite laite = new Laite(laitteetResult.getString("laitteen nimi"), laitteetResult.getInt("Hinta"), laitteetResult.getString("Kuvaus"));
                laiteTblView.getItems().add(laite);
            }
            /*
             * Suljetaan tietokantayhteys
             */
            Tietokantayhteys.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(LaiteListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
}    
    

