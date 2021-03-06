package net.wincn.utils.system;

import java.io.IOException;

import net.wincn.utils.exception.UtilsException;

import org.hyperic.sigar.SigarException;

/**
 * 序列号产生器，根据cpu型号、主板型号和磁盘序列号
 * 
 * @project wincn-utils
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createDate 2013-6-27 下午1:31:43
 */
public class GenerateSerialNo {
	static {
		// 先将需要的库加入到临时环境变量
		try {
			SystemPropUtils.addDir(GenerateSerialNo.class.getClassLoader().getResource("").getPath() + "/sigar/solib");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 产生序列号（cpuNO+hardNO+orardNO）
	 * 
	 * @return
	 */
	public static String generate() {
		String serialNo = "";
		try {
			String cpuNo = CPUData.getCPUSerial();
			String hardNo = HardDiskData.getHardDiskSN();
			String boardNo = MotherboardData.getMotherboardSN();

			serialNo = cpuNo + hardNo + boardNo;
		} catch (SigarException e) {
			throw new UtilsException("序列号产生失败！");
		}
		return serialNo;
	}
}
