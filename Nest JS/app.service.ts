import { Injectable } from "@nestjs/common";
import axios from "axios";

@Injectable()
export class AppService {

  async createWallet(dataObj) {
    const url = "http://Sheshu:Sheshu123@localhost:18332";
    const result = await axios.post(url, dataObj);
    return JSON.stringify(result.data);
  }

  async getBalance(dataObj, walletName) {
    const url = `http://Sheshu:Sheshu123@localhost:18332/wallet/${walletName}`;
    const result = await axios.post(url, dataObj);
    return JSON.stringify(result.data);
  }

  async getTransactionList(dataObj, walletName) {
    const url = `http://Sheshu:Sheshu123@localhost:18332/wallet/${walletName}`;
    const result = await axios.post(url, dataObj);
    return JSON.stringify(result.data);
  }

  async generateAddress(dataObj, walletName) {
    const url = `http://Sheshu:Sheshu123@localhost:18332/wallet/${walletName}`;
    const result = await axios.post(url, dataObj);
    return JSON.stringify(result.data);
  }

  async getWallets(dataObj) {
    const url = "http://Sheshu:Sheshu123@localhost:18332";
    const result = await axios.post(url, dataObj);
    return JSON.stringify(result.data);
  }

  async loadWallet(dataObj) {
    const url = "http://Sheshu:Sheshu123@localhost:18332";
    const result = await axios.post(url, dataObj);
    return JSON.stringify(result.data);
  }
}