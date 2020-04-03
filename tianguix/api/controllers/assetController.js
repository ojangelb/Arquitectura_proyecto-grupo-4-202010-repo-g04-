var Asset = require('../models/Asset');
var BookOrder = require('../models/BookOrder');

exports.create = function(req, res) {
    const {name, stocks, value, type} = req.body
    var assetObject = {
        name: name,
        stocks: stocks,
        value : {
            currency: value.currency,
            ammount: value.ammount
        },
        type: type,
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
    var filterList = [];
    req.body.filters.forEach(filter => {
        filterList.push({
            type: filter.type,
            stock_ammount_min: filter.stocks.ammount.min,
            stock_ammount_max: filter.stocks.ammount.max,
            currency: filter.value.currency,
            currency_min: filter.value.min,
            currency_max: filter.value.max
        });
    });

    return Asset.read(filterList, function(filteredAssets){

        var bookOrderObject = {
            status: 'PENDING',
            assetList: filteredAssets
        };

        BookOrder.create(bookOrderObject, function(err, bookOrderCreated){
            if (err) return res.sendStatus(400);
            else res.status(201).send(bookOrderCreated);
        });
    });
}