function palindromiTesti(sana) {
  let oikeinPain = []; //Varataan kaksi taulukkoa mihin otetaan sanan kirjaimet talteen
  let vaarinPain = [];

  for (var i = 0; i < sana.length; ) {
    //Tutkittavan sanan kirjaimet taulukkoon alkaen ensimmäisestä kirjaimesta
    oikeinPain.push(sana[i]);
    //console.log("oikeinpäin " + sana[i]); //tarkistus tulostus
    i++;
  }

  for (var i = sana.length - 1; i >= 0; ) {
    //Tutkittavan sanan kirjaimet taulukkoon alkaen viimeisestä kirjaimesta
    vaarinPain.push(sana[i]);
    //console.log("väärinpäin " + sana[i]); //tarkistus tulostus
    i--;
  }
  let vertaa1 = oikeinPain.toString(); //Otetaan taulukon sisältö vertailtavaan muotoon toString() metodilla
  let vertaa2 = vaarinPain.toString();

  if (vertaa1 === vertaa2) {
    //Vertaillaan muuttujien samuutta jos samat TRUE jos eri FALSE
    return true;
  } else {
    return false;
  }
}

let input = require("readline-sync");

let testattavaSana = input.question(
  "Anna sana niin tarkistan onko se palindromi:" //Otetaan vartailtava sana testattavaksi
);
console.log(palindromiTesti(testattavaSana)); //Tulosteena funktion kutsu. Tulostuu true / false
