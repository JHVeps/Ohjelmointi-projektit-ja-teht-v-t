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
 * ToimipisteListViewController FXML Controller luokka sisältää TableView
 * komponentin ja siihen liitettyjen TableColumnien alustavat toiminnot
 * toimipisteiden tietojen esittämiselle
 *
 * @author Sami Männistö
 * @version 1.08137289713
 */

public class ToimipisteListViewController implements Initializable {

    /**
     * toimipiste ID sarake
     */
    @FXML
    private TableColumn<Toimipiste, Integer> toimipisteIdColumn;
    /**
     * toimipiste nimi sarake
     */
    @FXML
    private TableColumn<Toimipiste, String> nimiColumn;
    /**
     * toimipiste lahiosoite sarake
     */
    @FXML
    private TableColumn<Toimipiste, String> lahiosoiteColumn;
    /**
     * toimipiste toimipaika sarake
     */
    @FXML
    private TableColumn<Toimipiste, String> toimipaikkaColumn;
    /**
     * toimipiste postinro sarake
     */
    @FXML
    private TableColumn<Toimipiste, String> postinroColumn;
    /**
     * toimipisteen kuvauksen sarake
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
         * @param lahiosoiteColumn toimipisteen osoitteen sarake
         * @param toimipaikkaColumn toimipisteen toimipaikka
         * @param postinroColumn toimipisteen postinumeron sarake
         * @param kuvausColumn toimipisteen kuvauksen sarake
         */
        toimipisteIdColumn.setCellValueFactory(new PropertyValueFactory<>("toimipisteId"));
        nimiColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        lahiosoiteColumn.setCellValueFactory(new PropertyValueFactory<>("lahiosoite"));
        postinroColumn.setCellValueFactory(new PropertyValueFactory<>("postinro"));
        toimipaikkaColumn.setCellValueFactory(new PropertyValueFactory<>("toimipaikka"));
        kuvausColumn.setCellValueFactory(new PropertyValueFactory<>("kuvaus"));

        try {
            /*
             * Otetaan yhteys tietokantaan
             */
            Connection conn = Tietokantayhteys.openConnection(
                    "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                    + "3306?user=opiskelija&password=opiskelija1");

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
                        toimipisteResult.getString("nimi"), toimipisteResult.getString("lahiosoite"), toimipisteResult.getString("postinro"),
                        toimipisteResult.getString("toimipaikka"), toimipisteResult.getString("kuvaus"));
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