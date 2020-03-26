var Asset = require('../models/Asset');

exports.create = function(req, res) {
    return Asset.create(null, function(createdAsset){
        res.send(createdAsset);
    });
};

exports.purchase = function(req, res) {
    return Asset.read(null, function(filteredAssets){
        console.log(`Filtered assets: ${JSON.stringify(filteredAssets)}`);
        res.send({
            "id": "d526effb-a9f3-4b2c-8e4b-a046280bc479",
            "status": "PENDING"
        });
    });
}