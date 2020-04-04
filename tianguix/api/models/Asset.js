"use strict";

var axios = require('axios');
var config = require('./../../config/params');
var baseURL = `http://${config.asset.url}:${config.asset.port}`;

exports.create = function(asset, callback) {
    axios.post(baseURL + `/asset`, asset)
        .then(function (response) {
            callback(null, response.data);
        })
        .catch(function (error) {
            console.log(`Error creating the asset: ${error}`);
            callback(error, null);
        });
};

exports.read = function(params, callback) {

    var request = function(filtros) {
        return axios.get(baseURL + `/asset`, {
            params: filtros
        });
    };

    var list = params.map( params => request(params));

    axios.all(list)
        .then(axios.spread(function (...responses) {
            var data = responses.map(r => r.data);
            var flattened = [].concat.apply([], data);
            callback(flattened);
        }));
};