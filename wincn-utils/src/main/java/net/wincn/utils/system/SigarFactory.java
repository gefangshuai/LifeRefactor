package net.wincn.utils.system;

import org.hyperic.sigar.Sigar;

public class SigarFactory {
	private static Sigar instance = null;

	private SigarFactory() {
	}

	public static Sigar getInstance() {
		if (instance == null) {
			instance = new Sigar();
		}
		return instance;
	}
}
