// BTC & ETH methods using "Tatum" documentation
var rp = require('request-promise');
const express = require("express");
const app = express();

app.get('/generateBTCAddress', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/bitcoin/address/tpubDFDrJCn3nu4aCcfWrhvK5GnWpLUaWhHXmHzdng56EBBTwGwJs8vriXmiWd6aJvMwfo1NQcoKxnHBEWTZJCRmGfrABnLFGZM6U6NniiCgRMo/1`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        }
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/getBTCBalance', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/bitcoin/address/balance/myaPeb3H34ngnn4p1JEtRwH4mq8xUF1V92`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        }
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/getBTCPrivateKeyUsingMnemonic', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/bitcoin/wallet/priv`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        },
        body: JSON.stringify({ "index": 0, "mnemonic": "urge pulp usage sister evidence arrest palm math please chief egg abuse" })
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/sendBTC', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/bitcoin/transaction`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        },
        body: JSON.stringify({ "fromAddress": [{ "address": "myaPeb3H34ngnn4p1JEtRwH4mq8xUF1V92", "privateKey": "cMotAJwwC3hruht3gYKBBLm9kUhEWvfovDTLGPy4biyNbR2VBXLG" }], "to": [{ "address": "n2qpPuR7vyePmnp9wCT27XRNKK42bmGzkM", "value": 0.01 }] })
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/getBTCTransaction', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/bitcoin/transaction/address/myaPeb3H34ngnn4p1JEtRwH4mq8xUF1V92?pageSize=10&offset=0`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        }
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/createBTCWallet', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/bitcoin/wallet?mnemonic`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        }
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/generateETHAddress', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/ethereum/address/xpub6DZWn1MppdzuCpUDzZVX4dag8e2QHvx8NqsNB27wuAgB5gSMoaMz5mmMnTLWtkjqC8XjdwiH6XenXj4fu8myJTWqZE5cH5wkVRXSQw4Tz6v/1`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        }
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/getETHBalance', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/ethereum/account/balance/0xd699Ff70b16a24F4983B289378346EB9577e9a15`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        }
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/getETHPrivateKeyUsingMnemonic', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/ethereum/wallet/priv`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        },
        body: JSON.stringify({ "index": 0, "mnemonic": "urge pulp usage sister evidence arrest palm math please chief egg abuse" })
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/sendETH', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/ethereum/transaction`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        },
        body: JSON.stringify({ "data": "My note to recipient.", "nonce": 0, "to": "0x273620072178ef7c70a408da50cb8598fe049bf3", "currency": "ETH", "fee": { "gasLimit": "40000", "gasPrice": "20" }, "amount": 0.01, "fromPrivateKey": "ba76222900b93566db58858004c0a8b423e7ca705a6bda046fc5fcb353fa17ab" })
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/getETHTransaction', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/ethereum/account/transaction/0x273620072178ef7c70a408da50cb8598fe049bf3?pageSize=10&offset=0`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        }
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.get('/createETHWallet', async (req, res) => {
    var options = {
        method: "GET",
        hostname: "api-eu1.tatum.io",
        port: null,
        uri: `https://api-eu1.tatum.io/v3/ethereum/wallet?mnemonic`,
        headers: {
            "x-api-key": "80343337-5563-4cce-974a-6b3254dd72f8 "
        }
    };
    var response = await rp(options)
    console.log(response)
    res.send(response);
})

app.listen(4000);