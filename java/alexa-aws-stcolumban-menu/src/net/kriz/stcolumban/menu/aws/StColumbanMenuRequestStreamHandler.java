package net.kriz.stcolumban.menu.aws;

import java.util.Arrays;
import java.util.HashSet;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class StColumbanMenuRequestStreamHandler extends SpeechletRequestStreamHandler {
	public StColumbanMenuRequestStreamHandler() {
		super(new StColumbanMenuSpeechlet(), new HashSet<String>(Arrays.asList("amzn1.ask.skill.d80db8d3-d170-4342-88e8-b4f6b0c27e2e")));
	}
}
