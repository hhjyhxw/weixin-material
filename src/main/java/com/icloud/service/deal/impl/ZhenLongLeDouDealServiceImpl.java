package com.icloud.service.deal.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icloud.common.util.ConfigUtil;
import com.icloud.service.deal.EventMessageDealService;

/**
 * 实际解析参数类(不同类型的 的请求 解析的方式不一样
 * 1、直接url 评价参数
 * 2、postxml格式数据
 * 3、......
 * )
 * @author z
 */
@Service
public class ZhenLongLeDouDealServiceImpl implements EventMessageDealService{


	private final static Logger log = LoggerFactory.getLogger(ZhenLongLeDouDealServiceImpl.class);
	@Autowired
	private CustomerMessageUtilService customerMessageUtilService;
	
	@Override
	public void dealMessage(HttpServletRequest request) {
		
		String sendType = request.getParameter("sendType");
		String openid = request.getParameter("openid");
		log.error("sendType==="+sendType+"&openid==="+openid);
		try {
			//判断是否限制发送
			String isLimit = ConfigUtil.get("isLimit");
			//不限制
			if("0".equals(isLimit)){
				sendType = sendType+UUID.randomUUID();
			}
			customerMessageUtilService.getCustomerMessage(openid,sendType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
