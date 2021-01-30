package com.app.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.TransientObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.domain.Artical;
import com.app.service.IArticalService;

@RestController
@RequestMapping("/artical")
public class ArticalRestController {
	@Autowired
	private IArticalService serv;

	@PostMapping("/save")
	public String saveArtical(@RequestBody Artical a) {
		int i=serv.saveArtical(a);

		return i+" artical details hsa been saved sucesssfully "+a.getArticalName();
	}

	@GetMapping("/{nam}")
	public ResponseEntity<?> fetchArticalsBasedOnRoles(@PathVariable("nam") String name,HttpSession session){
		ResponseEntity<?> res=null;
		try {
			Artical artical = serv.getByName(name);
			String type = artical.getArticalType();
			String role = (String)session.getAttribute("role");
			if(role==null) {
				role="open";
			}
			
			if((role.equals("paid") || role.equals ("free") || role.equals("open")) && type.equals("open")) {

				res=new ResponseEntity<Artical>(artical, HttpStatus.OK);

			}

			else if(role.equals("paid") && type.equals("paid")) {
				res=new ResponseEntity<Artical>(artical, HttpStatus.OK);
			}
			else if((role.equals("paid") || role.equals("free")) && type.equals("free")) {

				res=new ResponseEntity<Artical>(artical, HttpStatus.OK);


			}
			else {
				res=new ResponseEntity<String>("you dont have the permissin because you are a  '"+role+" 'user and it is a '"+artical.getArticalType(), HttpStatus.OK);
			}


		} catch (Exception e) {
			res=new ResponseEntity<String>("please login", HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	@GetMapping("getAll/{pro}")
	public ResponseEntity<?> searchingArticals(@PathVariable("pro") String prop){
		ResponseEntity<?> res=null;
		res=new ResponseEntity<List<Object[]>>(serv.searchArticals(prop), HttpStatus.OK);
		return res;
	}


}
