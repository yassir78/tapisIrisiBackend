package com.oracle.service;

import java.util.List;
import java.util.Optional;

import com.oracle.models.Motif;
import com.oracle.models.Propriete;

public interface ProprieteService {

    public List<Propriete> findAll();

    public Propriete save(Propriete propriete);

    public Optional<Propriete> findById(long id);

    List<Propriete> findByMotif(Motif motif);

    public void delete(long id);

    public void deleteProperties(List<Propriete> proprietes);

    public Propriete updatepropriete(long id, Propriete p);
//    public void update()
}
