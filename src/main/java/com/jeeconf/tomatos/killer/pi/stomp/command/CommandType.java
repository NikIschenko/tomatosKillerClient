package com.jeeconf.tomatos.killer.pi.stomp.command;

import static com.jeeconf.tomatos.killer.pi.stomp.command.CommandSide.CLIENT;
import static com.jeeconf.tomatos.killer.pi.stomp.command.CommandSide.SERVER;

public enum CommandType {
	CONNECT(CLIENT),
	SUBSCRIBE(CLIENT),

	CONNECTED(SERVER),
	MESSAGE(SERVER);

	private CommandSide side;

	CommandType(CommandSide side) {
		this.side = side;
	}

	public CommandSide side() {
		return side;
	}
}