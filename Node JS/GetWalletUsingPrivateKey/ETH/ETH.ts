import ethWallet, {hdkey as ethHdKey} from 'ethereumjs-wallet';

const convertEthPrivateKey = (testnet: boolean, privkey: string) => {
    const wallet = ethWallet.fromPrivateKey(Buffer.from(privkey.replace('0x', ''), 'hex'));
    return wallet.getAddressString() as string;
};

let address = convertEthPrivateKey(true, "0xdb03d55d458f295fe776eb53a89a3a41b5978cb64406625620f51d124c757ad7");
console.log(address);