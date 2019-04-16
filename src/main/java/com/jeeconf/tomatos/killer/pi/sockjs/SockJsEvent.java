package com.jeeconf.tomatos.killer.pi.sockjs;

import java.util.Arrays;

public class SockJsEvent implements Event {
	private final char eventTypeLetter;

	public SockJsEvent(char eventTypeLetter) {
		this.eventTypeLetter = eventTypeLetter;
	}

	public char eventTypeLetter() {
		return eventTypeLetter;
	}

	public FrameType eventType() {
		return Arrays.stream(FrameType. values())
			.filter(frameEventType -> frameEventType.eventTypeLetter() == eventTypeLetter)
			.findFirst()
			.orElse(null);
	}
}