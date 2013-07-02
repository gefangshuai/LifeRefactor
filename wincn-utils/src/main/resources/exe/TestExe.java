package exe;

import java.io.BufferedInputStream;
import java.io.IOException;

public class TestExe {
	public static void main(String[] args) throws IOException {
		StringBuffer text = new StringBuffer();
		Process p = Runtime.getRuntime().exec("C:/Users/方帅/Desktop/a.exe");
		BufferedInputStream br = new BufferedInputStream(p.getInputStream());
		int ch;
		while ((ch = br.read()) != -1) {
			text.append((char) ch);
		}
		System.out.println(text.toString());
	}
}
