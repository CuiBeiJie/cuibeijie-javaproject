package com.cbjprivilege.exception;

@SuppressWarnings("serial")
public class PermissionException extends RuntimeException{

	public PermissionException() {
		// TODO Auto-generated constructor stub
	}

	public PermissionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PermissionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PermissionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PermissionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
