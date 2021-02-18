package com.oracle.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.ProprieteDao;
import com.oracle.models.Propriete;
import com.oracle.service.ProprieteService;
@Service
public class ProprieteServiceImpl implements ProprieteService {
	@Autowired
	private ProprieteDao proprieteDao;

	@Override
	public List<Propriete> findAll() {
		// TODO Auto-generated method stub
		return proprieteDao.findAll();
	}

	@Override
	public Propriete save(Propriete propriete) {
		// TODO Auto-generated method stub
		return proprieteDao.save(propriete);
	}

}
