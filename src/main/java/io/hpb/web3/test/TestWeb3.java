package io.hpb.web3.test;

import java.net.URI;

import io.hpb.web3.protocol.Web3;
import io.hpb.web3.protocol.websocket.WebSocketClient;
import io.hpb.web3.protocol.websocket.WebSocketService;
import io.hpb.web3.protocol.websocket.events.NewHeadsNotification;
import io.reactivex.Flowable;

public class TestWeb3 {

	public static void main(String[] args) throws Exception {
		final WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://192.168.0.121:8546/"));
		final boolean includeRawResponses = false;
		final WebSocketService webSocketService = new WebSocketService(webSocketClient, includeRawResponses);
		webSocketService.connect();
		final Web3 web3 = Web3.build(webSocketService);
		final Flowable<NewHeadsNotification> notifications = web3.newHeadsNotifications();
		notifications.subscribe(b ->{
			System.out.println(b);
		});

		
	}

}
