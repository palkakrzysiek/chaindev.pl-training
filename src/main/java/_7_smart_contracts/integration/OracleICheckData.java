package _7_smart_contracts.integration;

import _7_smart_contracts.training.contract.Oracle;
import _7_smart_contracts.walletutils.Password;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.net.URI;

public class OracleICheckData {
    public static void main(String[] args) throws Exception {
        //this can be real wallet
        Credentials credentials = WalletUtils.loadCredentials(Password.pass, "src/main/resources/set-wallet.json");

        //use api key
        var webSocketClient = new WebSocketClient(new URI("wss://eth-rinkeby.ws.alchemyapi.io/v2/api-key"));
        WebSocketService webSocketService = new WebSocketService(webSocketClient, false);
        webSocketService.connect();

        var web3j = Web3j.build(webSocketService);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();

        //smart contract address
        var contract = Oracle.load("0xoracleAddress", web3j, credentials, contractGasProvider);
        var randomNumber = contract.randomNumber().send();

        System.out.println("random number from the oracle " + randomNumber);
    }
}
