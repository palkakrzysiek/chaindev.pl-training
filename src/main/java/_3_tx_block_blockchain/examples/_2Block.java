package _3_tx_block_blockchain.examples;

import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.evm.Configuration;
import org.web3j.evm.EmbeddedWeb3jService;
import org.web3j.evm.PassthroughTracer;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Collectors;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _2Block {
    public static void main(String[] args) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("Password123", "src/main/resources/wallet.json");
        Configuration configuration = new Configuration(new Address(credentials.getAddress()), 42);
        var embedded = new EmbeddedWeb3jService(configuration, new PassthroughTracer());

        //same way for connecting to real world node
        Web3j web3j = Web3j.build(embedded);

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
        var transactionReceipt1 = new Transfer(web3j, transactionManager).sendFunds(
                //transfer to myself
                credentials.getAddress(),
                new BigDecimal("21"),
                Convert.Unit.ETHER,
                //gas price set to 0
                BigInteger.ZERO,
                new BigInteger("21000")
        ).send();

        var block = web3j.ethGetBlockByNumber(
                new DefaultBlockParameterNumber(1), true)
                .send()
                .getBlock();

        System.out.println("block: " + show(block));
    }

    public static String show(EthBlock.Block block) {
        return
                "{\n" +
                        "number: " + block.getNumber() + "\n" +
                        "hash: " + block.getHash() + "\n" +
                        "parentHash: " + block.getParentHash() + "\n" +
                        "logsBloom: " + block.getLogsBloom() + "\n" +
                        "transactionsRoot: " + block.getTransactionsRoot() + "\n" +
                        "stateRoot: " + block.getStateRoot() + "\n" +
                        "receiptsRoot: " + block.getReceiptsRoot() + "\n" +
                        //related to mining
                        "miner: " + block.getMiner() + "\n" +
                        "mixHash: " + block.getMixHash() + "\n" +
                        "nonce: " + block.getNonce() + "\n" +
                        "difficulty: " + block.getDifficulty() + "\n" +
                        "totalDifficulty: " + block.getTotalDifficulty() + "\n" +
                        "sha3Uncles: " + block.getSha3Uncles() + "\n" +
                        "extraData: " + block.getExtraData() + "\n" +
                        //-----------------
                        "size: " + block.getSize() + "\n" +
                        "gasLimit: " + block.getGasLimit() + "\n" +
                        "gasUsed: " + block.getGasUsed() + "\n" +
                        "timestamp: " + block.getTimestamp() + "\n" +
                        "transactions: " + block.getTransactions()
                        .stream()
                        .map(tx -> _1Transaction.show((EthBlock.TransactionObject) tx))
                        .collect(Collectors.toList()) + "\n" +
                        "}";
    }
}
