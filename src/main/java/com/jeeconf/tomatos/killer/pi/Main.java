package com.jeeconf.tomatos.killer.pi;

import com.jeeconf.tomatos.killer.pi.servo.PiServo;
import com.jeeconf.tomatos.killer.pi.servo.RotationParameters;
import com.jeeconf.tomatos.killer.pi.subscribing.TwoSideConnection;
import com.jeeconf.tomatos.killer.pi.subscribing.TwoSideConnectionInfo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	private static final String SERVER_URL = "tomatoskiller.tk";
	private static final int PIN = 1;
	private static final int PUSH_ANGLE = 193;
	private static final int PULL_ANGLE = 180;

	public static void main(final String... argv) throws URISyntaxException, IOException, InterruptedException, ExecutionException {
		Main main = new Main();
		main.run();
	}

	public static PiServo piServo;

	private void run() throws URISyntaxException, IOException, InterruptedException, ExecutionException {
		piServo = new PiServo(PIN, new RotationParameters(PUSH_ANGLE, PULL_ANGLE));
		piServo.initialize();

		TwoSideConnectionInfo twoSideConnectionInfo = new TwoSideConnectionInfo(new URI("http://" + SERVER_URL+"/ws/info"));
		System.out.println(twoSideConnectionInfo.serverInfo());
		TwoSideConnection twoSideConnection = new TwoSideConnection(webSocketHandshakeUri());
		twoSideConnection.openFrame();
		twoSideConnection.connect();
		twoSideConnection.subscribe("/laser");
		/*--- Don't close immediately ---*/
		new Scanner(System.in).nextLine(); //
	}

	private URI webSocketHandshakeUri() throws URISyntaxException {
		DecimalFormat serverIdFormat = new DecimalFormat("000");
		String randomServerId = serverIdFormat.format(ThreadLocalRandom.current().nextInt(0, 999 ));
		return new URI("ws://" + SERVER_URL + "/ws/" + randomServerId + "/" + new RandomString().generate(8) + "/websocket");
	}
}