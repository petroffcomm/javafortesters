package com.example.fw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailMessage {

	private String text;

	public MailMessage(String text) {
		this.text = text;			
	}

	public String getConfirmationLink() {
		Pattern regex = Pattern.compile("http\\S*");
		Matcher matcher = regex.matcher(text);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		}
	}
}