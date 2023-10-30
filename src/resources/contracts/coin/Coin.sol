// SPDX-License-Identifier: MIT
pragma solidity >=0.8.0 <0.9.0;

contract Coin {
    // Permanently variables:
    mapping (address => uint) private balance;
    address private minter;

    // Events:
    event Minted(address mintedBy, uint amount);
    event Transferred(address sender, address receiver, uint amount);

    // Constructor:
    constructor() {
        minter = msg.sender;
    }

    // Functions:
    function mint(uint amount) public {
        // Minter required
        require (msg.sender == minter);

        // Minting
        balance[minter] += amount;

        // Event emitting
        emit Minted(minter, amount);
    }

    function transfer(address receiver, uint amount) public {
        // Amount requirement
        require (balance[msg.sender] >= amount);

        // Transferring
        balance[msg.sender] -= amount;
        balance[receiver] += amount;

        // Event emitting
        emit Transferred(msg.sender, receiver, amount);
    }

    function checkBalance() public view returns (uint) {
        return balance[msg.sender];
    }

    function checkBalance(address account) public view returns (uint) {
        return balance[account];
    }
}