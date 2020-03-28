const numberOfExperiments = 50;
// Definir cliente HTTP
const server = "http://localhost:8080";
const authEndpoint = `${server}/login`;
const createAssetEndpoint = `${server}/asset`;
const axios = require("axios");
const httpClientInstance = axios.create();
let authToken;

async function sendRequest(endpoint, data = {}) {
  return new Promise((resolve, reject) => {
    httpClientInstance({
      method: "post",
      url: endpoint,
      headers: {
        "User-Agent": "Console app",
        Authorization: `Bearer ${authToken}`
      },
      data
    })
      .then(res => {
        resolve(res.data);
      })
      .catch(error => {
        console.log('error', error);
        resolve({ error: error.response ? error.response.data : null });
      });
  });
}

function compareObjects(expected, current) {
  let equal = true;
  Object.keys(expected).forEach(key => {
    if (JSON.stringify(expected[key]) != JSON.stringify(current[key])) {
      equal = false;
      console.log(JSON.stringify(expected[key]), JSON.stringify(current[key]));
    }
  });
  return equal;
}

async function getAuthToken() {
  authToken = (
    await sendRequest(authEndpoint, {
      user: "oscar",
      pass: "sergio"
    })
  ).token;
}

async function consumeAssetApi() {
  const asset = {
    name: "Ecopetrol",
    stocks: 1000,
    value: {
      currency: "COP",
      ammount: 1000000
    },
    type: "OIL_AND_GAS"
  };

  const timeBeforeRequest = new Date().getTime();
  const assetCreationResult = await sendRequest(createAssetEndpoint, asset);
  const timeAfterRequest = new Date().getTime();
  // Calcular tiempo request

  const ellapsedTime = timeAfterRequest - timeBeforeRequest;
  // Validar respuesta esperada
  const isValid = compareObjects(asset, assetCreationResult);
  let result = { time: ellapsedTime, isValid: isValid };
  if (assetCreationResult.error) {
    result.error = assetCreationResult.error;
  }
  return result;
}

function analiceResults(results) {
  const maxTimeExpected = 1500;
  let timeExceededRequests = 0;
  let apiFailures = 0;
  let averageResponseTime = 0;
  results.forEach(result => {
    averageResponseTime += result.time;
    if (result.time > maxTimeExpected) {
      return timeExceededRequests++;
    }
    if (!result.isValid) {
      return apiFailures++;
    }
  });
  averageResponseTime = averageResponseTime / numberOfExperiments;
  const failures = timeExceededRequests + apiFailures;
  const successPercentage =
    (numberOfExperiments - failures) / numberOfExperiments;
  console.log("results", results);
  console.log("Success Percentage", successPercentage);
  console.log("Average response time", averageResponseTime);
  console.log("# Total tests", numberOfExperiments);
  console.log("# Total failures", failures);
  console.log("# Failed for response time", timeExceededRequests);
  console.log("# API Failure", apiFailures);
}

async function main() {
  // Consumir servicio auth (token)
  await getAuthToken();
  // Loop n veces
  let counter = 0;
  let results = [];

  do {
    // Consumir servicio (EJ. Asset)
    const apiConsumeResult = consumeAssetApi();
    // Poblar arreglo con resultados
    results.push(await apiConsumeResult);
    counter++;
  } while (counter < numberOfExperiments);
  analiceResults(results);
}

main();
