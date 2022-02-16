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
 *
 * @author Leevi Meriläinen
 */
public class VarausListViewController implements Initializable {
    /**
     * kolumni varauksen ID tiedolle
     */
    @FXML
    private TableColumn<Varaus, Integer> varausIdColumn;
    /**
     * kolumni varauksen asiakaan ID tiedolle
     */
    @FXML
    private TableColumn<Asiakas, Integer> asiakasIDColumn;
    /**
     * kolumni varauksen toimipisteen ID tiedolle
     */
    @FXML
    private TableColumn<Toimipiste, Integer> toimipisteIDColumn;
    /**
     * kolumni varauksen lopetusPvm tiedolle
     */
    @FXML
    private TableColumn<Varaus, Date> lopetusPvmColumn;
    /**
     * kolumni varauksen aloitusPvm tiedolle
     */
    @FXML
    private TableColumn<Varaus, Date> aloitusPvmColumn;
    /**
     * TableView komponentti varausten tietojen esittämistä varten
     */
    @FXML
    private TableView<Varaus> varausTblView;

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
         * @param toimipisteIDColumn varauksen toimipisteen ID kolumni
         * @param lopetusPvmColumn varauksen lopetuspvm kolumni
         * @param aloitusPvmColumn varauksen aloituspvm kolumni
         */
        varausIdColumn.setCellValueFactory(new PropertyValueFactory<>("varausId"));
        asiakasIDColumn.setCellValueFactory(new PropertyValueFactory<>("asiakasID"));
        toimipisteIDColumn.setCellValueFactory(new PropertyValueFactory<>("toimipisteID"));
        lopetusPvmColumn.setCellValueFactory(new PropertyValueFactory<>("lopetusPvm"));
       aloitusPvmColumn.setCellValueFactory(new PropertyValueFactory<>("aloitusPvm"));
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
            ResultSet varauksetResult = Tietokantayhteys.selectVaraukset(conn);
            /*
             * Lisätään uudet varaus luokan ilmentymät TableView komponenttiin
             */
            while (varauksetResult.next()) {
                Varaus varaus = new Varaus(varauksetResult.getInt("varausId"), varauksetResult.getInt("asiakasID"), varauksetResult.getInt("toimipisteID"), varauksetResult.getDate("aloitusPvm"), varauksetResult.getDate("lopetusPvm"));
                varausTblView.getItems().add(varaus);
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
    

