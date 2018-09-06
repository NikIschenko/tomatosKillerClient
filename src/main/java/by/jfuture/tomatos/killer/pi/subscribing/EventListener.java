package by.jfuture.tomatos.killer.pi.subscribing;

import by.jfuture.tomatos.killer.pi.Main;
import by.jfuture.tomatos.killer.pi.sockjs.Event;
import by.jfuture.tomatos.killer.pi.sockjs.EventLogger;
import by.jfuture.tomatos.killer.pi.sockjs.FrameType;
import by.jfuture.tomatos.killer.pi.sockjs.SockJsEvent;
import by.jfuture.tomatos.killer.pi.stomp.ServerMessage;
import jdk.incubator.http.WebSocket;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class EventListener implements WebSocket.Listener {
    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence message, WebSocket.MessagePart part) {
        System.out.println(message.toString());
        // Gimme one more
        webSocket.request(1);
        String textMessage = message.toString();
        // parseText sockjs event type
        if (textMessage.charAt(0)!='[') {
            char eventLetterChar = message.charAt(0);
            Event event = new EventLogger(new SockJsEvent(eventLetterChar));
            FrameType frameType = event.eventType();
            if (frameType != FrameType.DATA) {
                return CompletableFuture.completedFuture(frameType); // return just sockjs event type
            }
        }

        ServerMessage serverMessage = new ServerMessage(textMessage);

        switch (serverMessage.command().commandType()) {
            case CONNECTED: {
                return CompletableFuture.completedFuture(serverMessage.messageAsText()).thenAccept(System.out::println);
            }
            case MESSAGE: {
                if ("ON".equalsIgnoreCase(serverMessage.body().text()))
                    Main.piServo.push();
                else
                    Main.piServo.pull();
            }
        }
        return CompletableFuture.completedFuture(message).thenAccept(System.out::println);
    }
}