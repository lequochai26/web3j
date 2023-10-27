package org.lequochai.web3j;

import java.io.IOException;
import java.math.BigInteger;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;
import org.web3j.utils.Convert.Unit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        // Connect to Web3
        HttpService service = new HttpService("https://spring-proportionate-spring.ethereum-sepolia.quiknode.pro/7f5f1803a3f1ffabac9ab7200c59795308babbfe/");
        Web3j web3 = Web3j.build(service);

        // Get credentials
        Credentials credentials = Credentials.create("3d919d14fd2983a590291da8eba29a4bb886516d73aae3b75dab157702b99df6");

        // Get address
        String address = credentials.getAddress();
        System.out.println("Address: " + address);

        // Get nonce
        Request<?, EthGetTransactionCount> ethGetTransactionCountRequest = web3.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST);
        EthGetTransactionCount ethGetTransactionCount = null;
        try {
            ethGetTransactionCount = ethGetTransactionCountRequest.send();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (ethGetTransactionCount.getError() != null) {
            System.out.println(ethGetTransactionCount.getError().getMessage());
        }

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        System.out.println("Nonce: " + nonce);

        // Gas price
        BigInteger gasPrice = DefaultGasProvider.GAS_PRICE;
        System.out.println("Gas price: " + gasPrice);

        // Gas limit
        BigInteger gasLimit = DefaultGasProvider.GAS_LIMIT;
        System.out.println("Gas limit: " + gasLimit);

        // Create a raw transaction
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
            nonce, gasPrice, gasLimit, "0x23ED2bd330c346173d541cCCcE75f195a9B94595",
            Convert.toWei("0.001", Unit.ETHER).toBigInteger()
        );

        // Sign transaction
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, 11155111, credentials);
        String signedMessageHex = Numeric.toHexString(signedMessage);
        System.out.println("Signed message: " + signedMessageHex);

        // Send ETH Transaction
        EthSendTransaction transaction = web3.ethSendRawTransaction(signedMessageHex).send();

        // Print out result
        if (transaction.hasError()) {
            System.out.println(transaction.getError().getMessage());
        }
        else {
            System.out.println(transaction.getTransactionHash());
        }
        System.out.println("DONE");

        // Shutdown web3
        web3.shutdown();
        service.close();
    }
}
