package com.icloud.service.event;

import javax.servlet.http.HttpServletRequest;

import com.icloud.model.event.WeixinMaterial;
import com.icloud.service.BaseService;

public interface WeixinMaterialService extends BaseService<WeixinMaterial>{

	
	void saveWeixinMaterial(String[] titles, String[] newsImages,
			String[] descriptions, String[] urls, Long[] id,
			HttpServletRequest request);

	void updateDefaults(Long id);

}
