package toimistotilojenvarausapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * LaskuViewController FXML Controller luokka sisältää laskun toiminnot
 *
 * @author Joni Vepsäläinen, 2006234
 * @version 1.0
 */
public class LaskuViewController implements Initializable {
    /**
     * Määritellään asiakas olio mihin haetaan tietoja tietokannasta
     */
    private LaskutettavaAsiakas m_laskutettavaAsiakas = new LaskutettavaAsiakas(); 
    /**
     * Määritellään summa olio mihin haetaan tietoja tietokannasta
     */
    private Summa m_summa = new Summa();
    /**
     * Määritellään Lasku olio mihin haetaan tietoja tietokannasta
     */
    private Lasku m_lasku = new Lasku();
    /**
     * asetetaan dateMuoto muuttujaa päivämäärän haluttu muoto
     */
    private String dateMuoto = "dd.MM.yyyy";
    /**
     * päivämäärän muotoilu
     */
    private DateFormat dateformat = new SimpleDateFormat(dateMuoto);
    /**
     * Laskun esikatselu näkymän määrittely
     */
    @FXML
    private WebView webView;
    
    private static final String ASIAKAS_NIMI = "_AS_NIMI_";
    private static final String ASIAKAS_SUKUNIMI= "_AS_SUKUNIMI_";
    private static final String ASIAKAS_ORGANISAATIO = "_AS_ORG_";
    private static final String ASIAKAS_EMAIL = "_AS_EMAIL_";
    private static final String ASIAKAS_LAHIOSOITE = "_AS_LAHIOSOITE_";
    private static final String ASIAKAS_POSTINUMERO = "_AS_POSTINUMERO_";
    private static final String ASIAKAS_TOIMIPAIKKA = "_AS_TOIMIPAIKKA_";
    private static final String ASIAKAS_PUHELIN = "_AS_PUHELIN_";
    private static final String TABLE_CONTENT = "_TABLE_";
    /**
     * Tekstikenttä laskun varauksen ID tunukselle
     */
    @FXML
    private TextField addVarausIdTextField;
    /**
     * Tekstikenttä laskun asiakkaan ID tunnukselle
     */
    @FXML
    private TextField addAsiakasIdTextField;
    /**
     * Tekstikenttä laskun laskutettavalle summalle
     */
    @FXML
    private TextField addSummaTextField;
    /**
     * DatePicker valikko eräpäivälle
     */
    @FXML
    private DatePicker addErapaivaDatePicker;
    /**
     * Pudotusvalikko laskutustavalle
     */
    @FXML
    private ComboBox<String> comLaskutustapa;
    /**
     * Tekstikenttä hallinnoitavan laskun viitenumerolle
     */
    @FXML
    private TextField infoViiteNumeroTextField;
    /**
     * Tekstikenttä hallinnoitavan laskun varaus ID tunnukselle
     */
    @FXML
    private TextField infoVarausIdTextField;
    /**
     * Tekstikenttä hallinnoitavan laskun asiakas ID tunnukselle
     */
    @FXML
    private TextField infoAsiakasIdTextField;
    /**
     * Tekstikenttä hallinnoitavan laskun summan esittämiseksi
     */
    @FXML
    private TextField infoSummaTextField;
    /**
     * Tekstikenttä hallinnoitavan laskun eräpäivän esittämiseksi
     */
    @FXML
    private TextField infoErapaivaTextField;
    /**
     * DatePicker valikko laskun eräpäivän muuttamiseksi
     */
    @FXML
    private DatePicker infoErapaivaDatePicker;
    /**
     * Pudotusvalikko hallinnoitavan laskun laskutustavan esittämiseksi ja
     * muuttamiseksi
     */
    @FXML
    private ComboBox<String> comModLaskutustapa;
    /**
     * 
     */
    @FXML
    private TextField etunimiTextField;
    @FXML
    private TextField sukunimiTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField orgTextField;
    @FXML
    private TextField puhelinTextField;
    @FXML
    private TextField lahiosoiteTextField;
    @FXML
    private TextField postinumeroTextField;
    @FXML
    private TextField aloitusPvmTextField;
    @FXML
    private TextField lopetusPvmTextField;
    @FXML
    private TextField toimipaikkaTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comLaskutustapa.getItems().addAll("Sähköposti", "Kirje");
        comModLaskutustapa.getItems().addAll("Sähköposti", "Kirje");
    }
    @FXML
    private void getLaskutettavanTiedot(ActionEvent event) {
         /*
         * haetaan tietokannasta laskutettavaa asiakasta, jonka asiakasId = asiakasIdTextField
         */
        m_laskutettavaAsiakas = null;
        /*
         * Tarkistetaan että tekstikenttä on täytetty oikein
         */
         if (addVarausIdTextField.getText() == null || addVarausIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Varauksen ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskutettavan asiakkaan tietojen etsiminen");
            alert.show();
        } else if (addVarausIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laskutettavan asiakkaan tietojen etsiminen");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (addAsiakasIdTextField.getText() == null || addAsiakasIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskutettavan asiakkaan tietojen etsiminen");
            alert.show();
        } else if (addAsiakasIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
             alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
             alert.setTitle("Laskutettavan asiakkaan tietojen etsiminen");
             alert.setHeaderText("Virhe!");
             alert.show();
         } else {
             try {

                 // otetaan yhteys tietokantaan
                 Connection conn = Tietokantayhteys.openConnection(
                         "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                         + "3306?user=opiskelija&password=opiskelija1");

                 //määritetään käytettävä tietokanta
                 Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");

                 //otetaan käyttäjän tekstikenttiin syöttämät tiedot etsinnän paramatreiksi
                 m_laskutettavaAsiakas = LaskutettavaAsiakas.searchLaskutettavaAsiakas(conn, Integer.parseInt(addVarausIdTextField.getText()), Integer.parseInt(addAsiakasIdTextField.getText()));

                 //Suljetaan tietokantayhteys
                 Tietokantayhteys.closeConnection(conn);

             } catch (SQLException se) {
                 
                //SQL virheet
                  
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Laskutettavan asiakkaan tietojen hakeminen");
                 alert.setHeaderText("Virhe (EKA)");
                 alert.setContentText("Tietoja ei loydy.");
                 alert.showAndWait();

             } catch (Exception e) {
                
                //muut virheet
                
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Laskutettavan asiakkaan tietojen hakeminen");
                 alert.setHeaderText("Virhe (TOKA)");
                 alert.setContentText("Summaa ei loydy.");
                 alert.showAndWait();
                 
                //tyhjennetään tekstikentät sattuneen virheen jälkeen
                  
                 addVarausIdTextField.setText("");
                 addAsiakasIdTextField.setText("");

             }
             if (m_laskutettavaAsiakas.getEtunimi() == null) {
                
                //muut virheet
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Asiakkaan tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Asiakasta ei loydy.");
                alert.showAndWait();

            } else {
                
                //asetetaan haetut tiedot tekstikenttiin
                 
                etunimiTextField.setText(m_laskutettavaAsiakas.getEtunimi());
                sukunimiTextField.setText(m_laskutettavaAsiakas.getSukunimi());
                orgTextField.setText(m_laskutettavaAsiakas.getOrganisaatio());
                emailTextField.setText(m_laskutettavaAsiakas.getEmail());
                lahiosoiteTextField.setText(m_laskutettavaAsiakas.getLahiosoite());
                postinumeroTextField.setText(m_laskutettavaAsiakas.getPostinumero());
                toimipaikkaTextField.setText(m_laskutettavaAsiakas.getToimipaikka());
                puhelinTextField.setText(m_laskutettavaAsiakas.getPuhelin());
                aloitusPvmTextField.setText(dateformat.format(m_laskutettavaAsiakas.getAloitusPvm()));
                lopetusPvmTextField.setText(dateformat.format(m_laskutettavaAsiakas.getLopetusPvm()));
                System.out.println("\t>> Haettu laskutettavan asiakkaan tiedot onnistuneesti tietokannasta!");

            }
        }
    }
    /**
     * 
     * @param event 
     */
    @FXML
    private void getLaskunSumma(ActionEvent event) {
        
        //haetaan tietokannasta summaa, jonka varaus id = ? ja asiakas id = ?
        
        m_summa = null;

        if (addVarausIdTextField.getText() == null || addVarausIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Varauksen ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskun summa tietojen etsiminen");
            alert.show();
        } else if (addVarausIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laskun summa etsiminen");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (addAsiakasIdTextField.getText() == null || addAsiakasIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakkaan ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskun summa tietojen etsiminen");
            alert.show();
        } else if (addAsiakasIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laskun summa tietojen etsiminen");
            alert.setHeaderText("Virhe!");
            alert.show();

        } else {
            try {
                
                //otetaan yhteys tietokantaan
                
                Connection conn = Tietokantayhteys.openConnection(
                        "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                        + "3306?user=opiskelija&password=opiskelija1");


                
                //määritetään käytettävä tietokanta
                
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                /*
                 * otetaan käyttäjän tekstikenttiin syöttämät tiedot etsinnän
                 * paramatreiksi
                */
                m_summa = Summa.searchSumma(conn, Integer.parseInt(addVarausIdTextField.getText()),
                        Integer.parseInt(addAsiakasIdTextField.getText()));
                
                //suljetaan tietokantayhteys
                 
                Tietokantayhteys.closeConnection(conn);

            } catch (SQLException se) {
                
                //SQL virheet
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laskun summa tietojen hakeminen");
                alert.setHeaderText("Virhe EKA");
                alert.setContentText("Summaa ei loydy.");
                alert.showAndWait();

            } catch (Exception e) {
                
                //muut virheet
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laskun summa tietojen hakeminen");
                alert.setHeaderText("Virhe TOKA");
                alert.setContentText("Summaa ei loydy.");
                alert.showAndWait();
                
                //tyhjennetään tekstikentät sattuneen virheen jälkeen
                
                addVarausIdTextField.setText("");
                addAsiakasIdTextField.setText("");

            }
            if (m_summa.getLaskunSumma() == null) {
                
                //muut virheet
                

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laskun summa tietojen hakeminen");
                alert.setHeaderText("Virhe KOLMAS");
                alert.setContentText("Summaa ei loydy.");
                alert.showAndWait();
                
                //tyhjennetään tekstikentät sattuneen virheen jälkeen
                
                addVarausIdTextField.setText("");
                addAsiakasIdTextField.setText("");

            } else {
                
                //asetetaan haetut tiedot niille varattuihin tekstikenttiin
                

                addSummaTextField.setText(Double.toString(m_summa.getLaskunSumma()));

                System.out.println("\t>> Laskun summa tieto haettu onnistuneesti tietokannasta");
            }

        }
    }
    /**
     * 
     * @param event laskun lisäys toiminto
     */
    @FXML
    private void addLasku(ActionEvent event) {
        
        //Tarkistetaan että kaikissa tekstikentissä on tiedot
        
        if (addVarausIdTextField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Varauksen ID kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("laskun varaus ID lisäy");
            alert.show();
        } else if (addVarausIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("laskun varaus ID lisäy");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (addAsiakasIdTextField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Varauksen ID kentta puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("laskun asiakas ID lisäys");
            alert.show();
        } else if (addAsiakasIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("laskun asiakas ID lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (addSummaTextField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Laskun summa puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskun summan lisäys");
            alert.show();
        } else if (addSummaTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laskun summan lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (addErapaivaDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Eräpäivä kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskun eräpäivän lisäys");
            alert.show();
        } else if (comLaskutustapa.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Laskutustapa kenttä puuttuu tieto", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskutustavan lisäys");
            alert.show();
        } else {
            
            //Otetaan yhteys tietokantaan
            
            try {
                Connection conn = Tietokantayhteys.openConnection(
                        "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                        + "3306?user=opiskelija&password=opiskelija1");


                
                //Määritetään käytettävä tietokanta
                
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                
                //Varauksen lisääminen tietokantaan
                

                Tietokantayhteys.addLasku(conn, Integer.valueOf(addVarausIdTextField.getText()), Integer.valueOf(addAsiakasIdTextField.getText()),
                       Double.valueOf(addSummaTextField.getText()), java.sql.Date.valueOf(addErapaivaDatePicker.getValue()), comLaskutustapa.getValue());
                
                //Tietokantayhteyden sulkeminen
                
                Tietokantayhteys.closeConnection(conn);
                
                //Asetetaan tekstikentät tyhjiksi laskun lisäämisen jälkeen
                
                addVarausIdTextField.setText("");
                addAsiakasIdTextField.setText("");
                etunimiTextField.setText("");
                sukunimiTextField.setText("");
                orgTextField.setText("");
                emailTextField.setText("");
                lahiosoiteTextField.setText("");
                postinumeroTextField.setText("");
                toimipaikkaTextField.setText("");
                puhelinTextField.setText("");
                aloitusPvmTextField.setText("");
                lopetusPvmTextField.setText("");
                addSummaTextField.setText("");
                addErapaivaDatePicker.setValue(null);
                comLaskutustapa.setValue(null);
                
                //Ilmoitetaan käyttäjälle onnistuneesta tietojen lisäämisestä
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Laskun lisääminen");
                alert.setHeaderText("Tallennettu!");
                alert.setContentText("Laskun tiedot tallennettu tietokantaan");
                alert.showAndWait();
                /*
                 * Ilmoitetaan virheestä käyttäjälle
                 */
            } catch (SQLException ex) {
                Logger.getLogger(VarausViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laskun lisääminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                
                //Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                
                addVarausIdTextField.setText("");
                addAsiakasIdTextField.setText("");
                addSummaTextField.setText("");
                addErapaivaDatePicker.setValue(null);
                comLaskutustapa.setValue(null);
            } catch (Exception e) {
                
                //muut virheet
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laskun lisääminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Jokin meni pieleen!");
                alert.showAndWait();

                
                //Asetetaan tekstikentät tyhjiksi sattuneen virheen jälkeen
                
                addVarausIdTextField.setText("");
                addAsiakasIdTextField.setText("");
                addSummaTextField.setText("");
                addErapaivaDatePicker.setValue(null);
                comLaskutustapa.setValue(null);
            }

        }
    }
    /**
     * 
     * @param event toiminto laskun etsimiseksi
     */
    @FXML
    private void searchLasku(ActionEvent event) {
        
        //haetaan tietokannasta laskua, jonka viitenumero = infoViitenumeroTextField
        
        m_lasku = null;
        
        //Tarkistetaan että tekstikenttä on täytetty oikein
        
        if (infoViiteNumeroTextField.getText() == null || infoViiteNumeroTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Laskun viitenumero kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskun tietojen etsiminen");
            alert.show();
        } else if (infoViiteNumeroTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laskun tietojen etsiminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            infoViiteNumeroTextField.setText("");
            infoVarausIdTextField.setText("");
            infoAsiakasIdTextField.setText("");
            infoErapaivaTextField.setText("");
            infoSummaTextField.setText("");
            infoErapaivaDatePicker.setValue(null);
            comModLaskutustapa.setValue(null);

        } else {
            try {
                
                //Otetaan yhteys tietokantaan
                
                Connection conn = Tietokantayhteys.openConnection(
                        "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                        + "3306?user=opiskelija&password=opiskelija1");

                
                //Määritetään käytettävä tietokanta
                
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                
                
                m_lasku = Lasku.searchLasku(conn, Integer.parseInt(infoViiteNumeroTextField.getText()));
                
                //Suljetaan tietokantayhteys
                
                Tietokantayhteys.closeConnection(conn);

            } catch (Exception e) {
                
                //muut virheet
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laskun tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Laskua ei loydy");
                alert.showAndWait();

            }
            if (m_lasku.getVarausId() == null) {
                
                //muut virheet
                
                infoViiteNumeroTextField.setText("");
                infoVarausIdTextField.setText("");
                infoAsiakasIdTextField.setText("");
                infoErapaivaTextField.setText("");
                infoSummaTextField.setText("");
                infoErapaivaDatePicker.setValue(null);
                comModLaskutustapa.setValue(null);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Asiakkaan tietojen hakeminen");
                alert.setHeaderText("Virhe");
                alert.setContentText("Asiakasta ei loydy.");
                alert.showAndWait();

            } else {
                
                //asetetaan haetut tiedot tekstikenttiin
                
                infoVarausIdTextField.setText(Integer.toString(m_lasku.getVarausId()));
                infoAsiakasIdTextField.setText(Integer.toString(m_lasku.getAsiakasId()));
                infoErapaivaTextField.setText(dateformat.format(m_lasku.getErapaiva()));
                infoSummaTextField.setText(Double.toString(m_lasku.getSumma()));
                comModLaskutustapa.setValue(m_lasku.getLaskutustapa());
                
                System.out.println("\t>> Haettu lasku onnistuneesti tietokannasta!");
            }

        }

    }
    /**
     * 
     * @param event laskun muokkaus toiminto
     */
    @FXML
    private void modLasku(ActionEvent event) {
        
        //Tarkistetaan että kaikki tekstikentät ovat täyttetty
        
        if (infoVarausIdTextField.getText() == null || infoVarausIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Varaus ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskun tietojen muokkaus");
            alert.show();
        } else if (infoVarausIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laskun varauksen ID tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (infoAsiakasIdTextField.getText() == null || infoAsiakasIdTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Asiakas ID kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskun tietojen muokkaus");
            alert.show();
        } else if (infoAsiakasIdTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laskun asiakkaan ID tietojen lisäys");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (infoSummaTextField.getText() == null || infoSummaTextField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Laskun summa kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskun tietojen muokkaus");
            alert.show();
        } else if (infoSummaTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laskun summa tietojen muokkaus");
            alert.setHeaderText("Virhe!");
            alert.show();
        } else if (infoErapaivaDatePicker.getValue() == null || infoErapaivaDatePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Laskun eräpäivä kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskun tietojen muokkaus");
            alert.show();
        } else if (comModLaskutustapa.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Laskutustapa kentta puuttuu tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("laskun tietojen muokkaus");
            alert.show();

        } else {
            
            //Otetaan yhteys tietokantaan
            
            try {
                Connection conn = Tietokantayhteys.openConnection(
                        "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                        + "3306?user=opiskelija&password=opiskelija1");


               
                //Määritetään käytettävä tietokanta
                
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                
                //Viedään muokatut varaus tiedot tietokantaan
                
                Tietokantayhteys.modLasku(conn, Integer.valueOf(infoVarausIdTextField.getText()), Integer.valueOf(infoAsiakasIdTextField.getText()),
                        Double.valueOf(infoSummaTextField.getText()), java.sql.Date.valueOf(infoErapaivaDatePicker.getValue()), comModLaskutustapa.getValue(),
                        Integer.valueOf(infoViiteNumeroTextField.getText()));
                
                //Suljetaan tietokantayhteys
                
                Tietokantayhteys.closeConnection(conn);
                
                //Ilmoitetaan käyttäjälle onnistuneesta tietojen muokkauksesta
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Laskun tietojen päivitys");
                alert.setHeaderText("Päivitetty!");
                alert.setContentText("Laskun tiedot päivitetty tietokantaan");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät onnistuneen tietojen muokkauksen
                 * jälkeen
                 */
                infoViiteNumeroTextField.setText("");
                infoVarausIdTextField.setText("");
                infoAsiakasIdTextField.setText("");
                infoErapaivaTextField.setText("");
                infoSummaTextField.setText("");
                infoErapaivaDatePicker.setValue(null);
                comModLaskutustapa.setValue(null);
                /*
                 * Ilmoitetaan käyttäjälle mikäli varausta ei löydy
                 * tietokannasta
                 */
            } catch (SQLException ex) {
                Logger.getLogger(VarausViewController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Laskun tietojen hakeminen");
                alert.setHeaderText("Virhe!");
                alert.setContentText("Laskua ei loydy");
                alert.showAndWait();
                /*
                 * Tyhjennetään tekstikentät epäonnistuneen tiedonsiirron
                 * jälkeen
                 */
                infoViiteNumeroTextField.setText("");
                infoVarausIdTextField.setText("");
                infoAsiakasIdTextField.setText("");
                infoErapaivaTextField.setText("");
                infoSummaTextField.setText("");
                infoErapaivaDatePicker.setValue(null);
                comModLaskutustapa.setValue(null);

            }

        }
    }

    /**
     *
     * @param event toiminto laskun poistamiseksi tietokannasta
     */
    @FXML
    private void deleteLasku(ActionEvent event) {
        /*
         * Tarkistetaan että poistettavan laslun viitenumeron tekstikentässä on tieto
         * Ilmoitetaan käyttäjälle mikäli tieto puuttuu
         */
        if (infoViiteNumeroTextField.getText() == null | infoViiteNumeroTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "poistettavan laskun viitenumero tieto puuttuu", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Virhe!");
            alert.setTitle("Laskun tietojen poistaminen");
            alert.show();
        } else if (infoViiteNumeroTextField.getText().matches("[a-zA-Z]*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Syötä numero tieto!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Laskun tietojen poistaminen");
            alert.setHeaderText("Virhe!");
            alert.show();

            infoViiteNumeroTextField.setText("");

        } else {
            
            //Otetaan yhteys tietokantaan
            
            try {
                Connection conn = Tietokantayhteys.openConnection(
                        "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                        + "3306?user=opiskelija&password=opiskelija1");

                
                //Määritetään käytettävä tietokanta
                
                Tietokantayhteys.useDatabase(conn, "OHTU1kantaR04");
                
                //Otetaan käyttäjältä saatu laskun viitenumero tieto parametriksi
                 
                Tietokantayhteys.deleteLasku(conn, Integer.parseInt(infoViiteNumeroTextField.getText()));
                
                //Suljetaan tietokantayhteys
                
                Tietokantayhteys.closeConnection(conn);
                
                //Ilmoitetaan käyttäjälle onnistuneesta tietojen poistosta
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Laskun tiedot poistettu tietokannasta", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setHeaderText("Poistettu!");
                alert.setTitle("Laskun tietojen poistaminen");
                alert.show();
                /*
                 * Tyhjennetään tekstikenttä onnistuneen tietojen poiston
                 * jälkeen
                 */
                infoViiteNumeroTextField.setText("");
                infoVarausIdTextField.setText("");
                infoAsiakasIdTextField.setText("");
                infoErapaivaTextField.setText("");
                infoSummaTextField.setText("");
                infoErapaivaDatePicker.setValue(null);
                comModLaskutustapa.setValue(null);

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

                infoViiteNumeroTextField.setText("");

            }

        }
    }

    /**
     *
     * @param event Tyhjentää laskun tietojen tekstikentä
     */
    @FXML
    private void clearTextFields(ActionEvent event) {
        infoViiteNumeroTextField.setText("");
        infoVarausIdTextField.setText("");
        infoAsiakasIdTextField.setText("");
        infoErapaivaTextField.setText("");
        infoSummaTextField.setText("");
        infoErapaivaDatePicker.setValue(null);
        comModLaskutustapa.setValue(null);
        System.out.println("\t>> Laskun tietojen hakukentät tyhjennetty");
    }

    /**
     *
     * @param event laskun esikatselu
     * @throws IOException IO-Exception
     */
    @FXML
    private void showLaskuPreview(ActionEvent event) throws IOException {
        // 1. kopioi pohja
        File source = new File("./Reports/lasku_template.html");
        File dest = new File("./Reports/lasku100.html");

        Files.copy(source.toPath(), dest.toPath(), REPLACE_EXISTING);

        // 2. Korvaa pohjassa olevat paikat tiedoilla
        String content = new String(Files.readAllBytes(dest.toPath()), UTF_8);
        content = content.replaceAll(ASIAKAS_NIMI, etunimiTextField.getText());
        content = content.replaceAll(ASIAKAS_SUKUNIMI, sukunimiTextField.getText());
        content = content.replaceAll(ASIAKAS_ORGANISAATIO, orgTextField.getText());
        content = content.replaceAll(ASIAKAS_EMAIL, emailTextField.getText());
        content = content.replaceAll(ASIAKAS_LAHIOSOITE, lahiosoiteTextField.getText());
        content = content.replaceAll(ASIAKAS_POSTINUMERO, postinumeroTextField.getText());
        content = content.replaceAll(ASIAKAS_TOIMIPAIKKA, toimipaikkaTextField.getText());
        content = content.replaceAll(ASIAKAS_PUHELIN, puhelinTextField.getText());

        content = content.replaceAll(TABLE_CONTENT, createRow(addVarausIdTextField.getText(), addAsiakasIdTextField.getText(),
                addSummaTextField.getText(), addErapaivaDatePicker.getValue(), comLaskutustapa.getValue()));

        // 3. Lataa muodostettu lasku
        Files.write(dest.toPath(), content.getBytes(UTF_8));

        // 4. Näytä muodostettu lasku
        loadWebPage(dest.toPath().toString());

    }

    /**
     *
     * @param varausId varauksen ID
     * @param asiakasId asiakkaan ID
     * @param summa laskun summa
     * @param laskutustapa laskun muoto
     * @return laskun esitys tiedot
     */
        // Muodostetaan laskun yksi rivi html muodossa
    private String createRow(String varausId, String asiakasId , String summa, LocalDate erapaiva, String laskutustapa){
        
        return "<tr>" +
                "<td class=\"varausID\">" + varausId + "</td>" + 
                "<td class=\"asiakasID\">" + asiakasId + "</td>" +
                "<td class=\"summa\">" + summa + "€</td>" + 
                "<td class=\"erapaiva\">" + erapaiva + "</td>" +
                "<td class=\"laskutustapa\">" + laskutustapa + "</td>"
                ;
        
    }
    /**
     * 
     * @param path path
     */
     private void loadWebPage(String path){
        WebEngine engine = webView.getEngine();
        File f = new File(path);
        engine.load(f.toURI().toString());
    }

    @FXML
    private void showLaskuListView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LaskuListView.fxml"));

        Scene scene = new Scene(root);
        /*
         * asiakas lista näkymän määrittely, asetaan otsikko
         */
        Stage asiakkaatListStage = new Stage();
        asiakkaatListStage.setScene(scene);
        asiakkaatListStage.setTitle("Laskutetut eräpäivän mukaan lajiteltuna");
        asiakkaatListStage.show();
        System.out.println("\t>> Haettu lista laskutetuista onnistuneesti tietokannasta");
    }

}

/**
 * Luokka mitä käytetään laskun summan tietojen hakemiseen
 *
 * @author Joni Vepsäläinen
 * @version 1.0
 */
class Summa {
    
    private Double laskunSumma;
    /**
     * olion tyhjä oletus konstruktori
     */
    public Summa(){
    }
    
     /**
     * Olion konstruktori tallentaa asiakas olion laskun summan
     *
     * @param laskunSumma olion laskun summa double

     */
    public Summa(Double laskunSumma) {
        this.laskunSumma = laskunSumma;
    }
    //Getterit
    /**
     * Palautetaan laskun summa
     *
     * @return double laskunSumma
     */
    public Double getLaskunSumma() {
        return laskunSumma;
    }
   //Setterit
    /**
     * Asetetaan summa olion summa tieto
     *
     * @param laskunSumma laskun summa
     */
    public void setLaskunSumma(Double laskunSumma) {
        this.laskunSumma = laskunSumma;
    } 
    //metodit
    /**
     * Palautetaan summa oliota kuvaava merkkijono
     *
     * @return summan tiedot merkkijonona.
     */
    @Override
    public String toString() {
        return (" " + this.laskunSumma + " ");
    }
   /**
     * Haetaan suorituksen tiedot SQL - tietokannasta ja palautetaan
     * suoritusiolio kutsujalle. Staattinen metodi, ei vaadi fyysisen olion
     * olemassaoloa
     *
     * @param c tietokantayhteys parametri
     * @param varausId laskun varauksen id parametri
     * @param asiakasId laskun asiakkaan id parametri
     * @throws SQLException SQL-virheet
     * @throws Exception muut- virheet
     * @return SuoritusOlio laskun tiedot sisältävä olio
     */
     public static Summa searchSumma(Connection c, Integer varausId, Integer asiakasId) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
        
        //haetaan tietokannasta summaa jonka varaus id = ? ja asiakas id = ? 
         
        String sql = " SELECT SUM(VarauksenPalvelu.lkm* Palvelu.hinta)"
                   + " FROM Varaus, Palvelu, VarauksenPalvelu" 
                   + " WHERE (Palvelu.palveluId = VarauksenPalvelu.palveluId) AND (Varaus.varausId = ? AND VarauksenPalvelu.varausId = ?)";
                
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;
        try {
            
            //luo PreparedStatement-olio sql-lauseelle
            
            lause = c.prepareStatement(sql);
            lause.setInt(1, varausId); // asetetaan where ehtoon (?) arvo
            lause.setInt(2, asiakasId);
            
            //suorita sql-lause
             
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko == null) {
                throw new Exception("Summaa ei loydy");
            }
        } catch (SQLException se) {
            
            //SQL virheet
             
            throw se;
        } catch (Exception e) {
            
            //JDBC virheet
            
            throw e;
        }
        
        //käsitellään resultset - laitetaan tiedot summa oliolle
        
        Summa summaOlio = new Summa();

        try {
            if (tulosjoukko.next() == true) {
                
                //Laskun summa
                
                summaOlio.setLaskunSumma(tulosjoukko.getDouble("SUM(VarauksenPalvelu.lkm* Palvelu.hinta)"));
           
            }

        } catch (SQLException e) {
            throw e;
        }
        
        //palautetaan Suoritus olio
         

        return summaOlio;

    }
        
}
/**
 *
 * LaskutettavaAsiakas luokka sisältää asiakas olion atribuutit ja metodin
 * asiakas-olion tietojen palauttamiseen
 *
 * @author Joni Vepsäläinen, 2006234
 * @version 1.0
 */
class LaskutettavaAsiakas {
    /**
     * Asiakkaan etunimi
     */
    private String etunimi = null;
    /**
     * Asiakkaan sukunimi
     */
    private String sukunimi = null;
    /**
     * Asiakkaan organisaatio
     */
    private String organisaatio = null;
    /**
     * Asiakkaan email
     */
    private String email = null;
    /**
     * Asiakkaan lähiosoite
     */
    private String lahiosoite = null;
    /**
     * Asiakkaan postinumero
     */
    private String postinumero = null;
   /**
     * Asiakkaan toimipaikka
     */
    private String toimipaikka = null;    
    /**
     * Asiakkaan puhelinnumero
     */
    private String puhelin = null;    
  /**
     * Asiakkaan varauksen aloitus päivämäärä
     */
    private Date aloitusPvm = null;     
  /**
     * Asiakkaan varauksen lopetus päivämäärä
     */
    private Date lopetusPvm = null; 
    /**
     * Olion tyhjä oletus konstruktori
     */
    public LaskutettavaAsiakas() {
    }

    /**
     * Olion konstruktori tallentaa asiakas olion etunimen,
     * sukunimen, organisaation ja emailin
     *
     * @param etunimi olion etunimi merkkijonona
     * @param sukunimi olion sukunimi merkkijonona
     * @param organisaatio olion organisaatio merkkijonona
     * @param email olion email merkkijonona
     * @param lahiosoite olion lahiosoite merkkijonona
     * @param postinumero olion postinumero merkkijonona
     * @param toimipaikka olion toimipaikka merkkijonona
     * @param puhelin olion puhelinnumero merkkijonona
     * @param aloitusPvm varauksen aloitus päivämäärä
     * @param lopetusPvm varauksen lopetus päivämäärä
     */
    public LaskutettavaAsiakas(String etunimi, String sukunimi, String organisaatio,
            String email, String lahiosoite, String postinumero, String toimipaikka, String puhelin, Date aloitusPvm, Date lopetusPvm) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.organisaatio = organisaatio;
        this.email = email;
        this.lahiosoite = lahiosoite;
        this.postinumero = postinumero;
        this.toimipaikka = toimipaikka;
        this.puhelin = puhelin;
        this.aloitusPvm = aloitusPvm;
        this.lopetusPvm = lopetusPvm;
    }

    //Getterit

    /**
     * Palautetaan etunimi
     *
     * @return String asiakas olion etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }

    /**
     * Palautetaan sukunimi
     *
     * @return String asiakas olion sukunimi
     */
    public String getSukunimi() {
        return sukunimi;
    }

    /**
     * Palautetaan organisaatio tieto
     *
     * @return String asiakas olion organisaatio
     */
    public String getOrganisaatio() {
        return organisaatio;
    }

    /**
     * Palautetaan email tieto
     *
     * @return String asiakas olion email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Palautetaan lähiosoite tieto
     *
     * @return String asiakas olion lähiosoite
     */
    public String getLahiosoite() {
        return lahiosoite;
    }

    /**
     * Palautetaan postinumero tieto
     *
     * @return String asiakas olion postinumero
     */
    public String getPostinumero() {
        return postinumero;
    }
   /**
     * Palautetaan toimipaikka tieto
     *
     * @return String asiakas olion toimipaikka
     */
    public String getToimipaikka() {
        return toimipaikka;
    }    
        /**
         * Palautetaan puhelin tieto
         *
         * @return String asiakas olion puhelinnumero
         */
    public String getPuhelin() {
        return puhelin;
    }

    /**
     * Palautetaan varauksen aloitus päivämäärä
     *
     * @return String varauksen aloitus päivämäärä
     */
    public Date getAloitusPvm() {
        return aloitusPvm;
    }

    /**
     * Palautetaan varauksen lopetus päivämäärä
     *
     * @return String varauksen lopetus päivämäärä
     */
    public Date getLopetusPvm() {
        return lopetusPvm;
    }    
    //Setterit
    /**
     * Asetetaan asiakas olion etunimi tieto
     *
     * @param etunimi etunimi
     */
    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    /**
     * Asetetaan asiakas olion sukunimi tieto
     *
     * @param sukunimi sukunimi
     */
    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }   
    
    /**
     * Asetetaan asiakas olion organisaatio tieto
     *
     * @param organisaatio organisaatio
     */
    public void setOrganisaatio(String organisaatio) {
        this.organisaatio = organisaatio;
    }

    /**
     * Asetetaan asiakas olion email tieto
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }      
    
    /**
     * Asetetaan asiakas olion lähiosoite tieto
     *
     * @param lahiosoite lähiosoite
     */
    public void setLahiosoite(String lahiosoite) {
        this.lahiosoite = lahiosoite;
    }
    
    /**
     * Asetetaan asiakas olion postinumero tieto
     *
     * @param postinumero postinumero
     */
    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }
   /**
     * Asetetaan asiakas olion toimipaikka tieto
     *
     * @param toimipaikka toimipaikka
     */
    public void setToimipaikka(String toimipaikka) {
        this.toimipaikka = toimipaikka;
    }
    /**
     * Asetetaan asiakas olion puhelinnumero tieto
     *
     * @param puhelin puhelinnumero
     */
    public void setPuhelin(String puhelin) {
        this.puhelin = puhelin;
    }
    /**
     * Asetetaan olion varauksen aloitus päivämäärä
     *
     * @param aloitusPvm aloitus päivämäärä
     */
    public void setAloitusPvm(Date aloitusPvm) {
        this.aloitusPvm = aloitusPvm;
    }

    /**
     * Asetetaan olion varauksen lopetus päivämäärä
     *
     * @param lopetusPvm lopetus päivämäärä
     */
    public void setLopetusPvm(Date lopetusPvm) {
        this.lopetusPvm = lopetusPvm;
    }

   
    //metodit
    /**
     * Palautetaan Asiakas oliota kuvaava merkkijono
     *
     * @return Asiakkaan tiedot merkkijonona.
     */
    @Override
    public String toString() {
        return (this.etunimi + " " + this.sukunimi + " " + this.organisaatio + " " + this.email
                + " " + this.lahiosoite + " " + this.postinumero + " " + this.toimipaikka + " " + this.puhelin + " " + this.aloitusPvm + " " + this.lopetusPvm);
    }

    /**
     * Haetaan asiakkaan tiedot SQL - tietokannasta ja palautetaan
     * asiakasolio kutsujalle. Staattinen metodi, ei vaadi fyysisen olion
     * olemassaoloa
     *
     * @param c tietokantayhteys parametri
     * @param varausId varauksen id parametri
     * @param asiakasId asiakkaan id parametri
     * @throws SQLException SQL-virheer
     * @throws Exception muut virhet
     * @return AsiakasOlio asiakkaan tiedot sisältävä olio
     */
    public static LaskutettavaAsiakas searchLaskutettavaAsiakas(Connection c, Integer varausId, Integer asiakasId) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
        // haetaan tietokannasta asiakasta, jonka varausId = ? asiakasId = ?
        String sql = " SELECT Asiakas.etunimi, Asiakas.sukunimi, Asiakas.organisaatio, Asiakas.email, Asiakas.lahiosoite, Asiakas.postinumero, Asiakas.toimipaikka, Asiakas.puhelin, Varaus.Aloituspvm, Varaus.Lopetuspvm"
                    + " FROM Asiakas, Varaus "
                    + " WHERE (Varaus.varausId = ? AND Asiakas.asiakasId = ?)"; // ehdon arvo asetetaan jäljempänä
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;       
        try {
            
            //luo PreparedStatement-olio sql-lauseelle
            
            lause = c.prepareStatement(sql);
            lause.setInt(1, varausId); // asetetaan where ehtoon (?) arvo
            lause.setInt(2, asiakasId);
            
            //suorita sql-lause
            
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko == null) {
                throw new Exception("Laskutettavaa Asiakasta ei loydy");
            }
        } catch (Exception e) {
            
            //JDBC virheet
            
            throw e;
        }
        
        //käsitellään resultset - laitetaan tiedot AsiakasOliolle
        
        LaskutettavaAsiakas laskutettavaAsiakasOlio = new LaskutettavaAsiakas();

        try {
            if (tulosjoukko.next() == true) {
                //etunimi, sukunimi, organisaatio, email, lahiosoite, postinumeo, puhelin, aloitus päivämäärä, lopetus päivämäärä
                laskutettavaAsiakasOlio.setEtunimi(tulosjoukko.getString("Asiakas.etunimi"));
                laskutettavaAsiakasOlio.setSukunimi(tulosjoukko.getString("Asiakas.sukunimi"));
                laskutettavaAsiakasOlio.setOrganisaatio(tulosjoukko.getString("Asiakas.organisaatio"));
                laskutettavaAsiakasOlio.setEmail(tulosjoukko.getString("Asiakas.email"));
                laskutettavaAsiakasOlio.setLahiosoite(tulosjoukko.getString("Asiakas.lahiosoite"));
                laskutettavaAsiakasOlio.setPostinumero(tulosjoukko.getString("Asiakas.postinumero"));
                laskutettavaAsiakasOlio.setToimipaikka(tulosjoukko.getString("Asiakas.toimipaikka"));
                laskutettavaAsiakasOlio.setPuhelin(tulosjoukko.getString("Asiakas.puhelin"));
                laskutettavaAsiakasOlio.setAloitusPvm(tulosjoukko.getDate("Varaus.Aloituspvm"));
                laskutettavaAsiakasOlio.setLopetusPvm(tulosjoukko.getDate("Varaus.Lopetuspvm"));              
            }

        } catch (SQLException e) {
            throw e;
        }
        
        //palautetaan AsiakasOlio
        

        return laskutettavaAsiakasOlio;

    }

    
    
}
