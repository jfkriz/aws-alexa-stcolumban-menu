package net.kriz.stcolumban.menu;

import java.util.Date;

public interface OutputUtteranceServiceInterface {
	public String generateDailyMenuOutput(Date menuDate, DailyMenu menu);
}
