package net.kriz.stcolumban.menu;

import java.util.List;

public class OutputUtterances {
	private String noMenuFound;
	private String outputWithNoTreat;
	private String outputWithTreat;
	private List<String> randomQuotes;
	public String getNoMenuFound() {
		return noMenuFound;
	}
	public void setNoMenuFound(String noMenuFound) {
		this.noMenuFound = noMenuFound;
	}
	public String getOutputWithNoTreat() {
		return outputWithNoTreat;
	}
	public void setOutputWithNoTreat(String outputWithNoTreat) {
		this.outputWithNoTreat = outputWithNoTreat;
	}
	public String getOutputWithTreat() {
		return outputWithTreat;
	}
	public void setOutputWithTreat(String outputWithTreat) {
		this.outputWithTreat = outputWithTreat;
	}
	public List<String> getRandomQuotes() {
		return randomQuotes;
	}
	public void setRandomQuotes(List<String> randomQuotes) {
		this.randomQuotes = randomQuotes;
	}
	@Override
	public String toString() {
		return "OutputUtterances [noMenuFound=" + noMenuFound + ", outputWithNoTreat=" + outputWithNoTreat
				+ ", outputWithTreat=" + outputWithTreat + ", randomQuotes=" + randomQuotes + "]";
	}
}
