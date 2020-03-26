var jwt = require('jsonwebtoken');
var bcrypt = require('bcryptjs');
var config = require('../../config/params');
var mongoose = require("mongoose");

const userSchema = new mongoose.Schema({
    user: {
        type: String,
        unique: true,
        required: true,
        trim: true
    },
    pass: {
        type: String,
        required: true,
    }
});

userSchema.pre('save', function (next) {
    var user = this;
    bcrypt.hash(user.pass, 10, function (err, hash){
        if (err) {
            return next(err);
        }
        user.pass = hash;
        next();
    })
});
  
userSchema.statics.login = function (requestedUser, callback) {
    User.findOne({ user: requestedUser.user }).exec(function (err, dbUser) {
        if (err) {
            return callback({status: "ERR"});
        } else if (!dbUser) {
            return callback({status: "NOT_FOUND"});
        }
        bcrypt.compare(requestedUser.pass, dbUser.pass, function (err, result) {
            if (result === true) {
                return callback({
                    status: "OK",
                    value: jwt.sign({ id: dbUser.user }, config.key, {
                        expiresIn: 86400
                    })
                });
            } else {
                return callback({status: "WRONG"});
            }
        })
    });
}

const User = mongoose.model("User", userSchema);
module.exports = User;


