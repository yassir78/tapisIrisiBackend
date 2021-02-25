package com.oracle.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.oracle.models.Motif;
import com.oracle.models.UserMotif;

public interface MotifService {

	public List<Motif> findAll();

	// public List<Motif> findByUser(User user);
//    List<Motif> findByUser(User user);

	public Motif save(Motif motif);

	public Motif save(Motif motif, MultipartFile file, long id) throws IOException;

	public Motif findById(long id);



	public int delete(long id);


	public UserMotif updateMotif(long id, String libelle, MultipartFile file, Long idUserMorif);


}
