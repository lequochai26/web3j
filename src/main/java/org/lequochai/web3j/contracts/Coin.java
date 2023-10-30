package org.lequochai.web3j.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
 * <p>Generated with web3j version 1.5.0.
 */
@SuppressWarnings("rawtypes")
public class Coin extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b503360015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506106308061005d5f395ff3fe608060405234801561000f575f80fd5b506004361061004a575f3560e01c80635f5152261461004e578063a0712d681461007e578063a9059cbb1461009a578063c71daccb146100b6575b5f80fd5b6100686004803603810190610063919061040d565b6100d4565b6040516100759190610450565b60405180910390f35b61009860048036038101906100939190610493565b610119565b005b6100b460048036038101906100af91906104be565b610241565b005b6100be61036c565b6040516100cb9190610450565b60405180910390f35b5f805f8373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f20549050919050565b60015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610171575f80fd5b805f8060015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f8282546101dd9190610529565b925050819055507f30385c845b448a36257a6a1716e6ad2e1bc2cbe333cde1e69fe849ad6511adfe60015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff168260405161023692919061056b565b60405180910390a150565b805f803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f20541015610289575f80fd5b805f803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f8282546102d49190610592565b92505081905550805f808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f8282546103269190610529565b925050819055507fd1ba4ac2e2a11b5101f6cb4d978f514a155b421e8ec396d2d9abaf0bb02917ee338383604051610360939291906105c5565b60405180910390a15050565b5f805f3373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f2054905090565b5f80fd5b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f6103dc826103b3565b9050919050565b6103ec816103d2565b81146103f6575f80fd5b50565b5f81359050610407816103e3565b92915050565b5f60208284031215610422576104216103af565b5b5f61042f848285016103f9565b91505092915050565b5f819050919050565b61044a81610438565b82525050565b5f6020820190506104635f830184610441565b92915050565b61047281610438565b811461047c575f80fd5b50565b5f8135905061048d81610469565b92915050565b5f602082840312156104a8576104a76103af565b5b5f6104b58482850161047f565b91505092915050565b5f80604083850312156104d4576104d36103af565b5b5f6104e1858286016103f9565b92505060206104f28582860161047f565b9150509250929050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f61053382610438565b915061053e83610438565b9250828201905080821115610556576105556104fc565b5b92915050565b610565816103d2565b82525050565b5f60408201905061057e5f83018561055c565b61058b6020830184610441565b9392505050565b5f61059c82610438565b91506105a783610438565b92508282039050818111156105bf576105be6104fc565b5b92915050565b5f6060820190506105d85f83018661055c565b6105e5602083018561055c565b6105f26040830184610441565b94935050505056fea264697066735822122095e87c10d8bc7e09680a94047326ed2cec4b1c1e88a06a836567a804c5c58f3564736f6c63430008160033";

    public static final String FUNC_checkBalance = "checkBalance";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_TRANSFER = "transfer";

    public static final Event MINTED_EVENT = new Event("Minted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFERRED_EVENT = new Event("Transferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Coin(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Coin(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Coin(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Coin(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<MintedEventResponse> getMintedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MINTED_EVENT, transactionReceipt);
        ArrayList<MintedEventResponse> responses = new ArrayList<MintedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MintedEventResponse typedResponse = new MintedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.mintedBy = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MintedEventResponse getMintedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MINTED_EVENT, log);
        MintedEventResponse typedResponse = new MintedEventResponse();
        typedResponse.log = log;
        typedResponse.mintedBy = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMintedEventFromLog(log));
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINTED_EVENT));
        return mintedEventFlowable(filter);
    }

    public static List<TransferredEventResponse> getTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFERRED_EVENT, transactionReceipt);
        ArrayList<TransferredEventResponse> responses = new ArrayList<TransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferredEventResponse typedResponse = new TransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TransferredEventResponse getTransferredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRANSFERRED_EVENT, log);
        TransferredEventResponse typedResponse = new TransferredEventResponse();
        typedResponse.log = log;
        typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<TransferredEventResponse> transferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTransferredEventFromLog(log));
    }

    public Flowable<TransferredEventResponse> transferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERRED_EVENT));
        return transferredEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> checkBalance(String account) {
        final Function function = new Function(FUNC_checkBalance, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> checkBalance() {
        final Function function = new Function(FUNC_checkBalance, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(BigInteger amount) {
        final Function function = new Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String receiver, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, receiver), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Coin load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Coin(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Coin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Coin(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Coin load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Coin(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Coin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Coin(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Coin> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Coin.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Coin> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Coin.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Coin> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Coin.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Coin> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Coin.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class MintedEventResponse extends BaseEventResponse {
        public String mintedBy;

        public BigInteger amount;
    }

    public static class TransferredEventResponse extends BaseEventResponse {
        public String sender;

        public String receiver;

        public BigInteger amount;
    }
}
