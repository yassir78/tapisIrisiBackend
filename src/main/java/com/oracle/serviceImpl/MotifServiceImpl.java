package com.oracle.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.MotifDao;
import com.oracle.models.Motif;
import com.oracle.service.MotifService;

@Service
public class MotifServiceImpl implements MotifService {
    @Autowired
    private MotifDao motifDao;

    @Override
    public List<Motif> findAll() {
        return motifDao.findAll();
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
    public Motif findById(long id) {
        return motifDao.findById(id);
    }

}
