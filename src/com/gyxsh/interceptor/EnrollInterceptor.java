package com.gyxsh.interceptor;

import java.util.Date;

import com.gyxsh.entities.EnrollTime;
import com.gyxsh.service.EnrollTimeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class EnrollInterceptor extends MethodFilterInterceptor{

	private EnrollTimeService enrollTimeService;
	public EnrollTimeService getEnrollTimeService() {
		return enrollTimeService;
	}
	public void setEnrollTimeService(EnrollTimeService enrollTimeService) {
		this.enrollTimeService = enrollTimeService;
	}



	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		Date nowTime=new Date();
		EnrollTime et=enrollTimeService.getById(1);
		if(nowTime.after(et.getBegin())&&nowTime.before(et.getEnd())){
			return arg0.invoke();//递归调用拦截器
		}else{
			return Action.ERROR;//返回提示界面
		}
	}

}
