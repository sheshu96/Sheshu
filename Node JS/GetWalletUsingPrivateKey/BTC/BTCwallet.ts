import {ECPair, networks, payments} from 'bitcoinjs-lib';

const convertBtcPrivateKey = (testnet: boolean, privkey: string) => {
    const network = testnet ? networks.testnet : networks.bitcoin;
    const keyPair = ECPair.fromWIF(privkey, network);
    return payments.p2pkh({pubkey: keyPair.publicKey, network}).address as string;
};

let address = convertBtcPrivateKey(true, "cPF66jPx75ZTPv1MYsHQ8MiYS1v8Bkn9SDWJPwXhmCwKfzb8MoLY");
console.log(address);