package net.kriz.stcolumban.menu.aws;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import net.kriz.stcolumban.menu.DailyMenu;
import net.kriz.stcolumban.menu.JsonMenuReader;
import net.kriz.stcolumban.menu.MenuReaderInterface;
import net.kriz.stcolumban.menu.OutputUtteranceServiceImpl;
import net.kriz.stcolumban.menu.OutputUtteranceServiceInterface;
import net.kriz.stcolumban.menu.PdfMenuReader;

public class StColumbanMenuSpeechlet implements SpeechletV2 {
	private MenuReaderInterface menuReader;
	private OutputUtteranceServiceInterface outputUtteranceService;

	public StColumbanMenuSpeechlet() {
		String menuType = System.getenv("MenuType");
		if (StringUtils.isEmpty(menuType)) {
			menuType = "application/json";
		}
		
		String menuUrl = System.getenv("MenuUrl");
		if (StringUtils.isEmpty(menuUrl)) {
			menuUrl = "https://s3.amazonaws.com/lambda-function-bucket-us-east-1-1486176755721/SaintColumban/menu.json";
		}
		

		if ("application/pdf".equalsIgnoreCase(menuType)) {
			menuReader = new PdfMenuReader();
		} else {
			menuReader = new JsonMenuReader(menuUrl);
		}
		
		String outputJsonUrl = System.getenv("GetMenuOutputUrl");
		if(StringUtils.isEmpty(outputJsonUrl)) {
			outputJsonUrl = "https://s3.amazonaws.com/lambda-function-bucket-us-east-1-1486176755721/SaintColumban/dailyMenuOutputUtterances.json";
		}
		outputUtteranceService = new OutputUtteranceServiceImpl(outputJsonUrl);
	}

	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		return doHelp();
	}

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		String intentName = requestEnvelope.getRequest().getIntent().getName();

		if ("GetMenuIntent".equalsIgnoreCase(intentName)) {
			return doGetMenuIntent(requestEnvelope);
		}

		return doHelp();
	}

	private SpeechletResponse doHelp() {
		PlainTextOutputSpeech output = new PlainTextOutputSpeech();
		output.setText(
				"Sorry, you need to say something like \"Alexa, ask Saint Columban what's on the lunch menu today\" or \"Alexa, ask Saint Columban what's for lunch next Friday\".");
		return SpeechletResponse.newTellResponse(output);
	}

	private SpeechletResponse doGetMenuIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		LocalDate referenceDate = new LocalDate(DateTimeZone.forID("US/Eastern"));
		Slot whenSlot = requestEnvelope.getRequest().getIntent().getSlot("MenuDate");
		String when = whenSlot == null || StringUtils.isEmpty(whenSlot.getValue()) ? "today" : whenSlot.getValue();
		List<DateGroup> dates = new Parser().parse(when, referenceDate.toDate());
		Date date = dates.get(0).getDates().get(0);

		DailyMenu menu = menuReader.getMenuForDate(date);

		PlainTextOutputSpeech output = new PlainTextOutputSpeech();
		
		output.setText(outputUtteranceService.generateDailyMenuOutput(date, menu));

		return SpeechletResponse.newTellResponse(output);
	}

	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
	}

}
