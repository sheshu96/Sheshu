// Get ETH address using PrivateKey

var ethereumjs_wallet_1 = require("ethereumjs-wallet");
var convertEthPrivateKey = function (testnet, privkey) {
    var wallet = ethereumjs_wallet_1["default"].fromPrivateKey(Buffer.from(privkey.replace('0x', ''), 'hex'));
    return wallet.getAddressString();
};
var address = convertEthPrivateKey(true, "0xdb03d55d458f295fe776eb53a89a3a41b5978cb64406625620f51d124c757ad7");
console.log(address);
