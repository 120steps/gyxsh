package com.gyxsh.interceptor;

import java.util.Date;

import com.gyxsh.entities.DisposeTime;
import com.gyxsh.service.DisposeTimeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class DisposeInterceptor extends MethodFilterInterceptor{

	private DisposeTimeService disposeTimeService;
	public DisposeTimeService getDisposeTimeService() {
		return disposeTimeService;
	}
	public void setDisposeTimeService(DisposeTimeService disposeTimeService) {
		this.disposeTimeService = disposeTimeService;
	}


	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		Date nowTime=new Date();
		DisposeTime ht=disposeTimeService.getById(1);
		if(nowTime.after(ht.getBegin())&&nowTime.before(ht.getEnd())){
			return arg0.invoke();//递归调用拦截器
		}else{
			return Action.ERROR;//返回提示界面
		}
	}

}
