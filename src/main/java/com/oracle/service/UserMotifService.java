package com.oracle.service;

import com.oracle.models.Motif;
import com.oracle.models.User;
import com.oracle.models.UserMotif;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

public interface UserMotifService {
    public List<UserMotif> findByUser(User user);

    public List<UserMotif> findByMotif(Motif motif);
    public List<UserMotif> findByUser(Long id);

    public List<UserMotif> findAll();


    public Optional<UserMotif> findById(long id);

    public UserMotif save(UserMotif userMotif);

    public UserMotif updateUserMotif(UserMotif userMotif, long id);

    public void delete(long id);
    public int deleteUserMotifs(List<UserMotif> userMotifs);



//    public Blob createBlob(InputStream content, long size);
//
    public UserMotif saveImage(MultipartFile file);

	public List<UserMotif> findByImage(byte[] bytes);

//    public void storeImage(MultipartFile file) throws IOException;
}
