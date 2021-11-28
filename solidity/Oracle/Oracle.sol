
// SPDX-License-Identifier: MIT
pragma solidity >=0.8.4 <0.9.0;

import "https://raw.githubusercontent.com/smartcontractkit/chainlink/develop/contracts/src/v0.8/dev/VRFConsumerBase.sol";
import "https://raw.githubusercontent.com/smartcontractkit/chainlink/develop/contracts/src/v0.8/interfaces/AggregatorV3Interface.sol";

contract Oracle is VRFConsumerBase {

    //https://docs.chain.link/docs/vrf-contracts/#rinkeby
    bytes32 internal keyHash = 0x2ed0feb3e7fd2022120aa84fab1945545a9f2ffc9076fd6156fa96eaff4c1311;
    uint256 internal fee  = 0.1 * 10 ** 18;

    uint256 public randomNumber;

    //related to reading feed
    AggregatorV3Interface internal priceFeed;

    constructor() VRFConsumerBase(0xb3dCcb4Cf7a26f6cf6B120Cf5A73875B7BBc655B, 0x01BE23585060835E02B77ef475b0Cc51aA1e0709) {
        //address related to Oil price feed
        //check https://docs.chain.link/docs/ethereum-addresses/
        priceFeed = AggregatorV3Interface(0x6292aA9a6650aE14fbf974E5029f36F95a1848Fd);
    }

    //this should be restricted as calls will drain LINKs, currently it is not
    function getRandomNumber(uint256 userProvidedSeed) public returns (bytes32 requestId) {
        require(LINK.balanceOf(address(this)) >= fee, "Add LINK tokens to contract");
        return requestRandomness(keyHash, fee, userProvidedSeed);
    }

    //called by the oracle when providing random number
    function fulfillRandomness(bytes32 requestId, uint256 randomness) internal override {
        //in mormal case you sould check requestId
        randomNumber = randomness;
    }

    function getOilPrice() public view returns (int){
        //extract price
        ( , int price, , , ) = priceFeed.latestRoundData();
        return price;
    }
}