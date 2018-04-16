package com.icloud.dao.event;

import java.util.List;

import com.icloud.model.event.WeixinMaterial;

public interface WeixinMaterialMapper {
	   int deleteByPrimaryKey(Long id);

	   int insert(WeixinMaterial record);

	   int insertSelective(WeixinMaterial record);

	   WeixinMaterial selectByPrimaryKey(Long id);

	   int updateByPrimaryKeySelective(WeixinMaterial record);

	   int updateByPrimaryKey(WeixinMaterial record);
    
	   List<WeixinMaterial> findForList(WeixinMaterial record);
	   
	   List<WeixinMaterial> findByPage(WeixinMaterial record);
	   
	   int updateUnDefault();
}