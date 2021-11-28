package _4_node.examples;

import io.reactivex.disposables.Disposable;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;

import java.math.BigInteger;
import java.net.URI;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _3PendingTransactions {
    public static void main(String[] args) throws Exception {
        //use api key
        //var webSocketClient = new WebSocketClient(new URI("wss://mainnet.infura.io/ws/v3/get-api-ke-from-peovider"));
        var webSocketClient = new WebSocketClient(new URI("wss://eth-mainnet.ws.alchemyapi.io/v2/get-api-ke-from-peovider"));
        WebSocketService webSocketService = new WebSocketService(webSocketClient, false);
        webSocketService.connect();

        var web3j = Web3j.build(webSocketService);

        var pendingTransactionFlowable = web3j.pendingTransactionFlowable();
        Disposable disposable = pendingTransactionFlowable.forEach(ethTx -> {
            System.out.println("pending transactions gas price gwei: " + (ethTx.getGasPrice().divide(BigInteger.valueOf(1000000000))));
        });
    }
}
