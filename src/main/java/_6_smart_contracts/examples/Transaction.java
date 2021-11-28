package _6_smart_contracts.examples;

import org.web3j.crypto.Sign;

import java.math.BigDecimal;
import java.util.Optional;

public class Transaction {
    public Sign.SignatureData sig;
    public String to;
    public BigDecimal amount;
    public Optional<SmartContract> smartContract;
    public Optional<ContractCall> contractCall;

    public Transaction() {
        smartContract = Optional.empty();
        amount = BigDecimal.ZERO;
        contractCall = Optional.empty();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sig=" + sig +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }
}
