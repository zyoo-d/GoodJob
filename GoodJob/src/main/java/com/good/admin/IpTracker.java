package com.good.admin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IpTracker {
	
	private static final Map<String, Boolean> ipAddresses = new ConcurrentHashMap<>();
	
	public static synchronized boolean trackIp(String ipAddress) {
		if(!ipAddresses.containsKey(ipAddress)) {
			ipAddresses.put(ipAddress, true);
			return true;
		}
		return false;
	}

}
