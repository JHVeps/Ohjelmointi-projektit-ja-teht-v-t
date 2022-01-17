package toimistotilojenvarausapp;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
 * @author Joni Vepsäläinen, 2006234
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
     * kolumni asiakkaan toimipaikka tiedole
     */
    @FXML
    private TableColumn<Asiakas, String> toimipaikkaColumn;
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
     * 
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        asiakasIdColumn.setCellValueFactory(new PropertyValueFactory<>("asiakasId"));
        sukunimiColumn.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));
        etunimiColumn.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
        organisaatioColumn.setCellValueFactory(new PropertyValueFactory<>("organisaatio"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        lahiosoiteColumn.setCellValueFactory(new PropertyValueFactory<>("lahiosoite"));
        postinumeroColumn.setCellValueFactory(new PropertyValueFactory<>("postinumero"));
        toimipaikkaColumn.setCellValueFactory ((data -> new SimpleStringProperty(data.getValue().getToimipaikka())));
        puhelinColumn.setCellValueFactory(new PropertyValueFactory<>("puhelin"));        
        try {
            /*
             * Otetaan yhteys tietokantaan ((data -> new SimpleStringProperty(data.getValue().getLaskutustapa())));
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
            ResultSet asiakkaatResult = Tietokantayhteys.selectAsiakkaat(conn);
            /*
             * Lisätään uudet asiakas luokan ilmentymät TableView komponenttiin
             */
            while (asiakkaatResult.next()) {
                Asiakas asiakas = new Asiakas(asiakkaatResult.getInt("asiakasId"), asiakkaatResult.getString("etunimi"), asiakkaatResult.getString("sukunimi"),
                        asiakkaatResult.getString("organisaatio"), asiakkaatResult.getString("email"), asiakkaatResult.getString("lahiosoite"),
                        asiakkaatResult.getString("postinumero"), asiakkaatResult.getString("toimipaikka"), asiakkaatResult.getString("puhelin"));
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