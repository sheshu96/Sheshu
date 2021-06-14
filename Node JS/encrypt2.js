const CryptoJS = require("crypto-js");

var encrypted = CryptoJS.AES.encrypt(JSON.stringify("village host wreck fatigue bus cash dutch slender snap hard fire gravity"), "46GX10VP8YmNAw2Os9043kmBfMg3RnLGVFf5cX3y8ucqxxuPMcRAnA3wTTAGyd83NesSuGMz7WRiAAA7tPV3t2Qa2VXLinDZwsWzOSltolNGJwktoVJ0QghrcGupcqll").toString();
let bytes = CryptoJS.AES.decrypt(encrypted, '46GX10VP8YmNAw2Os9043kmBfMg3RnLGVFf5cX3y8ucqxxuPMcRAnA3wTTAGyd83NesSuGMz7WRiAAA7tPV3t2Qa2VXLinDZwsWzOSltolNGJwktoVJ0QghrcGupcqll')
var decrypted = JSON.parse(bytes.toString(CryptoJS.enc.Utf8));

console.log(encrypted);
console.log("-----------------------------");
console.log(decrypted);