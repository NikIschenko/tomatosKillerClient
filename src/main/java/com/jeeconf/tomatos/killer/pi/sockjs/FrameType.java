package com.jeeconf.tomatos.killer.pi.sockjs;

public enum FrameType {
	OPEN('o'),
	CLOSE('c'),
	HEARTBEAT('h'),
	DATA('a');

	private char eventTypeLetter;

	FrameType(char eventTypeLetter) {
		this.eventTypeLetter = eventTypeLetter;
	}

	public char eventTypeLetter() {
		return eventTypeLetter;
	}
}