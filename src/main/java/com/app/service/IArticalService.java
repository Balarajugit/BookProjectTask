package com.app.service;

import java.util.List;

import com.app.domain.Artical;
import com.app.domain.ArticalName;

public interface IArticalService {
	
	public int saveArtical(Artical a);
	
	public Artical getByName(String name);
	
	public List<Object[]> searchArticals(String prop);
}
