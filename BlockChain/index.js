const rp = require("request-promise");
const config = require("../config/btc.config").rpcConfig;
const axios = require('axios')

const createWallet = async (dataObject) => {
  try {
   // const dataObject = {"jsonrpc": "1.0", "id":"curltest", "method": "createwallet", "params": [walletId] };
    const url = `${config.protocol}://${config.user}:${config.pass}@${config.host}:${config.port}`;
    config.options.data = dataObject;
    config.options.url= url;
     let result = await axios(config.options);
     console.log(result);
     return result;
  }
  catch(ex){
      console.log(ex);
  }
//    rp(config.options).then(x=>{
       
//    }).catch(ex=>{
//      console.log(ex);
//    })

   
};

const generateAddress = async (walletId) => {};

const getTransactionHistory = async (address) => {};

const getBalance = (address) => {};


(async ()=>{
    console.log("Starting")
    const createWalletObj = {"jsonrpc": "1.0", "id":"curltest", "method": "createwallet", "params": ["Kiran"] };
    let wallet = await createWallet(createWalletObj);
    // const getBlockHash= {"jsonrpc": "1.0", "id":"curltest", "method": "getbalance", "params": ["*", 6] };
    // const data = await createWallet(getBlockHash)

     console.log(wallet)

})();
