package designpattern.structural.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOStream {
	public static void main(String[] args) throws FileNotFoundException {
		
		try(InputStream in = 
				new UpperCaseInputStream(
				new BufferedInputStream(
				new FileInputStream("src/designpattern/structural/decorator/text.txt")));)
		{
			StringBuilder sb = new StringBuilder();
			for(int c=0; (c=in.read()) != -1;) {
				sb.append((char)c);
			}
			System.out.println(sb);
			
		} catch (IOException e) {	}
	}
	
	static class UpperCaseInputStream extends FilterInputStream{

		public UpperCaseInputStream(InputStream in) {
			super(in);
		}
		@Override
		public int read() throws IOException {
			int data = in.read();
			return data == -1 ? data : Character.toUpperCase(data);
		}
	}
}
