package javabasic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

public class TEst {
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://ebook.kyobobook.co.kr/dig/pnd/showcase?pageNo=2892&cmdt=EBK&clst1=21&clst2=&clst3=&landing=Y");
		HttpsURLConnection openConnection = (HttpsURLConnection) url.openConnection();
		openConnection.setRequestMethod("GET");
		openConnection.connect();
		
		
		if(openConnection.getResponseCode()== 200) {
			InputStream stream = openConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(stream, Charset.defaultCharset());
			BufferedReader br = new BufferedReader(inputStreamReader);
			String tmp;
			while((tmp = br.readLine()) == null) {
				System.out.println(tmp);
			}
			
//			System.out.println(new String(openConnection.getInputStream().readAllBytes(),  "UTF-8" ));
		}
		
		
	}
}
