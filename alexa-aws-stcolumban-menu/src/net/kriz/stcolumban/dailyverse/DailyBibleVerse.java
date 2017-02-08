package net.kriz.stcolumban.dailyverse;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "votd")
public class DailyBibleVerse {
	private String text;
	private String reference;

	public String getText() {
		return StringUtils.remove(StringUtils.remove(text, "&ldquo;"), "&rdquo;");
	}

	public void setText(String content) {
		this.text = content;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getBook() {
		return StringUtils.isNotEmpty(reference) ? StringUtils.substringBeforeLast(reference, " ") : StringUtils.EMPTY;
	}
	
	public String getChapter() {
		return StringUtils.isNotEmpty(reference) ? StringUtils.substringBefore(StringUtils.substringAfterLast(reference, " "), ":") : StringUtils.EMPTY;
	}

	public String getVerse() {
		return StringUtils.isNotEmpty(reference) ? StringUtils.substringAfter(StringUtils.substringAfterLast(reference, " "), ":") : StringUtils.EMPTY;
	}

	@Override
	public String toString() {
		return "DailyBibleVerse [content=" + text + ", reference=" + reference + "]";
	}

	public class VerseOfTheDay {
		private DailyBibleVerse votd;

		public DailyBibleVerse getVotd() {
			return votd;
		}

		public void setVotd(DailyBibleVerse votd) {
			this.votd = votd;
		}

		@Override
		public String toString() {
			return "VerseOfTheDay [votd=" + votd + "]";
		}
	}
}
