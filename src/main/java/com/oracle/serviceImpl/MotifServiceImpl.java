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
	private UserMotifDao userMotifDao;
	@Autowired
	private ProprieteDao proprieteDao;

	@Override
	public List<Motif> findAll() {
		return motifDao.findAll();
	}

	@Override
	public int delete(long  id) {
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
	public UserMotif updateMotif(Motif motif, MultipartFile file, Long id) {

		// motifDao.save(motif);
		Motif foundedMotif = motifDao.getOne(motif.getId());
		for (Propriete propriete : motif.getProprietes()) {
			Propriete prop = proprieteDao.getOne(propriete.getId());
			prop.setDescription(propriete.getDescription());
			prop.setLibelle(propriete.getLibelle());
			proprieteDao.save(prop);
		}
		foundedMotif.setDescription(motif.getDescription());
		foundedMotif.setLibelle(motif.getLibelle());
		motifDao.save(foundedMotif);
		Optional<UserMotif> foundedUserMotif = userMotifDao.findById(id);
		if (foundedUserMotif != null) {
			try {
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

		return null;
	}

	@Override
	public Motif findById(long id) {
		return motifDao.findById(id);
	}

	@Override
	public List<Motif> findByImage(byte[] image) {
		// load the opencv lbrary
		OpenCV.loadShared();
		boolean fosIsNull = false;

		User user = userService.findByRole(Role.ADMIN);
		List<UserMotif> motifs = userMotifServiceImpl.findByUser(user);
		List<Motif> foundedMotifs = new ArrayList<>();
		String loadedImagefile = "loadedImagefilePathname51";
		String imageFile = "imageFilePathname55";
		List<Double> doubles = new ArrayList<>();

		for (UserMotif currentMotif : motifs) {

			try {
				FileOutputStream fos = new FileOutputStream(loadedImagefile);
				if (fos != null) {
					fos.write(currentMotif.getImage());
					fos.close();
				} else {
					fosIsNull = true;
				}
			} catch (FileNotFoundException ex) {
				System.out.println("err file output strem" + ex.getMessage());
			} catch (IOException ex) {
				System.out.println("err file output strem" + ex.getMessage());
			}

			try {
				FileOutputStream fos = new FileOutputStream(imageFile);
				if (fos != null) {
					fos.write(image);
					fos.close();
				} else {
					fosIsNull = true;
				}
			} catch (FileNotFoundException ex) {
				Logger.getLogger(MotifServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(MotifServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
			}

			if (!fosIsNull) {
				MatOfPoint2f approxCurve = new MatOfPoint2f();
				Mat src = Imgcodecs.imread(loadedImagefile);

				MatOfPoint2f imageapproxCurve = new MatOfPoint2f();
				Mat imageSrc = Imgcodecs.imread(imageFile);

				// Converting the source image to binary
				Mat gray = new Mat(src.rows(), src.cols(), src.type());
				Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
				Mat binary = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
				Imgproc.threshold(gray, binary, 100, 255, Imgproc.THRESH_BINARY_INV);

				Mat imageGray = new Mat(imageSrc.rows(), imageSrc.cols(), imageSrc.type());
				Imgproc.cvtColor(imageSrc, imageGray, Imgproc.COLOR_BGR2GRAY);
				Mat imageBinary = new Mat(imageSrc.rows(), imageSrc.cols(), imageSrc.type(), new Scalar(0));
				Imgproc.threshold(imageGray, imageBinary, 100, 255, Imgproc.THRESH_BINARY_INV);

				List<MatOfPoint> contours1 = new ArrayList<>();
				List<MatOfPoint> contours2 = new ArrayList<>();
				Mat hierarchey = new Mat();
				Imgproc.findContours(binary, contours1, hierarchey, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
				Imgproc.findContours(imageBinary, contours2, hierarchey, Imgproc.RETR_TREE,
						Imgproc.CHAIN_APPROX_SIMPLE);

				double result = Imgproc.matchShapes(contours1.get(0), contours2.get(0), 1, 0);
				doubles.add(result);

				if (result < 0.15) {
					double pourcentage = 100 - (100 * result);
					Motif motif = currentMotif.getMotif();
					motif.setPourcentage(pourcentage);
					foundedMotifs.add(motif);
				}
				System.out.println("serviceImpl.MotifServiceImpl.compareMotif() : "
						+ currentMotif.getMotif().getLibelle() + " => " + result + " = " + (result < 0.01));
			}
		}
		System.out.println("serviceImpl.MotifServiceImpl.compareMotif()" + foundedMotifs.size());
		return foundedMotifs;
	}


}
