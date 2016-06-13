package com.manage.core.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("myException")
@Scope("prototype")
public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyException(String frdMessage) {

		super(createFriendlyErrMsg(frdMessage));

	}

	public MyException(Throwable throwable) {

		super(throwable);

	}

	public MyException(Throwable throwable, String frdMessage) {

		super(throwable);

	}

	private static String createFriendlyErrMsg(String msgBody) {

		String prefixStr = "抱歉。";

		String suffixStr = "请稍后再试或与管理员联系！";

		StringBuffer friendlyErrMsg = new StringBuffer();

		friendlyErrMsg.append(prefixStr);

		friendlyErrMsg.append(msgBody);

		friendlyErrMsg.append(suffixStr);

		return friendlyErrMsg.toString();

	}

}
