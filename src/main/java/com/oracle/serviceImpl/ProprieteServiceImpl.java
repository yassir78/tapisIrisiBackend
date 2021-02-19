package com.oracle.serviceImpl;

import java.util.List;

import com.oracle.models.Motif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.ProprieteDao;
import com.oracle.models.Propriete;
import com.oracle.service.ProprieteService;

@Service
public class ProprieteServiceImpl implements ProprieteService {
    @Autowired
    private ProprieteDao proprieteDao;

    @Override
    public List<Propriete> findAll() {
        return proprieteDao.findAll();
    }

    @Override
    public Propriete save(Propriete propriete) {
        return proprieteDao.save(propriete);
    }

    @Override
    public List<Propriete> findByMotif(Motif motif) {
        return proprieteDao.findByMotif(motif);
    }

    @Override
    public void delete(long id) {
        proprieteDao.deleteById(id);
    }

}
