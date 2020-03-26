var User = require('../models/User');

exports.create = function(req, res) {
    var userObject = {
        user: req.body.user,
        pass: req.body.pass
    };
    User.create(userObject, function(err, userCreated){
        if (err) return res.sendStatus(400);
        else res.status(201).send(userCreated);
    });
};

exports.login = function(req, res) {
    var userObject = {
        user: req.body.user,
        pass: req.body.pass
    };
    User.login(userObject, function(token) {
        switch (token.status) {
            case "ERR":
                res.sendStatus(500);
                break;
            case "NOT_FOUND": 
                res.sendStatus(404);
                break;
            case "WRONG": 
                res.sendStatus(403);
                break;
            case "OK": 
                res.status(200).send({ auth: true, token: token.value });
                break;
            default:
                res.sendStatus(500);
        }
    });
}

exports.logout = function(req, res) {
    res.status(200).send({ auth: false, token: null });
}