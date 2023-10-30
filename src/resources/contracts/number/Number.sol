// SPDX-License-Identifier: MIT 
pragma solidity >=0.8.0 <0.9.0;

contract Number {
    uint private num;

    constructor() {
        num = 0;
    }

    function get() public view returns (uint) {
        return num;
    }

    function set(uint _num) public {
        num = _num;
    }
}