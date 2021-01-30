package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.domain.Artical;
import com.app.domain.ArticalName;

public interface IArticalRepository extends JpaRepository<Artical, Integer> {
	
	public Artical findByArticalName(String name);
	
	@Query("select artical.articalName from com.app.domain.Artical artical where (articalName LIKE '%'||?1||'%' OR author LIKE '%'||?1||'%' OR articalType LIKE '%'||?1||'%')")
	public List<Object[]> searchArtical(String prop);

}
