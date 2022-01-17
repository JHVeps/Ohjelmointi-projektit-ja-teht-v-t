package toimistotilojenvarausapp;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
 * LaskuListViewController FXML Controller luokka sisältää TableView
 * komponentin ja siihen liitettyjen TableColumnien alustavat toiminnot laskutettujen
 * laskujen tietojen esittämiseen
 *
 * @author Joni Vepsäläinen, 2006234
 * @version 1.0
 */
public class LaskuListViewController implements Initializable {

    @FXML
    private TableColumn<Laskutettu, Integer> viitenumeroColumn;
    @FXML
    private TableColumn<Laskutettu, Integer> varausIdColumn;
    @FXML
    private TableColumn<Laskutettu, Integer> asiakasIdColumn;
    @FXML
    private TableColumn<Laskutettu, String> etunimiColumn;
    @FXML
    private TableColumn<Laskutettu, String> sukunimiColumn;
    @FXML
    private TableColumn<Laskutettu, String> summaColumn;
    @FXML
    private TableColumn<Laskutettu, Date> erapaivaColumn;
    @FXML
    private TableColumn<Laskutettu, String> laskutustapaColumn;
    @FXML
    private TableView<Laskutettu> laskutetutTblView;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        viitenumeroColumn.setCellValueFactory(new PropertyValueFactory<>("viitenumero"));
        varausIdColumn.setCellValueFactory(new PropertyValueFactory<>("varausId"));
        asiakasIdColumn.setCellValueFactory(new PropertyValueFactory<>("asiakasId"));
        etunimiColumn.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
        sukunimiColumn.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));
        summaColumn.setCellValueFactory(new PropertyValueFactory<>("summa"));
        erapaivaColumn.setCellValueFactory(new PropertyValueFactory<>("erapaiva"));
        laskutustapaColumn.setCellValueFactory((data -> new SimpleStringProperty(data.getValue().getLaskutustapa())));
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
            ResultSet laskutetutResult = Tietokantayhteys.selectLaskutetut(conn);
            /*
             * Lisätään uudet asiakas luokan ilmentymät TableView komponenttiin
             */
            while (laskutetutResult.next()) {
                Laskutettu Laskutettu = new Laskutettu(laskutetutResult.getInt("viitenumero"), laskutetutResult.getInt("varausId"), laskutetutResult.getInt("asiakasId"),
                        laskutetutResult.getString("etunimi"), laskutetutResult.getString("sukunimi"), laskutetutResult.getDouble("summa"),
                        laskutetutResult.getDate("erapaiva"), laskutetutResult.getString("laskutustapa"));
                laskutetutTblView.getItems().add(Laskutettu);
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
    

