/** Tee puhelinluettelo.
Puhelinluetteloon lisäät taulukkoon objekteja (eli henkilöitä joilla nimi ja puhelinnumero).
Käyttäjältä kysytään henkilön nimi ja puhelinnumero.
Henkilön poisto -toimintoa ei tarvitse tässä versiossa olla.

Tee hakutoiminto jossa haet nimen perusteella puhelinnumeron.

Tee puhelinnumeron haku funktioksi.
Funktion parametrina on taulukko josta haetaan ja henkilön nimi.
Funktio palauttaa puhelinnumeron.
Kutsu funktiota.

Käyttöliittymän voit tehdä millaiseksi haluat (komentokehoite -pohjainen kuitenkin)*/

function Henkilo(nimi, puhelinNro) {
  this.nimi = nimi;
  this.puhelinNro = puhelinNro;
}
Henkilo.prototype.tiedot = function () {
  return this.nimi + " " + this.puhelinNro;
};

var puhelinluettelo = {};

puhelinluettelo.henkilorekisteri = (function () {
  var Lista = [];

  function lisaaTiedot(nimi, puhelinNro) {
    var henkilo = new Henkilo(nimi, puhelinNro);

    Lista.push(henkilo);
    console.log("Lisätty tiedot puhelinluetteloon!");
  }

  function etsiPuhelin(nimi) {
    const word = Lista.find((word) => word.nimi === nimi);

    console.log(word);


  }
  // rajapinta
  return {
    lisaa: lisaaTiedot,
    etsi: etsiPuhelin,
  };
})();

var input = require("readline-sync");

let lkm = input.question("Monta henkiloa lisaat: "); //Otetaan lisättävien henkilöiden lkm muuttujaan

for (let i = 0; i < lkm; i++) {
  let lisaaUusi = input.question(
    "Anna henkilon tiedot muodossa: (etunimi sukunimi, numerotieto) " //Otetaan syötetyt tiedot muuttujaan
  );

  puhelinluettelo.henkilorekisteri.lisaa(lisaaUusi);
}
let hae = input.question(
  "Anna haettavan nimi: " //Otetaan haettava nimi muuttujaan
);
puhelinluettelo.henkilorekisteri.etsi(hae);
