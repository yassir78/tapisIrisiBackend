package com.oracle.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.models.Propriete;
import com.oracle.service.ProprieteService;
@RestController
@CrossOrigin
@RequestMapping("/tapis-irisi/propriete")
public class ProprieteRest {
	@Autowired
	private ProprieteService proprieteService;

	@GetMapping("/")
	public List<Propriete> findAll() {
		return proprieteService.findAll();
	}

}
