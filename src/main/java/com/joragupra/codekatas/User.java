package com.joragupra.codekatas;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class User {

	private String lastQuestionAsked;

	private String cannedAnswer;

	private String lastConfirmationReceived;

	public User() {
		this("Inferno");
	}

	public User(String answer) {
		this.cannedAnswer = answer;
	}

	public String lastQuestionAsked() {
		return lastQuestionAsked;
	}

	public String ask(String question) {
		this.lastQuestionAsked = question;
		return cannedAnswer;
	}

	public String lastConfirmationReceived() {
		return lastConfirmationReceived;
	}

	public static User whoAnswers(String answer) {
		return new User(answer);
	}

	public void notify(String message) {
		this.lastConfirmationReceived = message;
	}
}
