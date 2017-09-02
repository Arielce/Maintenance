package ren.solid.library.utils;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				LogUtils.i("zhoul",e.getMessage());
			}
		}
		return true;
	}

	public static InputStream String2InputStrean(String str) {
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(str.getBytes());

			int byteRead;
			while ((byteRead = is.read()) != -1) {
				System.out.print((char)byteRead);
			}
			System.out.println();
			is.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return is;
	}
}
