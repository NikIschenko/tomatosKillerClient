package com.jeeconf.tomatos.killer.pi.servo;

import java.io.IOException;

public class PiServo {
	private final int pin;
	private final RotationParameters rotationParameters;

	public PiServo(final int pin, final RotationParameters rotationParameters) {
		this.pin = pin;
		this.rotationParameters = rotationParameters;
	}

	public void initialize() {
		try {
			Runtime runTime = Runtime.getRuntime();
			runTime.exec("gpio mode " + pin + " pwm");
			runTime.exec("gpio pwm-ms");
			runTime.exec("gpio pwmc 192");
			runTime.exec("gpio pwmr 2000");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void rotate(final int angle) {
		try {
			Runtime.getRuntime().exec("gpio pwm " + pin + " " + angle).waitFor();
			Thread.sleep(rotationParameters.delay());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void push() {
		rotate(rotationParameters.pushAngle());
	}

	public void pull() {
		rotate(rotationParameters.pullAngle());
	}
}