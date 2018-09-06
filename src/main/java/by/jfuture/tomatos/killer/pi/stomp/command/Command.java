package by.jfuture.tomatos.killer.pi.stomp.command;

import by.jfuture.tomatos.killer.pi.stomp.message.MessagePart;

public class Command implements MessagePart {
	private final String commandAsText;
	private CommandType commandType;

	public Command(String frameString) {
		int beginIndex = frameString.indexOf(beginSymbol()) + beginSymbol().length();
		int endIndex = frameString.indexOf(endSymbol());
		commandAsText = frameString.substring(beginIndex, endIndex);
	}

	public Command(CommandType commandType) {
		this.commandType = commandType;
		this.commandAsText = commandType.name();
	}

	public CommandType commandType() {
		if (commandType == null) {
			commandType = CommandType.valueOf(commandAsText.toUpperCase());
		}
		return commandType;
	}

	@Override
	public String beginSymbol() {
		return "[\"";
	}

	@Override
	public String endSymbol() {
		return "\\n";
	}

	@Override
	public String text() {
		return commandAsText;
	}
}