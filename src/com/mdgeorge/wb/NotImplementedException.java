package com.mdgeorge.wb;

public class NotImplementedException extends Error {
	public NotImplementedException() {
		super();
	}
	
	public NotImplementedException(String msg) {
		super(msg);
	}
	
	public NotImplementedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
