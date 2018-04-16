package com.icloud.dao.event;

import com.icloud.model.event.SendLimit;
import com.icloud.model.event.SendLimitKey;

public interface SendLimitMapper {
    int deleteByPrimaryKey(SendLimitKey key);

    int insert(SendLimit record);

    int insertSelective(SendLimit record);

    SendLimit selectByPrimaryKey(SendLimitKey key);

    int updateByPrimaryKeySelective(SendLimit record);

    int updateByPrimaryKey(SendLimit record);
}