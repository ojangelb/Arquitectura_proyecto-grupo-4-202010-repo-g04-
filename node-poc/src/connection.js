const mongoose = require("mongoose");
const User = ("./User.model");
const connection = "mongodb://mongo:27017/my-test";
const connectDatabase = () => {
    return mongoose.connect(connection);
};
module.exports = connectDatabase;