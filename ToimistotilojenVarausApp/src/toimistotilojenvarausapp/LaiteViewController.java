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
 * @author Leevi Meriläinen
 */
public class LaiteViewController implements Initializable {

    /**
     * Määritellään laite olio mihin haetaan tietoja tietokannasta
     */
    private Laite m_laite = new Laite();

    @FXML
    private TextField HintaAddTextField;
    @FXML
    private TextField KuvausAddTextField;
    @FXML
    private TextField infoHintaTextField;
    @FXML
    private TextField infoKuvausTextField;
    @FXML
    private TextField laitteenNimiTextField;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addLaite(ActionEvent event) {

        /*
         * Tarkistetaan että kaikissa tekstikentissä on tiedot
         */
        if (HintaAddTextField.getText() == null || HintaAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Hinta kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laitteen tietojen lisäys");
            alert.show();
        } else if (KuvausAddTextField.getText() == null || KuvausAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Kuvaus kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laitteen tietojen lisäys");
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
                 * Laitten lisääminen tietokantaan
                 */
                Tietokantayhteys.addLaite(conn, laitteenNimiTextField.getText(), Integer.parseInt(HintaAddTextField.getText()), KuvausAddTextField.getText());
                /*
                 * Tietokantayhteyden sulkeminen
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 * Asetetaan tekstikentät tyhjiksi asiakkaan lisäämisen jälkeen
                 */
                HintaAddTextField.setText("");
                KuvausAddTextField.setText("");
                /*
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen lisäämisestä
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Laitteen tietojen lisääminen");
                alert.setHeaderText("Tallennettu!");
                alert.setContentText("Laitteen tiedot tallennettu tietokantaan");
                alert.showAndWait();
                /*
                 * Ilmoitetaan virheestä käyttäjälle
                 */
            } catch (SQLException ex) {
                Logger.getLogger(LaiteViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laitteen tietojen lisääminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                /*
                 * Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                 */
                HintaAddTextField.setText("");
                KuvausAddTextField.setText("");
            } catch (Exception e) {
                /*
                 * muut virheet
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laitteen lisääminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                /*
                 * Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                 */
                HintaAddTextField.setText("");
                KuvausAddTextField.setText("");

            }

        }

    }

    @FXML
    private void modLaite(ActionEvent event) {

        /*
         * Tarkistetaan että kaikki tekstikentät ovat täyttetty
         */
        if (laitteenNimiTextField.getText() == null || laitteenNimiTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Laitteen nimi kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laitteen tietojen muokkaus");
            alert.show();
        } else if (laitteenNimiTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laitteen nimen tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (infoHintaTextField.getText() == null || infoHintaTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Laitteen hinta kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laitteen tietojen muokkaus");
            alert.show();
        } else if (infoKuvausTextField.getText() == null || infoKuvausTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Laitteen kuvaus kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laitteen tietojen muokkaus");
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
                Tietokantayhteys.modLaite(conn, laitteenNimiTextField.getText(), Integer.parseInt(infoHintaTextField.getText()), infoKuvausTextField.getText());
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 *
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen muokkauksesta
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Laitteen tietojen päivitys");
                alert.setHeaderText("Päivitetty!");
                alert.setContentText("Laitteen tiedot päivitetty tietokantaan");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät onnistuneen tietojen muokkauksen
                 * jälkeen
                 */
                laitteenNimiTextField.setText("");
                infoHintaTextField.setText("");
                infoKuvausTextField.setText("");
                /*
                 * Ilmoitetaan käyttäjälle mikäli laitetta ei löydy
                 * tietokannasta
                 */
            } catch (SQLException ex) {
                Logger.getLogger(LaiteViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laitteen tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Laitetta ei loydy");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät epäonnistuneen tiedonsiirron
                 * jälkeen
                 */
                laitteenNimiTextField.setText("");
                infoHintaTextField.setText("");
                infoKuvausTextField.setText("");

            }

        }

    }

    /**
     * toiminto näkymän esittämiseen mihin on listattu kaikki laitteet
     * TableView komponenttiin
     */
    @FXML
    private void showLaitteetList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LaiteListView.fxml"));

        Scene scene = new Scene(root);
        /*
         * laite lista näkymän määrittely, asetaan otsikko
         */
        Stage laitteetListStage = new Stage();
        laitteetListStage.setScene(scene);
        laitteetListStage.setTitle("Lista laitteista (A - Z)");
        laitteetListStage.show();
        System.out.println("\t>> Haettu lista laitteista onnistuneesti tietokannasta");
    }

    @FXML
    private void deleteLaite(ActionEvent event) {

        /*
         * Tarkistetaan että poistettavan laitteen nimi tekstikentässä on tieto
         * Ilmoitetaan käyttäjälle mikäli tieto puuttuu
         */
        if (laitteenNimiTextField.getText() == null || laitteenNimiTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "poistettavan laitteen nimi tieto puuttuu", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laitteen tietojen poistaminen");
            alert.show();
        } else if (laitteenNimiTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä merkkijono tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laitteen tietojen poistaminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            laitteenNimiTextField.setText("");

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
                                            //Tietokantayhteys.deleteLaite(conn, Integer.parseInt(laitteenNimiTextField.getText()));
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
                laitteenNimiTextField.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(LaiteViewController.class.getName()).log(Level.SEVERE, null, ex);
                /*
                 * ilmoitetaan sattuneesta virheestä käyttäjälle
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Jotain meni pieleen!");
                alert.setHeaderText("Virhe");
                alert.setContentText("Tietoja ei loydy");
                alert.showAndWait();

                laitteenNimiTextField.setText("");

            }

        }

    }

    @FXML
    private void searchLaite(ActionEvent event) {
        /*
         * haetaan tietokannasta laitetta, jonka laitteennimi = laitteenNimiTextField
         */
        m_laite = null;
        /*
         * Tarkistetaan että tekstikenttä on täytetty oikein
         */
        if (laitteenNimiTextField.getText() == null || laitteenNimiTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Laitteen nimi kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laitteen tietojen etsiminen");
            alert.show();
        } else if (laitteenNimiTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä merkkijono tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laitteen tietojen etsiminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            laitteenNimiTextField.setText("");

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
                m_laite = Laite.searchLaite(conn, (laitteenNimiTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);

            } catch (Exception e) {
                /*
                 * muut virheet
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("LAitteen tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Laitetta ei loydy");
                alert.showAndWait();

            }
            if (m_laite.getHinta() == null) {
                /*
                 * muut virheet
                 */
                infoHintaTextField.setText("");
                infoKuvausTextField.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("LAitteen tietojen hakeminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("LAitetta ei loydy.");
                alert.showAndWait();

            }
        }
    }

    /**
     * toiminto tekstikenttien tyhjentämiseksi
     */
    @FXML
    private void clearTextFields(ActionEvent event) {
        laitteenNimiTextField.setText("");
        infoHintaTextField.setText("");
        infoKuvausTextField.setText("");
        System.out.println("\t>> Laitteen tietojen hakukentät tyhjennetty");
    }

}
