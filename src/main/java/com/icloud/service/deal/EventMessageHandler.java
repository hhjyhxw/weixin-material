package com.icloud.service.deal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.icloud.common.SpringContextHolder;
import com.icloud.common.util.ConfigUtil;

/**
 * 转发器，
 * 根据不同的请求类型
 * 获取实际解析参数的处理类
 * @author z
 */
@Component
public class EventMessageHandler{
	public final static Logger log = LoggerFactory.getLogger(EventMessageHandler.class);
	//真实处理业务的对象
	private EventMessageDealService eventMessageDealService;
	
	public void handle(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//1、直接参数访问
		//sendType 是哪个业务发送过来 
		String sendType=request.getParameter("sendType");
		//这里乐豆首页传值
		try {
			String beanName = ConfigUtil.get(sendType);
			if(beanName!=null && "".equals(beanName)){
				this.eventMessageDealService = (EventMessageDealService) SpringContextHolder.getBean(Class.forName(beanName));
			}else{
				beanName = ConfigUtil.get("defaults");
				if(beanName!=null && "".equals(beanName)){
					//传过来的业务找不到处理对象 ，就获取默认处理
					this.eventMessageDealService = (EventMessageDealService) SpringContextHolder.getBean(Class.forName(beanName));
				}
			}
			log.error("beanName==="+beanName);
			if(eventMessageDealService!=null){
				eventMessageDealService.dealMessage(request);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
