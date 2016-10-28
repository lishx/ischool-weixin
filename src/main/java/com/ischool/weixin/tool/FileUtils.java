package com.ischool.weixin.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
	public static File createTmpFile(InputStream inputStream, String name, String ext) throws IOException {
		FileOutputStream fos = null;
		try {
			File tmpFile = File.createTempFile(name, '.' + ext);
			tmpFile.deleteOnExit();
			fos = new FileOutputStream(tmpFile);
			int read = 0;
			byte[] bytes = new byte[102400];
			while ((read = inputStream.read(bytes)) != -1) {
				fos.write(bytes, 0, read);
			}
			fos.flush();
			File localFile1 = tmpFile;
			return localFile1;
		} catch (IOException e) {
			throw e;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}
}