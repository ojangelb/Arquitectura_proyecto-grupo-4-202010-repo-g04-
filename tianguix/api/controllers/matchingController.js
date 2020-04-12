var Matching = require("../models/Matching");

exports.read = function (req, res) {
  return Matching.read(req.params, function (err, matchingResult) {
    if (err) return res.sendStatus(404);
    else res.status(200).send(matchingResult);
  });
};
