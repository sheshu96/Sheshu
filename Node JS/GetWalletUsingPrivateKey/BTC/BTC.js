// Get BTC address using it's PrivateKey

var bitcoinjs_lib_1 = require("bitcoinjs-lib");
var convertBtcPrivateKey = function (testnet, privkey) {
    var network = testnet ? bitcoinjs_lib_1.networks.testnet : bitcoinjs_lib_1.networks.bitcoin;
    var keyPair = bitcoinjs_lib_1.ECPair.fromWIF(privkey, network);
    return bitcoinjs_lib_1.payments.p2pkh({ pubkey: keyPair.publicKey, network: network }).address;
};
var address = convertBtcPrivateKey(true, "cPF66jPx75ZTPv1MYsHQ8MiYS1v8Bkn9SDWJPwXhmCwKfzb8MoLY");
console.log(address);
