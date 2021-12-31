package com.aim.jpay.phonebook.model;

public enum Country {

	CAMEROON("\\(237\\)\\ ?[2368]\\d{7,8}$", "237"), ETHIOPIA("\\(251\\)\\ ?[1-59]\\d{8}$", "251"),
	MOROCCO("\\(212\\)\\ ?[5-9]\\d{8}$", "212"), MOZAMBIQUE(" \\(258\\)\\ ?[28]\\d{7,8}$", "258"),
	UGANDA("\\(256\\)\\ ?\\d{9}$", "256");

	private final String pattern;
	private final String code;

	Country(String pattern, String code) {
		this.pattern = pattern;
		this.code = code;
	}

	public String regexPattern() {
		return this.pattern;
	}

	public String countryCode() {
		return this.code;
	}

}
