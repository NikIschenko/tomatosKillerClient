package by.jfuture.tomatos.killer.pi.sockjs;

public class EventLogger implements Event {
	private final Event event;

	public EventLogger(Event event) {
		this.event = event;
	}

	@Override
	public FrameType eventType() {
		FrameType frameType = event.eventType();
		switch (frameType) {
			case OPEN: System.out.println("[OPEN FRAME] New session was initialized"); break;
			case CLOSE: System.out.println("[CLOSE FRAME] Connection was closed"); break;
			case HEARTBEAT: System.out.println("[HEARTBEAT FRAME] Connection availability check was successful"); break;
			case DATA: System.out.println("[DATA FRAME] Message's array was received"); break;
		}
		return frameType;
	}

	@Override
	public char eventTypeLetter() {
		return event.eventTypeLetter();
	}
}