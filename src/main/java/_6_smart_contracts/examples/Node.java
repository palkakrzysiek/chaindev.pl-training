package _6_smart_contracts.examples;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.hyperledger.besu.ethereum.trie.MerklePatriciaTrie;
import org.hyperledger.besu.ethereum.trie.SimpleMerklePatriciaTrie;
import org.hyperledger.besu.util.bytes.Bytes32;
import org.hyperledger.besu.util.bytes.BytesValue;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class Node {
    public MerklePatriciaTrie<BytesValue, BytesValue> state = new SimpleMerklePatriciaTrie<>(b -> b);
    public SHA3.DigestSHA3 hash = new SHA3.DigestSHA3(256);

    public void executeTransaction(Transaction tx) {
        //create smart contract if required
        tx.smartContract
                .flatMap(this::serializeContract)
                .ifPresent(scData -> state.put(toMptKey(tx.to), scData));

        //if there is smart contract call execute it
        tx.contractCall
                .ifPresent(cc -> {
                    var smartContact = state.get(toMptKey(tx.to)).flatMap(this::extractContract);
                    var updatedContract = smartContact.map(sc -> sc.call(cc.input));
                    updatedContract.flatMap(this::serializeContract)
                            .ifPresent(scData -> state.put(toMptKey(tx.to), scData));
                });

        //other node stuff
    }

    public Optional<String> getSmartContractDataViaAPI(String contractName, String valueKey) {
        var contract = state.get(toMptKey(contractName));
        return contract.flatMap(this::extractContract).map(decoded -> decoded.state.get(valueKey));
    }

    private Optional<BytesValue> serializeContract(SmartContract sc) {
        try {
            var serialized = new ByteArrayOutputStream();
            new ObjectOutputStream(serialized).writeObject(sc);
            return Optional.of(BytesValue.wrap(serialized.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<SmartContract> extractContract(BytesValue serialized) {
        try {
            var contract = new ObjectInputStream(new ByteArrayInputStream(serialized.getByteArray())).readObject();
            return Optional.of((SmartContract) contract);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private BytesValue toMptKey(String key) {
        var mptKey = hash.digest(key.getBytes(StandardCharsets.UTF_8));
        return Bytes32.wrap(mptKey);
    }
}
