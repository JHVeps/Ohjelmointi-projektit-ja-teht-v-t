
package toimistotilojenvarausapp;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
 * VarauksenLisalaiteTietoListViewConroller FXML Controller luokka sisältää TableView
 * komponentin ja siihen liitettyjen TableColumnien toiminnot varauksen lisälaitteiden
 * tietojen esittämiseen
 *
 * @author Joni Vepsäläinen
 * @version 1.0
 */
public class VarauksenLisapalveluTietoListViewController implements Initializable {

    @FXML
    private TableColumn<VarauksenLisapalveluTieto, Integer> varausIdColumn;
    @FXML
    private TableColumn<VarauksenLisapalveluTieto, Integer> asiakasIdColumn;
    @FXML
    private TableColumn<VarauksenLisapalveluTieto, String> etunimiColumn;
    @FXML
    private TableColumn<VarauksenLisapalveluTieto, String> sukunimiColumn;
    @FXML
    private TableColumn<VarauksenLisapalveluTieto, Integer> palvelunIdColumn;
    @FXML
    private TableColumn<VarauksenLisapalveluTieto, String> palvelunNimiColumn;
    @FXML
    private TableColumn<VarauksenLisapalveluTieto, Date> aloitusPvmColumn;    
    @FXML
    private TableColumn<VarauksenLisapalveluTieto, Date> lopetusPvmColumn;
    @FXML
    private TableView<VarauksenLisapalveluTieto> varauksenLisapalveluTblView;




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Varauksen tietojen sarakkeiden alustaminen
         *
         * @param varausIdColumn varauksen ID kolumni
         * @param asiakasIdColumn varauksen asiakkaan ID kolumni
         * @param etunimiColumn varauksen asiakkaan etunimi kolumni
         * @param sukunimiColumn varauksen asiakkaan sukunimi kolumni
         * @param palvelunIdColumn varauksen palvelun ID kolumni
         * @param palvelunNimiColumn varauksen palvelun nimi kolumni
         * @param lopetusPvmColumn varauksen lopetuspvm kolumni
         * @param aloitusPvmColumn varauksen aloituspvm kolumni
         */
        varausIdColumn.setCellValueFactory(new PropertyValueFactory<>("varausId"));
        asiakasIdColumn.setCellValueFactory(new PropertyValueFactory<>("asiakasId"));
        etunimiColumn.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
        sukunimiColumn.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));
        palvelunIdColumn.setCellValueFactory(new PropertyValueFactory<>("palveluId"));
        palvelunNimiColumn.setCellValueFactory(new PropertyValueFactory<>("palvelunNimi"));
        lopetusPvmColumn.setCellValueFactory(new PropertyValueFactory<>("lopetusPvm"));
        aloitusPvmColumn.setCellValueFactory(new PropertyValueFactory<>("aloitusPvm"));
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
            ResultSet varauksenLisapalvelutResult = Tietokantayhteys.selectVarauksenLisapalvelut(conn);
            /*
             * Lisätään uudet varaus luokan ilmentymät TableView komponenttiin
             */
            while (varauksenLisapalvelutResult.next()) {
                VarauksenLisapalveluTieto varauksenLisapalveluTieto = new VarauksenLisapalveluTieto(varauksenLisapalvelutResult.getInt("varausId"),
                        varauksenLisapalvelutResult.getInt("asiakasId"), varauksenLisapalvelutResult.getString("etunimi"), varauksenLisapalvelutResult.getString("sukunimi"),
                        varauksenLisapalvelutResult.getInt("palveluId"), varauksenLisapalvelutResult.getString("palvelunNimi"), varauksenLisapalvelutResult.getDate("aloitusPvm"),
                        varauksenLisapalvelutResult.getDate("lopetusPvm"));
                varauksenLisapalveluTblView.getItems().add(varauksenLisapalveluTieto);
            }
            /*
             * Suljetaan tietokantayhteys
             */
            Tietokantayhteys.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(VarauksenLisapalveluTietoListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

