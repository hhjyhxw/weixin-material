package com.icloud.web.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icloud.service.deal.EventMessageHandler;
import com.icloud.web.AppBaseController;
import com.icloud.web.util.RequestIpUtil;
@Controller
public class ReceiveEventController extends AppBaseController{
	@Autowired
	private EventMessageHandler dventMessageHandler;
	/**
	 * 接收imcc-推送的事件
	 * @throws Exception 
	 */
	@RequestMapping(value = "/events/reveiveEvents")
	@ResponseBody
	public String reveiveEvents() throws Exception{
		if(RequestIpUtil.isAllow(request)){
			log.error("getRequestString=="+getRequestString());
			dventMessageHandler.handle(request);
		}
		return null;
	}
	
}
