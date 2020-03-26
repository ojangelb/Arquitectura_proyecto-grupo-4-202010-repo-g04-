var express = require('express'),
    app = express()
    port = process.env.PORT || 8080,
    bodyParser = require('body-parser'),
    connection = require("./config/connection");

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.disable('etag');

app.use(function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, DELETE, OPTIONS');
    res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization, Content-Length, X-Requested-With, x-access-token');

    if ('OPTIONS' === req.method) {
        res.sendStatus(200);
    }
    else {
        next();
    }
});

var authRoutes = require('./api/routes/authenticationRouter');
authRoutes(app);

app.listen(port, () => {
    console.log(`Tianguix is listening on PORT: ${port}`);
    connection().then(() => { console.log("Connected to Mongo!") });
});