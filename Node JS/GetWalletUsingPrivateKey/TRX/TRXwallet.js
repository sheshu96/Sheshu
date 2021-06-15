// Get TRX address using it's PrivateKey

var TronWeb = require('tronweb');
// console.log(TronWeb.address.fromHex("4150a74a315fca44efa8a33f3054ff0899ac46814c"));
console.log(TronWeb.address.fromPrivateKey('349cbb576b934c50c4bc9681a68767bfe5984c9df2a099494e645a2cb544270d'));
