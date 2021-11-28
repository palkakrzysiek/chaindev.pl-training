package _7_smart_contracts.integration;

import _7_smart_contracts.training.contract.Oracle;
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

public class OracleI {
    public static void main(String[] args) throws Exception {
        //this can be real wallet
        Credentials credentials = WalletUtils.loadCredentials(Password.pass, "src/main/resources/set-wallet.json");

        //use api key
        var webSocketClient = new WebSocketClient(new URI("wss://eth-rinkeby.ws.alchemyapi.io/v2/api-key"));
        WebSocketService webSocketService = new WebSocketService(webSocketClient, false);
        webSocketService.connect();

        var web3j = Web3j.build(webSocketService);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();

        var contract = Oracle.deploy(web3j, credentials, contractGasProvider).send();
        var contractAddress = contract.getContractAddress();
        System.out.println("contract address " + contractAddress);

        //part of interaction logic
        var oilPrice = contract.getOilPrice().send();
        System.out.println("latest reported oil price " + oilPrice);

        try {
            var result = contract.getRandomNumber().send();
            System.out.println("transaction status " + result.getStatus());
        } catch (TransactionException e) {
            System.out.println("Transaction Failed");
            var reason = e.getTransactionReceipt().map(TransactionReceipt::getRevertReason);
            System.out.println("transaction revert reason " + reason);
            e.printStackTrace();
        }


        webSocketService.close();
    }
}
