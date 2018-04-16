package com.icloud.web.event;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.icloud.model.event.WeixinMaterial;
import com.icloud.service.event.WeixinMaterialService;


@Controller
public class WeixinMaterialController{
	
	private static Logger logger = LoggerFactory.getLogger(WeixinMaterialController.class);
	
	@Autowired
	private WeixinMaterialService weixinMaterialService;

	@RequestMapping(value = "/event/weixin_material_list")
	public String list(HttpServletRequest request,Integer pageNo,Integer pageSize)throws Exception {
		
		WeixinMaterial t = new WeixinMaterial();
		t.setParentId(0L);
		PageInfo<WeixinMaterial> page = weixinMaterialService.findByPage(pageNo!=null?pageNo:1,pageSize!=null?pageSize:10, t);
		request.setAttribute("p", page);
		request.setAttribute("weixinMaterial",t);
		return "event/weixin_material_list";
	}

	
	/**
	 * 跳转添加图文页面
	 * @return
	 */
	@RequestMapping(value = "/event/beforeAddWeixinMaterial")
	public String beforeAddWeixinMaterial(HttpServletRequest request) {
		request.setAttribute("list",new ArrayList<WeixinMaterial>());
		request.setAttribute("wm",new WeixinMaterial());
		return "event/weixin_material_input";
	}


	/**
	 * 跳转编辑图文页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/event/beforeEditWeixinMaterial")
	public String beforeEditWeixinMaterial(HttpServletRequest request,Long id) throws Exception {
		WeixinMaterial wm = null;
		if (id!=null){
			wm = weixinMaterialService.findByKey(id);
		}
		request.setAttribute("wm",wm!=null?wm:new WeixinMaterial());
		WeixinMaterial paramWeixinMaterial = new WeixinMaterial();
		paramWeixinMaterial.setParentId(id);
		List<WeixinMaterial> list = weixinMaterialService.findList(paramWeixinMaterial);
		request.setAttribute("list",list!=null?list:new ArrayList<WeixinMaterial>());
		return "event/weixin_material_input";
	}
	

	/**
	 * 异步上传图片
	 * @return
	 */
	@ResponseBody	
	@RequestMapping(value = "/event/ajaxUploadFile")
    public Map<String,String> ajaxUploadFile(@RequestParam("images") MultipartFile file,
    		HttpServletResponse response,HttpServletRequest request) {  
		Map<String,String> map = new HashMap<String,String>();
		if(!file.isEmpty()) {  
            //获取文件类型  
            String contentType = file.getContentType();  
            if(!contentType.equals("")) {  
                //可以对文件类型进行检查  
            }  
            //获取文件名，带扩展名  
            String originFileName = file.getOriginalFilename();  
            //获取文件扩展名  
            String extension = originFileName.substring(originFileName.lastIndexOf("."));  
            //获取文件大小，单位字节  
            long site = file.getSize();  
            if(site > 1000000) {  
                //可以对文件大小进行检查  
            }  
            //文件存储的相对路径
            String  filePath =  "/upload/imagetext";// String  filePath =  "/upload/imagetext";
            //获取项目根路径的绝对路径
            String realPath = request.getSession().getServletContext().getRealPath(filePath); 
            logger.error("上传文件根路径==="+filePath);
    		File dirFile = new File(realPath);
    		if (!dirFile.exists()) {
    			dirFile.mkdirs();
    		}
            //获取新的文件名
            String id = UUID.randomUUID().toString();
    		id = id.replace("-", "");
            String newfileName =  id + extension;  
            try {  
                file.transferTo(new File(dirFile+"/"+newfileName));  
                map.put("filePath", filePath+"/"+newfileName);
                logger.error("上传文件成功");
            } catch (Exception e) {  
                e.printStackTrace();  
                logger.error("上传文件失败");
            }  
        }  
        return map;
    }  
	
	/**
	 * 保存素材
	 * @return
	 */
	@RequestMapping(value = "/event/saveWeixinMaterial")
	public String saveWeixinMaterial(HttpServletRequest request,Long[] id){
		String[] titles = request.getParameterValues("title");
		String[] newsImages = request.getParameterValues("news_images");
		String[] descriptions = request.getParameterValues("description");
		String[] urls = request.getParameterValues("news_url");

		try {
			weixinMaterialService.saveWeixinMaterial(titles, newsImages, descriptions, urls,id,request);
			return "redirect:/event/weixin_material_list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/event/weixin_material_list";
	}
	
	/**
	 * 删除素材
	 * @return
	 */
	@RequestMapping(value = "/event/delete")
	public String delete(HttpServletRequest request,Long id){
		try {
			weixinMaterialService.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/event/weixin_material_list";
	}
	
	
	/**
	 * 设置默认回复图文
	 * @return
	 */
	@RequestMapping(value = "/event/updateDefaults")
	public String updateDefaults(HttpServletRequest request,Long id){
		try {
			weixinMaterialService.updateDefaults(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/event/weixin_material_list";
	}
	
}
