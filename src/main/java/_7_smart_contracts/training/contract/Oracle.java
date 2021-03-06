package _7_smart_contracts.training.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.8.4.
 */
@SuppressWarnings("rawtypes")
public class Oracle extends Contract {
    public static final String BINARY = "60c06040527f2ed0feb3e7fd2022120aa84fab1945545a9f2ffc9076fd6156fa96eaff4c131160001b60015567016345785d8a000060025534801561004357600080fd5b5073b3dccb4cf7a26f6cf6b120cf5a73875b7bbc655b7301be23585060835e02b77ef475b0cc51aa1e07098173ffffffffffffffffffffffffffffffffffffffff1660a08173ffffffffffffffffffffffffffffffffffffffff1660601b815250508073ffffffffffffffffffffffffffffffffffffffff1660808173ffffffffffffffffffffffffffffffffffffffff1660601b815250505050736292aa9a6650ae14fbf974e5029f36f95a1848fd600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060805160601c60a05160601c610b5b61016c600039600081816101790152610366015260008181610220015261032a0152610b5b6000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c806390dc31011461005157806394985ddd1461006f578063ccbac9f51461008b578063dbdff2c1146100a9575b600080fd5b6100596100c7565b604051610066919061086e565b60405180910390f35b6100896004803603810190610084919061059b565b610177565b005b610093610213565b6040516100a091906108c9565b60405180910390f35b6100b1610219565b6040516100be91906107e5565b60405180910390f35b600080600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663feaf968c6040518163ffffffff1660e01b815260040160a06040518083038186803b15801561013257600080fd5b505afa158015610146573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061016a9190610600565b5050509150508091505090565b7f000000000000000000000000000000000000000000000000000000000000000073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610205576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101fc906108a9565b60405180910390fd5b61020f828261031b565b5050565b60035481565b60006002547f000000000000000000000000000000000000000000000000000000000000000073ffffffffffffffffffffffffffffffffffffffff166370a08231306040518263ffffffff1660e01b8152600401610277919061078c565b60206040518083038186803b15801561028f57600080fd5b505afa1580156102a3573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102c791906105d7565b1015610308576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102ff90610889565b60405180910390fd5b610316600154600254610326565b905090565b806003819055505050565b60007f000000000000000000000000000000000000000000000000000000000000000073ffffffffffffffffffffffffffffffffffffffff16634000aea07f00000000000000000000000000000000000000000000000000000000000000008486600060405160200161039a929190610800565b6040516020818303038152906040526040518463ffffffff1660e01b81526004016103c7939291906107a7565b602060405180830381600087803b1580156103e157600080fd5b505af11580156103f5573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104199190610572565b50600061043b8460003060008089815260200190815260200160002054610485565b905060016000808681526020019081526020016000205461045c9190610911565b6000808681526020019081526020016000208190555061047c84826104c1565b91505092915050565b60008484848460405160200161049e9493929190610829565b6040516020818303038152906040528051906020012060001c9050949350505050565b600082826040516020016104d6929190610760565b60405160208183030381529060405280519060200120905092915050565b60008151905061050381610ab2565b92915050565b60008135905061051881610ac9565b92915050565b60008151905061052d81610ae0565b92915050565b60008135905061054281610af7565b92915050565b60008151905061055781610af7565b92915050565b60008151905061056c81610b0e565b92915050565b60006020828403121561058457600080fd5b6000610592848285016104f4565b91505092915050565b600080604083850312156105ae57600080fd5b60006105bc85828601610509565b92505060206105cd85828601610533565b9150509250929050565b6000602082840312156105e957600080fd5b60006105f784828501610548565b91505092915050565b600080600080600060a0868803121561061857600080fd5b60006106268882890161055d565b95505060206106378882890161051e565b945050604061064888828901610548565b935050606061065988828901610548565b925050608061066a8882890161055d565b9150509295509295909350565b61068081610967565b82525050565b61068f81610985565b82525050565b6106a66106a182610985565b610a0c565b82525050565b60006106b7826108e4565b6106c181856108ef565b93506106d18185602086016109d9565b6106da81610a4f565b840191505092915050565b6106ee8161098f565b82525050565b6000610701601b83610900565b915061070c82610a60565b602082019050919050565b6000610724601f83610900565b915061072f82610a89565b602082019050919050565b610743816109b9565b82525050565b61075a610755826109b9565b610a16565b82525050565b600061076c8285610695565b60208201915061077c8284610749565b6020820191508190509392505050565b60006020820190506107a16000830184610677565b92915050565b60006060820190506107bc6000830186610677565b6107c9602083018561073a565b81810360408301526107db81846106ac565b9050949350505050565b60006020820190506107fa6000830184610686565b92915050565b60006040820190506108156000830185610686565b610822602083018461073a565b9392505050565b600060808201905061083e6000830187610686565b61084b602083018661073a565b6108586040830185610677565b610865606083018461073a565b95945050505050565b600060208201905061088360008301846106e5565b92915050565b600060208201905081810360008301526108a2816106f4565b9050919050565b600060208201905081810360008301526108c281610717565b9050919050565b60006020820190506108de600083018461073a565b92915050565b600081519050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600061091c826109b9565b9150610927836109b9565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111561095c5761095b610a20565b5b828201905092915050565b600061097282610999565b9050919050565b60008115159050919050565b6000819050919050565b6000819050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600069ffffffffffffffffffff82169050919050565b60005b838110156109f75780820151818401526020810190506109dc565b83811115610a06576000848401525b50505050565b6000819050919050565b6000819050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000601f19601f8301169050919050565b7f416464204c494e4b20746f6b656e7320746f20636f6e74726163740000000000600082015250565b7f4f6e6c7920565246436f6f7264696e61746f722063616e2066756c66696c6c00600082015250565b610abb81610979565b8114610ac657600080fd5b50565b610ad281610985565b8114610add57600080fd5b50565b610ae98161098f565b8114610af457600080fd5b50565b610b00816109b9565b8114610b0b57600080fd5b50565b610b17816109c3565b8114610b2257600080fd5b5056fea26469706673582212204084b7772a1f1263c37dadd3539836568559432f1ea8ee67b78176abf52e447464736f6c63430008040033";

    public static final String FUNC_GETOILPRICE = "getOilPrice";

    public static final String FUNC_GETRANDOMNUMBER = "getRandomNumber";

    public static final String FUNC_RANDOMNUMBER = "randomNumber";

    public static final String FUNC_RAWFULFILLRANDOMNESS = "rawFulfillRandomness";

    @Deprecated
    protected Oracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Oracle(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Oracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Oracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> getOilPrice() {
        final Function function = new Function(FUNC_GETOILPRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getRandomNumber() {
        final Function function = new Function(
                FUNC_GETRANDOMNUMBER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> randomNumber() {
        final Function function = new Function(FUNC_RANDOMNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> rawFulfillRandomness(byte[] requestId, BigInteger randomness) {
        final Function function = new Function(
                FUNC_RAWFULFILLRANDOMNESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(requestId), 
                new org.web3j.abi.datatypes.generated.Uint256(randomness)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Oracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Oracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Oracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Oracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Oracle load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Oracle(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Oracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Oracle(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Oracle> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Oracle.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Oracle> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Oracle.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Oracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Oracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Oracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Oracle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
