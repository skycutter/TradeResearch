import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.util.*;
import java.io.*;

public class Stock {
	public static void main(String args[]) {

		System.out.println("initial test approved!");

		URL url = null;
		try {
			url = new URL("http://finance.yahoo.com/d/quotes.json?s=MSFT&f=sb2b3jk");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		URLConnection openConnection = null;
		try {
			openConnection = url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String,List<String>> headerfields = openConnection.getHeaderFields();
		if (headerfields != null) {
			System.out.println(headerfields.get(0));
		}

		InputStream inputStream = null;
		try {
			inputStream = openConnection.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int nextInt = -1;
		try {
			nextInt = inputStream.read();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(nextInt);

		return;
	}
}