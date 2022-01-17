package toimistotilojenvarausapp;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * ToimistotilojenVarausApp luokka sisältää sovelluksen "Aloitus" näkymän,
 * tietokannan ja taulujen luovat DLL - lauseet, taulujen oletus populoinnin
 * sekä sovelluksen Main osan / käynnistyksen yhteydessä annettavat parametrit
 * merkkijonona
 *
 * @author Joni Vepsäläinen, 2006234
 * @version 1.0
 */
public class ToimistotilojenVarausApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
         * Tietokanta yhteyden määrittäminen
         */
        Connection conn = Tietokantayhteys.openConnection(
                "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                + "3306?user=opiskelija&password=opiskelija1");


        /*
         * Tietokannan luominen
         */
        Tietokantayhteys.createDatabase(conn, "OHTU1kantaR04");
        /*
         * Asiakas taulun tietokantaan luova DLL - lause
         */
        Tietokantayhteys.createTable(conn,
                "CREATE TABLE Asiakas ("
                + "asiakasId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "etunimi VARCHAR(20),"
                + "sukunimi VARCHAR(20),"
                + "organisaatio VARCHAR(50),"
                + "email VARCHAR(50),"
                + "lahiosoite VARCHAR(50),"
                + "postinumero VARCHAR(10),"
                + "toimipaikka VARCHAR(20),"
                + "puhelin VARCHAR(20))"
        );

        /*
         * Asiakas taulun oletus populointi
         */
        Tietokantayhteys.addAsiakas(conn, "Seppo", "Taalasmaa", "Talkkarit", "seppo@hotmail.com", "Pilajakatu 6", "00000", "Helsinki", "0506665555");
        Tietokantayhteys.addAsiakas(conn, "Esa", "Back", "FirstResponders", "esanposti@jippii.fi", "jokukatu 2", "11111", "LaalaaLand", "0452223333");
        Tietokantayhteys.addAsiakas(conn, "Ismo", "Laitela", "Merkonomen", "ippemail@gmail.com", "Pihlajakatu 5", "22222", "Helsinki", "0407778888");

        /*
        Toimipistetaulun luova SQL skripti
         */
        Tietokantayhteys.createTable(conn,
                "CREATE TABLE Toimipiste ("
                + "toimipisteId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "nimi VARCHAR(50),"
                + "lahiosoite VARCHAR(50),"
                + "postinro CHAR(5),"
                + "toimipaikka VARCHAR(50),"
                + "kuvaus VARCHAR (50))"
        );

        /*
        Toimipistetaulun oletus populointi
         */
        Tietokantayhteys.addToimispiste(conn, "VuokraToimistot Oy Joensuu", "Karjalankatu 3", "80200", "Joensuu", "Joensuun toimipiste");
        Tietokantayhteys.addToimispiste(conn, "VuokraToimistot Oy Kontiolahti", "Keskuskatu 20", "81100", "Kontiolahti", "Kontiolahden toimipiste");
        Tietokantayhteys.addToimispiste(conn, "VuokraToimistot Oy Liperi", "Meijeritie 1", "83100", "Liperi", "Liperin toimipiste");
        Tietokantayhteys.addToimispiste(conn, "VuokraToimistot Oy Outokumpu", "Koivikkopellontie 35", "83500", "Outokumpu", "Outokummun toimipiste");
        Tietokantayhteys.addToimispiste(conn, "VuokraToimistot Oy Polvijärvi", "Polvijärventie 3", "83700", "Polvijärvi", "Polvijärven toimipiste");
        Tietokantayhteys.addToimispiste(conn, "VuokraToimistot Oy Ilomantsi", "Kalevalantie 20", "82900", "Ilomantsi", "Ilomantsin toimipiste");
        Tietokantayhteys.addToimispiste(conn, "VuokraToimistot Oy Kitee", "Kanttorintie 5", "82500", "Kitee", "Kiteen toimipiste");
        Tietokantayhteys.addToimispiste(conn, "VuokraToimistot Oy Kesälahti", "Pyhäjärventie 11", "59800", "Kesälahti", "Kesälahden toimipiste");

        /*
        Palvelutaulun luova SQL skripti
         */
        Tietokantayhteys.createTable(conn,
                "CREATE TABLE Palvelu ("
                + "palveluId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "palvelunNimi VARCHAR(50) NOT NULL,"
                + "hinta DOUBLE NOT NULL,"
                + "tyyppi INT NOT NULL,"
                + "kuvaus VARCHAR(100) NOT NULL,"
                + "toimipisteId INT NOT NULL,"
                + "FOREIGN KEY (toimipisteId) REFERENCES Toimipiste(toimipisteId) ON DELETE CASCADE) "
        );

        /**
         * Alustava satunnainen populointi palvelutaululle
         */
        //Toimipisteiden vuokrat: nimi, päiväkohtainen vuokra, palvelun tyyppi, kuvaus, toimipisteen ID (On myös palvelu ID vuokran tapauksessa)
        Tietokantayhteys.addPalvelu(conn, "Vuokra", 50.0, 1, "Tilavuokra Toimipiste 1", 1);
        Tietokantayhteys.addPalvelu(conn, "Vuokra", 50.0, 1, "Tilavuokra Toimipiste 2", 2);
        Tietokantayhteys.addPalvelu(conn, "Vuokra", 50.0, 1, "Tilavuokra Toimipiste 3", 3);
        Tietokantayhteys.addPalvelu(conn, "Vuokra", 50.0, 1, "Tilavuokra Toimipiste 4", 4);
        Tietokantayhteys.addPalvelu(conn, "Vuokra", 50.0, 1, "Tilavuokra Toimipiste 5", 5);
        Tietokantayhteys.addPalvelu(conn, "Vuokra", 50.0, 1, "Tilavuokra Toimipiste 6", 6);
        Tietokantayhteys.addPalvelu(conn, "Vuokra", 50.0, 1, "Tilavuokra Toimipiste 7", 7);
        Tietokantayhteys.addPalvelu(conn, "Vuokra", 50.0, 1, "Tilavuokra Toimipiste 8", 8);


        
        //Lisäpalvelut 
        Tietokantayhteys.addPalvelu(conn, "Hieronta", 223.4, 4, "Hierontapalvelu", 1);// ID 9
        Tietokantayhteys.addPalvelu(conn, "Testi1", 100.0, 4, "Testipalvelu1", 1); // ID 10
        Tietokantayhteys.addPalvelu(conn, "Testi2", 200.0, 4, "Testipalvelu2", 1); // ID 11
        //Palvelutaulun populoiva skripti tulossa myöhemmin
        /*
        Varaus taulun luova SQL skripti
         */
        Tietokantayhteys.createTable(conn,
                "CREATE TABLE Varaus ("
                + "varausId INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
                + "asiakasId INT NOT NULL, "
                + "toimipisteId INT NOT NULL, "
                + "Aloituspvm DATE NOT NULL, "
                + "Lopetuspvm DATE NOT NULL, "
                + "FOREIGN KEY (asiakasId) REFERENCES Asiakas(asiakasId)ON DELETE CASCADE, "
                + "FOREIGN KEY (toimipisteId) REFERENCES Toimipiste(toimipisteId) ON DELETE CASCADE) "
        );
/*
        Varauksen palvelu taulun luova SQL skripti (Tarvitsee varauksen tiedot toimiakseen)
         */
        Tietokantayhteys.createTable(conn,
                "CREATE TABLE VarauksenPalvelu ("
                + "lkm INT,"
                + "varausId INT NOT NULL,"
                + "asiakasId INT NOT NULL, "
                + "palveluId INT NOT NULL,"
                + "FOREIGN KEY (varausId) REFERENCES Varaus(varausId)ON DELETE CASCADE,"
                + "FOREIGN KEY (palveluId) REFERENCES Palvelu(palveluId) ON DELETE CASCADE)"
        );

        /*
        Laskutaulun luova SQL skripti (Tarvitsee varauksen tiedot toimiakseen)
         */
        Tietokantayhteys.createTable(conn,
                "CREATE TABLE Lasku ("
                + "viitenumero INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "varausId INT NOT NULL,"
                + "asiakasId INT NOT NULL,"
                + "summa DOUBLE,"
                + "erapaiva DATE,"
                + "laskutustapa VARCHAR(10),"
                + "FOREIGN KEY (varausId) REFERENCES Varaus(varausId)ON DELETE CASCADE,"
                + "FOREIGN KEY (asiakasId) REFERENCES Asiakas(asiakasId)ON DELETE CASCADE)"
        );

        //Laskutaulun populointi myöhemmin, tarvitsee tiedot varauksesta
        Tietokantayhteys.closeConnection(conn);
       /*
         * Aloitus näkymän määrittäminen
         */
        Parent root = FXMLLoader.load(getClass().getResource("AloitusNakyma.fxml"));
        /*
         * Scene määritys
         */
        Scene scene = new Scene(root);
        /*
         * Asetetaan näkymän otsikko ja määritetään ikkunan koon
         * muuttamisen arvoksi false, että näkymä pysyy sellaisena kuin se on
         * tarkoitettu
         */
        primaryStage.setScene(scene);
        primaryStage.setTitle("Aloitus");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
