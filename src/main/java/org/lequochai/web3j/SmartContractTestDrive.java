package org.lequochai.web3j;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.lequochai.web3j.contracts.Coin;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

public class SmartContractTestDrive {
    // STATIC FIELDS:
    private static ContractGasProvider gasProvider = new ContractGasProvider() {
        @Override
        public BigInteger getGasPrice(String contractFunc) {
            return DefaultGasProvider.GAS_PRICE;
        }

        @Override
        public BigInteger getGasPrice() {
            return DefaultGasProvider.GAS_PRICE;
        }

        @Override
        public BigInteger getGasLimit(String contractFunc) {
            return BigInteger.valueOf(22000000L);
        }

        @Override
        public BigInteger getGasLimit() {
            return BigInteger.valueOf(22000000L);
        }
    };

    // STATIC METHODS:
    public static void main(String[] args) {
        // Service initialization
        HttpService service = new HttpService("http://localhost:8545");

        // Web3j build
        Web3j web3 = Web3j.build(service);

        // Connections test
        try {
            System.out.println(
                "Client connected successfully!\n" +
                    "Client version: " + web3.web3ClientVersion().send().getWeb3ClientVersion()
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to client!");
        }

        // Load credentials
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(
                "123456",
                "D:\\Ethereum Private\\node1\\data\\keystore\\UTC--2023-10-27T14-58-04.350953400Z--2549d419e19efbb6172906d10a053978d0222b7e"
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Print out credentials's address
        String address = credentials.getAddress();
        System.out.println("Account's address: " + address);

        // Print out credentials's balance
        Request<?, EthGetBalance> ethGetBalanceRequest = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST);
        EthGetBalance ethGetBalance = null;
        try {
            ethGetBalance = ethGetBalanceRequest.send();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        BigInteger balanceInWei = ethGetBalance.getBalance();
        BigDecimal balance = Convert.fromWei(new BigDecimal(balanceInWei), Unit.ETHER);
        System.out.println("Account's balance: " + balance + " ETH");

        // Get transaction manager
        TransactionManager transactionManager = new ClientTransactionManager(web3, address);

        // Print out gas information
        System.out.println("Gas limit: " + DefaultGasProvider.GAS_LIMIT);
        System.out.println("Gas price: " + DefaultGasProvider.GAS_PRICE);

        // Coin contract deployment
        coinContractDeployment(web3, credentials, transactionManager);
    }

    private static void coinContractTesting(Web3j web3, Credentials credentials, TransactionManager transactionManager) {
        // Coin contract load
        Coin coinContract = Coin.load("0xdb2475a6d96c0db536caed78b71aa4554a76b7d31c984e6487be517abe533b71", web3, transactionManager, gasProvider);

        System.out.println(coinContract);
    }

    private static void coinContractDeployment(Web3j web3, Credentials credentials, TransactionManager transactionManager) {
        // Deploying contract
        RemoteCall<Coin> coinCall = Coin.deploy(
            web3, transactionManager, gasProvider
        );
        Coin coinContract = null;
        try {
            coinContract = coinCall.send();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println(coinContract.getContractAddress());
    }
}
