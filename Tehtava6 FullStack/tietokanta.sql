CREATE OR REPLACE DATABASE urheilijat;


USE urheilijat;


CREATE TABLE urheilija (
id INT NOT NULL AUTO_INCREMENT,
nimi VARCHAR (50 )NOT NULL,
syntymavuosi YEAR NOT NULL,
paino INT NOT NULL,
kuva VARCHAR (500) NOT NULL,
laji VARCHAR (50) NOT NULL,
saavutukset VARCHAR (100) NOT NULL,
PRIMARY KEY(id)
)	ENGINE = INNODB DEFAULT CHARSET=UTF8 AUTO_INCREMENT=5; 


INSERT INTO urheilija(id, nimi,syntymavuosi,paino,kuva,laji,saavutukset) VALUES 
(1,'Kimi Raikkonen', 1989, 70, 'https://upload.wikimedia.org/wikipedia/commons/f/ff/F12019_Schloss_Gabelhofen_%2822%29_%28cropped%29.jpg','Formula 1','Voittoja: 21, Palkintosijoja: 103, Paalupaikkoja: 18, Nopeimpia kierroksia: 46, MM-pisteitä: 1 865'),
(2,'Mika Nyyssola', 1975, 96, 'https://mikanyyssola.fi/wp-content/uploads/2015/09/bb211116b55e0049e683b6ab44ce1333.jpg','Kehonrakennus','Kehonrakennuksen SM Overall x 2, Arnold Classic 1st place 80 kg'),
(3,'Michael Tyson', 1966, 100,'https://static.dw.com/image/55663744_403.jpg','Nyrkkeily','Raskaansarjan mm x2, Otteluita 58, Voitot: 50, Voitot tyrmayksella: 44, Haviot: 6')


SELECT* FROM urheilija;


CREATE OR REPLACE USER 'joni'@'localhost' IDENTIFIED BY 'salanensana';
GRANT SELECT, INSERT, UPDATE, DELETE ON urheilijat.urheilija TO 'joni'@'localhost';


DELIMITER $$
CREATE PROCEDURE `sp_get_urheilijat`()
BEGIN
SELECT id, nimi, syntymavuosi, paino, kuva, laji, saavutukset FROM urheilija;
END $$


GRANT EXECUTE ON PROCEDURE urheilijat.sp_get_urheilijat TO 'joni'@'localhost' IDENTIFIED 
BY 'J.Veps2020';

DELIMITER $$
CREATE PROCEDURE `sp_get_urheilijan_tiedot`(
IN urheilija_id INT
)
BEGIN
SELECT nimi, syntymavuosi, paino, kuva, laji, saavutukset FROM urheilija WHERE id = urheilija_id;
END $$


GRANT EXECUTE ON PROCEDURE urheilijat.sp_get_urheilijan_tiedot TO 'joni'@'localhost' IDENTIFIED
BY 'J.Veps2020';


DELIMITER $$
CREATE PROCEDURE `sp_insert_urheilija`(
OUT urheilija_id INT,
IN urheilija_nimi VARCHAR(50),
IN urheilija_syntymavuosi DATE,
IN urheilija_paino INT,
IN urheilija_kuva VARCHAR (200),
IN urheilija_laji VARCHAR (50),
IN urheilija_saavutukset VARCHAR (100)

)
BEGIN
INSERT INTO urheilija(nimi, syntymavuosi, paino, kuva, laji, saavutukset)
VALUES (urheilija_nimi, urheilija_syntymavuosi, urheilija_paino, urheilija_kuva, urheilija_laji, urheilija_saavutukset);
SET urheilija_id = LAST_INSERT_ID();
END $$


GRANT EXECUTE ON PROCEDURE urheilijat.sp_insert_urheilija TO 'joni'@'localhost' IDENTIFIED  
BY 'J.Veps2020';


DELIMITER $$
CREATE PROCEDURE `sp_delete_urheilija`(
IN urheilija_id int
)
BEGIN
DELETE FROM urheilija WHERE id = urheilija_id;
END $$


GRANT EXECUTE ON PROCEDURE urheilijat.sp_delete_urheilija TO 'joni'@'localhost' IDENTIFIED
BY 'J.Veps2020';