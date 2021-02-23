package com.oracle.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.oracle.models.Motif;
import org.springframework.beans.BeanUtils;
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
    public Optional<Propriete> findById(long id) {
        return proprieteDao.findById(id);
    }

    @Override
    public List<Propriete> findByMotif(Motif motif) {
        return proprieteDao.findByMotif(motif);
    }

    @Override
    public void delete(long id) {
        proprieteDao.deleteById(id);
    }

    @Override
    public void deleteProperties(List<Propriete> proprietes) {
        for (Propriete pr : proprietes) {
            proprieteDao.delete(pr);
        }
    }

    @Override
    public Propriete updatepropriete(long id, Propriete p) {
        Optional<Propriete> proprie = proprieteDao.findById(id);
        if (proprie != null) {
            proprie.get().setLibelle(p.getLibelle());
            proprie.get().setDescription(p.getDescription());
            return proprieteDao.save(proprie.get());
        } else {
            return null;
        }
    }
}
