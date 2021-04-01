import { Injectable } from "@nestjs/common";
const ethers = require("ethers");
const bip39 = require("bip39");
var providers = ethers.providers;
var provider = providers.getDefaultProvider('ropsten');

@Injectable()
export class AppService {

  getETHAddress() {
    let privateKey = "ba76222900b93566db58858004c0a8b423e7ca705a6bda046fc5fcb353fa17ab";
    let wallet = new ethers.Wallet(privateKey);
    let address = wallet.address;
    return address;
  }

  async getETHBalance(address) {
    var bal = "";
    await provider.getBalance(address).then(function(balance) {
        var etherString = ethers.utils.formatEther(balance);
        bal = (`Balance: ` + etherString + ` ETH`);
    });
    return bal;
  }

  async getTransactionHistory(address) {
    let etherscanProvider = new ethers.providers.EtherscanProvider('ropsten');
    let result = await etherscanProvider.getHistory(address).then(async(history) => {
      return history;
    });
    return result;
  }

  generateMnemonic() {
    const mnemonic = bip39.generateMnemonic();
    return mnemonic;
  }

  createWalletUsingMnemonic() {
    let mnemonic = "more cat tomato mimic spin ranch timber slice report tattoo travel harsh";
    let wallet = ethers.utils.HDNode.fromMnemonic(mnemonic);
    return wallet;
  }
}