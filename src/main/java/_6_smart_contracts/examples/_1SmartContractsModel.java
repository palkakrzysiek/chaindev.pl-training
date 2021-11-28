package _6_smart_contracts.examples;

public class _1SmartContractsModel {
    public static void main(String[] args) {
        var node = new Node();

        //static for example simplicity
        var contractAddress = "0x423252";
        var greetingAddress = "hello message";

        var createContract = new Transaction();
        createContract.to = contractAddress;
        createContract.smartContract = java.util.Optional.of(new SmartContract());

        var callContract = new Transaction();
        callContract.to = contractAddress;
        callContract.contractCall = java.util.Optional.of(new ContractCall("Ala"));

        var secondCallContract = new Transaction();
        secondCallContract.to = contractAddress;
        secondCallContract.contractCall = java.util.Optional.of(new ContractCall("Ela"));

        //transactions are validated before execution and after they are executed they are packed into block
        //those steps are omitted to simplify the example
        var beforeContractDeployment = node.getSmartContractDataViaAPI(contractAddress, greetingAddress);

        node.executeTransaction(createContract);
        var resultBefore = node.getSmartContractDataViaAPI(contractAddress, greetingAddress);

        node.executeTransaction(callContract);
        var resultAfter = node.getSmartContractDataViaAPI(contractAddress, greetingAddress);

        node.executeTransaction(secondCallContract);
        var resultSecondCallAfter = node.getSmartContractDataViaAPI(contractAddress, greetingAddress);

        System.out.println("greeting before contract was created: " + beforeContractDeployment);
        System.out.println();
        System.out.println("greeting after contract was created: " + resultBefore);
        System.out.println();
        System.out.println("greeting after contract was called: " + resultAfter);
        System.out.println();
        System.out.println("greeting after contract was called second time: " + resultSecondCallAfter);
    }
}
