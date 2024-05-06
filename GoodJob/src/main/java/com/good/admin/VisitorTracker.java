package com.good.admin;

public class VisitorTracker {
	
	
	private static final VisitorTracker visitorTracker = new VisitorTracker();;
	private int visitors;
	
	private VisitorTracker() {
		visitors = 0;
		
	}
	
	public static VisitorTracker getInstance() {
		return visitorTracker;
	}
	
	public synchronized void incrementVisitors() {
		visitors++;
	}
	
	public int getVisitors() {
		return visitors;
	}
	
	
}
