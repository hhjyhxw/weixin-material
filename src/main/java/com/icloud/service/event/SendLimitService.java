package com.icloud.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.dao.event.SendLimitMapper;
import com.icloud.model.event.SendLimit;
import com.icloud.model.event.SendLimitKey;
import com.icloud.service.BaseService;

@Service
public class SendLimitService implements BaseService<SendLimit>{

	@Autowired
	private SendLimitMapper sendLimitMapper;

	@Override
	public void save(SendLimit t) throws Exception {
		// TODO Auto-generated method stub
		sendLimitMapper.insert(t);
	}

	@Override
	public void update(SendLimit t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SendLimit> findList(SendLimit t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findCount(SendLimit t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SendLimit findByKey(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public SendLimit findByKey(SendLimitKey key) throws Exception {
		return sendLimitMapper.selectByPrimaryKey(key);
	}

	@Override
	public PageInfo<SendLimit> findByPage(int pageNo, int pageSize, SendLimit t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
