
// SPDX-License-Identifier: MIT
pragma solidity >=0.8.4 <0.9.0;

contract HelloWorld {
    //variables are stored in contract state
    string currentName;

    function store(string calldata name) public {
        currentName = name;
    }

    function retrieve() public view returns (string memory){
        return currentName;
    }
}