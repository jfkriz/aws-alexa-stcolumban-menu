package net.kriz.stcolumban.dailyverse;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DailyBibleVerseServiceImpl implements DailyBibleVerseService {
	private String bibleVerseUrl;

	public DailyBibleVerseServiceImpl(String bibleVerseUrl) {
		this.bibleVerseUrl = bibleVerseUrl;
	}

	@Override
	public String getDailyBibleVerse() {
		InputStream verseStream = null;
		try {
			verseStream = new BufferedInputStream(new URL(bibleVerseUrl).openStream());
			DailyBibleVerse votd = new ObjectMapper().configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
					.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(verseStream, DailyBibleVerse.class);
			return String.format("Today's bible verse is from %1$s, chapter %2$s, verse %3$s.  %4$s", votd.getBook(),
					votd.getChapter(), votd.getVerse(), votd.getText());
		} catch (IOException e) {
			return "Sorry, there was a problem reading today's bible verse";
		} finally {
			try {
				if (verseStream != null) {
					verseStream.close();
				}
			} catch (Exception e) {
			}
		}
	}
}
