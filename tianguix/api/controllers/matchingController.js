var BookOrder = require('../models/BookOrder');

exports.read = function (req, res) {
  return BookOrder.findById(req.params["matchingId"], function (err, matchingResult) {
    if (err) return res.sendStatus(404);
    else res.status(200).send(matchingResult);
  });
};
