package javabasic.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

public class StreamTest {
	public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir");
		String file = "/src/javabasic/io/asdasdasd";
		File f = new File(path + file);

		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
				BufferedOutputStream zos = new BufferedOutputStream((new FileOutputStream(new File(path+"/src/javabasic/io/test"))));
		) {
			int data = 0;
			while((data = bis.read()) != -1) {
				zos.write(data);
			}
			
			zos.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
