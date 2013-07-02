package net.wincn.utils.system;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 修改系统环境变量工具类
 * 
 * @project wincn-utils
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createDate 2013-6-28 下午3:18:41
 */
public class SystemPropUtils {
	private static String libraryPath = "";

	/**
	 * 将路径临时添加到java.library.path中
	 * 
	 * @param path
	 * @return
	 */
	/***
	 * 该方法基本上解决了动态加载dll路径问题、 如java.lang.UnsatisfiedLinkError: no xxx in
	 * java.library.path
	 * 
	 * @param s
	 *            环境变量路径
	 * @throws IOException
	 */
	public static void addDir(String s) throws IOException {
		try {
			Field field = ClassLoader.class.getDeclaredField("usr_paths");
			field.setAccessible(true);
			String[] paths = (String[]) field.get(null);
			for (int i = 0; i < paths.length; i++) {
				if (s.equals(paths[i])) {
					return;
				}
			}
			String[] tmp = new String[paths.length + 1];
			System.arraycopy(paths, 0, tmp, 0, paths.length);
			tmp[paths.length] = s;
			field.set(null, tmp);
		} catch (IllegalAccessException e) {
			throw new IOException("Failed to get permissions to set library path");
		} catch (NoSuchFieldException e) {
			throw new IOException("Failed to get field handle to set library path");
		}
	}

}
