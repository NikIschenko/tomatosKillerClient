package by.jfuture.tomatos.killer.pi;

import java.util.Random;

public class RandomString {
	private final int leftLimit;
	private final int rightLimit;

	public RandomString(int leftLimit, int rightLimit) {
		this.leftLimit = leftLimit;
		this.rightLimit = rightLimit;
	}

	public RandomString() {
		this(97,122);  // from 'a' to 'z'
	}

	public String generate(int targetStringLength) {
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}
}