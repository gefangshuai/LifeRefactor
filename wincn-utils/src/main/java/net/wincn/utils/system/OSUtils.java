package net.wincn.utils.system;

import java.io.IOException;
import java.util.Set;

import org.hyperic.sigar.SigarException;

import net.wincn.utils.crypto.DigestUtils;

/**
 * 获取系统一系列参数的工具类
 * 
 * @project wincn-utils
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createDate 2013-6-27 上午9:31:49
 */
public final class OSUtils {
	private static String serialNo = "";

	/**
	 * 检查物理地址的绑定
	 * 
	 * @param macAddr
	 *            客户的mac地址
	 * @return
	 * @throws IOException
	 * @throws SigarException
	 */
	public static boolean checkMacAddressBind(String macAddr) throws IOException, SigarException {
		String macMd5 = DigestUtils.md5(macAddr);
		Set<String> macs = NetInterfaceData.getHwaddrs();
		for (String mac : macs) {
			String tempMd5 = DigestUtils.md5(mac);
			if (macMd5.equals(tempMd5))
				return true;
		}
		return false;
	}

	/**
	 * 检查cpu绑定
	 * 
	 * @param cpuSN
	 *            客户的cpu序列号
	 * @return
	 * @throws IOException
	 */
	public static boolean checkCPUBind(String cpuSN) throws IOException {
		String cpuMd5 = DigestUtils.md5(CPUData.getCPUSerial());
		if (DigestUtils.md5(cpuSN).equals(cpuMd5))
			return true;
		return false;
	}

	/**
	 * 检查主板绑定
	 * 
	 * @param boardSN
	 * @return
	 * @throws IOException
	 */
	public static boolean checkMotherboardBind(String boardSN) throws IOException {
		String boardMd5 = DigestUtils.md5(MotherboardData.getMotherboardSN());
		if (DigestUtils.md5(boardSN).equals(boardMd5))
			return true;
		return false;
	}

	/**
	 * 检查序列号是否正确
	 * 
	 * @param sn
	 * @return
	 * @throws IOException
	 */
	public static boolean checkSerialBind(String sn) throws IOException {
//		System.out.println(serialNo);
		if ("".equals(serialNo))
			serialNo = DigestUtils.md5(GenerateSerialNo.generate());
		if (sn.equals(serialNo))
			return true;
		return false;
	}
}
