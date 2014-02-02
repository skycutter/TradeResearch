import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.util.*;
import java.io.*;

public class Stock {
	public static void main(String args[]) {
		Stock myStock = new Stock();
		myStock.getStockDataService();
		return;
	}

	/**
	 * A bunch of base service to do
	 */
	private void getStockDataService() {
		getStockDataBaseService("goog");
	}

	/**
	 * Base service to get single stock daily data
	 * @param	stockCode		code for current stock
	 */
	private void getStockDataBaseService(String stockCode) {
		String curUrl = generateURLDaily(stockCode, "json");
		InputStream response = getResponseFromURL(curUrl);

		try {
			String fileName = "../StockData/" + stockCode + "_data.txt";
			FileWriter fstream = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fstream);

			BufferedReader br = new BufferedReader(new InputStreamReader(response));
			String curLine = br.readLine();
			while (curLine != null) {
				bw.write(curLine + "\n");
				curLine = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
		// TO DO: save response to files..
	} 

	/**
	 * get response from current url
	 * @param inputUrl		endpoint url
	 * @return InputStream  from endpoint
	 */
	private InputStream getResponseFromURL(String inputUrl) {
		URL url = null;
		try {
			url = new URL(inputUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		URLConnection openConnection = null;
		try {
			openConnection = url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Map<String,List<String>> headerfields = openConnection.getHeaderFields();
		InputStream inputStream = null;
		try {
			inputStream = openConnection.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*int nextInt = -1;
		try {
			nextInt = inputStream.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(nextInt);*/System.out.println("cur Url is: " + inputUrl);
		return inputStream;
	}

	/**
	 * Generate finance api url for daily data
	 * @param stockCode			Stock code to query
	 * @param formmat			desired response formmat(such as "json")
	 * @return url
	 */
	private String generateURLDaily (String stockCode, String formmat) {
		if (stockCode.isEmpty()) {
			return null;
		}
		String url = "http://chartapi.finance.yahoo.com/instrument/1.0/" + stockCode 
					    + "/chartdata;type=quote;range=1d/" + formmat;

		return url;
	}
}