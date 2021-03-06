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
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * @author Leevi Meriläinen
 */
public class VarausViewController implements Initializable {

    /**
     * Määritellään varaus olio mihin haetaan tietoja tietokannasta
     */
    private Varaus m_varaus = new Varaus();

    @FXML
    private DatePicker aloitusPvmAddTextField;
    @FXML
    private DatePicker lopetusPvmAddTextField;
    @FXML
    private TextField asiakasIDAddTextField;
    @FXML
    private TextField toimipisteIDAddTextField;
    @FXML
    private DatePicker infoaloitusPvmTextField;
    @FXML
    private DatePicker infolopetusPvmTextField;
    @FXML
    private TextField infoasiakasIDTextField;
    @FXML
    private TextField infotoimipisteIDTextField;
    @FXML
    private TextField varausIdTextField;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addVaraus(ActionEvent event) {

        /*
         * Tarkistetaan että kaikissa tekstikentissä on tiedot
         */
        if (aloitusPvmAddTextField.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "AloitusPvm kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Varauksen tietojen lisäys");
            alert.show();
        } else if (lopetusPvmAddTextField.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "LopetusPvm kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Varauksen tietojen lisäys");
            alert.show();
        } else if (asiakasIDAddTextField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "asiakasID kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Varauksen tietojen lisäys");
            alert.show();
        } else if (toimipisteIDAddTextField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "toimipisteID kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Varauksen tietojen lisäys");
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
                 * Varauksen lisääminen tietokantaan
                 */
                
                Tietokantayhteys.addVaraus(conn, Integer.valueOf(asiakasIDAddTextField.getText()), Integer.valueOf(toimipisteIDAddTextField.getText()), java.sql.Date.valueOf(aloitusPvmAddTextField.getValue()), java.sql.Date.valueOf(lopetusPvmAddTextField.getValue()));
                /* 
                 * Tietokantayhteyden sulkeminen
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 * Asetetaan tekstikentät tyhjiksi asiakkaan lisäämisen jälkeen
                 */
                asiakasIDAddTextField.setText("");
                toimipisteIDAddTextField.setText("");
                aloitusPvmAddTextField.setValue(null);
                lopetusPvmAddTextField.setValue(null);
                /*
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen lisäämisestä
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Varauksen tietojen lisääminen");
                alert.setHeaderText("Tallennettu!");
                alert.setContentText("Varauksen tiedot tallennettu tietokantaan");
                alert.showAndWait();
                /*
                 * Ilmoitetaan virheestä käyttäjälle
                 */
            } catch (SQLException ex) {
                Logger.getLogger(VarausViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Varauksen tietojen lisääminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                /*
                 * Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                 */
                asiakasIDAddTextField.setText("");
                toimipisteIDAddTextField.setText("");
                aloitusPvmAddTextField.setValue(null);
                lopetusPvmAddTextField.setValue(null);
            } catch (Exception e) {
                /*
                 * muut virheet
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Varauksen lisääminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                /*
                 * Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                 */
                asiakasIDAddTextField.setText("");
                toimipisteIDAddTextField.setText("");
                aloitusPvmAddTextField.setValue(null);
                lopetusPvmAddTextField.setValue(null);

            }

        }

    }

    @FXML
    private void modVaraus(ActionEvent event) {

        /*
         * Tarkistetaan että kaikki tekstikentät ovat täyttetty
         */
        if (varausIdTextField.getText() == null || varausIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Varaus ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Varauksen tietojen muokkaus");
            alert.show();
        } else if (varausIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Varauksen ID tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (infoaloitusPvmTextField.getValue() == null || infoaloitusPvmTextField.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Varauksen aloitusPvm kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Varauksen tietojen muokkaus");
            alert.show();
        } else if (infolopetusPvmTextField.getValue() == null || infolopetusPvmTextField.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Varauksen lopetuspvm kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Varauksen tietojen muokkaus");
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
                 * Viedään muokatut varaus tiedot tietokantaan
                 */
                Tietokantayhteys.modVaraus(conn, Integer.valueOf(infoasiakasIDTextField.getText()), Integer.valueOf(infotoimipisteIDTextField.getText()), java.sql.Date.valueOf(infoaloitusPvmTextField.getValue()), java.sql.Date.valueOf(infolopetusPvmTextField.getValue()), Integer.parseInt(varausIdTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 *
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen muokkauksesta
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Varauksen tietojen päivitys");
                alert.setHeaderText("Päivitetty!");
                alert.setContentText("Varauksen tiedot päivitetty tietokantaan");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät onnistuneen tietojen muokkauksen
                 * jälkeen
                 */
                varausIdTextField.setText("");
                infoasiakasIDTextField.setText("");
                infotoimipisteIDTextField.setText("");
                infoaloitusPvmTextField.setValue(null);
                infolopetusPvmTextField.setValue(null);
                /*
                 * Ilmoitetaan käyttäjälle mikäli varausta ei löydy
                 * tietokannasta
                 */
            } catch (SQLException ex) {
                Logger.getLogger(VarausViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Varauksen tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Varausta ei loydy");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät epäonnistuneen tiedonsiirron
                 * jälkeen
                 */
                varausIdTextField.setText("");
                infoasiakasIDTextField.setText("");
                infotoimipisteIDTextField.setText("");
                infoaloitusPvmTextField.setValue(null);
                infolopetusPvmTextField.setValue(null);

            }

        }

    }

    /**
     * toiminto näkymän esittämiseen mihin on listattu kaikki asiakkaat
     * TableView komponenttiin
     */
    @FXML
    private void showVarauksetList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("VarausListView.fxml"));

        Scene scene = new Scene(root);
        /*
         * varaus lista näkymän määrittely, asetaan otsikko
         */
        Stage varauksetListStage = new Stage();
        varauksetListStage.setScene(scene);
        varauksetListStage.setTitle("Lista varauksista (A - Z)");
        varauksetListStage.show();
        System.out.println("\t>> Haettu lista varauksista onnistuneesti tietokannasta");
    }

    @FXML
    private void deleteVaraus(ActionEvent event) {

        /*
         * Tarkistetaan että poistettavan varauksen ID tekstikentässä on tieto
         * Ilmoitetaan käyttäjälle mikäli tieto puuttuu
         */
        if (varausIdTextField.getText() == null || varausIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "poistettavan varauksen ID tieto puuttuu", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Varauksen tietojen poistaminen");
            alert.show();
        } else if (varausIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Varauksen tietojen poistaminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            varausIdTextField.setText("");

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
                 * Otetaan käyttäjältä saatu Id tieto parametriksi
                 */
                Tietokantayhteys.deleteVaraus(conn, Integer.parseInt(varausIdTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen poistosta
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Varauksen tiedot poistettu tietokannasta", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setHeaderText("Poistettu!");
                alert.setTitle("Varauksen tietojen poistaminen");
                alert.show();
                /*
                 * Tyhjennetään tekstikenttä onnistuneen tietojen poiston
                 * jälkeen
                 */
                varausIdTextField.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(VarausViewController.class.getName()).log(Level.SEVERE, null, ex);
                /*
                 * ilmoitetaan sattuneesta virheestä käyttäjälle
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Jotain meni pieleen!");
                alert.setHeaderText("Virhe");
                alert.setContentText("Tietoja ei loydy");
                alert.showAndWait();

                varausIdTextField.setText("");

            }

        }

    }

    @FXML
    private void searchVaraus(ActionEvent event) {
        /*
         * haetaan tietokannasta varausta, jonka varausId = varausIdTextField
         */
        m_varaus = null;
        /*
         * Tarkistetaan että tekstikenttä on täytetty oikein
         */
        if (varausIdTextField.getText() == null || varausIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Varauksen ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Varauksen tietojen etsiminen");
            alert.show();
        } else if (varausIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Varauksen tietojen etsiminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            varausIdTextField.setText("");

        } else {
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
                 */
                m_varaus = Varaus.searchVaraus(conn, Integer.parseInt(varausIdTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);

            } catch (Exception e) {
                /*
                 * muut virheet
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Varauksen tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Varausta ei loydy");
                alert.showAndWait();

            }
            if (m_varaus.getaloitusPvm() == null) {
                /*
                 * muut virheet
                 */
                infoaloitusPvmTextField.setValue(null);
                infolopetusPvmTextField.setValue(null);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Varauksen tietojen hakeminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("Varausta ei loydy.");
                alert.showAndWait();

            }
        }
    }

    /**
     * toiminto tekstikenttien tyhjentämiseksi
     */
    @FXML
    private void clearTextFields(ActionEvent event) {
        varausIdTextField.setText("");
        infoaloitusPvmTextField.setValue(null);
        infolopetusPvmTextField.setValue(null);
        System.out.println("\t>> Varauksen tietojen hakukentät tyhjennetty");
    }

}
