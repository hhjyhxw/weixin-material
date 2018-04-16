package com.icloud.service.event.impl;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.common.util.ConfigUtil;
import com.icloud.common.util.wx.FormUploadMultimediaUtil;
import com.icloud.dao.event.WeixinMaterialMapper;
import com.icloud.model.event.WeixinMaterial;
import com.icloud.service.event.WeixinMaterialService;

@Service
public class WeixinMaterialServiceImpl implements WeixinMaterialService{

	private static Logger logger = LoggerFactory.getLogger(WeixinMaterialServiceImpl.class);
	@Autowired
	private WeixinMaterialMapper weixinMaterialMapper;
	@Override
	public void save(WeixinMaterial t) throws Exception {
		// TODO Auto-generated method stub
		weixinMaterialMapper.insert(t);
	}

	@Override
	public void update(WeixinMaterial t) throws Exception {
		// TODO Auto-generated method stub
		weixinMaterialMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public List<WeixinMaterial> findList(WeixinMaterial t) throws Exception {
		// TODO Auto-generated method stub
		return weixinMaterialMapper.findForList(t);
	}

	@Override
	public Integer findCount(WeixinMaterial t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		weixinMaterialMapper.deleteByPrimaryKey(id);
	}

	@Override
	public WeixinMaterial findByKey(Long id) throws Exception {
		// TODO Auto-generated method stub
		return weixinMaterialMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<WeixinMaterial> findByPage(int pageNo, int pageSize,
			WeixinMaterial t) throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		return new PageInfo<WeixinMaterial>(weixinMaterialMapper.findByPage(t));
	}

	/**
	 * 先保存微信永久图片素材
	 */
	@Override
	public void saveWeixinMaterial(String[] titles, String[] newsImages,
			String[] descriptions, String[] urls, Long[] id,
			HttpServletRequest request) {
				//用于上传图片到微信服务器
				FormUploadMultimediaUtil formUtil = new FormUploadMultimediaUtil();
				String uplaoad_image = ConfigUtil.get("uplaoad_image");
				logger.error("uplaoad_image1====================="+uplaoad_image);
				
//				AccessToken accessToken = AccessTokenUtil.getAccessToken();
//				uplaoad_image = uplaoad_image.replace("ACCESS_TOKEN", accessToken.getToken());
				logger.error("uplaoad_image2=================="+uplaoad_image);
				//保存图文消息
				WeixinMaterial parent = null;
				for(int i = 0;i<titles.length;i++){
					WeixinMaterial wm = new WeixinMaterial();	
					if(0==i){
						//第一个图文消息保存公众号id，paerntId为0		
						wm.setParentId(0L);
						if(id!=null){
							wm.setId(id[i]);
						}else{
							wm.setDefaults("0");
						}
					}else{
						wm.setParentId(parent.getId());
						wm.setDefaults("0");
						if(id!=null && id.length>i){
							wm.setId(id[i]);
						}
					}
					wm.setTitle(titles[i]);
//					wm.setPicUrl(newsImages[i]);
					wm.setDescription(descriptions[i]);
					wm.setCreateTime(Calendar.getInstance().getTime());
					//新的文件访问路径
					wm.setVistUrl(urls[i]);
					
					//图片上传到微信
//					JSONObject jsonresut = null;
//					String uploadPath = request.getSession().getServletContext().getRealPath(newsImages[i]);
//					logger.error("ploadPath======================="+uploadPath);
//					String result = formUtil.postFile(uplaoad_image,uploadPath);
//					logger.error("result="+result);
//					jsonresut = JSONObject.parseObject(result);
//					wm.setWxPicUrl(jsonresut.getString("url"));
					wm.setPicUrl(newsImages[i]);
					saveOrUpdate(wm);
					if(0==i){
						parent = wm;
					}
				}
		
	}

	private void saveOrUpdate(WeixinMaterial wm) {
		if(wm!=null && wm.getId()!=null){
			weixinMaterialMapper.updateByPrimaryKeySelective(wm);
		}else{
			weixinMaterialMapper.insert(wm);
		}
	}

	@Override
	public void updateDefaults(Long id) {
		WeixinMaterial mm = weixinMaterialMapper.selectByPrimaryKey(id);
		if(mm!=null){
			//设置其他图文为非默认图文 0
			weixinMaterialMapper.updateUnDefault();
			mm.setDefaults("1");
			weixinMaterialMapper.updateByPrimaryKey(mm);
		}
	}

}
