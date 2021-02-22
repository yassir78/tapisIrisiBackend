package com.oracle.serviceImpl;

import com.oracle.dao.UserMotifDao;
import com.oracle.models.Motif;
import com.oracle.models.User;
import com.oracle.models.UserMotif;
import com.oracle.service.UserMotifService;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Service
public class UserMotifServiceIml implements UserMotifService {
    @Autowired
    private UserMotifDao userMotifDao;
    private final SessionFactory sessionFactory;

    @Autowired
    public UserMotifServiceIml(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserMotif> findByUser(User user) {

        return userMotifDao.findByUser(user);
    }

    @Override
    public List<UserMotif> findByMotif(Motif motif) {
        return userMotifDao.findByMotif(motif);
    }

    @Override
    public List<UserMotif> findAll() {
        return userMotifDao.findAll();
    }

    @Override
    public Optional<UserMotif> findById(long id) {
        return
                userMotifDao.findById(id);
    }

    @Override
    public UserMotif save(UserMotif userMotif) {
        return userMotifDao.save(userMotif);
    }

    @Override
    public UserMotif updateUserMotif(UserMotif userMotif, long id) {
        Optional<UserMotif> um = userMotifDao.findById(id);
        if (um != null) {
            BeanUtils.copyProperties(userMotif, um.get());
            return userMotifDao.save(um.get());
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        UserMotif um = userMotifDao.getOne(id);
        userMotifDao.delete(um);
    }

    @Override
    public int deleteUserMotifs(List<UserMotif> userMotifs) {
        for (UserMotif um : userMotifs) {
            userMotifDao.delete(um);
        }
        return 0;
    }


    public Blob createBlob(InputStream content, long size) {
        return sessionFactory.getCurrentSession().getLobHelper().createBlob(content, size);
    }
//    @Override
//    public UserMotif saveImage(MultipartFile file) {
//        String docName = file.getOriginalFilename();
//        UserMotif motif = new UserMotif(docName.getBytes());
//        return userMotifDao.save(motif);
//    }
//    public void storeImage(MultipartFile file) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        UserMotif userMotif = new UserMotif(fileName, file.getContentType(), file.getBytes());
//        userMotifDao.save(userMotif);
//    }
}
