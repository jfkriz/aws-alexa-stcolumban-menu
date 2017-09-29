package net.kriz.stcolumban.menu;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.math.RandomUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OutputUtteranceServiceImpl implements OutputUtteranceServiceInterface {
	private OutputUtterances outputUtterances;

	public OutputUtteranceServiceImpl(String outputUtteranceUrl) {
		InputStream utteranceStream = null;
		try {
			utteranceStream = new BufferedInputStream(new URL(outputUtteranceUrl).openStream());
			outputUtterances = new ObjectMapper().readValue(utteranceStream, OutputUtterances.class);
		} catch (IOException e) {
			outputUtterances = new OutputUtterances();
			outputUtterances.setNoMenuFound(
					"Sorry, I couldn't find anything on the lunch menu for %1$s. Maybe you don't have school that day. Wouldn't that be great!");
			outputUtterances.setOutputWithNoTreat(
					"Here's the lunch menu for %1$s. The entree is %2$s, and the veggie is %3$s. %42$s");
			outputUtterances.setOutputWithTreat(
					"Here's the lunch menu for %1$s. The entree is %2$s, the veggie is %3$s, and the super-special treat of the day is %4$s! %5$s");
			outputUtterances.setRandomQuotes(new ArrayList<String>());
			outputUtterances.getRandomQuotes().add(
					"So, the question you have to ask yourself is, 'Am I going to buy this, or am I going to eat the great lunch my Dad packs for me'?");
		} finally {
			try {
				if (utteranceStream != null) {
					utteranceStream.close();
				}
			} catch (Exception e) {
			}
		}
	}

	@Override
	public String generateDailyMenuOutput(Date menuDate, DailyMenu menu) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d");
		String menuDateString = sdf.format(menuDate);
		
		if (menu == null) {
			return String.format(outputUtterances.getNoMenuFound(), menuDateString);
		}

		String randomQuote = outputUtterances.getRandomQuotes().get(RandomUtils.nextInt(outputUtterances.getRandomQuotes().size()));

		if (menu.hasTreatItem()) {
			return String.format(outputUtterances.getOutputWithTreat(), menuDateString, menu.getEntreeItem().getDescription(),
					menu.getVeggieItem().getDescription(), menu.getTreatItem().getDescription(), randomQuote);
		} else {
			return String.format(outputUtterances.getOutputWithNoTreat(), menuDateString, menu.getEntreeItem().getDescription(),
					menu.getVeggieItem().getDescription(), randomQuote);
		}
	}

}
