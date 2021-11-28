package _4_node.examples;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _1ConnectToNode {
    public static void main(String[] args) throws Exception {
        //use api key
        var web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/get-api-ke-from-peovider"));

        var chainId = web3j.ethChainId().send();
        System.out.println(chainId.getChainId());
    }
}
