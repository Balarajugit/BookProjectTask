package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.Artical;
import com.app.domain.ArticalName;
import com.app.repo.IArticalRepository;
@Service
public class ArticalServiceImpl implements IArticalService {
	@Autowired
	private IArticalRepository repo;

	@Override
	public int saveArtical(Artical a) {
		// TODO Auto-generated method stub
		return repo.save(a).getId();
	}
	
	@Override
	public Artical getByName(String name) {
		// TODO Auto-generated method stub
		return repo.findByArticalName(name);
	}
	
	
@Override
public List<Object[]> searchArticals(String prop) {
	// TODO Auto-generated method stub
	return repo.searchArtical(prop);
}

}
