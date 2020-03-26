'use strict';

var tokenVerifier = require('./tokenVerifier');

module.exports = function(app) {

    var asset = require('../controllers/assetController');

    app.route('/asset')
        .post(tokenVerifier, asset.create);
    
    app.route('/purchase')
        .post(tokenVerifier, asset.purchase);

};