import { Controller, Get } from "@nestjs/common";
import { AppService } from "./app.service";

@Controller("home")
export class AppController {

  constructor(private wltsrc: AppService) {

  }

  @Get("createWallet")
  createWallet() {
    let dataObj = {"jsonrpc": "1.0", "id": "curltest", "method": "createwallet", "params": ["Run"]};
    let result = this.wltsrc.createWallet(dataObj);
    return result;
  }

  @Get("getBalance")
  getBalance() {
    let walletName = "Sheshu";
    let dataObj = {"jsonrpc": "1.0", "id": "curltest", "method": "getbalance", "params": []};
    let result = this.wltsrc.getBalance(dataObj, walletName);
    return result;
  }

  @Get("getTransactionList")
  getTransactionList() {
    let walletName = "Sheshu";
    let dataObj = {"jsonrpc": "1.0", "id": "curltest", "method": "listtransactions", "params": []};
    let result = this.wltsrc.getTransactionList(dataObj, walletName);
    return result;
  }

  @Get("generateAddress")
  generateAddress() {
    let walletName = "Sheshu";
    let dataObj = {"jsonrpc": "1.0", "id": "curltest", "method": "getnewaddress", "params": []};
    let result = this.wltsrc.generateAddress(dataObj, walletName);
    return result;
  }

  @Get("getWallets")
  getWallets() {
    let dataObj = {"jsonrpc": "1.0", "id": "curltest", "method": "listwallets", "params": []};
    let result = this.wltsrc.getWallets(dataObj);
    return result;
  }

  @Get("loadWallet")
  loadWallet() {
    let dataObj = {"jsonrpc": "1.0", "id": "curltest", "method": "loadwallet", "params": ["Sheshu"]};
    let result = this.wltsrc.loadWallet(dataObj);
    return result;
  }
}
