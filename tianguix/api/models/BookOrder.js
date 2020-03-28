var mongoose = require("mongoose");

const valueSchema = new mongoose.Schema({
    currency: 'string',
    ammount: 'Number'
});

const childAssetSchema = new mongoose.Schema({
    name: 'string',
    stocks: 'Number',
    value: valueSchema,
    type: 'string',
    trader_id: 'string'
});

const bookOrder = new mongoose.Schema({
    status: {
        type: String,
        required: true,
    },
    assetList :[childAssetSchema]
});

const BookOrder = mongoose.model("BookOrder", bookOrder);
module.exports = BookOrder;


