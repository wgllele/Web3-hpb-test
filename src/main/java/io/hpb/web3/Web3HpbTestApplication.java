package io.hpb.matic.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.ipc.UnixIpcService;
import org.web3j.protocol.ipc.WindowsIpcService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.utils.Async;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.hpb.CacheUtil;
import io.hpb.matic.util.bnb.MytradeTestBnbNew;
import io.hpb.matic.util.bnb.PairClass;
import io.hpb.matic.util.bnb.TxObject2;
import io.hpb.util.AppObjectUtil;
import io.hpb.util.HttpUtil;
import io.hpb.util.MyContractcAll;
import io.hpb.util.ObjectJsonHelper;
import io.hpb.util.Testme4;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
@SuppressWarnings("rawtypes")
public class TestMainBnb {

	private static final File NOTBNB = new File("/path/docker/t/notbnb.txt");
	public static final Logger log = LoggerFactory.getLogger(TestMainBnb.class);
	public static final Long tos = 18L;
	private static final BigInteger _30 = new BigInteger("30");
	private static final BigInteger _3 = new BigInteger("3");
	private static final BigInteger _5 = new BigInteger("5");
	private static ContractGasProvider contractGasProvider;
	public static final String skimUtil0="0xb800006A0000572188F30045cf008656c976398A";
	public static final String skimUtil1="0x9900006600AaEeC900e5e8d96881c111c3000058";
	public static final String skimUtilBatch="0xf3000000711C2f00D54be8000025bC4f2a166E08";
	public final static Long timeoutMillis = 3000L;
//	public static BigInteger bigPrice = Convert.toWei("3.31", Convert.Unit.GWEI).toBigInteger();
//	public static BigInteger cgasPrice = Convert.toWei("3", Convert.Unit.GWEI).toBigInteger();
//	public static BigInteger cgasPrice2 = Convert.toWei("3.0000001", Convert.Unit.GWEI).toBigInteger();
	public static BigInteger cgasPrice3 = Convert.toWei("0.33", Convert.Unit.GWEI).toBigInteger();
	public static BigInteger mgasPrice = Convert.toWei("0.3", Convert.Unit.GWEI).toBigInteger();
	public static BigInteger gasPrice = Convert.toWei("0.1", Convert.Unit.GWEI).toBigInteger();
	public static BigInteger gasPrice1 = Convert.toWei("0.135", Convert.Unit.GWEI).toBigInteger();
	public static BigInteger maXOut = Convert.toWei("0.02", Convert.Unit.ETHER).toBigInteger();
	public static BigInteger gasLimit1 = new BigInteger("500").multiply(new BigInteger("10000"));
	private static final BigInteger _50000 = new BigInteger("50000");
	private static final BigInteger _100000 = new BigInteger("100000");
	private static final MathContext mc = new MathContext(18, RoundingMode.HALF_UP);
	static {
		if (contractGasProvider == null) {
			BigInteger gasLimit = new BigInteger("300").multiply(new BigInteger("10000"));
			contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
		}
	}
	public static Map<String, String> prss = new ConcurrentHashMap<String, String>();
	public static File file1f = new File("/path/docker/t/getAllBnb_value.txt");
	static {
		try {
			List<String> lines2 = FileUtils.readLines(file1f, "UTF-8").stream().distinct().collect(Collectors.toList());
			for (String str1 : lines2) {
				@SuppressWarnings("unchecked")
				List<Object> list = ObjectJsonHelper.deserialize(str1.toLowerCase(), List.class);
				if (list.get(5) != null) {
					prss.put(list.get(2).toString(), list.get(5).toString());
				}
			}
		} catch (IOException e) {
			log.info(e.getMessage());
		}
	}
	private static File file0 = new File("/path/docker/t/amountBNBTokenUsed.txt");
	public static Map<String, BigInteger> useGas;
	public static BigInteger outMin = Convert.toWei("0.00008", Convert.Unit.ETHER).toBigInteger();
	static {
		if (useGas == null) {
			try {
				List<String> lines0 = FileUtils.readLines(file0, "UTF-8").stream().distinct()
						.collect(Collectors.toList());
				useGas = new ConcurrentHashMap<String, BigInteger>();
				for (String num : lines0) {
					if (StringUtils.isNotBlank(num)) {
						String[] split = num.split("&");
						useGas.put(split[0], new BigInteger(split[1]));
					}
				}
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}
	}
	
	public static Credentials create = Credentials
			.create("0x123");
	public static Map<String, String> allpairAddrs = new ConcurrentHashMap<String, String>();
	public static Map<String, String> allpairAddrs1 = new ConcurrentHashMap<String, String>();
	public static File file1 = new File("/path/docker/t/getBnb-meme.txt");
	public static File filere = new File("/path/docker/t/getBnb-re.txt");
	public static File file3 = new File("/path/docker/t/getBnb-memeNot.txt");
	public static final String WBNB = "0xbb4cdb9cbd36b01bd1cbaebf2de08d9173bc095c";
	public static Map<String, BigInteger> deMap = new ConcurrentHashMap<String, BigInteger>();
	static {
		deMap.put(WBNB, // WBNB
				new BigInteger("500000000000000"));
		deMap.put("0x7130d2a12b9bcbfae4f2634d864a1ee1ce3ead9c", // BTC
				new BigInteger("4037166967118"));
		deMap.put("0x2170ed0880ac9a755fd29b2688956bd959f933f8", // eth
				new BigInteger("63607002243825"));
		deMap.put("0xe9e7cea3dedca5984780bafc599bd69add087d56", // busdt
				new BigInteger("121683945622336782"));
		deMap.put("0x1af3f329e8be154074d8769d1ffa4ee058b1dbc3", // bdai
				new BigInteger("121627482888713414"));
		deMap.put("0x55d398326f99059ff775485246999027b3197955", // busdt-t
				new BigInteger("121686048248910542"));
		deMap.put("0x8ac76a51cc950d9822d68b83fe1ad97b32cd580d", // usdc
				new BigInteger("121833051192445389"));
		deMap.put("0xd17479997f34dd9156deef8f95a52d81d265be9c", // USDD
				new BigInteger("121833051192445389"));
		deMap.put("0x40af3827f39d0eacbf4a168f8d4ee67c121d11c9", // TUSD
				new BigInteger("121833051192445389"));
		deMap.put("0xfd5840cd36d94d7229439859c0112a4185bc0255", // vUSDT
				new BigInteger("121833051192445389"));
		deMap.put("0xfd5840cd36d94d7229439859c0112a4185bc0255", // TUSD
				new BigInteger("12183305"));
	}
	public static void main(String[] args) throws Exception {
		taskExecutor.setCorePoolSize(5000);
		// 线程池维护线程的最大数量
		taskExecutor.setMaxPoolSize(50000);
		// 缓存队列
		taskExecutor.setQueueCapacity(80000000);
		taskExecutor.initialize();

		processToken();
//		notbbbbb();
//		getaldfsfsf();
//		
//		extracted();
	}

	private static void notbbbbb() throws Exception {
		List<String> lines2 = FileUtils.readLines(new File("/path/docker/t/notbnb.txt"), "UTF-8");
		List<String> lines3 = FileUtils.readLines(new File("/path/docker/t/getBnb-memenew1.txt"), "UTF-8");
		for(String st1:lines3) {
			boolean has=false;
			for(String st2:lines2) {
				if(StringUtils.isNotBlank(st2)) {
					if(st1.contains(st2)) {
						has=true;
						break;
					}
				}
			}
			if(!has) {
				System.out.println(st1);
			}
		}
	}

	private static void getaldfsfsf() throws Exception {
		File file1 = new File("/path/docker/t/getBnb-meme.txt");
//		File file1 = new File("/path/docker/t/getBnb-memenew3.txt");
		File file2 = new File("/path/docker/t/getBnb-memenew3.txt");
//		File file2 = new File("/path/docker/t/getBnb-memenew.txt");
		List<String> lines2= FileUtils.readLines(file2, "UTF-8");
		
		for (String str1 : lines2) {
			String str = str1.toLowerCase();
			@SuppressWarnings("unchecked")
			List<Object> list = ObjectJsonHelper.deserialize(str, List.class);
			String addr=null;
			String token0=null;
			String token1=null;
			try {
				addr = list.get(0).toString();
				token0 = list.get(1).toString();
				token1 = list.get(2).toString();
			} catch (Exception e) {
				System.out.println("226:===  "+str);
				e.printStackTrace();
			}
			if (allpairAddrs.get(addr) == null && allpairAddrs.get(token0) == null
					&& allpairAddrs.get(token1) == null&& notFrAddrs.get(addr) == null
					&& notFrAddrs.get(token0) == null&& notFrAddrs.get(token1) == null) {
				String object = list.get(list.size()-1).toString();
				if(object.length()>20) {
					str1=str1.replace(object, object.substring(0, 19));
				}
				allpairAddrs.put(addr, str1);
			}else {
				//System.out.println(str1);
			}
		}
		
		List<String> lines8= FileUtils.readLines(file1, "UTF-8");
		List<String> lines1= new ArrayList<String>();
		for(String str1:lines8) {
			String str = str1.toLowerCase();
			@SuppressWarnings("unchecked")
			List<Object> list = ObjectJsonHelper.deserialize(str, List.class);
			String addr = list.get(0).toString();
			String token0 = list.get(1).toString();
			String token1 = list.get(2).toString();
			if (notFrAddrs.get(addr) == null
					&& notFrAddrs.get(token0) == null&& notFrAddrs.get(token1) == null&&
					allpairAddrs.get(addr) == null && allpairAddrs.get(token0) == null
					&& allpairAddrs.get(token1) == null&&
					allpairAddrs1.get(addr) == null && allpairAddrs1.get(token0) == null
					&& allpairAddrs1.get(token1) == null
					
					) {
				allpairAddrs1.put(addr, str1);
				lines1.add(str1);
			}
		}
		System.out.println(lines1.size());
		int scal = lines1.size() / 80 + 1;
		scal =RandomUtils.nextInt(350, 400);
		
		Map<String, PairClass> pairClass = new ConcurrentHashMap<String, PairClass>();
		int p = 0;
		for (String str1 : lines1) {
			String str = str1.toLowerCase();
			@SuppressWarnings("unchecked")
			List<Object> list = ObjectJsonHelper.deserialize(str, List.class);
			String addr = list.get(0).toString();
			if("0xfcd7c1fae2d5f40a804cd9455c35351ed12d8c75".equals(addr)) {
				System.out.println(addr);
			}
			String token0 = list.get(1).toString();
			String token1 = list.get(2).toString();
			if (notFrAddrs.get(addr) == null
					&& notFrAddrs.get(token0) == null&& notFrAddrs.get(token1) == null&&
					allpairAddrs.get(addr) == null && allpairAddrs.get(token0) == null
					&& allpairAddrs.get(token1) == null
//					&&allpairAddrs1.get(addr)!=null
					) {
				allpairAddrs.put(addr, str1);
				p++;
				String k = (p / scal + 1) + "";
				PairClass classs = pairClass.get(k);
				if (classs == null) {
					classs = new PairClass();
					pairClass.put(k, classs);
				}
				classs.getPairAddrs().add(addr);
				classs.getToken0s().add(token0);
				classs.getToken1s().add(token1);
			}
		}
		
		System.out.println(pairClass.size());
		System.out.println(scal);

		try {
			String[] thsaddr=new String[] {
					"https://bsc-dataseed1.defibit.io",
					"https://bsc-dataseed2.defibit.io",
					"https://bsc-dataseed3.defibit.io",
					"https://bsc-dataseed4.defibit.io",
					"https://bsc-dataseed1.ninicoin.io",
					"https://bsc-dataseed2.ninicoin.io",
					"https://bsc-dataseed3.ninicoin.io",
					"https://bsc-dataseed4.ninicoin.io",
					"https://bsc.nodereal.io",
					};
			for(int i=1;i<=thsaddr.length;i++) {
				Admin admin1 = admin(thsaddr[i-1]);
				adminss.put(""+i, admin1);
				adminvalues.put(""+i, thsaddr[i-1]);
			}


		} catch (Exception e) {
			//log.info(e.getMessage());
		}
	
		for (int i = 1; i <= 9; i++) {
			String key = i + "";
			contractcAlls.put(key, MyContractcAll.load("0x123a549CD5CF2E4A532608386886a7F9ff1607B2", adminss.get(key),
					create, contractGasProvider));
		}
		Instant start = Instant.now();
		CountDownLatch countDownLatch=new CountDownLatch(pairClass.size());
		for (Map.Entry<String, PairClass> en : pairClass.entrySet()) {
			PairClass classs = en.getValue();
			List<String> pairAddrs = classs.getPairAddrs();
			List<String> token1s = classs.getToken1s();
			List<String> token0s = classs.getToken0s();
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						getAllResults(pairAddrs, token1s, token0s);
					} catch (Exception e) {
						e.printStackTrace();
					}
					countDownLatch.countDown();
				}
			});
		}
		countDownLatch.await(timeoutMillis,TimeUnit.MINUTES);
		Duration between1 = Duration.between(start, Instant.now());
		System.out.println("spend==" + DurationFormatUtils.formatDuration(between1.toMillis(), "ss.SSS "));
	}

	private static void getAllResults(List<String> pairAddrs, List<String> token1s, List<String> token0s) {
		try {
			Tuple4<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>> tuple4 = contractcAlls
					.get(CacheUtil.getCount()).getSkimOuts(token0s, token1s, pairAddrs).send();
			
			List<BigInteger> reserve0s = tuple4.component1();
			List<BigInteger> reserve1s = tuple4.component2();
			List<BigInteger> bal0s = tuple4.component3();
			List<BigInteger> bal1s = tuple4.component4();
			for(int i=0;i<pairAddrs.size();i++) {
				BigInteger r0 = reserve0s.get(i);
				BigInteger r1 = reserve1s.get(i);
				BigInteger b0 = bal0s.get(i);
				BigInteger b1 = bal1s.get(i);
//				genExtal(pairAddrs, token1s, token0s, i, r0, r1, b0, b1);
				if(       
						r0.compareTo(BigInteger.ZERO)>0
						&&r1.compareTo(BigInteger.ZERO)>0
						&&b0.compareTo(BigInteger.ZERO)>0
						&&b1.compareTo(BigInteger.ZERO)>0
				) {
					if(deMap.get(token0s.get(i))!=null) {
						if(r0.equals(b0)) {
							if(r0.compareTo(deMap.get(token0s.get(i)).multiply(new BigInteger("1")))>0) {
								System.out.println(allpairAddrs.get(pairAddrs.get(i)));
							}
						}
					}else if(deMap.get(token1s.get(i))!=null) {
						if(r1.equals(b1)) {
							if(r1.compareTo(deMap.get(token1s.get(i)).multiply(new BigInteger("1")))>0) {
								System.out.println(allpairAddrs.get(pairAddrs.get(i)));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			
			if(pairAddrs.size()==1) {
				System.out.println(AppObjectUtil.toJson(pairAddrs));
			}else if(e.getMessage()==null) {
				//System.out.println("m"+tocount);
				getFen(pairAddrs, token1s, token0s);
			}else if(e.getMessage()!=null&&!e.getMessage().contains("timeout")&&!e.getMessage().contains("Connection reset")) {
//				System.out.println("------------"+adminvalues.get(CacheUtil.getCount()));
//				System.out.println(e.getMessage()+",,,"+pairAddrs.size());
//				//System.out.println(AppObjectUtil.toJson(pairAddrs));
//				System.out.println("------------");
				getFen(pairAddrs, token1s, token0s);
			}else {
				//System.out.println("mmmm"+tocount);
				//System.out.println(e.getMessage()+",,"+pairAddrs.size());
				getFen(pairAddrs, token1s, token0s);
			}
		}
	}
	private static void getFen(List<String> pairAddrs, List<String> token1s, List<String> token0s)  {
		List<String> pairAddrs_1=new ArrayList<String>();
		List<String> token1s_1=new ArrayList<String>(); 
		List<String> token0s_1=new ArrayList<String>();
		List<String> pairAddrs_2=new ArrayList<String>();
		List<String> token1s_2=new ArrayList<String>(); 
		List<String> token0s_2=new ArrayList<String>();
		for(int i=0;i<pairAddrs.size();i++) {
			if(i%2==0) {
				pairAddrs_1.add(pairAddrs.get(i));
				token1s_1.add(token1s.get(i));
				token0s_1.add(token0s.get(i));
			}else {
				pairAddrs_2.add(pairAddrs.get(i));
				token1s_2.add(token1s.get(i));
				token0s_2.add(token0s.get(i));
			}
		}
		getAllResults(pairAddrs_1, token1s_1, token0s_1);
		getAllResults(pairAddrs_2, token1s_2, token0s_2);
	}
	public static ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	public static ThreadPoolTaskExecutor taskExecutor1 = new ThreadPoolTaskExecutor();
	public static Map<String, Admin> adminss = new ConcurrentHashMap<String, Admin>();
	public static Map<String, String> adminvalues = new ConcurrentHashMap<String, String>();
//	public static Map<String, ERC20> ercs = new ConcurrentHashMap<String, ERC20>();
	private static Map<String, MyContractcAll> contractcAlls = new ConcurrentHashMap<String, MyContractcAll>();
	private static Map<String, MyContractcAll> contractcAll1s = new ConcurrentHashMap<String, MyContractcAll>();

	private static void processToken()throws Exception {
		File file1 = new File("/path/docker/t/getBnb-memenew.txt");
		List<String> lines1 = FileUtils.readLines(file1, "UTF-8").stream().distinct().collect(Collectors.toList());
		System.out.println(lines1.size());
		int scal = RandomUtils.nextInt(200, 210);
		System.out.println(scal);
		Map<String, PairClass> pairClass = new ConcurrentHashMap<String, PairClass>();
		int p = 0;
		for (String str1 : lines1) {
			String str = str1.toLowerCase();
			@SuppressWarnings("unchecked")
			List<Object> list = ObjectJsonHelper.deserialize(str, List.class);
			String addr = list.get(0).toString();
			String token0 = list.get(1).toString();
			String token1 = list.get(2).toString();
			
			if (allpairAddrs.get(addr) == null && allpairAddrs.get(token0) == null
					&& allpairAddrs.get(token1) == null &&notFrAddrs.get(addr) == null
					&& notFrAddrs.get(token0) == null&& notFrAddrs.get(token1) == null) {
				allpairAddrs.put(addr, "");
				p++;
				String k = (p / scal + 1) + "";
				PairClass classs = pairClass.get(k);
				if (classs == null) {
					classs = new PairClass();
					pairClass.put(k, classs);
				}
				classs.getPairAddrs().add(addr);
				classs.getToken0s().add(token0);
				classs.getToken1s().add(token1);
			}
		}
		System.out.println(pairClass.size());
		adminss=MytradeTestBnbNew.adminss;
		MytradeTestBnbNew.MyExhNon();
		for (int i = 1; i <= 17; i++) {
			Admin admin = adminss.get(i+"");
			EthGasPrice send = admin.ethGasPrice().send();
			BigInteger _gasPrice2 = send.getGasPrice();
			if(_gasPrice2.compareTo(gasPrice)<0) {
				cgasPrice3=cgasPrice3.multiply(_gasPrice2).divide(gasPrice);
				mgasPrice=mgasPrice.multiply(_gasPrice2).divide(gasPrice);
				gasPrice1=gasPrice1.multiply(_gasPrice2).divide(gasPrice);
				gasPrice=_gasPrice2;
				System.out.println( Convert.fromWei(_gasPrice2.toString(), Convert.Unit.GWEI));
			}
			contractcAlls.put(i + "", 
					MyContractcAll.load("0x123a549CD5CF2E4A532608386886a7F9ff1607B2", admin,
					create, contractGasProvider));
			contractcAll1s.put(i + "", 
					MyContractcAll.load("0x3dC2DB3E4CDA84339566e8F36FF130a5B7fFC0Bf", admin,
							create, contractGasProvider));
//			ercs.put(i + "",
//					ERC20.load("0x0000000000004946c0e9f43f4dee607b0ef1fa1c", admin, create, contractGasProvider));
		}
		System.out.println(AppObjectUtil.toJson(MytradeTestBnbNew.deMap));
		while (true) {
			Instant start = Instant.now();
			try {
				continueToken(pairClass);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			Duration between1 = Duration.between(start, Instant.now());
			System.out.println("spend==" + DurationFormatUtils.formatDuration(between1.toMillis(), "ss.SSS "));
		}
	}
	

	private static void continueToken(Map<String, PairClass> pairClass) throws Exception {
		CountDownLatch countDownLatch=new CountDownLatch(pairClass.size());
		List<TxObject2> tos = new ArrayList<TxObject2>();
		for (Map.Entry<String, PairClass> en : pairClass.entrySet()) {
			PairClass classs = en.getValue();
			String key = en.getKey();
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						getTokkkkk(tos, classs, key);
					} catch (Exception e) {
						e.printStackTrace();
					}
					countDownLatch.countDown();
				}
			});
			Thread.sleep(100);
		}
		countDownLatch.await(timeoutMillis,TimeUnit.MINUTES);
		if (tos.size() > 0) {
			process7(tos);
		}
	}

	private static void getTokkkkk(List<TxObject2> tos, PairClass classs, String key) {
		List<String> pairAddrs = classs.getPairAddrs();
		List<String> token1s = classs.getToken1s();
		List<String> token0s = classs.getToken0s();
		try {
			fenget(tos, key, pairAddrs, token1s, token0s);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	private static void fenget(List<TxObject2> tos, String key, List<String> pairAddrs, List<String> token1s,
			List<String> token0s) throws Exception {
		List<String> _pairAddrs = new ArrayList<String>();
		List<String> _token1s = new ArrayList<String>();
		List<String> _token0s = new ArrayList<String>();
		for (int i = 0; i < pairAddrs.size(); i++) {
			if (_pairAddrs.size() < pairAddrs.size() / 2) {
				_pairAddrs.add(pairAddrs.get(i));
				_token1s.add(token1s.get(i));
				_token0s.add(token0s.get(i));
			} else {
				continueGet(tos, key, _pairAddrs, _token1s, _token0s, 0);
				_pairAddrs = new ArrayList<String>();
				_token1s = new ArrayList<String>();
				_token0s = new ArrayList<String>();
				_pairAddrs.add(pairAddrs.get(i));
				_token1s.add(token1s.get(i));
				_token0s.add(token0s.get(i));
			}
		}
		if (_pairAddrs != null && _pairAddrs.size() > 0) {
			continueGet(tos, key, _pairAddrs, _token1s, _token0s, 0);
		}
	}

	private static void continueGet(List<TxObject2> tos, String key, List<String> pairAddrs, List<String> token1s,
			List<String> token0s, int count) throws Exception {
		if (count > 3) {
			log.info(pairAddrs.size()+"");
			if (pairAddrs.size() < 10) {
				System.out.println(AppObjectUtil.toJson(pairAddrs));
			}
			fenget(tos, key, pairAddrs, token1s, token0s);
			return;
		}
		try {
			
			try {
				MyContractcAll myContractcAll = contractcAll1s.get(CacheUtil.getCount());
				if(myContractcAll==null) {
					log.info(CacheUtil.getCount()+",contractcAlls.szie():"+contractcAlls.size());
				}
				Tuple4<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>> tuple4 = myContractcAll.getSkimOuts(token0s, token1s, pairAddrs).send();
				List<BigInteger> reserve0s = tuple4.component1();
				List<BigInteger> reserve1s = tuple4.component2();
				List<BigInteger> bal0s = tuple4.component3();
				List<BigInteger> bal1s = tuple4.component4();
				
				tocontinueProcess1(pairAddrs, token0s, token1s, reserve0s, reserve1s, bal0s, bal1s, tos);
			} catch (Exception e) {
				
				log.info(e.getMessage());
				System.out.println(MytradeTestBnbNew.adminss1.get(CacheUtil.getCount()));
				continueGet(tos, key, pairAddrs, token1s, token0s, count + 1);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	
	private static void process7(List<TxObject2> tos) throws Exception {
		if(tos.size()>0) {
			
			EthGetTransactionCount count = adminss.get(CacheUtil.getCount())
					.ethGetTransactionCount(create.getAddress(), DefaultBlockParameterName.LATEST).send();
			EthSendTransaction transaction1=null;
			
			if(tos.size()==1) {
				BigInteger amountUsed = tos.get(0).getAmountUsed();
				BigInteger tout = tos.get(0).getTout();
//				BigInteger targetPrice = tout.multiply(_10000).divide(new BigInteger(""+scalr)).divide(amountUsed).add(
//						Convert.toWei("0.0" + RandomStringUtils.random(10, "0123456789"), Convert.Unit.GWEI).toBigInteger());
//				System.out.println("targetPrice:" + Convert.fromWei(targetPrice.toString(), Convert.Unit.GWEI));
//				if (targetPrice.compareTo(cgasPrice) < 0) {
//					targetPrice = cgasPrice.add(new BigInteger(scalr+""));
//				}
//				if (targetPrice.compareTo(bigPrice) > 0) {
//					targetPrice = bigPrice.add(new BigInteger(scalr+""));
//				}
				BigInteger gasLimit = amountUsed.multiply(_14000).divide(_10000);
				BigInteger divide = tout.divide(amountUsed).divide(gasPrice.add(_55000));
				int scalr=RandomUtils.nextInt(11000, 50000);
				if(divide.compareTo(_30)>0) {
					RawTransaction rawTransaction = RawTransaction.createTransaction(count.getTransactionCount(),
							cgasPrice3.add(new BigInteger(""+scalr)),
							gasLimit, tos.get(0).getContractAddr(), BigInteger.ZERO, tos.get(0).getDatas());
					String signMessage = Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction, 56, create));
					System.out.println(signMessage);
					new Thread(new Runnable(){  
						public void run(){  
							
							try {
								adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
								
							} catch (Exception e) {
								
							}
						}}).start();  
					new Thread(new Runnable(){  
						public void run(){  
							
							try {
								adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
								
							} catch (Exception e) {
								
							}
						}}).start();  
					new Thread(new Runnable(){  
						public void run(){  
							
							try {
								adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
								
							} catch (Exception e) {
								
							}
						}}).start();  
					transaction1 = adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
					
				}else if(divide.compareTo(_5)>0) {
					RawTransaction rawTransaction = RawTransaction.createTransaction(count.getTransactionCount(),
							mgasPrice.add(new BigInteger(""+scalr)),
							gasLimit, tos.get(0).getContractAddr(), BigInteger.ZERO, tos.get(0).getDatas());
					String signMessage = Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction, 56, create));
					System.out.println(signMessage);
					new Thread(new Runnable(){  
						public void run(){  
							
							try {
								adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
								
							} catch (Exception e) {
								
							}
						}}).start();  
					new Thread(new Runnable(){  
						public void run(){  
							
							try {
								adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
								
							} catch (Exception e) {
								
							}
						}}).start();  
					new Thread(new Runnable(){  
						public void run(){  
							
							try {
								adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
								
							} catch (Exception e) {
								
							}
						}}).start();  
					transaction1 = adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
					
				}else {
					BigDecimal used = new BigDecimal(amountUsed).multiply(new BigDecimal("1.3"));
					BigInteger cgasPrice =new BigDecimal(tout).divide(used,mc).toBigInteger().add(_50000);
					if(cgasPrice.compareTo(gasPrice)<0) {
						cgasPrice=gasPrice;
					}
					if(cgasPrice.compareTo(gasPrice1)>0) {
						cgasPrice=gasPrice1;
					}
					
					cgasPrice = cgasPrice.add(new BigInteger(""+scalr));
					System.out.println(cgasPrice);
					RawTransaction rawTransaction = RawTransaction.createTransaction(count.getTransactionCount(),
							cgasPrice,
							gasLimit, tos.get(0).getContractAddr(), BigInteger.ZERO, tos.get(0).getDatas());
					String signMessage = Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction, 56, create));
					System.out.println(signMessage);
					transaction1 = adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
				}
			}else {
				List<byte[]> allPackBatch=new ArrayList<byte[]>();
				List<String> pairs=new ArrayList<String>();
				List<BigInteger> _reserve0s=new ArrayList<BigInteger>();
				List<BigInteger> out0s=new ArrayList<BigInteger>();
				List<BigInteger> out1s=new ArrayList<BigInteger>();
				BigInteger amountUsed =BigInteger.ZERO;
				BigInteger tout =BigInteger.ZERO;
				for(TxObject2 tx:tos) {
					String pair = tx.getPair();
					if(skimUtil0.equals(tx.getContractAddr())) {
						byte[] a = SkimPacker.packBatchItem(0, pair, tx.getOut1(), tx.getReserve0()); // skim0
						allPackBatch.add(a);
						tout=tout.add(tx.getTout());
						amountUsed=amountUsed.add(tx.getAmountUsed());
					}else if(skimUtil1.equals(tx.getContractAddr())){
						byte[] b = SkimPacker.packBatchItem(1, pair,  tx.getOut0(), tx.getReserve0()); // skim1
						allPackBatch.add(b);
						tout=tout.add(tx.getTout());
						amountUsed=amountUsed.add(tx.getAmountUsed());
					}else if(pair!=null) {
						pairs.add(pair);
						process7(Arrays.asList(tx));
					}
				}
				if(pairs.size()>0) {
					pairs.clear();
					pairs=new ArrayList<String>();
				}else {
					String data =SkimPacker.packBatchHex(allPackBatch);
					int scalr=RandomUtils.nextInt(11000, 50000);
					BigInteger gasLimit = amountUsed.multiply(_14000).divide(_10000);
					BigInteger divide = tout.divide(amountUsed).divide(gasPrice.add(_55000));
					if(divide.compareTo(_30)>0) {
						RawTransaction rawTransaction = RawTransaction.createTransaction(count.getTransactionCount(),
								cgasPrice3.add(new BigInteger(""+scalr)),
								gasLimit, 
								skimUtilBatch,
								BigInteger.ZERO, 
								data
						);
						String signMessage = Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction, 56, create));
						System.out.println(signMessage);
						new Thread(new Runnable(){  
			                public void run(){  
			                	try {
									adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
			                	} catch (Exception e) {
			                		
								}
			                }}).start();  
						new Thread(new Runnable(){  
							public void run(){  
								
								try {
									adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
								} catch (Exception e) {
								}
							}}).start();  
						new Thread(new Runnable(){  
							public void run(){  
								try {
									adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
								} catch (Exception e) {
									
								}
							}}).start();  
						transaction1 = adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
						
					}else if(divide.compareTo(_5)>0) {
						RawTransaction rawTransaction = RawTransaction.createTransaction(count.getTransactionCount(),
								mgasPrice.add(new BigInteger(""+scalr)),
								gasLimit, 
								skimUtilBatch,
								BigInteger.ZERO, 
								data
						);
						String signMessage = Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction, 56, create));
						System.out.println(signMessage);
						new Thread(new Runnable(){  
			                public void run(){  
			                	
			                	try {
									adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
									
			                	} catch (Exception e) {
									
								}
			                }}).start();  
						new Thread(new Runnable(){  
							public void run(){  
								
								try {
									adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
								} catch (Exception e) {
									
								}
							}}).start();  
						new Thread(new Runnable(){  
							public void run(){  
								
								try {
									adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
									
								} catch (Exception e) {
									
								}
							}}).start();  
						transaction1 = adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
						
					}else {
						BigDecimal used = new BigDecimal(amountUsed).multiply(new BigDecimal("1.3"));
						BigInteger cgasPrice =new BigDecimal(tout).divide(used,mc).toBigInteger().add(_50000);
						if(cgasPrice.compareTo(gasPrice)<0) {
							cgasPrice=gasPrice;
						}
						if(cgasPrice.compareTo(gasPrice1)>0) {
							cgasPrice=gasPrice1;
						}
						cgasPrice = cgasPrice.add(new BigInteger(""+scalr));
						System.out.println(cgasPrice);
						RawTransaction rawTransaction = RawTransaction.createTransaction(count.getTransactionCount(),
								cgasPrice,
								gasLimit, 
								skimUtilBatch,
								BigInteger.ZERO, 
								data
						);
						String signMessage = Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction, 56, create));
						System.out.println(signMessage);
						transaction1 = adminss.get(CacheUtil.getCount()).ethSendRawTransaction(signMessage).send();
					}
				}
				if(pairs.size()>0) {}
			}
			
			if(transaction1!=null) {
				if (transaction1.getTransactionHash() != null) {
					System.out.println(transaction1.getTransactionHash());
				}
				tos.clear();
				if(!transaction1.hasError()) {
					for(int i=0;i<60;i++) {
						EthGetTransactionCount count1 = adminss.get(CacheUtil.getCount())
								.ethGetTransactionCount(create.getAddress(), DefaultBlockParameterName.LATEST).send();
						if(count1.getTransactionCount().equals(count.getTransactionCount())) {
							new PollingTransactionReceiptProcessor(adminss.get(CacheUtil.getCount()), 3000, 30).waitForTransactionReceipt(transaction1.getTransactionHash());
						}else {
							break;
						}
					}
				}
			}
		}
	}
	private static void tocontinueProcess1(List<String> pairAddrs, List<String> token0s,
			List<String> token1s, List<BigInteger> reserve0s, List<BigInteger> reserve1s, List<BigInteger> bal0s,
			List<BigInteger> bal1s, List<TxObject2> tos2) throws Exception {
		for (int i = 0; i < pairAddrs.size(); i++) {
			String pairAddr = pairAddrs.get(i);
			String token0 = token0s.get(i);
			String token1 = token1s.get(i);
			BigInteger reserve0 = reserve0s.get(i);
			BigInteger reserve1 = reserve1s.get(i);
			BigInteger bal0 = bal0s.get(i);
			BigInteger bal1 = bal1s.get(i);
			BigInteger intFee = _30;
			try {
				intFee = new BigInteger(prss.get(pairAddr));
			} catch (Exception e) {
				// System.out.println(pairAddr+":"+prss.get(pairAddr));
				// e.printStackTrace();
			}
			if (bal1.compareTo(reserve1) > 0) {
				if (MytradeTestBnbNew.deMap.get(token0) != null) {
					BigInteger amountIn = bal1.subtract(reserve1);
					BigInteger amountOut = getAmountOut(amountIn, reserve1, reserve0, pairAddr);
					BigInteger balance0 = bal0.subtract(amountOut);
					BigInteger balance1 = bal1;
					
					BigInteger tout = amountOut.multiply(MytradeTestBnbNew.deMap.get(MytradeTestBnbNew.BASE_TOKEN))
							.divide(MytradeTestBnbNew.deMap.get(token0));
					BigInteger gasUseThis = useGas.get(pairAddr);
					BigInteger thiMin = outMin.multiply(gasPrice).divide(gasPrice);
					if (gasUseThis != null) {
						thiMin = gasUseThis.multiply(gasPrice);
					}
					if (tout.compareTo(thiMin) > 0) {
						if (limitBig.compareTo(balance0) < 0 || limitBig.compareTo(balance1) < 0) {
							log.error("limitBig"+","+pairAddr);
							log.error(balance0+","+balance1);
							log.error(reserve0+","+reserve1);
							System.out.println("tout1:" + Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
							continue;
						}

						BigInteger balance0Adjusted = balance0.multiply(_10000);
						BigInteger balance1Adjusted = balance1.multiply(_10000).subtract(amountIn.multiply(intFee));
						if (balance0Adjusted.multiply(balance1Adjusted)
								.compareTo(reserve0.multiply(reserve1).multiply(_10000).multiply(_10000)) < 0) {
							 log.error("balance1Adjustedbalance1"+","+pairAddr+":"+intFee);
							 System.out.println("tout1:" + Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
							 System.out.println("amountOut:" + amountOut);
							 amountOut=amountOut.multiply(_999999999999).divide(_1000000000000);
							 System.out.println("amountOut:" + amountOut);
							//continue;
						}
						String data1 = SkimPacker.packSkim01Hex(pairAddr, amountOut, reserve0);
//						Function function1 = new Function(
//				                "swapssmeSkim1", 
//				                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, pairAddr), 
//				                new org.web3j.abi.datatypes.generated.Uint256(reserve0), 
//				                new org.web3j.abi.datatypes.generated.Uint256(amountOut)), 
//				                Collections.<TypeReference<?>>emptyList());
//						String data1 = FunctionEncoder.encode(function1);
						org.web3j.protocol.core.methods.request.Transaction transaction1 = org.web3j.protocol.core.methods.request.Transaction
								.createEthCallTransaction(create.getAddress(),skimUtil1, data1);
						
						EthEstimateGas estimateGas1 = adminss.get(CacheUtil.getCount()).ethEstimateGas(transaction1).send();
						
						if (!estimateGas1.hasError()) {
							System.out.println("-------------");
							BigInteger amountUsed = estimateGas1.getAmountUsed();
							System.out.println("amountUsed1:" + amountUsed);
							useGas.put(pairAddr, amountUsed);
							FileUtils.writeLines(file0, "UTF-8", Arrays.asList(pairAddr + "&" + amountUsed), true);
							BigInteger outThis = amountUsed.multiply(gasPrice);
							System.out.println("outgas1:" + Convert.fromWei(outThis.toString(), Convert.Unit.ETHER));
							System.out.println("tout1:" + Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
							if (tout.compareTo(outThis) > 0) {
								System.out.println(pairAddr+","+amountUsed);
								if(amountUsed.compareTo(_50000)>0&&amountUsed.compareTo(gasLimit1)<0) {
									tos2.add(
											new TxObject2(
													data1,
													skimUtil1,
													amountUsed,
													tout,
													amountOut,
													BigInteger.ZERO,
													pairAddr,
													reserve0));
								}
							} else {
								
							}
							System.out.println("-------------");
						} else {
							if("0xdfa1c7ffa17bee308109b0f83dfdb1affc24d160".equals(pairAddr)) {
								System.out.println("-----" + estimateGas1.getError().getMessage());
								System.out.println(pairAddr);
								System.out.println(pairAddr+ ":"+Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
								if(tout.compareTo(maXOut)>0) {
									
									Testme4.skim(adminss.get(CacheUtil.getCount()));
								}
							}else {
								System.out.println(estimateGas1.getError().getMessage());
								System.out.println(pairAddr+ ":"+Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
								if (estimateGas1.getError().getMessage().contains("execution reverted")) {
									Function function = new Function("swap",
											Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amountOut),
													new org.web3j.abi.datatypes.generated.Uint256(BigInteger.ZERO),
													new org.web3j.abi.datatypes.Address(160,
															create.getAddress())),
											Collections.<TypeReference<?>>emptyList());
									
									data1 = FunctionEncoder.encode(function);
									transaction1 = org.web3j.protocol.core.methods.request.Transaction
											.createEthCallTransaction(create.getAddress(), pairAddr, data1);
									estimateGas1 = adminss.get(CacheUtil.getCount()).ethEstimateGas(transaction1).send();
									
									if (!estimateGas1.hasError()) {
										BigInteger amountUsed = estimateGas1.getAmountUsed();
										System.out.println("amountUsed1:" + amountUsed);
										useGas.put(pairAddr, amountUsed);
										FileUtils.writeLines(file0, "UTF-8", Arrays.asList(pairAddr + "&" + amountUsed),
												true);
										BigInteger outThis = amountUsed.multiply(gasPrice);
										System.out.println(
												"outgas1:" + Convert.fromWei(outThis.toString(), Convert.Unit.ETHER));
										System.out.println("tout1:" + Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
										if (tout.compareTo(outThis) > 0) {
											System.out.println(pairAddr+","+amountUsed);
											if(amountUsed.compareTo(_50000)>0&&amountUsed.compareTo(gasLimit1)<0) {
												tos2.add(
														new TxObject2(
																data1,
																pairAddr, 
																amountUsed, 
																tout));
											}
										} else {
										}
									} else {
										System.out.println("-----" + estimateGas1.getError().getMessage());
										System.out.println(pairAddr);
										
									}
								}
							}
						}
					}
					// BigInteger outThis =
					// gasUseThis.multiply(deMap.get(tokenA)).multiply(cgasPrice).divide(deMap.get(BASE_TOKEN));
				}
			} else if (bal0.compareTo(reserve0) > 0) {
				if (MytradeTestBnbNew.deMap.get(token1) != null) {
					BigInteger amountIn = bal0.subtract(reserve0);
					BigInteger amountOut = getAmountOut(amountIn, reserve0, reserve1, pairAddr);
					BigInteger balance0 = bal0;
					BigInteger balance1 = bal1.subtract(amountOut);
					
					BigInteger tout = amountOut.multiply(MytradeTestBnbNew.deMap.get(MytradeTestBnbNew.BASE_TOKEN))
							.divide(MytradeTestBnbNew.deMap.get(token1));
					BigInteger thiMin = outMin.multiply(gasPrice).divide(MytradeTestBnbNew.gasPrice);
					BigInteger gasUseThis = useGas.get(pairAddr);
					if (gasUseThis != null) {
						thiMin = gasUseThis.multiply(gasPrice);
					}
					if (tout.compareTo(thiMin) > 0) {
						if (limitBig.compareTo(balance0) < 0 || limitBig.compareTo(balance1) < 0) {
							log.error("limitBig:"+pairAddr);
							log.error(balance0+","+balance1);
							log.error(reserve0+","+reserve1);
							System.out.println("tout1:" + Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
							continue;
						}
						BigInteger balance0Adjusted = balance0.multiply(_10000).subtract(amountIn.multiply(intFee));
						BigInteger balance1Adjusted = balance1.multiply(_10000);
						if (balance0Adjusted.multiply(balance1Adjusted)
								.compareTo(reserve0.multiply(reserve1).multiply(_10000).multiply(_10000)) < 0) {
							 log.error("balance0Adjustedbalance0"+","+pairAddr+":"+intFee);
							System.out.println("tout1:" + Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
							System.out.println("amountOut:" + amountOut);
							amountOut=amountOut.multiply(_999999999999).divide(_1000000000000);
							System.out.println("amountOut:" + amountOut);
							//continue;
						}
//						
//						Function function1 = new Function(
//		                "swapssmeSkim0", 
//		                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, pairAddr), 
//		                new org.web3j.abi.datatypes.generated.Uint256(reserve0), 
//		                new org.web3j.abi.datatypes.generated.Uint256(amountOut)), 
//		                Collections.<TypeReference<?>>emptyList());
//						String data1 = FunctionEncoder.encode(function1);
						String data1 = SkimPacker.packSkim01Hex(pairAddr, amountOut, reserve1);
						org.web3j.protocol.core.methods.request.Transaction transaction1 = org.web3j.protocol.core.methods.request.Transaction
								.createEthCallTransaction(create.getAddress(),
										skimUtil0, data1);
						
						//adminss.get(CacheUtil.getCount())
						EthEstimateGas estimateGas1 = adminss.get(CacheUtil.getCount()).ethEstimateGas(transaction1).send();
						
						if (!estimateGas1.hasError()) {
							System.out.println("-------------");
							BigInteger amountUsed = estimateGas1.getAmountUsed();
							useGas.put(pairAddr, amountUsed);
							FileUtils.writeLines(file0, "UTF-8", Arrays.asList(pairAddr + "&" + amountUsed), true);
							BigInteger outThis = amountUsed.multiply(gasPrice);
							System.out.println("amountUsed1:" + amountUsed);
							System.out.println("outgas1:" + Convert.fromWei(outThis.toString(), Convert.Unit.ETHER));
							System.out.println("tout1:" + Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
							if (tout.compareTo(outThis) > 0) {
								System.out.println(pairAddr+","+amountUsed);
								if(amountUsed.compareTo(_50000)>0&&amountUsed.compareTo(gasLimit1)<0) {
									tos2.add(
											new TxObject2(
													data1,
													skimUtil0,
													amountUsed,
													tout,
													BigInteger.ZERO,
													amountOut,
													pairAddr,
													reserve1));
								}
							} else {
							}
							System.out.println("-------------");
						} else {
							if("0xdfa1c7ffa17bee308109b0f83dfdb1affc24d160".equals(pairAddr)) {
								if(tout.compareTo(maXOut)>0) {
									
									//adminss.get(CacheUtil.getCount())
									Testme4.skim(adminss.get(CacheUtil.getCount()));
								}
								System.out.println("-----" + estimateGas1.getError().getMessage());
								System.out.println(pairAddr);
								System.out.println(pairAddr+ ":"+Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
							}else {
								System.out.println(estimateGas1.getError().getMessage());
								System.out.println(pairAddr+ ":"+Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
								if (estimateGas1.getError().getMessage().contains("execution reverted")) {
									Function function = new Function("swap",
											Arrays.<Type>asList(
													new org.web3j.abi.datatypes.generated.Uint256(BigInteger.ZERO),
													new org.web3j.abi.datatypes.generated.Uint256(amountOut),
													new org.web3j.abi.datatypes.Address(160,
															create.getAddress())),
											Collections.<TypeReference<?>>emptyList());
									data1 = FunctionEncoder.encode(function);
									transaction1 = org.web3j.protocol.core.methods.request.Transaction
											.createEthCallTransaction(create.getAddress(), pairAddr, data1);
									
									//adminss.get(CacheUtil.getCount())
									estimateGas1 = adminss.get(CacheUtil.getCount()).ethEstimateGas(transaction1).send();
									
									if (!estimateGas1.hasError()) {
										System.out.println("-------------");
										BigInteger amountUsed = estimateGas1.getAmountUsed();
										useGas.put(pairAddr, amountUsed);
										FileUtils.writeLines(file0, "UTF-8", Arrays.asList(pairAddr + "&" + amountUsed),
												true);
										BigInteger outThis = amountUsed.multiply(gasPrice);
										System.out.println("amountUsed1:" + amountUsed);
										System.out.println(
												"outgas1:" + Convert.fromWei(outThis.toString(), Convert.Unit.ETHER));
										System.out.println("tout1:" + Convert.fromWei(tout.toString(), Convert.Unit.ETHER));
										if (tout.compareTo(outThis) > 0) {
											System.out.println(pairAddr+","+amountUsed);
											if(amountUsed.compareTo(_50000)>0&&amountUsed.compareTo(gasLimit1)<0) {
												tos2.add(new TxObject2(data1, pairAddr, amountUsed, tout));
											}
										} else {
										}
										System.out.println("-------------");
										
									} else {
										System.out.println("-----" + estimateGas1.getError().getMessage());
										System.out.println(pairAddr);
										
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private static void dsfsdf() throws IOException, JsonParseException, JsonMappingException {
		List<String> lines1 = FileUtils.readLines(file1, "UTF-8").stream().distinct().collect(Collectors.toList());
		System.out.println(lines1.size());
		for (String str1 : lines1) {
			String str = str1.toLowerCase();
			@SuppressWarnings("unchecked")
			List<Object> list = ObjectJsonHelper.deserialize(str, List.class);
			Object o = list.get(0);
			String addr = o.toString();
			if (allpairAddrs.get(addr) == null) {
				allpairAddrs.put(addr, "");
				System.out.println(str1);
			}
		}
	}

	private static void getMax() throws Exception {
		MytradeTestBnbNew.MyExhNon();
		File file0 = new File("/path/docker/t/getBnb-memeMax1.txt");
		List<String> lines0 = FileUtils.readLines(file0, "UTF-8").stream().distinct().collect(Collectors.toList());
		for (String str1 : lines0) {
			String str = str1.toLowerCase();
			@SuppressWarnings("unchecked")
			List<Object> list = ObjectJsonHelper.deserialize(str, List.class);
			Object o = list.get(0);
			String addr = o.toString();
			if (allpairAddrs.get(addr) == null) {
				allpairAddrs.put(addr, "");
				//System.out.println(str1);
			}
		}
		File file1 = new File("/path/docker/t/getBnb-meme.txt");
		List<String> lines1 = FileUtils.readLines(file1, "UTF-8").stream().distinct().collect(Collectors.toList());
		System.out.println(lines1.size());
		Map<String, List<String>> mp = new ConcurrentHashMap<String, List<String>>();
		Map<String, List<Object>> mq = new ConcurrentHashMap<String, List<Object>>();
		int i = 0;
		for (String str1 : lines1) {
			i++;
			String str = str1.toLowerCase();
			@SuppressWarnings("unchecked")
			List<Object> list = ObjectJsonHelper.deserialize(str, List.class);
			Object o = list.get(0);
			String addr = o.toString();
			if (allpairAddrs.get(addr) == null) {
				allpairAddrs.put(addr, "");
				int k = i / 200;
				List<String> list2 = mp.get(k + "");
				if (list2 == null) {
					list2 = new ArrayList<String>();
					mp.put(k + "", list2);
				}
				list2.add(addr);
				mq.put(addr, list);
			}
		}
		Admin admin1 = admin("https://bsc-dataseed.binance.org");
		MyContractcAll contractcAll = MyContractcAll.load("0x9727A8bA13e2dF097acf769eeE55281e1c8F31dF", admin1, create,
				contractGasProvider);
		for (List<String> addrs : mp.values()) {
			Tuple2<List<BigInteger>, List<BigInteger>> tuple2 = contractcAll.getAllReserves(addrs).send();
			List<BigInteger> r0s = tuple2.component1();
			List<BigInteger> r1s = tuple2.component2();
			for (int k = 0; k < addrs.size(); k++) {
				String addr = addrs.get(k);
				List<Object> list = mq.get(addr);
				String token0 = list.get(1).toString();
				String token1 = list.get(2).toString();
				if (MytradeTestBnbNew.deMap.get(token0) != null) {
					if (r0s.get(k).compareTo(MytradeTestBnbNew.deMap.get(token0)) > 0) {
						System.out.println(ObjectJsonHelper.serialize(list));
						FileUtils.writeLines(file0, "UTF-8", Arrays.asList(ObjectJsonHelper.serialize(list)), true);
					}
				} else if (MytradeTestBnbNew.deMap.get(token1) != null) {
					if (r1s.get(k).compareTo(MytradeTestBnbNew.deMap.get(token1)) > 0) {
						System.out.println(ObjectJsonHelper.serialize(list));
						FileUtils.writeLines(file0, "UTF-8", Arrays.asList(ObjectJsonHelper.serialize(list)), true);
					}
				}
			}
		}
	}

	public static final Map<String, String> notFrAddrs = new ConcurrentHashMap<String, String>();
	public static final Map<String, String> ssssAddrs = new ConcurrentHashMap<String, String>();
	static {
		ssssAddrs.put(WBNB, WBNB);
		ssssAddrs.put("0x7130d2a12b9bcbfae4f2634d864a1ee1ce3ead9c", // btc
				"0x7130d2a12b9bcbfae4f2634d864a1ee1ce3ead9c");
		ssssAddrs.put("0x2170ed0880ac9a755fd29b2688956bd959f933f8", // eth
				"0x2170ed0880ac9a755fd29b2688956bd959f933f8");
		ssssAddrs.put("0xe9e7cea3dedca5984780bafc599bd69add087d56", // busdt
				"0xe9e7cea3dedca5984780bafc599bd69add087d56");
		ssssAddrs.put("0x1af3f329e8be154074d8769d1ffa4ee058b1dbc3", // bdai
				"0x1af3f329e8be154074d8769d1ffa4ee058b1dbc3");
		ssssAddrs.put("0x55d398326f99059ff775485246999027b3197955", // busdt-t
				"0x1af3f329e8be154074d8769d1ffa4ee058b1dbc3");
		ssssAddrs.put("0x8ac76a51cc950d9822d68b83fe1ad97b32cd580d", // usdc
				"0x8ac76a51cc950d9822d68b83fe1ad97b32cd580d");
		ssssAddrs.put("0x1d2f0da169ceb9fc7b3144628db156f3f6c60dbe", // xrp
				"0x1d2f0da169ceb9fc7b3144628db156f3f6c60dbe");
		ssssAddrs.put("0x1ce0c2827e2ef14d5c4f29a091d735a204794041", // avax
				"0x1ce0c2827e2ef14d5c4f29a091d735a204794041");
		ssssAddrs.put("0x3ee2200efb3400fabb9aacf31297cbdd1d435d47", 
				"0x3ee2200efb3400fabb9aacf31297cbdd1d435d47");
		ssssAddrs.put("0x7083609fce4d1d8dc0c979aab8c869ea2c873402", 
				"0x7083609fce4d1d8dc0c979aab8c869ea2c873402");
		ssssAddrs.put("0xf8a0bf9cf54bb92f17374d9e9a321e6a111a51bd", 
				"0xf8a0bf9cf54bb92f17374d9e9a321e6a111a51bd");
		ssssAddrs.put("0xcc42724c6683b7e57334c4e856f4c9965ed682bd", 
				"0xcc42724c6683b7e57334c4e856f4c9965ed682bd");
		ssssAddrs.put("0x4338665cbb7b2485a8855a139b75d5e34ab0db94", 
				"0x4338665cbb7b2485a8855a139b75d5e34ab0db94");
		ssssAddrs.put("0x0eb3a705fc54725037cc9e008bdede697f62f335", 
				"0x0eb3a705fc54725037cc9e008bdede697f62f335");
		ssssAddrs.put("0xbf5140a22578168fd562dccf235e5d43a02ce9b1", 
				"0xbf5140a22578168fd562dccf235e5d43a02ce9b1");
		ssssAddrs.put("0x3d6545b08693dae087e957cb1180ee38b9e3c25e", 
				"0x3d6545b08693dae087e957cb1180ee38b9e3c25e");
		ssssAddrs.put("0x0d8ce2a99bb6e3b7db580ed848240e4a0f9ae153", 
				"0x0d8ce2a99bb6e3b7db580ed848240e4a0f9ae153");
		ssssAddrs.put("0x6fdcdfef7c496407ccb0cec90f9c5aaa1cc8d888", 
				"0x6fdcdfef7c496407ccb0cec90f9c5aaa1cc8d888");
		ssssAddrs.put("0x8595f9da7b868b1822194faed312235e43007b49", 
				"0x8595f9da7b868b1822194faed312235e43007b49");
		ssssAddrs.put("0x40af3827f39d0eacbf4a168f8d4ee67c121d11c9", 
				"0x40af3827f39d0eacbf4a168f8d4ee67c121d11c9");
		ssssAddrs.put("0x7130d2a12b9bcbfae4f2634d864a1ee1ce3ead9c", 
				"0x7130d2a12b9bcbfae4f2634d864a1ee1ce3ead9c");
		ssssAddrs.put("0xa050ffb3eeb8200eeb7f61ce34ff644420fd3522", 
				"0xa050ffb3eeb8200eeb7f61ce34ff644420fd3522");
		ssssAddrs.put("0xad29abb318791d579433d831ed122afeaf29dcfe", 
				"0xad29abb318791d579433d831ed122afeaf29dcfe");
		ssssAddrs.put("0x5f0da599bb2cccfcf6fdfd7d81743b6020864350", 
				"0x5f0da599bb2cccfcf6fdfd7d81743b6020864350");
		ssssAddrs.put("0x352cb5e19b12fc216548a2677bd0fce83bae434b", 
				"0x352cb5e19b12fc216548a2677bd0fce83bae434b");
		ssssAddrs.put("0x26433c8127d9b4e9b71eaa15111df99ea2eeb2f8", 
				"0x26433c8127d9b4e9b71eaa15111df99ea2eeb2f8");
		ssssAddrs.put("0x56b6fb708fc5732dec1afc8d8556423a2edccbd6", 
				"0x56b6fb708fc5732dec1afc8d8556423a2edccbd6");
		ssssAddrs.put("0x9ac983826058b8a9c7aa1c9171441191232e8404", 
				"0x9ac983826058b8a9c7aa1c9171441191232e8404");
		ssssAddrs.put("0x170c84e3b1d282f9628229836086716141995200", 
				"0x170c84e3b1d282f9628229836086716141995200");
		ssssAddrs.put("0x1ba42e5193dfa8b03d15dd1b86a3113bbbef8eeb", 
				"0x1ba42e5193dfa8b03d15dd1b86a3113bbbef8eeb");
		ssssAddrs.put("0x111111111117dc0aa78b770fa6a738034120c302", 
				"0x111111111117dc0aa78b770fa6a738034120c302");
		ssssAddrs.put("0xb86abcb37c3a4b64f74f59301aff131a1becc787", 
				"0xb86abcb37c3a4b64f74f59301aff131a1becc787");
		ssssAddrs.put("0x52ce071bd9b1c4b00a0b92d298c512478cad67e8", 
				"0x52ce071bd9b1c4b00a0b92d298c512478cad67e8");
		ssssAddrs.put("0x101d82428437127bf1608f699cd651e6abf9766e", 
				"0x101d82428437127bf1608f699cd651e6abf9766e");
		ssssAddrs.put("0x302cd8973be5ca2334b4ff7e7b01ba41455559b3", 
				"0x302cd8973be5ca2334b4ff7e7b01ba41455559b3");
		ssssAddrs.put("0x66e4d38b20173f509a1ff5d82866949e4fe898da", 
				"0x66e4d38b20173f509a1ff5d82866949e4fe898da");
		ssssAddrs.put("0xf307910a4c7bbc79691fd374889b36d8531b08e3", 
				"0xf307910a4c7bbc79691fd374889b36d8531b08e3");
		ssssAddrs.put("0x76f3ce6af26de7a9854dbd153acd8f46a2cf5133", 
				"0x76f3ce6af26de7a9854dbd153acd8f46a2cf5133");
		ssssAddrs.put("0xd4ed60d8368a92b5f1ca33af61ef2a94714b2d46", 
				"0xd4ed60d8368a92b5f1ca33af61ef2a94714b2d46");
		ssssAddrs.put("0x882c173bc7ff3b7786ca16dfed3dfffb9ee7847b", 
				"0x882c173bc7ff3b7786ca16dfed3dfffb9ee7847b");
		ssssAddrs.put("0x570a5d26f7765ecb712c0924e4de545b89fd43df", 
				"0x570a5d26f7765ecb712c0924e4de545b89fd43df");
		ssssAddrs.put("0x8965349fb649a33a30cbfda057d8ec2c48abe2a2", 
				"0x8965349fb649a33a30cbfda057d8ec2c48abe2a2");
		try {
			List<String> lines2 = FileUtils.readLines(NOTBNB, "UTF-8");
			for (String str1 : lines2) {
				if(notFrAddrs.get(str1)==null) {
					notFrAddrs.put(str1, "");
				//	System.out.println(str1);
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		notFrAddrs.put("0xb3fce37e0b8dfeaee6f9b17b7bbc17051c3d743a", "");
		notFrAddrs.put("0xb3fce37e0b8dfeaee6f9b17b7bbc17051c3d743a", "");
	}
	private static void extracted() throws Exception {
		try {
			String[] thsaddr=new String[] {
					"https://bsc-dataseed.bnbchain.org",
					"https://bsc-dataseed.nariox.org",
					"https://bsc-dataseed.defibit.io",
					"https://bsc-dataseed.ninicoin.io",
					"https://bsc.nodereal.io",
					"https://bsc-dataseed-public.bnbchain.org",
					"https://bsc-dataseed4.bnbchain.org",
					"https://bsc-dataseed2.defibit.io",
					"https://bsc-dataseed3.defibit.io",
					"https://bsc-dataseed4.defibit.io",
				};
			for(int i=1;i<=thsaddr.length;i++) {
				Admin admin1 = admin(thsaddr[i-1]);
				adminss.put(""+i, admin1);
				adminvalues.put(""+i, thsaddr[i-1]);
				EthGasPrice send = admin1.ethGasPrice().send();
				System.out.println(thsaddr[i-1]+","+send.getGasPrice());
			}


		} catch (Exception e) {
			//log.info(e.getMessage());
		}
		
		for (int i = 1; i <= adminss.size(); i++) {
			contractcAlls.put(i + "", MyContractcAll.load("0x123a549CD5CF2E4A532608386886a7F9ff1607B2",
					adminss.get(""+i),
					create, contractGasProvider));
		}
		
		List<String> lines1 = FileUtils.readLines(file1, "UTF-8").stream().distinct().collect(Collectors.toList());
		List<String> lines9= FileUtils.readLines(file3, "UTF-8").stream().distinct().collect(Collectors.toList());
		lines1.addAll(lines9);
		for (String str1 : lines1) {
			String str = str1.toLowerCase();
			@SuppressWarnings("unchecked")
			List<Object> list = ObjectJsonHelper.deserialize(str, List.class);
			Object o = list.get(0);
			String addr = o.toString();
			if (allpairAddrs.get(addr) == null) {
				allpairAddrs.put(addr, "");
//				String string = MytradeTestBnbNew.mmp.get(addr + addr);
//				if(notFrAddrs.get(string.split(",")[2])!=null) {
//					continue;
//				}
				//System.out.println(str1);
			}
		}
		File file0 = new File("/path/docker/t/getAllBnb.txt");
		List<String> lines2 = FileUtils.readLines(file0, "UTF-8").stream().distinct().collect(Collectors.toList());
		List<String> pairAddrs = new ArrayList<String>();
		List<String> token0s = new ArrayList<String>();
		List<String> token1s = new ArrayList<String>();
		System.out.println(lines2.size());
		while(true) {
			i=0;
			Map<String,List<List<String>>> mmmm=new ConcurrentHashMap<String,List<List<String>>>();
			int p=0;
			int size = lines2.size();
			for (int p1=size-1;p1>=0;p1--) {
				String str = lines2.get(p1).toLowerCase();
				@SuppressWarnings("unchecked")
				List<String> list = ObjectJsonHelper.deserialize(str, List.class);
				String faAddr = list.get(0);
				String pairAddr = list.get(2);
				String token0 = list.get(3);
				String token1 = list.get(4);
				if (allpairAddrs.get(pairAddr) == null 
						&& notFrAddrs.get(pairAddr) == null
						&& notFrAddrs.get(faAddr) == null
						) {
					if(ssssAddrs.get(token0)!=null&&ssssAddrs.get(token1)!=null) {
					}else if(deMap.get(token0)!=null&&deMap.get(token1)!=null) {
					}else {
						pairAddrs.add(pairAddr);
						token0s.add(token0);
						token1s.add(token1);
					}
				}
				if (pairAddrs.size() > 1000) {
					p++;
					mmmm.put(""+p, Arrays.asList(pairAddrs,token0s,token1s));
					pairAddrs = new ArrayList<String>();
					token0s = new ArrayList<String>();
					token1s = new ArrayList<String>();
				}
			}
			if (pairAddrs.size() > 0) {
				p++;
				mmmm.put(""+p, Arrays.asList(pairAddrs,token0s,token1s));
				pairAddrs = new ArrayList<String>();
				token0s = new ArrayList<String>();
				token1s = new ArrayList<String>();
			}
			Instant start = Instant.now();
			System.out.println(mmmm.size());
			CountDownLatch countDownLatch=new CountDownLatch(mmmm.size());
			for(Map.Entry<String,List<List<String>>> en: mmmm.entrySet()) {
				List<List<String>> value = en.getValue();
				List<String> _pairAddrs = value.get(0);
				List<String> _token0s =  value.get(1);
				List<String> _token1s =  value.get(2);
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							getAll(_pairAddrs, _token0s, _token1s);
							i=i+_pairAddrs.size();
							System.out.println(mmmm.size()+"----"+i+"----"+countDownLatch.getCount());
						} catch (Exception e) {
							log.info(e.getMessage());
						}
						countDownLatch.countDown();
					}
				});
			}
			countDownLatch.await(timeoutMillis,TimeUnit.MINUTES);
			System.out.println(countDownLatch.getCount());
			Duration between1 = Duration.between(start, Instant.now());
			System.out.println("aaaaaaspend==" + DurationFormatUtils.formatDuration(between1.toMillis(), "ss.SSS "));
			Thread.sleep(1000);
		}
	}

	
	private static void getAll(List<String> pairAddrs, List<String> token0s, List<String> token1s)
			throws Exception {
		if(pairAddrs.size()<1) {
			return;
		}
		
		try {
			Tuple4<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>> tuple4 = contractcAlls
					.get(CacheUtil.getCount()).getSkimOuts(token0s, token1s, pairAddrs).send();
			
			//System.out.println("pairAddrs.size():"+pairAddrs.size());
			List<BigInteger> reserve0s = tuple4.component1();
			List<BigInteger> reserve1s = tuple4.component2();
			List<BigInteger> bal0s = tuple4.component3();
			List<BigInteger> bal1s = tuple4.component4();
			tocontinueProcess(pairAddrs, token0s, token1s, reserve0s, reserve1s, bal0s, bal1s);
		} catch (Exception e) {
			if (pairAddrs.size() > 1) {
				List<String> _pairAddrs0 = new ArrayList<String>();
				List<String> _token0s0 = new ArrayList<String>();
				List<String> _token1s0 = new ArrayList<String>();
				List<String> _pairAddrs1 = new ArrayList<String>();
				List<String> _token0s1 = new ArrayList<String>();
				List<String> _token1s1 = new ArrayList<String>();
				for (int i = 0; i < pairAddrs.size(); i++) {
					if (i % 2 == 0) {
						_pairAddrs0.add(pairAddrs.get(i));
						_token0s0.add(token0s.get(i));
						_token1s0.add(token1s.get(i));
					} else {
						_pairAddrs1.add(pairAddrs.get(i));
						_token0s1.add(token0s.get(i));
						_token1s1.add(token1s.get(i));
					}
				}
				getAll(_pairAddrs0, _token0s0, _token1s0);
				getAll(_pairAddrs1, _token0s1, _token1s1);
			} else if(e.getMessage().contains("reverted")||e.getMessage().contains("Index: 0")){
				log.info(e.getMessage()+pairAddrs.get(0));
				notFrAddrs.put(pairAddrs.get(0), pairAddrs.get(0));
				FileUtils.writeLines(NOTBNB, "UTF-8", pairAddrs, true);
			}else {
				log.info(e.getMessage()+pairAddrs);
			}
		}
		
	}

	

	private static void tocontinueProcess(List<String> pairAddrs, List<String> token0s,
			List<String> token1s, List<BigInteger> reserve0s, List<BigInteger> reserve1s, List<BigInteger> bal0s,
			List<BigInteger> bal1s) throws Exception, JsonProcessingException, InterruptedException {
		for (int i = 0; i < pairAddrs.size(); i++) {
			String pairAddr = pairAddrs.get(i);
			String token0 = token0s.get(i);
			String token1 = token1s.get(i);
			BigInteger reserve0 = reserve0s.get(i);
			BigInteger reserve1 = reserve1s.get(i);
			BigInteger bal0 = bal0s.get(i);
			BigInteger bal1 = bal1s.get(i);
//			List<Object> pathList1 = new ArrayList<Object>();
//			pathList1.add(pairAddr);
//			pathList1.add(token0);
//			pathList1.add(token1);
//			pathList1.add(reserve0);
//			pathList1.add(reserve1);
//			FileUtils.writeLines(filere, "UTF-8", Arrays.asList(ObjectJsonHelper.serialize(pathList1)), true);
			 
			if (bal1.compareTo(reserve1) > 0 || bal0.compareTo(reserve0) > 0) {
				if (MytradeTestBnbNew.deMap.get(token0) != null) {
					List<Object> pathList = new ArrayList<Object>();
					pathList.add(pairAddr);
					pathList.add(token0);
					pathList.add(token1);
					pathList.add(false);
					pathList.add("_nnn");
//					try {
//						
//						
//						ERC20 erc20 = ERC20.load(token1, admin1, Credentials.create("0x123"), contractGasProvider);
//						String name = erc20.name().send();
//						pathList.add(name);_10000
//					} catch (Exception e) {
//					}
					// getFeeInfo(pairAddr, token0, token1, reserve0, reserve1, pathList);
					System.out.println(ObjectJsonHelper.serialize(pathList));
					FileUtils.writeLines(file1, "UTF-8", Arrays.asList(ObjectJsonHelper.serialize(pathList)), true);
				} else if (MytradeTestBnbNew.deMap.get(token1) != null) {
					List<Object> pathList = new ArrayList<Object>();
					pathList.add(pairAddr);
					pathList.add(token0);
					pathList.add(token1);
					pathList.add(true);
					pathList.add("_nnn");
//					try {
//						
//						ERC20 erc20 = ERC20.load(token0, admin1, Credentials.create("0x123"), contractGasProvider);
//						String name = erc20.name().send();
//						pathList.add(name);
//					} catch (Exception e) {
//					}
					// getFeeInfo2(pairAddr, token0, token1, reserve0, reserve1, pathList);
					System.out.println(ObjectJsonHelper.serialize(pathList));
					FileUtils.writeLines(file1, "UTF-8", Arrays.asList(ObjectJsonHelper.serialize(pathList)), true);
				}else {
					List<Object> pathList = new ArrayList<Object>();
					pathList.add(pairAddr);
					pathList.add(token0);
					pathList.add(token1);
					pathList.add(true);
					pathList.add("_nnn");
//					try {
//						
//						ERC20 erc20 = ERC20.load(token0, admin1, Credentials.create("0x123"), contractGasProvider);
//						String name = erc20.name().send();
//						pathList.add(name);
//					} catch (Exception e) {
//					}
					// getFeeInfo2(pairAddr, token0, token1, reserve0, reserve1, pathList);
					System.out.println(ObjectJsonHelper.serialize(pathList));
					FileUtils.writeLines(file3, "UTF-8", Arrays.asList(ObjectJsonHelper.serialize(pathList)), true);
				}
			}
		}
		
	}
	private static int i=0;
	private static final BigInteger _14000 = new BigInteger("14000");
	private static final BigInteger _12000 = new BigInteger("12000");
	private static final BigInteger _10000 = new BigInteger("10000");
	private static final BigInteger _1000000000000 = new BigInteger("1000000000000");
	private static final BigInteger _999999999999 =  new BigInteger("999999999999");
	private static final BigInteger _21000 = new BigInteger("21000");
	private static final BigInteger _55000 = new BigInteger("55000");

	private static final BigInteger limitBig = new BigInteger("5192296858534827628530486329220095");

	public static BigInteger getAmountOut(BigInteger amountIn, BigInteger reserveIn, BigInteger reserveOut,
			String pairAddr) {
		if (amountIn.compareTo(limitBig) > 0) {
			System.out.println("pairAddr" + pairAddr);
			System.out.println("amountIn" + amountIn);
			return BigInteger.ZERO;
		}
		BigInteger intfee = _30;
		try {
			intfee = new BigInteger(prss.get(pairAddr));
		} catch (Exception e) {
			// System.out.println(pairAddr+":"+prss.get(pairAddr));
			// e.printStackTrace();
		}
		BigInteger amountInWithFee = amountIn.multiply(_10000.subtract(intfee));
		BigInteger numerator = amountInWithFee.multiply(reserveOut);
		BigInteger denominator = reserveIn.multiply(_10000).add(amountInWithFee);
		return new BigDecimal(numerator).divide(new BigDecimal(denominator), mc).toBigInteger();
	}

	public static Admin admin(String clientAddress) {
		Web3jService web3jService = buildService(clientAddress);
		return Admin.build(web3jService, 200, Async.defaultExecutorService());
		// return Admin.build(web3jService);
	}

	private static Web3jService buildService(String clientAddress) {
		Web3jService web3jService;

		if (clientAddress == null || clientAddress.equals("")) {
			web3jService = new HttpService(createOkHttpClient());
		} else if (clientAddress.startsWith("http")) {
			web3jService = new HttpService(clientAddress, createOkHttpClient(), false);
		} else if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
			web3jService = new WindowsIpcService(clientAddress);
		} else {
			web3jService = new UnixIpcService(clientAddress);
		}

		return web3jService;
	}

	private static OkHttpClient createOkHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		configureLogging(builder);
		configureTimeouts(builder);
		return builder.build();
	}

	private static void configureTimeouts(OkHttpClient.Builder builder) {
		builder.connectTimeout(tos, TimeUnit.SECONDS);
		builder.readTimeout(tos, TimeUnit.SECONDS); // Sets the socket timeout too
		builder.writeTimeout(tos, TimeUnit.SECONDS);
	}

	private static void configureLogging(OkHttpClient.Builder builder) {
		if (log.isDebugEnabled()) {
			HttpLoggingInterceptor logging = new HttpLoggingInterceptor(log::debug);
			logging.setLevel(HttpLoggingInterceptor.Level.NONE);
			builder.addInterceptor(logging);
		}
	}
}
