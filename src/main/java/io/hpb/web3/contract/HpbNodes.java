package io.hpb.web3.contract;

import io.hpb.web3.abi.EventEncoder;
import io.hpb.web3.abi.TypeReference;
import io.hpb.web3.abi.datatypes.Address;
import io.hpb.web3.abi.datatypes.DynamicArray;
import io.hpb.web3.abi.datatypes.Event;
import io.hpb.web3.abi.datatypes.Function;
import io.hpb.web3.abi.datatypes.Type;
import io.hpb.web3.abi.datatypes.Utf8String;
import io.hpb.web3.abi.datatypes.generated.Bytes32;
import io.hpb.web3.abi.datatypes.generated.Uint256;
import io.hpb.web3.crypto.Credentials;
import io.hpb.web3.protocol.Web3;
import io.hpb.web3.protocol.core.DefaultBlockParameter;
import io.hpb.web3.protocol.core.RemoteCall;
import io.hpb.web3.protocol.core.methods.request.HpbFilter;
import io.hpb.web3.protocol.core.methods.response.Log;
import io.hpb.web3.protocol.core.methods.response.TransactionReceipt;
import io.hpb.web3.tuples.generated.Tuple4;
import io.hpb.web3.tx.Contract;
import io.hpb.web3.tx.TransactionManager;
import io.hpb.web3.tx.gas.ContractGasProvider;
import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the the io.hpb.web3.codegen.SolidityFunctionWrapperGenerator to update.
 *
 * <p>Generated with web3 version none.
 */
