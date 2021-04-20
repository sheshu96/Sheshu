// BTC & ETH methods using "Tatum" documentation
var http = require("https");
var rp = require('request-promise');
const express = require("express");
const app = express();

async function performOperation(path) {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `${path}`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        }
    };
    var response = await rp(options)
    return response;
}

async function getBTCPrivateKeyUsingMnemonic(path) {
    var options = {
        method: "POST",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `${path}`,
        headers: {
            "content-type": "application/json",
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8"
        },
        body: JSON.stringify({ "index": 0, "mnemonic": "urge pulp usage sister evidence arrest palm math please chief egg abuse" })
    };
    var response = await rp(options)
    return response;
}

async function sendBTC(path, senderAddress, senderPrivateKey, receiverAddress, amountToBeSent) {
    var options = {
        method: "POST",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `${path}`,
        headers: {
            "content-type": "application/json",
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8"
        },
        body: JSON.stringify({ "fromAddress": [{ "address": "myaPeb3H34ngnn4p1JEtRwH4mq8xUF1V92", "privateKey": "cMotAJwwC3hruht3gYKBBLm9kUhEWvfovDTLGPy4biyNbR2VBXLG" }], "to": [{ "address": "n2qpPuR7vyePmnp9wCT27XRNKK42bmGzkM", "value": 0.01 }] })
    };
    var response = await rp(options)
    return response;
}

async function getETHPrivateKeyUsingMnemonic(path) {
    var options = {
        method: "POST",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `${path}`,
        headers: {
            "content-type": "application/json",
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8"
        },
        body: JSON.stringify({ "index": 0, "mnemonic": "urge pulp usage sister evidence arrest palm math please chief egg abuse" })
    };
    var response = await rp(options)
    return response;
}

async function sendETH(path, senderPrivateKey, receiverAddress, amountToBeSent) {
    var options = {
        method: "POST",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `${path}`,
        headers: {
            "content-type": "application/json",
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8"
        },
        body: JSON.stringify({ "data": "My note to recipient.", "nonce": 0, "to": "0x273620072178ef7c70a408da50cb8598fe049bf3", "currency": "ETH", "fee": { "gasLimit": "40000", "gasPrice": "20" }, "amount": 0.01, "fromPrivateKey": "ba76222900b93566db58858004c0a8b423e7ca705a6bda046fc5fcb353fa17ab" })
    };
    var response = await rp(options)
    return response;
}

app.get('/createBTCWallet', async (req, res) => {
    let path = `https://api-eu1.tatum.io/v3/bitcoin/wallet?mnemonic`
    let result = await performOperation(path);
    console.log(result)
    res.send(result);
})

app.get('/generateBTCAddress', async (req, res) => {
    let xpub = "tpubDFDrJCn3nu4aCcfWrhvK5GnWpLUaWhHXmHzdng56EBBTwGwJs8vriXmiWd6aJvMwfo1NQcoKxnHBEWTZJCRmGfrABnLFGZM6U6NniiCgRMo";
    let path = `https://api-eu1.tatum.io/v3/bitcoin/address/${xpub}/1`
    let result = await performOperation(path);
    console.log(result);
    res.send(result);
})

app.get('/getBTCPrivateKey', async (req, res) => {
    let path = `https://api-eu1.tatum.io/v3/bitcoin/wallet/priv`
    let result = await getBTCPrivateKeyUsingMnemonic(path);
    console.log(result)
    res.send(result);
})

app.get('/getBTCBalance', async (req, res) => {
    let address = "myaPeb3H34ngnn4p1JEtRwH4mq8xUF1V92";
    let path = `https://api-eu1.tatum.io/v3/bitcoin/address/balance/${address}`
    let result = await performOperation(path);
    console.log(result);
    res.send(result);
})

app.get('/getBTCTransactionList', async (req, res) => {
    let address = "myaPeb3H34ngnn4p1JEtRwH4mq8xUF1V92";
    let path = `https://api-eu1.tatum.io/v3/bitcoin/transaction/address/${address}?pageSize=10&offset=0`
    let result = await performOperation(path);
    console.log(result);
    res.send(result);
})

app.get('/sendBTC', async (req, res) => {
    let senderAddress = "myaPeb3H34ngnn4p1JEtRwH4mq8xUF1V92";
    let senderPrivateKey = "cMotAJwwC3hruht3gYKBBLm9kUhEWvfovDTLGPy4biyNbR2VBXLG";
    let receiverAddress = "n2qpPuR7vyePmnp9wCT27XRNKK42bmGzkM";
    let amountToBeSent = 0.01;
    let path = `https://api-eu1.tatum.io/v3/bitcoin/transaction`
    let result = await sendBTC(path, senderAddress, senderPrivateKey, receiverAddress, amountToBeSent);
    console.log(result);
    res.send(result);
})

app.get('/createETHWallet', async (req, res) => {
    let path = "https://api-eu1.tatum.io/v3/ethereum/wallet?mnemonic"
    let result = await performOperation(path);
    console.log(result);
    res.send(result);
})

app.get('/generateETHAddress', async (req, res) => {
    let xpub = "xpub6DZWn1MppdzuCpUDzZVX4dag8e2QHvx8NqsNB27wuAgB5gSMoaMz5mmMnTLWtkjqC8XjdwiH6XenXj4fu8myJTWqZE5cH5wkVRXSQw4Tz6v";
    let path = `https://api-eu1.tatum.io/v3/ethereum/address/${xpub}/1`
    let result = await performOperation(path);
    console.log(result);
    res.send(result);
})

app.get('/getETHPrivateKey', async (req, res) => {
    let path = "https://api-eu1.tatum.io/v3/ethereum/wallet/priv"
    let result = await getETHPrivateKeyUsingMnemonic(path);
    console.log(result);
    res.send(result);
})

app.get('/getETHBalance', async (req, res) => {
    let address = "0xd699Ff70b16a24F4983B289378346EB9577e9a15";
    let path = `https://api-eu1.tatum.io/v3/ethereum/account/balance/${address}`
    let result = await performOperation(path);
    console.log(result);
    res.send(result);
})

app.get('/getETHTransactionList', async (req, res) => {
    let address = "0x273620072178ef7c70a408da50cb8598fe049bf3";
    let path = `https://api-eu1.tatum.io/v3/ethereum/account/transaction/${address}?pageSize=10&offset=0`
    let result = await performOperation(path);
    console.log(result);
    res.send(result);
})

app.get('/sendETH', async (req, res) => {
    let senderPrivateKey = "ba76222900b93566db58858004c0a8b423e7ca705a6bda046fc5fcb353fa17ab";
    let receiverAddress = "0x273620072178ef7c70a408da50cb8598fe049bf3";
    let amountToBeSent = 0.01;
    let path = "https://api-eu1.tatum.io/v3/ethereum/transaction"
    let result = await sendETH(path, senderPrivateKey, receiverAddress, amountToBeSent);
    console.log(result);
    res.send(result);
})

app.listen(4000);