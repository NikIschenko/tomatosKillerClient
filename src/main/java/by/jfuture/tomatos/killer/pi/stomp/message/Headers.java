package by.jfuture.tomatos.killer.pi.stomp.message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Headers implements MessagePart {
	private final String headersAsText;
	private Map<String, String> keyValue;

	private String entrySeparator = "\\\\n";
	private String keyValueSeparator = ":";

	public Headers(String frameString) {
		int beginIndex = frameString.indexOf(beginSymbol()) + beginSymbol().length();
		int endIndex = frameString.indexOf(endSymbol());
		headersAsText = frameString.substring(beginIndex, endIndex);
	}

	public Headers(Map<String, String> keyValue) {
		this.keyValue = keyValue;
		this.headersAsText = keyValue.entrySet()
				.stream()
				.map(entry -> entry.getKey() + keyValueSeparator + entry.getValue())
				.collect(Collectors.joining("\\n"));
	}

	public Map<String, String> keyValue() {
		if (keyValue == null) {
			keyValue = new HashMap<>();
			Arrays.stream(headersAsText.split(entrySeparator))
					.filter(str-> str!=null&&str.trim().length()>0)
					.forEach(pair -> {
						int separatorIndex = pair.indexOf(keyValueSeparator);
						this.keyValue.put(pair.substring(0, separatorIndex), pair.substring(separatorIndex+1, pair.length()));
					});
		}
		return keyValue;
	}

	@Override
	public String beginSymbol() {
		return "\\n";
	}

	@Override
	public String endSymbol() {
		return "\\n\\n";
	}

	@Override
	public String text() {
		return headersAsText;
	}
}