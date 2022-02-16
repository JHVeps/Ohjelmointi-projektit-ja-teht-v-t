package toimistotilojenvarausapp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * @author Sami Männistö
 * @version 1.0
 */

public class ToimipisteViewController implements Initializable {

    private Toimipiste toimipiste = new Toimipiste();

    @FXML
    private TextField nimiAddTextField;
    @FXML
    private TextField osoiteAddTextField;
    @FXML
    private TextField kuvausAddTextField;
    @FXML
    private TextField modNimiTextField;
    @FXML
    private TextField modOsoiteTextField;
    @FXML
    private TextField modKuvausTextField;
    @FXML
    private TextField modToimipisteIdTextField;
    @FXML
    private TextField searchToimipisteIdTextField;
    @FXML
    private TextField searchToimipisteNimiTextField;
    @FXML
    private TextField searchToimipisteOsoiteTextField;
    @FXML
    private TextField searchToimipisteKuvausTextField;
    @FXML
    private TextField deleteToimipisteIdTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addToimipiste(ActionEvent event) {
        /*
        Tietojen tarkasteleminen
         */
        if (nimiAddTextField.getText() == null || nimiAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nimi kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Toimipisteen tietojen lisäys");
            alert.show();
        } else if (osoiteAddTextField.getText() == null || osoiteAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Osoite kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Toimipisteen tietojen lisäys");
            alert.show();
        } else if (kuvausAddTextField.getText() == null || kuvausAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Kuvaus kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Toimipisteen tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else {
            /*
             * Otetaan yhteys tietokantaan
             */
            try {
                Connection conn = Tietokantayhteys.openConnection(
                        ""
                                + "");
                

                /*
                 * Määritetään käytettävä tietokanta
                 */
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                /*
                 * Toimipisteen lisääminen tietokantaan
                 */
                Tietokantayhteys.addToimispiste(conn, nimiAddTextField.getText(), osoiteAddTextField.getText(), kuvausAddTextField.getText());
                /*
                 * Tietokantayhteyden sulkeminen
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 * Asetetaan tekstikentät tyhjiksi toimipisteen lisäämisen jälkeen
                 */
                nimiAddTextField.setText("");
                osoiteAddTextField.setText("");
                kuvausAddTextField.setText("");
                /*
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen lisäämisestä
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Toimipisteen tietojen lisääminen");
                alert.setHeaderText("Tallennettu!");
                alert.setContentText("Toimipisteen tiedot tallennettu tietokantaan");
                alert.showAndWait();
                /*
                 * Ilmoitetaan virheestä jos sellainen sattuu
                 */
            } catch (SQLException ex) {
                Logger.getLogger(ToimipisteViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Toimipisteen tietojen lisääminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                /*
                 * Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                 */
                nimiAddTextField.setText("");
                osoiteAddTextField.setText("");
                kuvausAddTextField.setText("");
            } catch (Exception e) {
                /*
                 * muut virheet
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Toimipisteen lisääminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                /*
                 * Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                 */
                nimiAddTextField.setText("");
                osoiteAddTextField.setText("");
                kuvausAddTextField.setText("");

            }

        }

    }

    @FXML
    private void modToimipiste(ActionEvent event) {

        /*
         * Tarkistetaan että kaikki tekstikentät ovat täyttetty
         */
        if (modToimipisteIdTextField.getText() == null || modToimipisteIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen muokkaus");
            alert.show();
        } else if (modToimipisteIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Asiakkaan ID tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (modNimiTextField.getText() == null || modNimiTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan etunimi kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen muokkaus");
            alert.show();
        } else if (modOsoiteTextField.getText() == null || modOsoiteTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan sukunimi kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen muokkaus");
            alert.show();

        } else {
            /*
             * Otetaan yhteys tietokantaan
             */
            try {
                Connection conn = Tietokantayhteys.openConnection(
                        ""
                                + "");
                

                /*
                 * Määritetään käytettävä tietokanta
                 */
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                /*
                 * Viedään muokatut asiakkaan tiedot tietokantaan
                 */
                Tietokantayhteys.modToimipiste(conn, modNimiTextField.getText(), modOsoiteTextField.getText(), modKuvausTextField.getText(), Integer.parseInt(modToimipisteIdTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 *
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen muokkauksesta
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Toimipisteen tietojen päivitys");
                alert.setHeaderText("Päivitetty!");
                alert.setContentText("Toimipisteen tiedot päivitetty tietokantaan");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät kun muokattu
                 */
                modToimipisteIdTextField.setText("");
                modNimiTextField.setText("");
                modOsoiteTextField.setText("");
                modKuvausTextField.setText("");
                /*
                 * Ilmoitetaan toimipistetä mikäli ei löytynyt
                 */
            } catch (SQLException ex) {
                Logger.getLogger(ToimipisteViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Toimipisteen tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Toimipistettä ei loydy");
                alert.showAndWait();
                /*
                 * Tyhjennys ellei löytynyt
                 */
                modToimipisteIdTextField.setText("");
                modNimiTextField.setText("");
                modOsoiteTextField.setText("");
                modKuvausTextField.setText("");
            }

        }

    }

    /**
     * toiminto näkymän tableview toimipisteet
     */
    @FXML
    private void showToimipisteetList(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ToimipisteListView.fxml"));

        Scene scene = new Scene(root);
        /*
         * toimipiste lista näkymän määrittely ja otsikko
         */
        Stage toimipisteListStage = new Stage();
        toimipisteListStage.setScene(scene);
        toimipisteListStage.setTitle("Lista toimipisteistä (A - Z)");
        toimipisteListStage.show();
        System.out.println("\t>> Haettu lista toimipisteistä onnistuneesti tietokannasta");
    }

    @FXML
    private void deleteToimipiste(ActionEvent event) {

        /*
         * Tarkistetaan että poistettavan asiakkaan ID tekstikentässä on tieto
         * Ilmoitetaan käyttäjälle mikäli tieto puuttuu
         */
        if (deleteToimipisteIdTextField.getText() == null || deleteToimipisteIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "poistettavan toimipisteen ID tieto puuttuu", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Toimipisteen tietojen poistaminen");
            alert.show();
        } else if (deleteToimipisteIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Toimipisteen tietojen poistaminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            deleteToimipisteIdTextField.setText("");

        } else {
            /*
             * Tietokantaan yhdistäminen
             */
            try {
                Connection conn = Tietokantayhteys.openConnection(
                        ""
                                + "");                


                /*
                 * Määritetään käytettävä tietokanta
                 */
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                /*
                 * Otetaan käyttäjältä saatu Id tieto parametriksi
                 */
                Tietokantayhteys.deleteToimipiste(conn, Integer.parseInt(deleteToimipisteIdTextField.getText()));
                /*
                 * Tietokanta yhteyden sulkeminen
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen poistosta
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Toimipisteen tiedot poistettu tietokannasta", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setHeaderText("Poistettu!");
                alert.setTitle("Toimipisteen tietojen poistaminen");
                alert.show();
                /*
                 * Tyhjennetään tekstikenttä onnistuneen tietojen poiston
                 * jälkeen
                 */
                deleteToimipisteIdTextField.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(ToimipisteViewController.class.getName()).log(Level.SEVERE, null, ex);
                /*
                 * ilmoitetaan sattuneesta virheestä käyttäjälle
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Jotain meni pieleen!");
                alert.setHeaderText("Virhe");
                alert.setContentText("Tietoja ei loydy");
                alert.showAndWait();

                deleteToimipisteIdTextField.setText("");

            }

        }

    }

    @FXML
    private void searchToimipiste(ActionEvent event) {
        /*
         * haetaan tietokannasta toimipistettä vastaavalla id arvolla
         */
        toimipiste = null;
        /*
         * Tarkistetaan että tekstikenttä on täytetty oikein
         */
        if (searchToimipisteIdTextField.getText() == null || searchToimipisteIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Toimipiste ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Toimipisteen tietojen etsiminen");
            alert.show();
        } else if (searchToimipisteIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Asiakkaan tietojen etsiminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            searchToimipisteIdTextField.setText("");

        } else {
            try {
                /*
                 * Tietokantaan yhdistäminen
                 */
                Connection conn = Tietokantayhteys.openConnection(
                        ""
                                + "");
                
                /*
                 * Määritetään käytettävä tietokanta
                 */
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                /*
                 */
                toimipiste = Toimipiste.searchToimipiste(conn, Integer.parseInt(searchToimipisteIdTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);

            } catch (Exception e) {
                /*
                 * muut virheet
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Asiakkaan tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Toimipistettä ei loydy");
                alert.showAndWait();

            }
            if (toimipiste.getNimi() == null) {
                /*
                 * muut virheet
                 */
                searchToimipisteNimiTextField.setText("");
                searchToimipisteOsoiteTextField.setText("");
                searchToimipisteKuvausTextField.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Asiakkaan tietojen hakeminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("Toimipistettä ei loydy.");
                alert.showAndWait();

            } else {
                /*
                 * asetetaan haetut tiedot tekstikenttiin
                 */
                searchToimipisteNimiTextField.setText(toimipiste.getNimi());
                searchToimipisteOsoiteTextField.setText(toimipiste.getOsoite());
                searchToimipisteKuvausTextField.setText(toimipiste.getKuvaus());
                System.out.println("\t>> Haettu toimipiste onnistuneesti tietokannasta!");
            }

        }
    }

    /**
     * toiminto tekstikenttien tyhjentämiseksi
     */
    @FXML
    private void clearTextFields(ActionEvent event) {
        searchToimipisteIdTextField.setText("");
        searchToimipisteNimiTextField.setText("");
        searchToimipisteOsoiteTextField.setText("");
        searchToimipisteKuvausTextField.setText("");
        System.out.println("\t>> Toimipisteiden tietojen hakukentät tyhjennetty");
    }


}