package com.logistics.express.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.TransportCompanyMapper;
import com.logistics.express.entity.Driver;
import com.logistics.express.entity.TransportCompany;
import com.logistics.express.service.TransportCompanyService;

@Service("transportCompanyService")
public class TransportCompanyServiceImpl implements TransportCompanyService{
	
	@Autowired
	private TransportCompanyMapper transportCompanyMapper;

	@Override
	public int addCompany(TransportCompany transportCompany) {
		
		return transportCompanyMapper.insertSelective(transportCompany);
	}

	@Override
	public List<TransportCompany> getTransportCompany(Map<String, Object> map) {
		
		return transportCompanyMapper.getTransportCompanyList(map);
	}

	@Override
	public int getCompanyCount() {
		
		return transportCompanyMapper.getCompanyCount();
	}

	@Override
	public String getNameById(int id) {
		
		return transportCompanyMapper.getNameById(id);
	}

	@Override
	public int addDriverToCompany(Driver driver) {
	
		return transportCompanyMapper.addDriverToCompany(driver);
	}

	@Override
	public List<TransportCompany> getCompanyByName(String name) {
		
		return transportCompanyMapper.getCompanyByName(name);
	}
	
	

}
