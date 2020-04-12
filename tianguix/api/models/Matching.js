"use strict";

var axios = require("axios");
var config = require("../../config/params");
var baseURL = `http://${config.asset.url}:${config.asset.port}`;

exports.read = (params, callback) => {
  const { matchingId } = params;
  return axios
    .get(baseURL + `/matching/${matchingId}`)
    .then((response) => {
      const {data} = response
      callback(null, data);
    })
    .catch((error) => {
      console.log('FAIL', error.response.status);
      callback(error, null);
    });
};
