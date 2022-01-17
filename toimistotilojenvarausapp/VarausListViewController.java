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
 * VarausListViewController FXML Controller luokka sisältää TableView
 * komponentin ja siihen liitettyjen TableColumnien toiminnot varauksen
 * tietojen esittämiseen
 * 
 * @author Leevi Meriläinen (@version 1.0)
 * @author Joni Vepsäläinen
 * @version 2.0
 */
public class VarausListViewController implements Initializable {
    /**
     * kolumni varauksen ID tiedolle
     */
    @FXML
    private TableColumn<VarausTieto, Integer> varausIdColumn;
    /**
     * kolumni varauksen asiakaan ID tiedolle
     */
    @FXML
    private TableColumn<VarausTieto, Integer> asiakasIDColumn;
    /**
     * kolumni varauksen asiakaan etunimi tiedolle
     */
    @FXML
    private TableColumn<VarausTieto, String> asiakasEtunimiColumn;
    /**
     * kolumni varauksen asiakaan sukunimi tiedolle
     */
    @FXML
    private TableColumn<VarausTieto, String> asiakasSukunimiColumn;
    
    /**
     * kolumni varauksen toimipisteen ID tiedolle
     */
    @FXML
    private TableColumn<VarausTieto, Integer> toimipisteIDColumn;
    /**
     * kolumni varauksen toimipisteen nimi tiedolle
     */
    @FXML
    private TableColumn<VarausTieto, String> toimipisteNimiColumn;
   
    /**
     * kolumni varauksen lopetusPvm tiedolle
     */
    @FXML
    private TableColumn<VarausTieto, Date> lopetusPvmColumn;
    /**
     * kolumni varauksen aloitusPvm tiedolle
     */
    @FXML
    private TableColumn<VarausTieto, Date> aloitusPvmColumn;
    
    
    /**
     * TableView komponentti varausten tietojen esittämistä varten
     */
    @FXML
    private TableView<VarausTieto> varausTblView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Varauksen tietojen sarakkeiden alustaminen
         *
         * @param varausIdColumn varauksen ID kolumni
         * @param asiakasIDColumn varauksen asiakkaan ID kolumni
         * @param asiakasEtunimi varauksen asiakkaan etunimi kolumni
         * @param asiakasEtunimi varauksen asiakkaan sukunimi kolumni
         * @param toimipisteIDColumn varauksen toimipisteen ID kolumni
         * @param toimipisteNimiColumn varauksen toimipisteen nimi kolumni
         * @param lopetusPvmColumn varauksen lopetuspvm kolumni
         * @param aloitusPvmColumn varauksen aloituspvm kolumni
         */
        varausIdColumn.setCellValueFactory(new PropertyValueFactory<>("varausId"));
        asiakasIDColumn.setCellValueFactory(new PropertyValueFactory<>("asiakasId"));
        asiakasEtunimiColumn.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
        asiakasSukunimiColumn.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));
        toimipisteIDColumn.setCellValueFactory(new PropertyValueFactory<>("toimipisteId"));
        toimipisteNimiColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));
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
            ResultSet varauksetResult = Tietokantayhteys.selectVaraukset(conn);
            /*
             * Lisätään uudet varaus luokan ilmentymät TableView komponenttiin
             */
            while (varauksetResult.next()) {
                VarausTieto varausTieto = new VarausTieto(varauksetResult.getInt("varausId"), varauksetResult.getInt("asiakasId"), varauksetResult.getString("etunimi"), 
                        varauksetResult.getString("sukunimi"),varauksetResult.getInt("toimipisteId"),varauksetResult.getString("nimi"), varauksetResult.getDate("aloitusPvm"), varauksetResult.getDate("lopetusPvm"));
                varausTblView.getItems().add(varausTieto);
            }
            /*
             * Suljetaan tietokantayhteys
             */
            Tietokantayhteys.closeConnection(conn);

        } catch (SQLException ex) {
            Logger.getLogger(VarausListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

