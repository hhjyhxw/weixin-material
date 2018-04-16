package com.icloud.service.bms;


import com.icloud.model.bms.Tadmin;
import com.icloud.service.Service;
public interface AdminService extends Service<Tadmin>{
	/**
	 * 登录
	 * @param tadmin
	 * @return
	 * @throws Exception
	 */
	public Tadmin login(Tadmin tadmin) throws Exception ;
	
  
    
    /**
     * 查找账号是否已被使用
     * @param account
     * @return
     * @throws Exception
     */
    public int selectCountByAccount(String account) throws Exception;
    
   
    
    
    
   
}
