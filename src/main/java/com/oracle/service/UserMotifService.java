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

    public Optional<UserMotif> findById(long id);

    public UserMotif save(UserMotif userMotif);

    public Blob createBlob(InputStream content, long size);

    public UserMotif saveImage(MultipartFile file);

    public void storeImage(MultipartFile file) throws IOException;
}
