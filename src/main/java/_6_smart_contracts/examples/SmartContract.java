package _6_smart_contracts.examples;

import java.io.Serializable;
import java.util.HashMap;

public class SmartContract implements Serializable {
    public HashMap<String, String> state = new HashMap<>();

    public SmartContract call(String input) {
        //simple hello world
        state.put("hello message", "hello " + input);
        return this;
    }
}
