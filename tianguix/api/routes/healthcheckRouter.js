'use strict';

module.exports = function(app) {

    app.route('/healthcheck').get(function(req, res) {
        res.status(200).send('OK');
    });

};