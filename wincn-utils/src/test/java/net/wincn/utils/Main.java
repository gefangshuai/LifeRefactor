package net.wincn.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Main {
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		properties.setProperty("java.library.path", properties.get("java.library.path") + ";F:/test/hyperic");
		System.out.println(System.getProperty("java.library.path"));
	}
}
