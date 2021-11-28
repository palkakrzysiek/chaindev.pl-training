package _7_smart_contracts.integration;

import _7_smart_contracts.training.contract.HelloWorld;
import _7_smart_contracts.walletutils.Password;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.net.URI;

public class HelloWorldI {
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
        var contract = HelloWorld.deploy( web3j, credentials, contractGasProvider).send();
        var contractAddress = contract.getContractAddress();
        System.out.println("contract address " + contractAddress);

        //connecting to already existing smart contract
        var deployedContract = HelloWorld.load(contractAddress, web3j, credentials, contractGasProvider);
        var transactionResult = deployedContract.store("ala ma kota").send();
        System.out.println("transaction result " + transactionResult.getStatus());

        var stored = deployedContract.retrieve().send();
        System.out.println("stored " + stored);

        webSocketService.close();
    }
}
