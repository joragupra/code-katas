package com.joragupra.codekatas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class User {

	private String lastQuestionAsked;

	private String lastConfirmationReceived;

	private List<String> confirmationsReceived;

	private List<String> cannedAnswers;

	public User() {
		this("Inferno");
	}

	public User(String answer) {
		this(new ArrayList<String>(Arrays.asList(new String[] {answer})));
	}

	public User(List<String> answers) {
		this.cannedAnswers = answers;
		this.confirmationsReceived = new ArrayList<String>();
	}

	public String lastQuestionAsked() {
		return lastQuestionAsked;
	}

	public String ask(String question) {
		this.lastQuestionAsked = question;
		return this.cannedAnswers.remove(0);
	}

	public String lastConfirmationReceived() {
		return lastConfirmationReceived;
	}

	public List<String> confirmationsReceived() {
		return confirmationsReceived;
	}

	public static User whoAnswers(String answer) {
		return new User(answer);
	}

	public static User whoAnswers(List<String> answers) {
		return new User(answers);
	}

	public void notify(String message) {
		this.confirmationsReceived.add(message);
		this.lastConfirmationReceived = message;
	}
}
