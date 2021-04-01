import { Body, Controller, Get, Param, Post } from "@nestjs/common";
import { AppService } from "./app.service";

@Controller("home")
export class AppController {

  constructor(private wltsrc: AppService) {}
  
  @Get("getETHAddress")
  getETHAddress() {
    let result = this.wltsrc.getETHAddress();
    return result;
  }

  @Get("getETHBalance/:address")
  getETHBalance(@Param('address') address: string) {
    let result = this.wltsrc.getETHBalance(address);
    return result;
  }

  @Get("getTransactionHistory/:address")
  getTransactionHistory(@Param('address') address: string) {
    let result = this.wltsrc.getTransactionHistory(address);
    return result;
  }

  @Get("generateMnemonic")
  generateMnemonic() {
    let result = this.wltsrc.generateMnemonic();
    return result;
  }

  @Get("createWalletUsingMnemonic")
  createWalletUsingMnemonic() {
    let result = this.wltsrc.createWalletUsingMnemonic();
    return result;
  }
}