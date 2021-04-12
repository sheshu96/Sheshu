const ethers = require("ethers");
const bip39 = require("bip39");
const express = require("express");
const app = express();
var providers = ethers.providers;
var provider = providers.getDefaultProvider('ropsten');

app.get('/getETHAddress', (req, res) => {
    let privateKey = "ba76222900b93566db58858004c0a8b423e7ca705a6bda046fc5fcb353fa17ab";
    let wallet = new ethers.Wallet(privateKey);
    address = wallet.address;
    res.send(address);
});

app.get('/getETHBalance', (req, res) => {
    let address = "0xd699Ff70b16a24F4983B289378346EB9577e9a15";
    provider.getBalance(address).then(function(balance) {
        var etherString = ethers.utils.formatEther(balance);
        res.send("Balance: " + etherString);
    });
})

app.get('/getTransactionHistory', (req, res) => {
    let etherscanProvider = new ethers.providers.EtherscanProvider('ropsten');
    etherscanProvider.getHistory(address).then(async(history) => {
    await history.forEach(async(tx) => {
            res.send(tx)
        })
    });
})

app.get('/generateMnemonic', (req, res) => {
    const mnemonic = bip39.generateMnemonic();
    res.send(mnemonic);
})

app.get('/createWalletUsingMnemonic', (req, res) => {
    let mnemonic = "more cat tomato mimic spin ranch timber slice report tattoo travel harsh";
    let wallet = ethers.utils.HDNode.fromMnemonic(mnemonic);
    res.send(wallet);
})

app.listen(4000);