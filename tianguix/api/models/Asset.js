"use strict";

var axios = require('axios');

exports.create = function(asset, callback) {
    axios.post('http://localhost:8090/asset', asset)
        .then(function (response) {
            callback(null, response.data);
        })
        .catch(function (error) {
            console.log(`Error creating the asset: ${error}`);
            callback(error, null);
        });
};

exports.read = function(params, callback) {
    return callback([]);
};