package by.jfuture.tomatos.killer.pi.subscribing;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;

public class TwoSideConnectionInfo {
	private final URI serverUri;
	private HttpClient client;

	public TwoSideConnectionInfo(final HttpClient httpClient, final URI serverUri) {
		this.serverUri = serverUri;
		this.client = httpClient;
	}

	public TwoSideConnectionInfo(final URI serverUri) {
		this(HttpClient.newHttpClient(), serverUri);
	}

	public String serverInfo() throws IOException, InterruptedException {
		final HttpRequest request = HttpRequest.newBuilder()
				.uri(serverUri)
				.GET()
				.build();
		final HttpResponse<String> result = client.send(request, HttpResponse.BodyHandler.asString());
		if (result.statusCode() != 200)
			throw new ConnectException("Cant connect to server " + serverUri.toString());
		return result.body(); //TODO: should be parsed in dto
	}
}