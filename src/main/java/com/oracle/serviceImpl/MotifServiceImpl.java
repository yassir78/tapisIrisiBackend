package com.oracle.serviceImpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.dao.MotifDao;
import com.oracle.dao.ProprieteDao;
import com.oracle.dao.UserMotifDao;
import com.oracle.models.Motif;
import com.oracle.models.Propriete;
import com.oracle.models.Role;
import com.oracle.models.User;
import com.oracle.models.UserMotif;
import com.oracle.service.MotifService;
import com.oracle.service.ProprieteService;
import com.oracle.service.UserMotifService;
import com.oracle.service.UserService;

import nu.pattern.OpenCV;

@Service
public class MotifServiceImpl implements MotifService {

	@Autowired
	private MotifDao motifDao;
	@Autowired
	private UserMotifService motifService;
	@Autowired
	private ProprieteService proprieteService;
	private UserMotifServiceIml userMotifServiceImpl;
	@Autowired
	private UserService userService;
	@Autowired
	private UserMotifService userMotifService;
	@Autowired
	private UserMotifDao userMotifDao;
	@Autowired
	private ProprieteDao proprieteDao;

	@Override
	public List<Motif> findAll() {
		return motifDao.findAll();
	}

	@Override
	public int delete(long id) {
		Motif motif = motifDao.getOne(id);
		motifDao.delete(motif);
		return 1;

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
	public UserMotif updateMotif(long id, String libelle, MultipartFile file, Long idUserMorif) {

		// motifDao.save(motif);
		Motif foundedMotif = motifDao.getOne(id);

		foundedMotif.setLibelle(libelle);
		motifDao.save(foundedMotif);
		Optional<UserMotif> foundedUserMotif = userMotifDao.findById(idUserMorif);
		System.out.println("///////////////////////////");
		if (foundedUserMotif != null) {
			try {
				System.out.println("file not null");
				if (file != null) {
					foundedUserMotif.get().setImage(file.getBytes());
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userMotifDao.save(foundedUserMotif.get());
			return foundedUserMotif.get();
		}

		return foundedUserMotif.get();
	}

	@Override
	public Motif findById(long id) {
		return motifDao.findById(id);
	}

	@Override
	public Motif save(Motif motif, MultipartFile file, long id) throws IOException {
		Motif ms = motifDao.save(motif);
		UserMotif us = new UserMotif();
		User u = userService.findById(id);
		us.setMotif(ms);
		us.setUser(u);
		us.setImage(file.getBytes());
		userMotifService.save(us);
		return ms;

	}

}
