let dictionary = [];

const express = require("express");
const fs = require("fs");

var app = express();
app.use(express.json()); // for parsing application/json
app.use(express.urlencoded({ extended: true }));

/*CORS isn’t enabled on the server, this is due to security reasons by default,
so no one else but the webserver itself can make requests to the server.*/
// Add headers
app.use(function (req, res, next) {
  // Website you wish to allow to connect
  res.setHeader("Access-Control-Allow-Origin", "*");

  // Request methods you wish to allow
  res.setHeader(
    "Access-Control-Allow-Methods",
    "GET, POST, OPTIONS, PUT, PATCH, DELETE"
  );

  // Request headers you wish to allow
  res.setHeader(
    "Access-Control-Allow-Headers",
    "Origin, Accept, Content-Type, X-Requested-With, X-CSRF-Token"
  );

  // Set to true if you need the website to include cookies in the requests sent
  // to the API (e.g. in case you use sessions)
  res.setHeader("Access-Control-Allow-Credentials", true);

  res.setHeader("Content-type", "application/json");

  // Pass to next layer of middleware
  next();
});

//jos dictionary on tyhjä lisätään sanakirja.txt sisältö siihen
if (dictionary.length <= 0) {
  const data = fs.readFileSync("./sanakirja.txt", {
    encoding: "utf8",
    flag: "r",
  });
  const splitLines = data.split(/\r?\n/);
  splitLines.forEach((line) => {
    const words = line.split(" "); //sanat taulukkoon words

    const word = {
      fi: words[0],
      en: words[1],
    };
    dictionary.push(word);
  });
}
// GET all words
app.get("/words", (req, res) => {
  const data = fs.readFileSync("./sanakirja.txt", {
    encoding: "utf8",
    flag: "r",
  });

  const splitLines = data.split(/\r?\n/);

  splitLines.forEach((line) => {
    const words = line.split(" ");
    console.log(words); // tulostetaan sanat konsoliin
  });
  res.json(dictionary);
  console.log("search completed");
});
// ADD a word
app.post("/words", (req, res) => {
  const word = req.body;
  const JSONword = JSON.stringify(word);
  const parsedWord = JSON.parse(JSONword);

  dictionary.push(parsedWord);
  res.json(dictionary);

  const data = fs.appendFile(
    "sanakirja.txt",
    `\n${parsedWord.fi} ${parsedWord.en}`,
    (err) => {
      if (err) {
        console.error(err);
        return;
      }
      console.log("new word added!");
    }
  );
});
// GET a word
app.get("/words/:fi", (req, res) => {
  const fi = String(req.params.fi);
  const word = dictionary.find((word) => word.fi === fi);

  res.json(word ? word : { message: "Not  found" });

  console.log(`found word!${res}`);
});

app.listen(8000, () => {
  console.log("Server listening at port 8000");
});
