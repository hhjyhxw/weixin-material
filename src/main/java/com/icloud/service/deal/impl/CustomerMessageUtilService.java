package com.icloud.service.deal.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icloud.common.util.ConfigUtil;
import com.icloud.common.util.wx.AccessToken;
import com.icloud.common.util.wx.AccessTokenUtil;
import com.icloud.common.util.wx.CommonUtil;
import com.icloud.model.event.SendLimit;
import com.icloud.model.event.SendLimitKey;
import com.icloud.model.event.WeixinMaterial;
import com.icloud.service.event.SendLimitService;
import com.icloud.service.event.WeixinMaterialService;

/**
 * 
 * 1、判断是否已经发送
 * 2、获取默认发送素材
 * 3、封装客服接口消息
 * 4、发送客户消息
 * 5、保存发送记录
 * @author z
 *
 */
@Service
public class CustomerMessageUtilService {

	private final static Logger log = LoggerFactory.getLogger(CustomerMessageUtilService.class);
	@Autowired
	private WeixinMaterialService weixinMaterialService;
	
	@Autowired
	private SendLimitService sendLimitService;
	/**
	 * 获取图文 格式  字符串
	 * @return
	 * @throws Exception 
	 */
	public void getCustomerMessage(String openId,String sendType) throws Exception{
		SendLimitKey sendLimitKey = new SendLimitKey();
		sendLimitKey.setOpenid(openId);
		sendLimitKey.setSendType(sendType);
		SendLimit sendLimit = sendLimitService.findByKey(sendLimitKey);
		if(sendLimit!=null){
			log.error("已发送 不用再发送");
			return;
		}
		//查找默认图文
		WeixinMaterial weixinMaterial = new WeixinMaterial();
		weixinMaterial.setDefaults("1");
		try {
			List<WeixinMaterial> weixinMateriallist = weixinMaterialService.findList(weixinMaterial);
			weixinMaterial = weixinMateriallist.get(0);
			if(weixinMaterial!=null){
				WeixinMaterial sonMaterialParm = new WeixinMaterial();
				sonMaterialParm.setParentId(weixinMaterial.getId());
				//查找子图文
				List<WeixinMaterial> sonMateriallist =  weixinMaterialService.findList(weixinMaterial);
				String customerMessage = getJsonString(weixinMaterial,sonMateriallist,openId);
				//获取accesstoken
				AccessToken accessToken = AccessTokenUtil.getAccessToken();
				String custom_send_url = ConfigUtil.get("custom_send_url").replace("ACCESS_TOKEN", accessToken.getToken());
				JSONObject result = CommonUtil.httpRequest(custom_send_url, "POST",customerMessage); //推送客服消息
				if(result!=null && result.getInteger("errcode")==0){
					log.error("发送客服消息成功");
					sendLimit = new SendLimit();
					sendLimit.setCreateTime(new Date());
					sendLimit.setSendType(sendType);
					sendLimit.setOpenid(openId);
					sendLimitService.update(sendLimit);
				}else{
					log.error("发送客服消息失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 拼装客服接口消息
	 * @param weixinMaterial
	 * @param sonMateriallist
	 * @param openId
	 * @return
	 */
	private String getJsonString(WeixinMaterial weixinMaterial,
			List<WeixinMaterial> sonMateriallist,String openId){
		JSONObject obj = null;
		if(weixinMaterial!=null){
			obj = new JSONObject();
			obj.put("touser", openId);
			obj.put("msgtype", "news");
			JSONArray jsonarry = new JSONArray();
				JSONObject obj0 = new JSONObject();
				obj0.put("title", weixinMaterial.getTitle());
				obj0.put("description", weixinMaterial.getDescription());
				obj0.put("url", weixinMaterial.getVistUrl());
				obj0.put("picurl", weixinMaterial.getWxPicUrl());
				jsonarry.add(obj0);
			if(sonMateriallist!=null && sonMateriallist.size()>0){
				for (int i = 0; i <sonMateriallist.size(); i++) {
					if(i>=7){
						break;
					}
					weixinMaterial = sonMateriallist.get(i);
					obj0 = new JSONObject();
					obj0.put("title", weixinMaterial.getTitle());
					obj0.put("description", weixinMaterial.getDescription());
					obj0.put("url", weixinMaterial.getVistUrl());
					obj0.put("picurl", weixinMaterial.getWxPicUrl());
					jsonarry.add(obj0);
				}
			}
			
			JSONObject articles = new JSONObject();
			articles.put("articles", jsonarry);
			obj.put("news", articles);
			
			return obj.toJSONString();
		}
		return null;
	}
}
