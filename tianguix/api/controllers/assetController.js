var Asset = require('../models/Asset');

exports.create = function(req, res) {
    var assetObject = {
        name: req.body.name,
        stocks: req.body.stocks,
        value : {
            currency: req.body.value.currency,
            ammount: req.body.value.ammount
        },
        type: req.body.type,
        trader_id: req.username
    };
    return Asset.create(assetObject, function(err, createdAsset){
        if (createdAsset != null) {
            res.send(createdAsset);
        }
        else {
            res.status(500).send();
        }
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