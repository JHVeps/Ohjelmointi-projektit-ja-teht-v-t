package toimistotilojenvarausapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sami Männistö
 */

public class ToimipisteListViewController implements Initializable {

    /**
     * toimipiste ID sarake
     */
    @FXML
    private TableColumn<Toimipiste, Integer> toimipisteIdColumn;
    /**
     * toimipiste ID sarake
     */
    @FXML
    private TableColumn<Toimipiste, String> nimiColumn;
    /**
     * toimipiste nimi sarake
     */
    @FXML
    private TableColumn<Toimipiste, String> osoiteColumn;
    /**
     * toimipiste osoite sarake
     */
    @FXML
    private TableColumn<Toimipiste, String> kuvausColumn;
    /**
     * TableView komponentti jolla siirretään toimipisteiden tietoa
     */
    @FXML
    private TableView<Toimipiste> toimipisteTblView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /**
         * @param toimipisteIdColumn toimipiste id sarake
         * @param nimiColumn toimipisteen nimen sarake
         * @param osoiteColumn toimipisteen osoitteen sarake
         * @param kuvausColumn toimipisteen kuvauksen sarake
         */
        toimipisteIdColumn.setCellValueFactory(new PropertyValueFactory<>("toimipisteId"));
        nimiColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        osoiteColumn.setCellValueFactory(new PropertyValueFactory<>("osoite"));
        kuvausColumn.setCellValueFactory(new PropertyValueFactory<>("kuvaus"));

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
            ResultSet toimipisteResult = Tietokantayhteys.selectToimipiste(conn);
            /*
             * Lisätään uudet toimipiste luokan ilmentymät TableViewiin
             */
            while (toimipisteResult.next()) {
                Toimipiste toimipiste = new Toimipiste(toimipisteResult.getInt("toimipisteID"),
                        toimipisteResult.getString("nimi"), toimipisteResult.getString("osoite"), toimipisteResult.getString("kuvaus"));
                toimipisteTblView.getItems().add(toimipiste);
            }
            /*
             * Tietokanta yhteyden sulkeminen
             */
            Tietokantayhteys.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(ToimipisteListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}