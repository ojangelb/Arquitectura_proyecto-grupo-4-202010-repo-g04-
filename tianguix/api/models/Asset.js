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

    var request = function(filtros) {
        return axios.get('http://localhost:8090/asset', {
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