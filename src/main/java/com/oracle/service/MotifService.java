package com.oracle.service;

import java.util.List;

import com.oracle.models.Motif;

public interface MotifService {
	
	public List<Motif> findAll();

	public Motif save(Motif motif);
}
