package com.jeeconf.tomatos.killer.pi.stomp.message;


public class Body implements MessagePart {
	private String bodyAsText;

	public Body(String frameString) {
		if (frameString == null) {
			this.bodyAsText = "";
		} else {
			int beginIndex = frameString.indexOf(beginSymbol()) + beginSymbol().length();
			int endIndex = frameString.indexOf(endSymbol());
			this.bodyAsText = frameString.substring(beginIndex, endIndex).replace("\\\"", "\"");
		}
	}

	@Override
	public String beginSymbol() {
		return "\\n\\n";
	}

	@Override
	public String endSymbol() {
		return "\\u0000\"]";
	}

	@Override
	public String text() {
		return bodyAsText;
	}
}