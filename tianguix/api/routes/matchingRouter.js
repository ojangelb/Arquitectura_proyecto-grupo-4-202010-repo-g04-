"use strict";

var tokenVerifier = require("./tokenVerifier");

module.exports = function (app) {
  var matching = require("../controllers/matchingController");
  app.route(`/matching/:matchingId`).get(tokenVerifier, matching.read);
};
