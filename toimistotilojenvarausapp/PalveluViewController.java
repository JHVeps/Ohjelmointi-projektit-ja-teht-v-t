package toimistotilojenvarausapp;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.spi.AbstractInterruptibleChannel;
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
 * PalveluViewController FXML Controller luokka sisältää näkymät ja
 * toiminnot palveluiden tietojen lisäämistä, muuttamista, hakemista
 * tietokannasta ja poistamista varten
 *
 * @author Sami Männistö
 * @version 1.0
 */

public class PalveluViewController implements Initializable {

    private Palvelu palvelu = new Palvelu();

    @FXML
    private TextField nimiAddTextField;
    @FXML
    private TextField hintaAddTextField;
    @FXML
    private TextField tyyppiAddTextField;
    @FXML
    private TextField kuvausAddTextField;
    @FXML
    private TextField toimipisteIdAddTextField;

    @FXML
    private TextField nimiInfoTextField;
    @FXML
    private TextField hintaInfoTextField;
    @FXML
    private TextField tyyppiInfoTextField;
    @FXML
    private TextField kuvausInfoTextField;
    @FXML
    private TextField toimipisteIdInfoTextField;
    @FXML
    private TextField palveluIdInfoTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void addPalvelu(ActionEvent event) {
        if (nimiAddTextField.getText() == null || nimiAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nimi kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Palvelun tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (hintaAddTextField.getText() == null || hintaAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Hinta kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Palvelun tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (tyyppiAddTextField.getText() == null || tyyppiAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Tyyppi kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Palvelun tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (kuvausAddTextField.getText() == null || kuvausAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Kuvaus kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Palvelun tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (toimipisteIdAddTextField.getText() == null || toimipisteIdAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ToimipisteId kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Palvelun tietojen lisäys");
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
                 * Asiakkaan lisääminen tietokantaan
                 */
                Tietokantayhteys.addPalvelu(conn, nimiAddTextField.getText(), Double.parseDouble(hintaAddTextField.getText()), Integer.parseInt(tyyppiAddTextField.getText()),
                        kuvausAddTextField.getText(), Integer.parseInt(toimipisteIdAddTextField.getText()));

                 /*
                Tietokantayhteyden sulkeminen
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 * Asetetaan tekstikentät tyhjiksi palveluiden lisäämisen jälkeen
                 */
                nimiAddTextField.setText("");
                hintaAddTextField.setText("");
                tyyppiAddTextField.setText("");
                kuvausAddTextField.setText("");
                toimipisteIdAddTextField.setText("");
                /*
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen lisäämisestä
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Palvelun tietojen lisääminen");
                alert.setHeaderText("Tallennettu!");
                alert.setContentText("Palvelun tiedot tallennettu tietokantaan");
                alert.showAndWait();
                /*
                 * Ilmoitetaan virheestä käyttäjälle
                 */
            } catch (SQLException ex) {
                Logger.getLogger(AsiakasViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Palvelun tietojen lisääminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                /*
                 * Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                 */
                nimiAddTextField.setText("");
                hintaAddTextField.setText("");
                tyyppiAddTextField.setText("");
                kuvausAddTextField.setText("");
                toimipisteIdAddTextField.setText("");
            } catch (Exception e) {
                /*
                 * muut virheet
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Palvelun lisääminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                /*
                 * Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                 */
                nimiAddTextField.setText("");
                hintaAddTextField.setText("");
                tyyppiAddTextField.setText("");
                kuvausAddTextField.setText("");
                toimipisteIdAddTextField.setText("");
            }
        }
    }

    @FXML
    private void modPalvelu(ActionEvent event) {
        /*
         * Tarkistetaan että kaikki tekstikentät ovat täyttetty
         */
        if (palveluIdInfoTextField.getText() == null || palveluIdInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Palvelu ID kenttä puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Palvelun tietojen muokkaus");
            alert.show();
        } else if (palveluIdInfoTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Palvelun ID tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (nimiInfoTextField.getText() == null || nimiInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Palvelun nimi kenttä puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Palvelun tietojen muokkaus");
            alert.show();
        } else if (hintaInfoTextField.getText() == null || hintaInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Palvelun hinta kenttä puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Palvelun tietojen muokkaus");
            alert.show();
        } else if (hintaInfoTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Palvelun hintatietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (tyyppiInfoTextField.getText() == null || tyyppiInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Palvelun tyyppi kenttä puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Palvelun tietojen muokkaus");
            alert.show();
        } else if (tyyppiInfoTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Palvelun tyypin lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (kuvausInfoTextField.getText() == null || kuvausInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Palvelun kuvaus kenttä puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Palvelun tietojen muokkaus");
            alert.show();
        } else if (toimipisteIdInfoTextField.getText() == null || toimipisteIdInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Palvelun toimipisteId kenttä puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Palvelun tietojen muokkaus");
            alert.show();
        } else if (toimipisteIdInfoTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Palvelun toimipisteId lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();

        } else {
            /*
             * Otetaan yhteys tietokantaan
             */
            try {
                Connection conn = Tietokantayhteys.openConnection(
                        "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                        + "3306?user=opiskelija&password=opiskelija1");

                /*
                 * Määritetään käytettävä tietokanta
                 */
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                /*
                 * Viedään muokatut asiakkaan tiedot tietokantaan
                 */
                Tietokantayhteys.modPalvelu(conn, nimiInfoTextField.getText(), Double.parseDouble(hintaInfoTextField.getText()), Integer.parseInt(tyyppiInfoTextField.getText()), kuvausInfoTextField.getText(),
                        Integer.parseInt(toimipisteIdInfoTextField.getText()), Integer.parseInt(palveluIdInfoTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 *
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen muokkauksesta
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Palvelun tietojen päivitys");
                alert.setHeaderText("Päivitetty!");
                alert.setContentText("Palvelun tiedot päivitetty tietokantaan");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät onnistuneen tietojen muokkauksen
                 * jälkeen
                 */
                palveluIdInfoTextField.setText("");
                nimiInfoTextField.setText("");
                hintaInfoTextField.setText("");
                tyyppiInfoTextField.setText("");
                kuvausInfoTextField.setText("");
                toimipisteIdInfoTextField.setText("");
                /*
                 * Ilmoitetaan käyttäjälle mikäli asiakasta ei löydy
                 * tietokannasta
                 */
            } catch (SQLException ex) {
                Logger.getLogger(AsiakasViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Palvelun tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Palvelua ei löydy");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät epäonnistuneen tiedonsiirron
                 * jälkeen
                 */
                palveluIdInfoTextField.setText("");
                nimiInfoTextField.setText("");
                hintaInfoTextField.setText("");
                tyyppiInfoTextField.setText("");
                kuvausInfoTextField.setText("");
                toimipisteIdInfoTextField.setText("");
            }

        }

    }

    @FXML
    private void showPalvelutList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PalveluListView.fxml"));

        Scene scene = new Scene(root);
        /*
         * palvelu lista näkymän määrittely, asetaan otsikko
         */
        Stage palvelutListStage = new Stage();
        palvelutListStage.setScene(scene);
        palvelutListStage.setTitle("Lista palveluista (A - Z)");
        palvelutListStage.show();
        System.out.println("\t>> Haettu lista palveluista onnistuneesti tietokannasta");
    }

    @FXML
    private void deletePalvelu (ActionEvent event) {

        if (palveluIdInfoTextField.getText() == null || palveluIdInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Poistettavan palvelun ID tieto puuttuu", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Palvelun tietojen poistaminen");
            alert.show();
        } else if (palveluIdInfoTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Palvelun tietojen poistaminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            palveluIdInfoTextField.setText("");
            nimiInfoTextField.setText("");
            hintaInfoTextField.setText("");
            tyyppiInfoTextField.setText("");
            kuvausInfoTextField.setText("");
            toimipisteIdInfoTextField.setText("");

        } else {
            /*
             * Otetaan yhteys tietokantaan
             */
            try {
                Connection conn = Tietokantayhteys.openConnection(
                        "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                        + "3306?user=opiskelija&password=opiskelija1");


                /*
                 * Määritetään käytettävä tietokanta
                 */
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                /*
                 * Otetaan käyttäjältä saatu Id tieto parametriksi
                 */
                Tietokantayhteys.deletePalvelu(conn, Integer.parseInt(palveluIdInfoTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen poistosta
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Palvelun tiedot poistettu tietokannasta", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setHeaderText("Poistettu!");
                alert.setTitle("Palvelun tietojen poistaminen");
                alert.show();

                /*
                 * Tyhjennetään tekstikenttä onnistuneen tietojen poiston
                 * jälkeen
                 */
                palveluIdInfoTextField.setText("");
                nimiInfoTextField.setText("");
                hintaInfoTextField.setText("");
                tyyppiInfoTextField.setText("");
                kuvausInfoTextField.setText("");
                toimipisteIdInfoTextField.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(AsiakasViewController.class.getName()).log(Level.SEVERE, null, ex);
                /*
                 * ilmoitetaan sattuneesta virheestä käyttäjälle
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Jotain meni pieleen!");
                alert.setHeaderText("Virhe");
                alert.setContentText("Tietoja ei löydy");
                alert.showAndWait();

                palveluIdInfoTextField.setText("");
                nimiInfoTextField.setText("");
                hintaInfoTextField.setText("");
                tyyppiInfoTextField.setText("");
                kuvausInfoTextField.setText("");
                toimipisteIdInfoTextField.setText("");

            }

        }

    }

    @FXML
    private void searchPalvelu (ActionEvent event) {
    /*
     * haetaan tietokannasta palvelua, jonka palveluId = palveluIdTextField
     */
        palvelu = null;
    /*
     * Tarkistetaan että tekstikenttä on täytetty oikein
     */
        if (palveluIdInfoTextField.getText() == null || palveluIdInfoTextField.getText().trim().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Palvelu ID kentta puuttuu tieto!", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText("Virhe!");
        alert.setTitle("Palvelun tietojen etsiminen");
        alert.show();
    } else if (palveluIdInfoTextField.getText().matches("[a-zA-Z]*")) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Palvelun tietojen etsiminen");
        alert.setHeaderText("Virhe!");
        alert.show();

        palveluIdInfoTextField.setText("");
        nimiInfoTextField.setText("");
        hintaInfoTextField.setText("");
        tyyppiInfoTextField.setText("");
        kuvausInfoTextField.setText("");
        toimipisteIdInfoTextField.setText("");

        } else {
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
                 */
                palvelu = Palvelu.searchPalvelu(conn, Integer.parseInt(palveluIdInfoTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);

            } catch (Exception e) {
                /*
                 * muut virheet
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Palvelun tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Palvelua ei löydy");
                alert.showAndWait();

            }
            if (palvelu.getNimi() == null) {
                /*
                 * muut virheet
                 */
                palveluIdInfoTextField.setText("");
                nimiInfoTextField.setText("");
                hintaInfoTextField.setText("");
                tyyppiInfoTextField.setText("");
                kuvausInfoTextField.setText("");
                toimipisteIdInfoTextField.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Palvelun tietojen hakeminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("Palvelua ei löydy");
                alert.showAndWait();

            } else {
                /*
                 * asetetaan haetut tiedot tekstikenttiin
                 */
                nimiInfoTextField.setText(palvelu.getNimi());
                hintaInfoTextField.setText(String.valueOf(palvelu.getHinta()));
                tyyppiInfoTextField.setText(String.valueOf((palvelu.getTyyppi())));
                kuvausInfoTextField.setText(palvelu.getKuvaus());
                toimipisteIdInfoTextField.setText(String.valueOf(palvelu.getToimipisteId()));
                System.out.println("\t>> Haettu palvelu onnistuneesti tietokannasta!");

            }
        }
    }

    /**
     * toiminto tekstikenttien tyhjentämiseksi
     */
    @FXML
    private void clearTextFields(ActionEvent event) {
        palveluIdInfoTextField.setText("");
        nimiInfoTextField.setText("");
        hintaInfoTextField.setText("");
        tyyppiInfoTextField.setText("");
        kuvausInfoTextField.setText("");
        toimipisteIdInfoTextField.setText("");
        System.out.println("\t>> Palvelun tietojen hakukentät tyhjennetty");
    }

}
