package com.oracle.serviceImpl;

import java.util.List;
import java.util.Optional;


import com.oracle.models.Propriete;
import com.oracle.models.User;

import com.oracle.models.UserMotif;
import com.oracle.service.ProprieteService;
import com.oracle.service.UserMotifService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.MotifDao;
import com.oracle.models.Motif;
import com.oracle.service.MotifService;

@Service
public class MotifServiceImpl implements MotifService {
    @Autowired
    private MotifDao motifDao;
    @Autowired
    private UserMotifService motifService;
    @Autowired
    private ProprieteService proprieteService;

    @Override
    public List<Motif> findAll() {
        return motifDao.findAll();
    }

    @Override
    public void delete(long id) {
        Motif m = new Motif();
        m.setId(id);
        List<UserMotif> userMotifs = motifService.findByMotif(m);
        List<Propriete> proprietes = proprieteService.findByMotif(m);
        proprieteService.deleteProperties(proprietes);
        motifService.deleteUserMotifs(userMotifs);
        motifDao.delete(m);

    }

//    @Override
//    public List<Motif> findByUser(User user) {
//        return motifDao.findByUser(user);
//    }

    @Override
    public Motif save(Motif motif) {
        return motifDao.save(motif);
    }

    @Override
    public Motif updateMotif(Motif motif, long id) {
        Motif motifF = motifDao.findById(id);
        if (motifF != null) {
            motifF.setLibelle(motif.getLibelle());
            return motifDao.save(motifF);
        } else {
            return null;
        }
    }

    @Override
    public Motif findById(long id) {
        return motifDao.findById(id);
    }

}