@SuppressWarnings({
        "rawtypes",
        "unchecked"
})
public class HpbNodes extends Contract {
    private static final String BINARY = "60806040526040805190810160405280601181526020017f485042204e6f64657320536572766963650000000000000000000000000000008152506000908051906020019062000051929190620002bb565b5060006003553480156200006457600080fd5b5033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550620000c16000620000c7640100000000026401000000009004565b62000462565b8060038190555060028054809190600101620000e4919062000342565b5060006002600354815481101515620000f957fe5b906000526020600020906003020160020160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555060026003548154811015156200015d57fe5b90600052602060002090600302016001016080604051908101604052803373ffffffffffffffffffffffffffffffffffffffff168152602001600060010260001916815260200160006001026000191681526020016000600102600019168152509080600181540180825580915050906001820390600052602060002090600402016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001019060001916905560408201518160020190600019169055606082015181600301906000191690555050504360026003548154811015156200027657fe5b906000526020600020906003020160000181905550807f9d49bd24aa746fc8f19004008bcfc0a0bebf6660e0bb478c3a1b83d27fa2759960405160405180910390a250565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002fe57805160ff19168380011785556200032f565b828001600101855582156200032f579182015b828111156200032e57825182559160200191906001019062000311565b5b5090506200033e919062000377565b5090565b81548183558181111562000372576003028160030283600052602060002091820191016200037191906200039f565b5b505050565b6200039c91905b80821115620003985760008160009055506001016200037e565b5090565b90565b620003d891905b80821115620003d457600080820160009055600182016000620003ca9190620003db565b50600301620003a6565b5090565b90565b5080546000825560040290600052602060002090810190620003fe919062000401565b50565b6200045f91905b808211156200045b57600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160009055600282016000905560038201600090555060040162000408565b5090565b90565b61201680620004726000396000f3006080604052600436106100db576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306ec967b146100e057806306fdde03146101465780630eb82c13146101d6578063175a4ed2146102435780631de4745f1461026e5780632cf45905146102af57806352baee20146103de5780637e5a9ed9146105385780638655f0da146106675780638da5cb5b146106945780639508614b146106eb5780639879101014610702578063dbe0179014610745578063ebae7bf114610889578063f2fde38b146108f6575b600080fd5b3480156100ec57600080fd5b5061014460048036038101908080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290505050610939565b005b34801561015257600080fd5b5061015b6109d6565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561019b578082015181840152602081019050610180565b50505050905090810190601f1680156101c85780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101e257600080fd5b50610241600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803560001916906020019092919080356000191690602001909291908035600019169060200190929190505050610a74565b005b34801561024f57600080fd5b50610258610d13565b6040518082815260200191505060405180910390f35b34801561027a57600080fd5b5061029960048036038101908080359060200190929190505050610d19565b6040518082815260200191505060405180910390f35b3480156102bb57600080fd5b506103dc60048036038101908080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091929192908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919291929080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290505050610d46565b005b3480156103ea57600080fd5b5061040960048036038101908080359060200190929190505050610e2e565b6040518080602001806020018060200180602001858103855289818151815260200191508051906020019060200280838360005b8381101561045857808201518184015260208101905061043d565b50505050905001858103845288818151815260200191508051906020019060200280838360005b8381101561049a57808201518184015260208101905061047f565b50505050905001858103835287818151815260200191508051906020019060200280838360005b838110156104dc5780820151818401526020810190506104c1565b50505050905001858103825286818151815260200191508051906020019060200280838360005b8381101561051e578082015181840152602081019050610503565b505050509050019850505050505050505060405180910390f35b34801561054457600080fd5b50610665600480360381019080803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091929192908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919291929080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091929192905050506111df565b005b34801561067357600080fd5b50610692600480360381019080803590602001909291905050506112c7565b005b3480156106a057600080fd5b506106a96113c4565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156106f757600080fd5b506107006113ea565b005b34801561070e57600080fd5b50610743600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611456565b005b34801561075157600080fd5b5061075a6117e1565b6040518080602001806020018060200180602001858103855289818151815260200191508051906020019060200280838360005b838110156107a957808201518184015260208101905061078e565b50505050905001858103845288818151815260200191508051906020019060200280838360005b838110156107eb5780820151818401526020810190506107d0565b50505050905001858103835287818151815260200191508051906020019060200280838360005b8381101561082d578082015181840152602081019050610812565b50505050905001858103825286818151815260200191508051906020019060200280838360005b8381101561086f578082015181840152602081019050610854565b505050509050019850505050505050505060405180910390f35b34801561089557600080fd5b506108f4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803560001916906020019092919080356000191690602001909291908035600019169060200190929190505050611800565b005b34801561090257600080fd5b50610937600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611adb565b005b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561099757600080fd5b600090505b81518110156109d2576109c582828151811015156109b657fe5b90602001906020020151611456565b808060010191505061099c565b5050565b60008054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a6c5780601f10610a4157610100808354040283529160200191610a6c565b820191906000526020600020905b815481529060010190602001808311610a4f57829003601f168201915b505050505081565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610ad257600080fd5b6002600354815481101515610ae357fe5b906000526020600020906003020160020160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905060008114151515610b4457600080fd5b846002600354815481101515610b5657fe5b906000526020600020906003020160010182815481101515610b7457fe5b906000526020600020906004020160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550836002600354815481101515610bd557fe5b906000526020600020906003020160010182815481101515610bf357fe5b90600052602060002090600402016001018160001916905550826002600354815481101515610c1e57fe5b906000526020600020906003020160010182815481101515610c3c57fe5b90600052602060002090600402016002018160001916905550816002600354815481101515610c6757fe5b906000526020600020906003020160010182815481101515610c8557fe5b9060005260206000209060040201600301816000191690555081600019168573ffffffffffffffffffffffffffffffffffffffff166003547f0f4c6e2a86640f4bcfd9af5da2cd576d60d8b0c1abe01dc40872e725d2b87ee6878760405180836000191660001916815260200182600019166000191681526020019250505060405180910390a45050505050565b60035481565b600281815481101515610d2857fe5b90600052602060002090600302016000915090508060000154905081565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610da457600080fd5b600090505b8451811015610e2757610e1a8582815181101515610dc357fe5b906020019060200201518583815181101515610ddb57fe5b906020019060200201518584815181101515610df357fe5b906020019060200201518585815181101515610e0b57fe5b90602001906020020151611800565b8080600101915050610da9565b5050505050565b606080606080610e3c611dc4565b600060608060608060006003548c11151515610e5757600080fd5b60028c815481101515610e6657fe5b90600052602060002090600302016040805190810160405290816000820154815260200160018201805480602002602001604051908101604052809291908181526020016000905b82821015610f6d5783829060005260206000209060040201608060405190810160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001600182015460001916600019168152602001600282015460001916600019168152602001600382015460001916600019168152505081526020019060010190610eae565b505050508152505096506001876020015151111515610f8b57600080fd5b600187602001515103955085604051908082528060200260200182016040528015610fc55781602001602082028038833980820191505090505b50945085604051908082528060200260200182016040528015610ff75781602001602082028038833980820191505090505b509350856040519080825280602002602001820160405280156110295781602001602082028038833980820191505090505b5092508560405190808252806020026020018201604052801561105b5781602001602082028038833980820191505090505b509150600190505b8660200151518110156111c55786602001518181518110151561108257fe5b906020019060200201516000015185600183038151811015156110a157fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250508660200151818151811015156110ef57fe5b9060200190602002015160200151846001830381518110151561110e57fe5b90602001906020020190600019169081600019168152505086602001518181518110151561113857fe5b9060200190602002015160400151836001830381518110151561115757fe5b90602001906020020190600019169081600019168152505086602001518181518110151561118157fe5b906020019060200201516060015182600183038151811015156111a057fe5b9060200190602002019060001916908160001916815250508080600101915050611063565b848484849a509a509a509a50505050505050509193509193565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561123d57600080fd5b600090505b84518110156112c0576112b3858281518110151561125c57fe5b90602001906020020151858381518110151561127457fe5b90602001906020020151858481518110151561128c57fe5b9060200190602002015185858151811015156112a457fe5b90602001906020020151610a74565b8080600101915050611242565b5050505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561132357600080fd5b6000811415151561133357600080fd5b6003548110151561134357600080fd5b60028181548110151561135257fe5b9060005260206000209060030201600101600260035481548110151561137457fe5b9060005260206000209060030201600101908054611393929190611dde565b50807fa7a6ca9013c493f0ca553426096cd4fde53db0c3e6b49a427fb54b6578618d9360405160405180910390a250565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561144657600080fd5b611454600160035401611bd5565b565b600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156114b557600080fd5b60026003548154811015156114c657fe5b906000526020600020906003020160020160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205491506000821415151561152757600080fd5b8190505b6001600260035481548110151561153e57fe5b9060005260206000209060030201600101805490500381101561167f57600260035481548110151561156c57fe5b90600052602060002090600302016001016001820181548110151561158d57fe5b906000526020600020906004020160026003548154811015156115ac57fe5b9060005260206000209060030201600101828154811015156115ca57fe5b90600052602060002090600402016000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600182015481600101906000191690556002820154816002019060001916905560038201548160030190600019169055905050808060010191505061152b565b600260035481548110151561169057fe5b9060005260206000209060030201600101828154811015156116ae57fe5b9060005260206000209060040201600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160009055600282016000905560038201600090555050600260035481548110151561170f57fe5b90600052602060002090600302016001018054809190600190036117339190611ecd565b506000600260035481548110151561174757fe5b906000526020600020906003020160020160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff167f7e9922a9d0ca154569fb50d594599577dcbc2c274b19ea3646ec5f765aabb00460405160405180910390a2505050565b6060806060806117f2600354610e2e565b935093509350935090919293565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561185e57600080fd5b600260035481548110151561186f57fe5b906000526020600020906003020160020160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205490506000811415156118cf57600080fd5b60026003548154811015156118e057fe5b906000526020600020906003020160010180549050905080600260035481548110151561190957fe5b906000526020600020906003020160020160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600260035481548110151561196c57fe5b90600052602060002090600302016001016080604051908101604052808773ffffffffffffffffffffffffffffffffffffffff168152602001866000191681526020018560001916815260200184600019168152509080600181540180825580915050906001820390600052602060002090600402016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010190600019169055604082015181600201906000191690556060820151816003019060001916905550505081600019168573ffffffffffffffffffffffffffffffffffffffff166003547fa675bdfa314d9b6e636aae0b61a77597134927bbc7e168b00490f7379c73019c878760405180836000191660001916815260200182600019166000191681526020019250505060405180910390a45050505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515611b3757600080fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f5c486528ec3e3f0ea91181cff8116f02bfa350e03b8b6f12e00765adbb5af85c60405160405180910390a350565b8060038190555060028054809190600101611bf09190611eff565b5060006002600354815481101515611c0457fe5b906000526020600020906003020160020160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506002600354815481101515611c6757fe5b90600052602060002090600302016001016080604051908101604052803373ffffffffffffffffffffffffffffffffffffffff168152602001600060010260001916815260200160006001026000191681526020016000600102600019168152509080600181540180825580915050906001820390600052602060002090600402016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550602082015181600101906000191690556040820151816002019060001916905560608201518160030190600019169055505050436002600354815481101515611d7f57fe5b906000526020600020906003020160000181905550807f9d49bd24aa746fc8f19004008bcfc0a0bebf6660e0bb478c3a1b83d27fa2759960405160405180910390a250565b604080519081016040528060008152602001606081525090565b828054828255906000526020600020906004028101928215611ebc5760005260206000209160040282015b82811115611ebb5782826000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600182015481600101906000191690556002820154816002019060001916905560038201548160030190600019169055505091600401919060040190611e09565b5b509050611ec99190611f31565b5090565b815481835581811115611efa57600402816004028360005260206000209182019101611ef99190611f31565b5b505050565b815481835581811115611f2c57600302816003028360005260206000209182019101611f2b9190611f8f565b5b505050565b611f8c91905b80821115611f8857600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560018201600090556002820160009055600382016000905550600401611f37565b5090565b90565b611fc391905b80821115611fbf57600080820160009055600182016000611fb69190611fc6565b50600301611f95565b5090565b90565b5080546000825560040290600052602060002090810190611fe79190611f31565b505600a165627a7a7230582044b7e24bc50c7fe483bffe5f36ffaacd313fa2caa3e31f693a05a183c2f033d10029";

