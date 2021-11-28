
// SPDX-License-Identifier: MIT
pragma solidity >=0.8.4 <0.9.0;

import "@openzeppelin/contracts/token/ERC20/presets/ERC20PresetFixedSupply.sol";

contract MyToken is ERC20PresetFixedSupply  {
    constructor() ERC20PresetFixedSupply("Fake", "FKE", 42, _msgSender()){}
}