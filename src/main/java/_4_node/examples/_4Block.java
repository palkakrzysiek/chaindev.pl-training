package _4_node.examples;

import io.reactivex.disposables.Disposable;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;

import java.net.URI;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _4Block {
    public static void main(String[] args) throws Exception {
        //use api key
        var webSocketClient = new WebSocketClient(new URI("wss://eth-mainnet.ws.alchemyapi.io/v2/get-api-ke-from-peovider"));
        WebSocketService webSocketService = new WebSocketService(webSocketClient, false);
        webSocketService.connect();

        var web3j = Web3j.build(webSocketService);

        var blocksFlowable = web3j.blockFlowable(true);
        Disposable disposable = blocksFlowable.forEach(ethBlock -> {
            System.out.println("got new block : " + ethBlock.getBlock().getNumber());
        });

        //in "normal" app remember to close ws when app is stopped
        //webSocketService.close();
    }
}
