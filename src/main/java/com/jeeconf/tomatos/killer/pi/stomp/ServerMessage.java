package com.jeeconf.tomatos.killer.pi.stomp;

import com.jeeconf.tomatos.killer.pi.stomp.command.Command;
import com.jeeconf.tomatos.killer.pi.stomp.message.Body;
import com.jeeconf.tomatos.killer.pi.stomp.message.Headers;
import com.jeeconf.tomatos.killer.pi.stomp.message.Message;

public class ServerMessage implements Message {
	private final Command command;
	private final Headers headers;
	private final Body body;

	public ServerMessage(Command command, Headers headers, Body body) {
		this.command = command;
		this.headers = headers;
		this.body = body;
	}

	public ServerMessage(Command command, Headers headers) {
		this(command, headers, new Body(""));
	}

	public ServerMessage(String textMessage) {
		this(new Command(textMessage), new Headers(textMessage), new Body(textMessage));
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
}