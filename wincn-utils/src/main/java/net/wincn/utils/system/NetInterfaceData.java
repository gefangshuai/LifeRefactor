package net.wincn.utils.system;

import java.util.HashSet;
import java.util.Set;

import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * 网卡信息
 * 
 * @project wincn-utils
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createDate 2013-6-27 上午10:43:38
 */
public class NetInterfaceData {
	private static Sigar sigar = SigarFactory.getInstance();
	private static Set<String> set = new HashSet<String>();

	/**
	 * 获取系统中所有的物理地址
	 * 
	 * @return
	 * @throws SigarException
	 */
	public static Set<String> getHwaddrs() throws SigarException {
		if (set.size() > 0)
			return set;
		String[] netNames = getNetInterface();
		for (String netName : netNames) {
			set.add(sigar.getNetInterfaceConfig(netName).getHwaddr());
		}
		return set;
	}

	/**
	 * 获取系统中所有的网卡名字
	 * 
	 * @return
	 * @throws SigarException
	 */
	public static String[] getNetInterface() throws SigarException {
		String[] netNames = sigar.getNetInterfaceList();
		return netNames;
	}

	/**
	 * 获取系统中所有的配置信息
	 * 
	 * @return
	 * @throws SigarException
	 */
	public static Set<NetInterfaceConfig> getNetInterfaceConfigs() throws SigarException {
		Set<NetInterfaceConfig> set = new HashSet<NetInterfaceConfig>();
		String[] netNames = getNetInterface();
		for (String netName : netNames) {
			set.add(sigar.getNetInterfaceConfig(netName));
		}
		return set;
	}
}
