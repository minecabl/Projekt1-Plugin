//Laden der benötigten Pakete
const MongoClient = require("mongodb").MongoClient;
const assert = require("assert");
const express = require("express");

//Erzeugen einer ExpressJS-App und Zuweisung der Portnummer
const app = express();

const port = 3000;

const url = "mongodb+srv://<username>:<password>@projekt1.sgnbqhy.mongodb.net/?retryWrites=true&w=majority";
//const url = "mongodb://<username>:<password>@181.214.231.116:27017/userdata";

//Festlegung des Namens der Datenbank
const dbName = "userdata"

//Initialisierung des Clients
const client = new MongoClient(url);

//Kontext der View-Engine auf ejs setzen (benötigt, um Views aufrufen zu können)
app.set("view engine", "ejs");

//home-Route
app.get("/", (req, res) => { 
    res.send("Hier werden Sie keine Inhalte finden! Bitte rufen Sie die Adresse mit dem Zusatz /allstats (für alle Spielerdaten) oder /single_stats (für die Daten eines Spielers) auf!");
})


//Anzeige der Daten für alle Spieler
app.get("/allstats", (req, res) => {
    
    //Festlegung der Datenkbank 
    const db = client.db(dbName);
    const collection = db.collection("players");

    //Suchen der Dokumente innerhalb der angegebenen Datenbank und Kollektion
    collection.find({}).toArray(function(err, spielerdaten) {

        //Überprüfung der Fehler auf 0
        assert.equal(err, null);

        //Rendern der Datei players.ejs mit Übergabe von "spielerdaten" unter dem Bezeichner "spielers"
        res.render("players", {"spielers": spielerdaten})
    });
})


//Anzeige der Daten eines Spielers; selbe Suche nach Dokumenten und Übergabe der Daten
//Unterschied: andere Route ("/single_stats") und andere zu rendernde Datei ("single_stats")
app.get("/single_stats", (req, res) => {

    const db = client.db(dbName);
    const collection = db.collection("players");

    //Suchen der Dokumente innerhalb der angegebenen Datenbank und Kollektion
    collection.find({}).toArray(function(err, spielers) {
        assert.equal(err, null);
        res.render("single_stats", {"spielers": spielers})
    });
})

//Verbinden des MongoDB-Clients mit der Datenbank
client.connect(function(err){
    assert.equal(null, err);

    //Ausgabe auf der Konsole, dass die Verbindung mit der Datenbank erfolgreich war
    console.log("Ich habe mich erfolgreich mit meiner Datenbank verbunden!");

    //tatsächliches Setzen der App auf die spezifizierte Portnummer
    app.listen(port, () => console.log(`Die App läuft auf der Portnummer ${port}!`))
});