    public static final String FUNC_DELETEHPBNODEBATCH = "deleteHpbNodeBatch";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_UPDATEHPBNODE = "updateHpbNode";

    public static final String FUNC_CURRENTSTAGENUM = "currentStageNum";

    public static final String FUNC_NODESTAGES = "nodeStages";

    public static final String FUNC_ADDHPBNODEBATCH = "addHpbNodeBatch";

    public static final String FUNC_GETALLHPBNODESBYSTAGENUM = "getAllHpbNodesByStageNum";

    public static final String FUNC_UPDATEHPBNODEBATCH = "updateHpbNodeBatch";

    public static final String FUNC_COPYALLHPBNODESBYSTAGENUM = "copyAllHpbNodesByStageNum";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ADDSTAGE = "addStage";

    public static final String FUNC_DELETEHPBNODE = "deleteHpbNode";

    public static final String FUNC_GETALLHPBNODES = "getAllHpbNodes";

    public static final String FUNC_ADDHPBNODE = "addHpbNode";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event TRANSFEROWNERSHIP_EVENT = new Event("TransferOwnership", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event CHANGESTAGE_EVENT = new Event("ChangeStage", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    public static final Event ADDHPBNODE_EVENT = new Event("AddHpbNode", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event UPDATEHPBNODE_EVENT = new Event("UpdateHpbNode", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event DELETEHPBNODE_EVENT = new Event("DeleteHpbNode", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event COPYALLHPBNODESBYSTAGENUM_EVENT = new Event("CopyAllHpbNodesByStageNum", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected HpbNodes(String contractAddress, Web3 web3, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3, credentials, gasPrice, gasLimit);
    }

    protected HpbNodes(String contractAddress, Web3 web3, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3, credentials, contractGasProvider);
    }

    @Deprecated
    protected HpbNodes(String contractAddress, Web3 web3, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3, transactionManager, gasPrice, gasLimit);
    }

    protected HpbNodes(String contractAddress, Web3 web3, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> deleteHpbNodeBatch(List<String> coinbases) {
        final Function function = new Function(
                FUNC_DELETEHPBNODEBATCH, 
                Arrays.<Type>asList(new io.hpb.web3.abi.datatypes.DynamicArray<io.hpb.web3.abi.datatypes.Address>(
                        io.hpb.web3.abi.datatypes.Address.class,
                        io.hpb.web3.abi.Utils.typeMap(coinbases, io.hpb.web3.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> updateHpbNode(String coinbase, byte[] cid1, byte[] cid2,
            byte[] hid) {
        final Function function = new Function(
                FUNC_UPDATEHPBNODE, 
                Arrays.<Type>asList(new io.hpb.web3.abi.datatypes.Address(coinbase), 
                new io.hpb.web3.abi.datatypes.generated.Bytes32(cid1), 
                new io.hpb.web3.abi.datatypes.generated.Bytes32(cid2), 
                new io.hpb.web3.abi.datatypes.generated.Bytes32(hid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> currentStageNum() {
        final Function function = new Function(FUNC_CURRENTSTAGENUM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> nodeStages(BigInteger param0) {
        final Function function = new Function(FUNC_NODESTAGES, 
                Arrays.<Type>asList(new io.hpb.web3.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addHpbNodeBatch(List<String> coinbases,
            List<byte[]> cid1s, List<byte[]> cid2s, List<byte[]> hids) {
        final Function function = new Function(
                FUNC_ADDHPBNODEBATCH, 
                Arrays.<Type>asList(new io.hpb.web3.abi.datatypes.DynamicArray<io.hpb.web3.abi.datatypes.Address>(
                        io.hpb.web3.abi.datatypes.Address.class,
                        io.hpb.web3.abi.Utils.typeMap(coinbases, io.hpb.web3.abi.datatypes.Address.class)), 
                new io.hpb.web3.abi.datatypes.DynamicArray<io.hpb.web3.abi.datatypes.generated.Bytes32>(
                        io.hpb.web3.abi.datatypes.generated.Bytes32.class,
                        io.hpb.web3.abi.Utils.typeMap(cid1s, io.hpb.web3.abi.datatypes.generated.Bytes32.class)), 
                new io.hpb.web3.abi.datatypes.DynamicArray<io.hpb.web3.abi.datatypes.generated.Bytes32>(
                        io.hpb.web3.abi.datatypes.generated.Bytes32.class,
                        io.hpb.web3.abi.Utils.typeMap(cid2s, io.hpb.web3.abi.datatypes.generated.Bytes32.class)), 
                new io.hpb.web3.abi.datatypes.DynamicArray<io.hpb.web3.abi.datatypes.generated.Bytes32>(
                        io.hpb.web3.abi.datatypes.generated.Bytes32.class,
                        io.hpb.web3.abi.Utils.typeMap(hids, io.hpb.web3.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple4<List<String>, List<byte[]>, List<byte[]>, List<byte[]>>> getAllHpbNodesByStageNum(BigInteger _stageNum) {
        final Function function = new Function(FUNC_GETALLHPBNODESBYSTAGENUM, 
                Arrays.<Type>asList(new io.hpb.web3.abi.datatypes.generated.Uint256(_stageNum)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        return new RemoteCall<Tuple4<List<String>, List<byte[]>, List<byte[]>, List<byte[]>>>(
                new Callable<Tuple4<List<String>, List<byte[]>, List<byte[]>, List<byte[]>>>() {
                    @Override
                    public Tuple4<List<String>, List<byte[]>, List<byte[]>, List<byte[]>> call()
                            throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<List<String>, List<byte[]>, List<byte[]>, List<byte[]>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                                convertToNative((List<Bytes32>) results.get(2).getValue()), 
                                convertToNative((List<Bytes32>) results.get(3).getValue()));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> updateHpbNodeBatch(List<String> coinbases,
            List<byte[]> cid1s, List<byte[]> cid2s, List<byte[]> hids) {
        final Function function = new Function(
                FUNC_UPDATEHPBNODEBATCH, 
                Arrays.<Type>asList(new io.hpb.web3.abi.datatypes.DynamicArray<io.hpb.web3.abi.datatypes.Address>(
                        io.hpb.web3.abi.datatypes.Address.class,
                        io.hpb.web3.abi.Utils.typeMap(coinbases, io.hpb.web3.abi.datatypes.Address.class)), 
                new io.hpb.web3.abi.datatypes.DynamicArray<io.hpb.web3.abi.datatypes.generated.Bytes32>(
                        io.hpb.web3.abi.datatypes.generated.Bytes32.class,
                        io.hpb.web3.abi.Utils.typeMap(cid1s, io.hpb.web3.abi.datatypes.generated.Bytes32.class)), 
                new io.hpb.web3.abi.datatypes.DynamicArray<io.hpb.web3.abi.datatypes.generated.Bytes32>(
                        io.hpb.web3.abi.datatypes.generated.Bytes32.class,
                        io.hpb.web3.abi.Utils.typeMap(cid2s, io.hpb.web3.abi.datatypes.generated.Bytes32.class)), 
                new io.hpb.web3.abi.datatypes.DynamicArray<io.hpb.web3.abi.datatypes.generated.Bytes32>(
                        io.hpb.web3.abi.datatypes.generated.Bytes32.class,
                        io.hpb.web3.abi.Utils.typeMap(hids, io.hpb.web3.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> copyAllHpbNodesByStageNum(BigInteger stageNum) {
        final Function function = new Function(
                FUNC_COPYALLHPBNODESBYSTAGENUM, 
                Arrays.<Type>asList(new io.hpb.web3.abi.datatypes.generated.Uint256(stageNum)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addStage() {
        final Function function = new Function(
                FUNC_ADDSTAGE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> deleteHpbNode(String coinbase) {
        final Function function = new Function(
                FUNC_DELETEHPBNODE, 
                Arrays.<Type>asList(new io.hpb.web3.abi.datatypes.Address(coinbase)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple4<List<String>, List<byte[]>, List<byte[]>, List<byte[]>>> getAllHpbNodes() {
        final Function function = new Function(FUNC_GETALLHPBNODES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        return new RemoteCall<Tuple4<List<String>, List<byte[]>, List<byte[]>, List<byte[]>>>(
                new Callable<Tuple4<List<String>, List<byte[]>, List<byte[]>, List<byte[]>>>() {
                    @Override
                    public Tuple4<List<String>, List<byte[]>, List<byte[]>, List<byte[]>> call()
                            throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<List<String>, List<byte[]>, List<byte[]>, List<byte[]>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                                convertToNative((List<Bytes32>) results.get(2).getValue()), 
                                convertToNative((List<Bytes32>) results.get(3).getValue()));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> addHpbNode(String coinbase, byte[] cid1, byte[] cid2,
            byte[] hid) {
        final Function function = new Function(
                FUNC_ADDHPBNODE, 
                Arrays.<Type>asList(new io.hpb.web3.abi.datatypes.Address(coinbase), 
                new io.hpb.web3.abi.datatypes.generated.Bytes32(cid1), 
                new io.hpb.web3.abi.datatypes.generated.Bytes32(cid2), 
                new io.hpb.web3.abi.datatypes.generated.Bytes32(hid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new io.hpb.web3.abi.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<TransferOwnershipEventResponse> getTransferOwnershipEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFEROWNERSHIP_EVENT, transactionReceipt);
        ArrayList<TransferOwnershipEventResponse> responses = new ArrayList<TransferOwnershipEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferOwnershipEventResponse typedResponse = new TransferOwnershipEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferOwnershipEventResponse> transferOwnershipEventFlowable(HpbFilter filter) {
        return web3.hpbLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferOwnershipEventResponse>() {
            @Override
            public TransferOwnershipEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFEROWNERSHIP_EVENT, log);
                TransferOwnershipEventResponse typedResponse = new TransferOwnershipEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferOwnershipEventResponse> transferOwnershipEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        HpbFilter filter = new HpbFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFEROWNERSHIP_EVENT));
        return transferOwnershipEventFlowable(filter);
    }

    public List<ChangeStageEventResponse> getChangeStageEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGESTAGE_EVENT, transactionReceipt);
        ArrayList<ChangeStageEventResponse> responses = new ArrayList<ChangeStageEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChangeStageEventResponse typedResponse = new ChangeStageEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.stageNum = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ChangeStageEventResponse> changeStageEventFlowable(HpbFilter filter) {
        return web3.hpbLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ChangeStageEventResponse>() {
            @Override
            public ChangeStageEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANGESTAGE_EVENT, log);
                ChangeStageEventResponse typedResponse = new ChangeStageEventResponse();
                typedResponse.log = log;
                typedResponse.stageNum = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ChangeStageEventResponse> changeStageEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        HpbFilter filter = new HpbFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANGESTAGE_EVENT));
        return changeStageEventFlowable(filter);
    }

    public List<AddHpbNodeEventResponse> getAddHpbNodeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDHPBNODE_EVENT, transactionReceipt);
        ArrayList<AddHpbNodeEventResponse> responses = new ArrayList<AddHpbNodeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddHpbNodeEventResponse typedResponse = new AddHpbNodeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.stageNum = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.coinbase = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.hid = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.cid1 = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.cid2 = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddHpbNodeEventResponse> addHpbNodeEventFlowable(HpbFilter filter) {
        return web3.hpbLogFlowable(filter).map(new io.reactivex.functions.Function<Log, AddHpbNodeEventResponse>() {
            @Override
            public AddHpbNodeEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDHPBNODE_EVENT, log);
                AddHpbNodeEventResponse typedResponse = new AddHpbNodeEventResponse();
                typedResponse.log = log;
                typedResponse.stageNum = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.coinbase = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.hid = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.cid1 = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.cid2 = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddHpbNodeEventResponse> addHpbNodeEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        HpbFilter filter = new HpbFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDHPBNODE_EVENT));
        return addHpbNodeEventFlowable(filter);
    }

    public List<UpdateHpbNodeEventResponse> getUpdateHpbNodeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UPDATEHPBNODE_EVENT, transactionReceipt);
        ArrayList<UpdateHpbNodeEventResponse> responses = new ArrayList<UpdateHpbNodeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UpdateHpbNodeEventResponse typedResponse = new UpdateHpbNodeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.stageNum = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.coinbase = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.hid = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.cid1 = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.cid2 = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UpdateHpbNodeEventResponse> updateHpbNodeEventFlowable(HpbFilter filter) {
        return web3.hpbLogFlowable(filter).map(new io.reactivex.functions.Function<Log, UpdateHpbNodeEventResponse>() {
            @Override
            public UpdateHpbNodeEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UPDATEHPBNODE_EVENT, log);
                UpdateHpbNodeEventResponse typedResponse = new UpdateHpbNodeEventResponse();
                typedResponse.log = log;
                typedResponse.stageNum = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.coinbase = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.hid = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.cid1 = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.cid2 = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UpdateHpbNodeEventResponse> updateHpbNodeEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        HpbFilter filter = new HpbFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPDATEHPBNODE_EVENT));
        return updateHpbNodeEventFlowable(filter);
    }

    public List<DeleteHpbNodeEventResponse> getDeleteHpbNodeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DELETEHPBNODE_EVENT, transactionReceipt);
        ArrayList<DeleteHpbNodeEventResponse> responses = new ArrayList<DeleteHpbNodeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DeleteHpbNodeEventResponse typedResponse = new DeleteHpbNodeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.coinbase = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DeleteHpbNodeEventResponse> deleteHpbNodeEventFlowable(HpbFilter filter) {
        return web3.hpbLogFlowable(filter).map(new io.reactivex.functions.Function<Log, DeleteHpbNodeEventResponse>() {
            @Override
            public DeleteHpbNodeEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DELETEHPBNODE_EVENT, log);
                DeleteHpbNodeEventResponse typedResponse = new DeleteHpbNodeEventResponse();
                typedResponse.log = log;
                typedResponse.coinbase = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DeleteHpbNodeEventResponse> deleteHpbNodeEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        HpbFilter filter = new HpbFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELETEHPBNODE_EVENT));
        return deleteHpbNodeEventFlowable(filter);
    }

    public List<CopyAllHpbNodesByStageNumEventResponse> getCopyAllHpbNodesByStageNumEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(COPYALLHPBNODESBYSTAGENUM_EVENT, transactionReceipt);
        ArrayList<CopyAllHpbNodesByStageNumEventResponse> responses = new ArrayList<CopyAllHpbNodesByStageNumEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CopyAllHpbNodesByStageNumEventResponse typedResponse = new CopyAllHpbNodesByStageNumEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.stageNum = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CopyAllHpbNodesByStageNumEventResponse> copyAllHpbNodesByStageNumEventFlowable(HpbFilter filter) {
        return web3.hpbLogFlowable(filter).map(new io.reactivex.functions.Function<Log, CopyAllHpbNodesByStageNumEventResponse>() {
            @Override
            public CopyAllHpbNodesByStageNumEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(COPYALLHPBNODESBYSTAGENUM_EVENT, log);
                CopyAllHpbNodesByStageNumEventResponse typedResponse = new CopyAllHpbNodesByStageNumEventResponse();
                typedResponse.log = log;
                typedResponse.stageNum = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CopyAllHpbNodesByStageNumEventResponse> copyAllHpbNodesByStageNumEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        HpbFilter filter = new HpbFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(COPYALLHPBNODESBYSTAGENUM_EVENT));
        return copyAllHpbNodesByStageNumEventFlowable(filter);
    }

    @Deprecated
    public static HpbNodes load(String contractAddress, Web3 web3, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new HpbNodes(contractAddress, web3, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static HpbNodes load(String contractAddress, Web3 web3,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new HpbNodes(contractAddress, web3, transactionManager, gasPrice, gasLimit);
    }

    public static HpbNodes load(String contractAddress, Web3 web3, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new HpbNodes(contractAddress, web3, credentials, contractGasProvider);
    }

    public static HpbNodes load(String contractAddress, Web3 web3,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new HpbNodes(contractAddress, web3, transactionManager, contractGasProvider);
    }

    public static RemoteCall<HpbNodes> deploy(Web3 web3, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(HpbNodes.class, web3, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<HpbNodes> deploy(Web3 web3, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(HpbNodes.class, web3, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<HpbNodes> deploy(Web3 web3, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(HpbNodes.class, web3, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<HpbNodes> deploy(Web3 web3, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(HpbNodes.class, web3, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class TransferOwnershipEventResponse {
        public Log log;

        public String from;

        public String to;
    }

    public static class ChangeStageEventResponse {
        public Log log;

        public BigInteger stageNum;
    }

    public static class AddHpbNodeEventResponse {
        public Log log;

        public BigInteger stageNum;

        public String coinbase;

        public byte[] hid;

        public byte[] cid1;

        public byte[] cid2;
    }

    public static class UpdateHpbNodeEventResponse {
        public Log log;

        public BigInteger stageNum;

        public String coinbase;

        public byte[] hid;

        public byte[] cid1;

        public byte[] cid2;
    }

    public static class DeleteHpbNodeEventResponse {
        public Log log;

        public String coinbase;
    }

    public static class CopyAllHpbNodesByStageNumEventResponse {
        public Log log;

        public BigInteger stageNum;
    }
}
