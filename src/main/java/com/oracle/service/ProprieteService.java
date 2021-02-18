package com.oracle.service;

import java.util.List;

import com.oracle.models.Propriete;

public interface ProprieteService {
	
	public List<Propriete> findAll();

	public Propriete save(Propriete propriete);
}
