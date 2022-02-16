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
 * AsiakasViewController FXML Controller luokka sisältää näkymät ja
 * toiminnot asiakkaan tietojen lisäämistä, muuttamista, hakemista
 * tietokannasta ja poistamista varten
 *
 * @author Joni Vepsäläinen
 * @version 1.0
 */
public class AsiakasViewController implements Initializable {

    /**
     * Määritellään asiakas olio mihin haetaan tietoja tietokannasta
     */
    private Asiakas m_asiakas = new Asiakas();

    @FXML
    private TextField etunimiAddTextField;
    @FXML
    private TextField sukunimiAddTextField;
    @FXML
    private TextField orgAddTextField;

    @FXML
    private TextField lahiosoiteAddTextField;
    @FXML
    private TextField postinumeroAddTextField;
    @FXML
    private TextField puhelinAddTextField;
    @FXML
    private TextField etunimiInfoTextField;
    @FXML
    private TextField sukunimiInfoTextField;
    @FXML
    private TextField orgInfoTextField;
    @FXML
    private TextField emailInfoTextField;
    @FXML
    private TextField postinumeroInfoTextField;
    @FXML
    private TextField puhelinInfoTextField;
    @FXML
    private TextField asiakasIdInfoTextField;
    @FXML
    private TextField lahiosoiteInfoTextField;
    @FXML
    private TextField emailAddTextField;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addAsiakas(ActionEvent event) {
        //TESTATTU! Tyhjillä tekstikentillä tulee ilmoitus käyttäjälle. Tekstikenttiin syötetyt tiedot 
        //päivittyvät titokantaan "lisää" painikkeen painalluksella.
        /*
         * Tarkistetaan että kaikissa tekstikentissä on tiedot
         */
        if (etunimiAddTextField.getText() == null || etunimiAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Etunimi kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen lisäys");
            alert.show();
        } else if (sukunimiAddTextField.getText() == null || sukunimiAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Sukunimi kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen lisäys");
            alert.show();
        } else if (orgAddTextField.getText() == null || orgAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Organiosaatio kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen lisäys");
            alert.show();
        } else if (emailAddTextField.getText() == null || emailAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "email kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Asiakkaan tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        }else if (lahiosoiteAddTextField.getText() == null || lahiosoiteAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "lähiosoite kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen lisäys");
            alert.show();
        } else if (postinumeroAddTextField.getText() == null || postinumeroAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "postinumero kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen lisäys");
            alert.show();
        } else if (puhelinAddTextField.getText() == null || puhelinAddTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "puhelin kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Asiakkaan tietojen lisäys");
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
                Tietokantayhteys.addAsiakas(conn, etunimiAddTextField.getText(), sukunimiAddTextField.getText(), orgAddTextField.getText(), 
                        emailAddTextField.getText(),lahiosoiteAddTextField.getText(), postinumeroAddTextField.getText(), puhelinAddTextField.getText());
                /*
                 * Tietokantayhteyden sulkeminen
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 * Asetetaan tekstikentät tyhjiksi asiakkaan lisäämisen jälkeen
                 */
                etunimiAddTextField.setText("");
                sukunimiAddTextField.setText("");
                orgAddTextField.setText("");
                emailAddTextField.setText("");
                lahiosoiteAddTextField.setText("");
                postinumeroAddTextField.setText("");
                puhelinAddTextField.setText("");                
                /*
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen lisäämisestä
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Asiakkaan tietojen lisääminen");
                alert.setHeaderText("Tallennettu!");
                alert.setContentText("Asiakkaan tiedot tallennettu tietokantaan");
                alert.showAndWait();
                /*
                 * Ilmoitetaan virheestä käyttäjälle
                 */
            } catch (SQLException ex) {
                Logger.getLogger(AsiakasViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Asiakkaan tietojen lisääminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                /*
                 * Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                 */
                etunimiAddTextField.setText("");
                sukunimiAddTextField.setText("");
                orgAddTextField.setText("");
                emailAddTextField.setText("");
                lahiosoiteAddTextField.setText("");
                postinumeroAddTextField.setText("");
                puhelinAddTextField.setText("");
            } catch (Exception e) {
                /*
                 * muut virheet
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Asiakkaan lisääminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                /*
                 * Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                 */
                etunimiAddTextField.setText("");
                sukunimiAddTextField.setText("");
                orgAddTextField.setText("");
                emailAddTextField.setText("");
                lahiosoiteAddTextField.setText("");
                postinumeroAddTextField.setText("");
                puhelinAddTextField.setText("");
            }

        }

    }

    @FXML
    private void modAsiakas(ActionEvent event) {
        //TESTATTU! Asiakas ID kenttä toimii vain kokonaisluku syötteillä ja tekstikentät eivät voi olla tyhjänä.
        //Näistä tulee ilmoitus käyttäjälle. Kaikissa kentissä tulee olla päivitettävää tietoa. Tiedot päivittyvät 
        //tietokantaan "muokkaa" napin painalluksella.
        /*
         * Tarkistetaan että kaikki tekstikentät ovat täyttetty
         */
        if (asiakasIdInfoTextField.getText() == null || asiakasIdInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen muokkaus");
            alert.show();
        } else if (asiakasIdInfoTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Asiakkaan ID tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (etunimiInfoTextField.getText() == null || etunimiInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan etunimi kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen muokkaus");
            alert.show();
        } else if (sukunimiInfoTextField.getText() == null || sukunimiInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan sukunimi kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen muokkaus");
            alert.show();
        } else if (orgInfoTextField.getText() == null || orgInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan organisaatio kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen muokkaus");
            alert.show();
        } else if (emailInfoTextField.getText() == null || emailInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan email kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen muokkaus");
            alert.show();

        }  else if (lahiosoiteInfoTextField.getText() == null || lahiosoiteInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan lähiosoite kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen muokkaus");
            alert.show();
        } else if (postinumeroInfoTextField.getText() == null || postinumeroInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan postinumero kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen muokkaus");
            alert.show();
        } else if (puhelinInfoTextField.getText() == null || puhelinInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan puhelin kentta puuttuu tieto!", ButtonType.OK);
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
                Tietokantayhteys.modAsiakas(conn, etunimiInfoTextField.getText(), sukunimiInfoTextField.getText(), orgInfoTextField.getText(), emailInfoTextField.getText(), 
                        lahiosoiteInfoTextField.getText(), postinumeroInfoTextField.getText(), puhelinInfoTextField.getText(), Integer.parseInt(asiakasIdInfoTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 *
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen muokkauksesta
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Asiakkaan tietojen päivitys");
                alert.setHeaderText("Päivitetty!");
                alert.setContentText("Asiakkaan tiedot päivitetty tietokantaan");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät onnistuneen tietojen muokkauksen
                 * jälkeen
                 */
                asiakasIdInfoTextField.setText("");
                etunimiInfoTextField.setText("");
                sukunimiInfoTextField.setText("");
                orgInfoTextField.setText("");
                emailInfoTextField.setText("");
                lahiosoiteInfoTextField.setText("");
                postinumeroInfoTextField.setText("");
                puhelinInfoTextField.setText("");                
                /*
                 * Ilmoitetaan käyttäjälle mikäli asiakasta ei löydy
                 * tietokannasta
                 */
            } catch (SQLException ex) {
                Logger.getLogger(AsiakasViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Asiakkaan tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Asiakasta ei loydy");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät epäonnistuneen tiedonsiirron
                 * jälkeen
                 */
                asiakasIdInfoTextField.setText("");
                etunimiInfoTextField.setText("");
                sukunimiInfoTextField.setText("");
                orgInfoTextField.setText("");
                emailInfoTextField.setText("");
                lahiosoiteInfoTextField.setText("");
                postinumeroInfoTextField.setText("");
                puhelinInfoTextField.setText("");

            }

        }

    }
    //TESTATTU! "Näytä" napin painalluksella tulee table view näkymä mihin on listattu
    //asiakkaat sukunimen mukaan järjestykseen A-Z.
    /**
     * toiminto näkymän esittämiseen mihin on listattu kaikki asiakkaat
     * TableView komponenttiin
     */
    @FXML
    private void showAsiakkaatList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AsiakasListView.fxml"));

        Scene scene = new Scene(root);
        /*
         * asiakas lista näkymän määrittely, asetaan otsikko
         */
        Stage asiakkaatListStage = new Stage();
        asiakkaatListStage.setScene(scene);
        asiakkaatListStage.setTitle("Lista asiakkaista (A - Z)");
        asiakkaatListStage.show();
        System.out.println("\t>> Haettu lista asiakkaista onnistuneesti tietokannasta");
    }
     
    @FXML
    private void deleteAsiakas(ActionEvent event) {
        //TESTATTU! Asiakas ID tulee syöttää kokonaislukuna ja tekstikenttä ei voi jättää tyhjäksi.
        //Näistä tulee ilmoitukset käyttäjälle. Annettua asiakas ID:tä vastaavat tiedot poistetaan tietokannasta.
        /*
         * Tarkistetaan että poistettavan asiakkaan ID tekstikentässä on tieto
         * Ilmoitetaan käyttäjälle mikäli tieto puuttuu
         */
        if (asiakasIdInfoTextField.getText() == null || asiakasIdInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "poistettavan asiakkaan ID tieto puuttuu", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen poistaminen");
            alert.show();
        } else if (asiakasIdInfoTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Asiakkaan tietojen poistaminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            asiakasIdInfoTextField.setText("");
            etunimiInfoTextField.setText("");
            sukunimiInfoTextField.setText("");
            orgInfoTextField.setText("");
            emailInfoTextField.setText("");
            lahiosoiteInfoTextField.setText("");
            postinumeroInfoTextField.setText("");
            puhelinInfoTextField.setText("");
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
                Tietokantayhteys.deleteAsiakas(conn, Integer.parseInt(asiakasIdInfoTextField.getText()));
                /*
                 * Suljetaan tietokantayhteys
                 */
                Tietokantayhteys.closeConnection(conn);
                /*
                 * Ilmoitetaan käyttäjälle onnistuneesta tietojen poistosta
                 */
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Asiakkaan tiedot poistettu tietokannasta", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setHeaderText("Poistettu!");
                alert.setTitle("Asiakkaan tietojen poistaminen");
                alert.show();
                /*
                 * Tyhjennetään tekstikenttä onnistuneen tietojen poiston
                 * jälkeen
                 */
                asiakasIdInfoTextField.setText("");
                etunimiInfoTextField.setText("");
                sukunimiInfoTextField.setText("");
                orgInfoTextField.setText("");
                emailInfoTextField.setText("");
                lahiosoiteInfoTextField.setText("");
                postinumeroInfoTextField.setText("");
                puhelinInfoTextField.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(AsiakasViewController.class.getName()).log(Level.SEVERE, null, ex);
                /*
                 * ilmoitetaan sattuneesta virheestä käyttäjälle
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Jotain meni pieleen!");
                alert.setHeaderText("Virhe");
                alert.setContentText("Tietoja ei loydy");
                alert.showAndWait();
                
                asiakasIdInfoTextField.setText("");
                etunimiInfoTextField.setText("");
                sukunimiInfoTextField.setText("");
                orgInfoTextField.setText("");
                emailInfoTextField.setText("");
                lahiosoiteInfoTextField.setText("");
                postinumeroInfoTextField.setText("");
                puhelinInfoTextField.setText("");

            }

        }

    }
    //TESTATTU! Asiakas ID tule syöttää vain kokonailukuja ja ei voi jättää tekstikenttiä tyhjäksi.
    //Näistä tulee ilmoitukset käytäjälle. "Hae" napin painallus tuo annettua asiakas ID:tä vastaavat 
    //tiedot niille varattuihin tekstikenttiin.
    @FXML
    private void searchAsiakas(ActionEvent event) {
        /*
         * haetaan tietokannasta asiakasta, jonka asiakasId = asiakasIdTextField
         */
        m_asiakas = null;
        /*
         * Tarkistetaan että tekstikenttä on täytetty oikein
         */
        if (asiakasIdInfoTextField.getText() == null || asiakasIdInfoTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Asiakkaan tietojen etsiminen");
            alert.show();
        } else if (asiakasIdInfoTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Asiakkaan tietojen etsiminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            asiakasIdInfoTextField.setText("");
            etunimiInfoTextField.setText("");
            sukunimiInfoTextField.setText("");
            orgInfoTextField.setText("");
            emailInfoTextField.setText("");
            lahiosoiteInfoTextField.setText("");
            postinumeroInfoTextField.setText("");
            puhelinInfoTextField.setText("");

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
                m_asiakas = Asiakas.searchAsiakas(conn, Integer.parseInt(asiakasIdInfoTextField.getText()));
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
                alert.setContentText("Asiakasta ei loydy");
                alert.showAndWait();

            }
            if (m_asiakas.getEtunimi() == null) {
                /*
                 * muut virheet
                 */
                asiakasIdInfoTextField.setText("");
                etunimiInfoTextField.setText("");
                sukunimiInfoTextField.setText("");
                orgInfoTextField.setText("");
                emailInfoTextField.setText("");
                lahiosoiteInfoTextField.setText("");
                postinumeroInfoTextField.setText("");
                puhelinInfoTextField.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Asiakkaan tietojen hakeminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("Asiakasta ei loydy.");
                alert.showAndWait();

            } else {
                /*
                 * asetetaan haetut tiedot tekstikenttiin
                 */
                etunimiInfoTextField.setText(m_asiakas.getEtunimi());
                sukunimiInfoTextField.setText(m_asiakas.getSukunimi());
                orgInfoTextField.setText(m_asiakas.getOrganisaatio());
                emailInfoTextField.setText(m_asiakas.getEmail());
                lahiosoiteInfoTextField.setText(m_asiakas.getLahiosoite());
                postinumeroInfoTextField.setText(m_asiakas.getPostinumero());
                puhelinInfoTextField.setText(m_asiakas.getPuhelin());
                System.out.println("\t>> Haettu asiakas onnistuneesti tietokannasta!");

            }

        }
    }
    //TESTATTU! "Tyhjennä" napin painallus tyhjentää tekstikentät.
    /**
     * toiminto tekstikenttien tyhjentämiseksi
     */
    @FXML
    private void clearTextFields(ActionEvent event) {
        asiakasIdInfoTextField.setText("");
        etunimiInfoTextField.setText("");
        sukunimiInfoTextField.setText("");
        orgInfoTextField.setText("");
        emailInfoTextField.setText("");
        lahiosoiteInfoTextField.setText("");
        postinumeroInfoTextField.setText("");
        puhelinInfoTextField.setText("");
        System.out.println("\t>> Asiakkaan tietojen hakukentät tyhjennetty");
    }

}
