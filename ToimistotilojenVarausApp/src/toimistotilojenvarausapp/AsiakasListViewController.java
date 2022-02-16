
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
 * AsiakasListViewController FXML Controller luokka sisältää TableView
 * komponentin ja siihen liitettyjen TableColumnien alustavat toiminnot asiakkaan
 * tietojen esittämiseen
 *
 * @author Joni Vepsäläinen
 * @version 1.0
 */
public class AsiakasListViewController implements Initializable {
    /**
     * kolumni asiakkaan ID tiedolle
     */
    @FXML
    private TableColumn<Asiakas, Integer> asiakasIdColumn;
    /**
     * kolumni asiakkaan sukunimi tiedolle
     */
    @FXML
    private TableColumn<Asiakas, String> sukunimiColumn;
    /**
     * kolumni asiakkaan etunimi tiedolle
     */
    @FXML
    private TableColumn<Asiakas, String> etunimiColumn;
    /**
     * kolumni asiakkaan organisaatio tiedolle
     */
    @FXML
    private TableColumn<Asiakas, String> organisaatioColumn;
    /**
     * kolumni asiakkaan email tiedolle
     */
    @FXML
    private TableColumn<Asiakas, String> emailColumn;
    /**
     * kolumni asiakkaan lähiosoite tiedolle
     */
    @FXML
    private TableColumn<Asiakas, String> lahiosoiteColumn;
    /**
     * kolumni asiakkaan postinumero tiedolle
     */    
    @FXML
    private TableColumn<Asiakas, String> postinumeroColumn;
    /**
     * kolumni asiakkaan puhelinnumero tiedolle
     */    
    @FXML
    private TableColumn<Asiakas, String> puhelinColumn;
    /**
     * TableView komponentti asiakkaiden tietojen esittämistä varten
     */
    @FXML
    private TableView<Asiakas> asiakasTblView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Asiakkaan tietojen sarakkeiden alustaminen
         *
         * @param asiakasIdColumn asiakkaan ID kolumni
         * @param sukunimiColumn asiakkaan sukunimi kolumni
         * @param etunimiColumn asiakkaan etunimi kolumni
         * @param organisaatioColumn asiakkaan organisaatio kolumni
         * @param emailColumn asiakkaan email kolumni
         * @param lahiosoiteColumn asiakkaan lähiosoite kolumni
         * @param postinumeroColumn asiakkaan postinumero kolumni
         * @param puhelinColumn asiakkaan puhelinnumero kolumni 
         */
        asiakasIdColumn.setCellValueFactory(new PropertyValueFactory<>("asiakasId"));
        sukunimiColumn.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));
        etunimiColumn.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
        organisaatioColumn.setCellValueFactory(new PropertyValueFactory<>("organisaatio"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        lahiosoiteColumn.setCellValueFactory(new PropertyValueFactory<>("lahiosoite"));
        postinumeroColumn.setCellValueFactory(new PropertyValueFactory<>("postinumero"));
        puhelinColumn.setCellValueFactory(new PropertyValueFactory<>("puhelin"));        
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
            ResultSet asiakkaatResult = Tietokantayhteys.selectAsiakkaat(conn);
            /*
             * Lisätään uudet asiakas luokan ilmentymät TableView komponenttiin
             */
            while (asiakkaatResult.next()) {
                Asiakas asiakas = new Asiakas(asiakkaatResult.getInt("asiakasId"), asiakkaatResult.getString("etunimi"), asiakkaatResult.getString("sukunimi"),
                        asiakkaatResult.getString("organisaatio"), asiakkaatResult.getString("email"), asiakkaatResult.getString("lahiosoite"),
                        asiakkaatResult.getString("postinumero"), asiakkaatResult.getString("puhelin"));
                asiakasTblView.getItems().add(asiakas);
            }
            /*
             * Suljetaan tietokantayhteys
             */
            Tietokantayhteys.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(AsiakasListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
}