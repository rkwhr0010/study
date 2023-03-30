package designpattern.headfirst.chapter3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOStream {
	static class LowerCaseInputStream extends FilterInputStream{
		protected LowerCaseInputStream(InputStream in) {
			super(in);
		}
		@Override
		public int read() throws IOException {
			int c = in.read();
			return (c == -1 )? c : Character.toLowerCase(((char)c));
		}
		@Override
		public int read(byte[] b, int off, int len) throws IOException {
			int result = read(b, off, len);
			for (int i = off; i < off+result; i++) {
				b[i] = (byte) Character.toLowerCase((char)b[i]);
			}
			return result;
		}
	}
	public static void main(String[] args) {
		String path = System.getProperty("user.dir");
		String file = "/src/designpattern/headfirst/chapter3/text";
		
		try (InputStream in = 
				new LowerCaseInputStream(
						new BufferedInputStream(
								new FileInputStream(path+file)));
				){
			int c = -1;
			while((c=in.read())!=-1) {
				System.out.print((char)c);
			}
		} catch (Exception e) {	}
	}
}
