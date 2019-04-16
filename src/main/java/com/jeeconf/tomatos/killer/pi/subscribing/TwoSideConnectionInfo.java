package com.jeeconf.tomatos.killer.pi.subscribing;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
		final HttpResponse<String> result = client.send(request, HttpResponse.BodyHandlers.ofString());
		if (result.statusCode() != 200)
			throw new ConnectException("Cant connect to server " + serverUri.toString());
		return result.body(); //TODO: should be parsed in dto
	}
}