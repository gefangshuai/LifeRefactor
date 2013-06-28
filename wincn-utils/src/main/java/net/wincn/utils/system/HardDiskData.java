package net.wincn.utils.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.wincn.utils.exception.UtilsException;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * 硬盘信息
 * 
 * @project wincn-utils
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createDate 2013-6-27 上午11:52:06
 */
public class HardDiskData {
	private static Sigar sigar = SigarFactory.getInstance();

	/**
	 * 获取硬盘序列号
	 * 
	 * @param drive
	 *            盘符
	 * @return
	 */
	public static String getHardDiskSN(String drive) {
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n" + "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
					+ "Wscript.Echo objDrive.SerialNumber"; // see note
			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.trim();
	}

	/**
	 * 获取硬盘序列号
	 * 
	 * @param drive
	 *            盘符
	 * @return
	 * @throws SigarException
	 */
	public static String getHardDiskSN() throws SigarException {

		List<String> devNames = getAllDevNames();
		if (devNames == null || devNames.size() <= 0) {
			throw new UtilsException("盘符获取失败！");
		}
		String drive = devNames.get(0);
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n" + "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
					+ "Wscript.Echo objDrive.SerialNumber"; // see note
			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.trim();
	}

	/**
	 * 获取所有盘符
	 * 
	 * @return
	 * @throws SigarException
	 */
	public static List<String> getAllDevNames() throws SigarException {
		List<String> devList = new ArrayList<String>();
		FileSystem[] fileSystemList = sigar.getFileSystemList();
		for (FileSystem sys : fileSystemList) {
			String devName = sys.getDevName();
			if (devName.contains(":")) {
				devList.add(devName.split(":")[0]);
			}
		}
		return devList;
	}

	public static void main(String[] args) throws SigarException {
		List<String> devList = HardDiskData.getAllDevNames();
		for (String sys : devList) {
			System.out.println(sys);
		}
		System.out.println(HardDiskData.getHardDiskSN());
	}
}
