package by.jfuture.tomatos.killer.pi.stomp.message;

import by.jfuture.tomatos.killer.pi.stomp.command.Command;

public interface Message<T> {
	Command command();
	Headers headers();
	Body body();

	default String messageAsText() {
		return command().beginSymbol() + command().text()
			 + headers().beginSymbol() + headers().text()
			 + body().beginSymbol()    + body().text()    + body().endSymbol();
	}
}