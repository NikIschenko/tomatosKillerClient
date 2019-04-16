package com.jeeconf.tomatos.killer.pi.servo;

public class RotationParameters {
	private final int pushAngle;
	private final int pullAngle;
	private final int delay;

	public RotationParameters(final int pushAngle, final int pullAngle, final int delay) {
		this.delay = delay;
		this.pushAngle = pushAngle;
		this.pullAngle = pullAngle;
	}

	public RotationParameters(final int pushAngle, final int pullAngle) {
		this(pushAngle, pullAngle, 500);
	}

	public int pushAngle() {
		return pushAngle;
	}

	public int pullAngle() {
		return pullAngle;
	}

	public int delay() {
		return delay;
	}
}