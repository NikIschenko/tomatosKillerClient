package by.jfuture.tomatos.killer.pi.stomp;

import by.jfuture.tomatos.killer.pi.stomp.command.Command;
import by.jfuture.tomatos.killer.pi.stomp.command.CommandType;
import by.jfuture.tomatos.killer.pi.stomp.message.Body;
import by.jfuture.tomatos.killer.pi.stomp.message.Headers;
import by.jfuture.tomatos.killer.pi.stomp.message.Message;

import java.util.HashMap;
import java.util.Map;

public class ClientMessage implements Message {
	private final Command command;
	private final Headers headers;
	private final Body body;

	public ClientMessage(Command command, Headers headers, Body body) {
		this.command = command;
		this.headers = headers;
		this.body = body;
	}

	private ClientMessage(Builder builder) {
		this.command = new Command(builder.commandType);
		this.headers = new Headers(builder.headers);
		this.body = new Body(builder.body);
	}

	@Override
	public Command command() {
		return command;
	}

	@Override
	public Headers headers() {
		return headers;
	}

	@Override
	public Body body() {
		return body;
	}

	public static class Builder {
		private final CommandType commandType;
		private Map<String, String> headers = new HashMap<>();
		private String body;

		public Builder(CommandType commandType) {
			this.commandType = commandType;
		}

		public Builder withHeader(String headerKey, String headerValue) {
			headers.put(headerKey, headerValue);
			return this;
		}

		public Builder withBody(String body) {
			this.body = body;
			return this;
		}

		public ClientMessage clientMessage() {
			return new ClientMessage(this);
		}
	}

}