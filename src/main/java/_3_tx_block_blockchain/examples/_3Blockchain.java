package _3_tx_block_blockchain.examples;

import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.evm.Configuration;
import org.web3j.evm.EmbeddedWeb3jService;
import org.web3j.evm.PassthroughTracer;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _3Blockchain {
    public static void main(String[] args) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("Password123", "src/main/resources/wallet.json");
        Configuration configuration = new Configuration(new Address(credentials.getAddress()), 42);
        var embedded = new EmbeddedWeb3jService(configuration, new PassthroughTracer());

        //same way for connecting to real world node
        Web3j web3j = Web3j.build(embedded);

        for (int i = 1; i <= 42; i++) {
            TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
            var transfer = new Transfer(web3j, transactionManager).sendFunds(
                    //transfer to myself
                    credentials.getAddress(),
                    new BigDecimal("21"),
                    Convert.Unit.ETHER,
                    //gas price set to 0
                    BigInteger.ZERO,
                    new BigInteger("21000")
            );
            transfer.send();

            var block = web3j.ethGetBlockByNumber(new DefaultBlockParameterNumber(i), true).send();

            System.out.println("block number: " + i);
            System.out.println("parent hash: " + block.getBlock().getParentHash());
            System.out.println("block hash: " + block.getBlock().getHash());
            System.out.println("\n");
        }
    }
}
