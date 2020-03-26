const mongoose = require("mongoose");
const User = ("./../api/models/User");
const config = require("./params");

const connection = `mongodb://${config.db.host}:27017/tianguix`;
const connectDatabase = () => {
    return mongoose.connect(connection, {useNewUrlParser: true});
};
module.exports = connectDatabase;