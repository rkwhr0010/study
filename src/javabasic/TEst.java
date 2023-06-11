package javabasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

public class TEst {
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://ebook.kyobobook.co.kr/dig/pnd/showcase?pageNo=2892&cmdt=EBK&clst1=21&clst2=&clst3=&landing=Y");
		HttpsURLConnection openConnection = (HttpsURLConnection) url.openConnection();
		openConnection.setRequestMethod("GET");
//		openConnection.connect();
		
		
//		if(openConnection.getResponseCode()== 200) {
//			InputStream stream = openConnection.getInputStream();
//			InputStreamReader inputStreamReader = new InputStreamReader(stream, Charset.defaultCharset());
//			BufferedReader br = new BufferedReader(inputStreamReader);
//			String tmp;
//			while((tmp = br.readLine()) == null) {
//				System.out.println(tmp);
//			}
//			
////			System.out.println(new String(openConnection.getInputStream().readAllBytes(),  "UTF-8" ));
//		}
		
//		new CommandImpl().execute();
		
		Command command = ()->{
			Command tmp = ()->{
				System.out.println("뭔가함...");
				
				System.out.println(0/0);
			};
			
			
			System.out.println("열심히하는중...");
			tmp.execute();
		
		};
		command.execute();
		
		
	}
	static interface Command{
		void execute();
	}
	
	static class CommandImpl implements Command{
		public void execute() {
			try {
				doExcep();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		private void doExcep() throws Exception {
			throw new Exception("의도적으로 발생 시킴");
		}
		
	}
	
	
}
