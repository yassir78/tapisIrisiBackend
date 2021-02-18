package com.oracle.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.models.Motif;
import com.oracle.service.MotifService;
@RestController
@CrossOrigin
@RequestMapping("/tapis-irisi/motif")
public class MotifRest {
	@Autowired
	private MotifService motifService;
    @GetMapping("/")
	public List<Motif> findAll() {
		return motifService.findAll();
	}

}
