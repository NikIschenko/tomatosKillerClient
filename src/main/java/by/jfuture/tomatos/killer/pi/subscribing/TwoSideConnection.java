package by.jfuture.tomatos.killer.pi.subscribing;

import by.jfuture.tomatos.killer.pi.stomp.ClientMessage;
import by.jfuture.tomatos.killer.pi.stomp.command.CommandType;
import by.jfuture.tomatos.killer.pi.stomp.message.Message;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.WebSocket;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TwoSideConnection {
	private final CompletableFuture<WebSocket> webSocketCompletableFuture;
	private WebSocket webSocket;

	public TwoSideConnection(final HttpClient httpClient, final URI serverUri) {
		this.webSocketCompletableFuture = httpClient.newWebSocketBuilder().buildAsync(serverUri, new EventListener());
	}

	public TwoSideConnection(final URI serverUri) {
		this(HttpClient.newHttpClient(), serverUri);
	}

	public void openFrame() {
		webSocket = webSocketCompletableFuture.join();
	}

	public void connect() throws ExecutionException, InterruptedException {
		Message connect = new ClientMessage.Builder(CommandType.CONNECT)
				.withHeader("accept-version", "1.1,1.0")
				.withHeader("heart-beat", "10000,10000")
				.clientMessage();
		webSocket.sendText(connect.messageAsText(), true).get().request(1);
		System.out.println(connect.messageAsText());
	}

	public void subscribe(String destination) throws ExecutionException, InterruptedException {
		Message subscribe = new ClientMessage.Builder(CommandType.SUBSCRIBE)
				.withHeader("id", "sub-0")
				.withHeader("destination", destination)
				.clientMessage();
		webSocket.sendText(subscribe.messageAsText(), true).get().request(1);
		System.out.println(subscribe.messageAsText());
	}
}