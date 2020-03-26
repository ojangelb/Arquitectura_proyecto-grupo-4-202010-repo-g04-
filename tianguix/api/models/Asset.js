  
"use strict";

exports.create = function(asset, callback) {
    return callback({
        "id": "f4121425-db18-4f62-bb77-f90f231594b2",
        "name": "Ecopetrol",
        "stocks": "1000",
        "value": {
            "currency": "COP",
            "ammount": 1000000
        },
        "type": "OIL_AND_GAS",
        "created_at": "1585011420"
    });
};

exports.read = function(params, callback) {
    return callback([]);
};