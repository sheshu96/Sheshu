const rp = require("request-promise")
const config = require("../config/exconfig").rcpcConfig
const axios = require("axios")

const executeMethod = async(dataObj) => {
  let url = `${config.protocol}://${config.user}:${config.password}@${config.host}:${config.port}/wallet/sheshu`
  config.options.data = dataObj
  config.options.url = url
  let result = await axios(config.options)
  console.log(result.data.result.length)
}

(async () => {
  let dataObj = {"jsonrpc": "1.0", "id": "curltest", "method": "listtransactions", "params": ["*", 3, 0]}
  executeMethod(dataObj)
})()