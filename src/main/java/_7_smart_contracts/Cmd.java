package _7_smart_contracts;

import org.web3j.codegen.SolidityFunctionWrapperGenerator;

import java.io.File;
import java.io.IOException;

public class Cmd {
    //wrapper for web3j code gen
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        generate("solidity/hello/Hello.abi", "solidity/hello/Hello.bin", "HelloWorld");
        generate("solidity/ERC20/ERC20.abi", "solidity/ERC20/ERC20.bin", "ERC20");
        generate("solidity/Oracle/Oracle.abi", "solidity/Oracle/Oracle.bin", "Oracle");
    }

    static void generate(String abi, String binary, String contractName) throws IOException, ClassNotFoundException {
        new SolidityFunctionWrapperGenerator(
                new File(binary),
                new File(abi),
                new File("src/main/java"),
                contractName,
                "_7_smart_contracts.training.contract",
                true,
                false,
                20)
                .generate();
    }
}
