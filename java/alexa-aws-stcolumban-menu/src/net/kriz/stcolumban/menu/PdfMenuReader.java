package net.kriz.stcolumban.menu;

import java.util.Date;

public class PdfMenuReader implements MenuReaderInterface {
	@Override
	public DailyMenu getMenuForDate(Date date) {
		// try {
		// PdfReader menuPdf = new PdfReader(
		// new
		// URL("http://www.saintcolumbanschool.org/wp-content/uploads/2016/12/Jan-May-St.-Columban-2017.pdf")
		// .openStream());
		// PdfDictionary menuPage = menuPdf.getPageN(1);
		// PdfReader contents = ((PRIndirectReference) menuPage.get(new
		// PdfName("Contents"))).getReader();
		//
		// for (PdfName key : contents.getCatalog().getKeys()) {
		// System.out.println("/Contents/" + key.toString());
		// }

		// for (PdfName key : menuPage.getKeys()) {
		// System.out.println(key.toString());
		// }
		//
		// PdfReaderContentParser parser = new
		// PdfReaderContentParser(menuPdf);
		// TextExtractionStrategy strategy;
		// for(int i = 1; i <= menuPdf.getNumberOfPages(); i++) {
		// strategy = parser.processContent(i, new
		// SimpleTextExtractionStrategy());
		// System.out.println(strategy.getResultantText());
		// }
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		return new DailyMenu();
	}
}
