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
 * @author Sami Männistö
 * @version 1.0
 */

public class PalveluListViewController implements  Initializable{


    /**
     * Palvelu Id sarake
     */
    @FXML
    private TableColumn<Palvelu, Integer> palveluIdColumn;
    /**
     * Palvelun nimen sarake
     */
    @FXML
    private TableColumn<Palvelu, String> nimiColumn;
    /**
     * Palvelun hinta sarake
     */
    @FXML
    private TableColumn<Palvelu, Double> hintaColumn;
    /**
     * Palvelun tyyppi sarake
     */
    @FXML
    private TableColumn<Palvelu, Integer> tyyppiColumn;
    /**
     * Palvelun kuvaus sarake
     */
    @FXML
    private TableColumn<Palvelu, String> kuvausColumn;
    /**
     * Palvelun toimpisteId sarake
     */
    @FXML
    private TableColumn<Palvelu, Integer> toimipisteIdColumn;
    /**
     * TableView komponentti palvelujen tietojen esittämistä varten
     */
    @FXML
    private TableView<Palvelu> palveluTableView;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Palvelu tietojen alustaminen
         */

        palveluIdColumn.setCellValueFactory(new PropertyValueFactory<>("palveluId"));
        nimiColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        hintaColumn.setCellValueFactory(new PropertyValueFactory<>("hinta"));
        tyyppiColumn.setCellValueFactory(new PropertyValueFactory<>("tyyppi"));
        kuvausColumn.setCellValueFactory(new PropertyValueFactory<>("kuvaus"));
        toimipisteIdColumn.setCellValueFactory(new PropertyValueFactory<>("toimipisteId"));

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
            ResultSet palvelutResult = Tietokantayhteys.selectPalvelu(conn);
            /*
             * Lisätään uudet palvelu luokan ilmentymät TableView komponenttiin
             */
            while (palvelutResult.next()) {
                Palvelu palvelu = new Palvelu(palvelutResult.getInt("palveluId"), palvelutResult.getString("nimi"), palvelutResult.getDouble("hinta"),
                        palvelutResult.getInt("tyyppi"), palvelutResult.getString("kuvaus"), palvelutResult.getInt("toimipisteId"));
                palveluTableView.getItems().add(palvelu);
            }
            /*
             * Suljetaan tietokantayhteys
             */
            Tietokantayhteys.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(PalveluListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



}
