package com.jeeconf.tomatos.killer.pi.stomp.message;

public interface MessagePart {
	String beginSymbol();
	String endSymbol();
	String text();
}