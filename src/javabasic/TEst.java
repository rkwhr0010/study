package javabasic;

import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TEst {
	public static void main(String[] args) throws IOException {
		URI uri = URI.create("http://10.7.23.53:8080/cinf/forward.cinfCustMktgInfoInq.adm?_mnu_seq=1114&_title=고객마케팅정보조회");
		System.out.println(uri);


		String requestUri = "/10.7.23.53:8080/cinf/forward.cinfCustMktgInfoInq.adm?_mnu_seq=1114&_title=고객마케팅정보조회";
		String forwardExt = "adm";
		Pattern pattern = Pattern.compile("(.*/)(forward\\.(.*)\\." + forwardExt + ")");
		Matcher matcher = pattern.matcher(requestUri);
		
		System.out.println(matcher.groupCount());
		while(matcher.find()){
			for(int i = 1; i <= matcher.groupCount(); i++){
				System.out.println(matcher.group(i));
			}
		}
	}
}
