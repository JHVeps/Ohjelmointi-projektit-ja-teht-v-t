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
 * @author Joni Vepsäläinen
 * @version 1.0
 */
public class ToimistotilojenVarausApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
         * Tietokanta yhteyden määrittäminen
         */
        Connection conn = Tietokantayhteys.openConnection(
                ""
                + "");
        
        

        /*
         * Tietokannan luominen
         */
        Tietokantayhteys.createDatabase(conn, "OHTU1kantaR04");
        /*
         * Asiakas taulun tietokantaan luova DLL - lause
         */
        Tietokantayhteys.createTable(conn,
//                "CREATE TABLE Asiakas ("
//                + "asiakasId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
//                + "etunimi VARCHAR(50),"
//                + "sukunimi VARCHAR(50),"
//                + "organisaatio VARCHAR (50),"
//                + "email VARCHAR (50))"
//        );
                "CREATE TABLE Asiakas ("
                + "asiakasId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "etunimi VARCHAR(20),"
                + "sukunimi VARCHAR(20),"
                + "organisaatio VARCHAR(50),"
                + "email VARCHAR(50),"
                + "lahiosoite VARCHAR(50),"    
                + "postinumero VARCHAR(10),"
                + "puhelin VARCHAR(20))"
                );

        /*
         * Asiakas taulun oletus populointi
         */
        Tietokantayhteys.addAsiakas(conn, "Seppo", "Taalasmaa", "Talkkarit", "seppo@hotmail.com", "Pilajakatu 6", "00000", "0506665555");
        Tietokantayhteys.addAsiakas(conn, "Esa", "Back", "FirstResponders", "esanposti@jippii.fi", "jokukatu 2", "11111", "0452223333");
        Tietokantayhteys.addAsiakas(conn, "Ismo", "Laitela", "Merkonomen", "ippemail@gmail.com", "Pihlajakatu 5", "22222", "0407778888");

        /*
        Toimipistetaulun luova SQL skripti
         */
        Tietokantayhteys.createTable(conn,
                "CREATE TABLE Toimipiste ("
                + "toimipisteId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "nimi VARCHAR(50),"
                + "osoite VARCHAR(50),"
                + "kuvaus VARCHAR (50))"
        );

        /*
        Toimipistetaulun oletus populointi
         */

        Tietokantayhteys.addToimispiste(conn, "VuokraToimistot Oy", "Karjalankatu 3, 80200 Joensuu", "VuokraToimistot Oy:n Joensuun toimipiste");

        /*
        Palvelutaulun luova SQL skripti
         */
        Tietokantayhteys.createTable(conn,
                "CREATE TABLE Palvelu ("
                + "palveluId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "nimi VARCHAR(50) NOT NULL,"
                + "hinta DOUBLE NOT NULL,"
                        + "tyyppi INT NOT NULL,"
                + "kuvaus VARCHAR(100) NOT NULL,"
                + "toimipisteId INT NOT NULL,"
                + "FOREIGN KEY (toimipisteId) REFERENCES Toimipiste(toimipisteId))"
        );

        /**
         * Alustava satunnainen populointi palvelutaululle
         */
        Tietokantayhteys.addPalvelu(conn, "Hieronta", 223.4, 4, "Hierontapalvelu", 1);

        //Palvelutaulun populoiva skripti tulossa myöhemmin

        
        /*
        Laskutaulun luova SQL skripti (Tarvitsee varauksen tiedot toimiakseen)
        */
//        Tietokantayhteys.createTable(conn,
//                "CREATE TABLE Lasku ("
//                + "summa DOUBLE NOT NULL,"
//                + "erapaiva VARCHAR(10) NOT NULL,"
//                + "viitenumero INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
//                + "varausId INT NOT NULL,"
//                + "asiakasId INT NOT NULL,"
//                + "FOREIGN KEY (varausId) REFERENCES Varaus(varausId),"
//                + "FOREIGN KEY (asiakasId) REFERENCES Asiakas(asiakasId))"
//        );
        
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
