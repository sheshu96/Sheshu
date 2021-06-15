const TronWeb = require('tronweb')

// const tronWeb = new TronWeb({
//     fullHost: 'https://api.trongrid.io'
//     //headers: { "TRON-PRO-API-KEY": '6187d034-76ce-498d-9c74-af779d9fa6d3' },
// })
// console.log(TronWeb.address.fromHex("4150a74a315fca44efa8a33f3054ff0899ac46814c"));
console.log(TronWeb.address.fromPrivateKey('349cbb576b934c50c4bc9681a68767bfe5984c9df2a099494e645a2cb544270d'))