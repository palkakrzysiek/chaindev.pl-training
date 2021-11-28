package _7_smart_contracts.integration;

import _7_smart_contracts.training.contract.ERC20;
import _7_smart_contracts.walletutils.Password;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.net.URI;

public class ERC20I {
    public static void main(String[] args) throws Exception {
        //this can be real wallet
        Credentials credentials = WalletUtils.loadCredentials(Password.pass, "src/main/resources/set-wallet.json");

        //use api key
        var webSocketClient = new WebSocketClient(new URI("wss://eth-rinkeby.ws.alchemyapi.io/v2/api-key"));
        WebSocketService webSocketService = new WebSocketService(webSocketClient, false);
        webSocketService.connect();

        var web3j = Web3j.build(webSocketService);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();

        var contract = ERC20.deploy(web3j, credentials, contractGasProvider).send();
        var contractAddress = contract.getContractAddress();
        System.out.println("contract address " + contractAddress);

        //part of interaction logic
        var currentBalance = contract.balanceOf(credentials.getAddress()).send();
        System.out.println("my current balance " + currentBalance);
        var transferResult = contract.transfer("0x98201512ceCb2Fdf0a58668fEeb2cb8866d4666E", new BigInteger("5")).send();
        System.out.println("correct transaction result" + transferResult.getStatus());

        try {
            contract.transfer("0x98201512ceCb2Fdf0a58668fEeb2cb8866d4666E", new BigInteger("500")).send();
        } catch (TransactionException e) {
            System.out.println("Transaction Failed");
            var reason = e.getTransactionReceipt().map(TransactionReceipt::getRevertReason);
            System.out.println("transaction revert reason " + reason);
            e.printStackTrace();
        }

        webSocketService.close();
    }
}
