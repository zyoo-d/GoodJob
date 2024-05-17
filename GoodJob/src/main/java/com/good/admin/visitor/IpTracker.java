package com.good.admin.visitor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* IpTracker 클래스는 IP 주소를 추적하고 관리하는 기능을 제공합니다.
*/
public class IpTracker {
	
	/**
	    * IP 주소를 저장하는 동시성 안전(thread-safe)한 맵입니다.
	    */
	private static final Map<String, Boolean> ipAddresses = new ConcurrentHashMap<>();
	
	 /**
	    * 주어진 IP 주소를 추적하고, 새로운 IP 주소인 경우 true를 반환하고 그렇지 않으면 false를 반환합니다.
	    *
	    * @param ipAddress 추적할 IP 주소
	    * @return 새로운 IP 주소인 경우 true, 아니면 false
	    */
	public static synchronized boolean trackIp(String ipAddress) {
		if(!ipAddresses.containsKey(ipAddress)) {
			ipAddresses.put(ipAddress, true);
			return true;
		}
		return false;
	}

}
