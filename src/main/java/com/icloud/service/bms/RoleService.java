package com.icloud.service.bms;

import java.util.List;

import com.icloud.model.bms.Trole;
import com.icloud.service.Service;

public interface RoleService extends Service<Trole>{
	
    int insertSelective(Trole record);
     
    int selectCountByName(String roleName);
    List<Trole> selectByAdmin(String adminId);
    List<Trole> selectAll();
}
