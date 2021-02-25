package com.oracle.serviceImpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.dao.UserMotifDao;
import com.oracle.models.Motif;
import com.oracle.models.Role;
import com.oracle.models.User;
import com.oracle.models.UserMotif;
import com.oracle.service.UserMotifService;
import com.oracle.service.UserService;

import nu.pattern.OpenCV;

@Service
public class UserMotifServiceIml implements UserMotifService {
	@Autowired
	private UserMotifDao userMotifDao;
	@Autowired
	private UserService userService;
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
        return userMotifDao.findById(id);
    }

//	@Override
//	public Optional<UserMotif> findById(long id) {
//		return userMotifDao.findById(id);
//	}


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

//	public Blob createBlob(InputStream content, long size) {
//		return sessionFactory.getCurrentSession().getLobHelper().createBlob(content, size);
//	}

	@Override
	public UserMotif saveImage(MultipartFile file) {
		String docName = file.getOriginalFilename();
		UserMotif motif = new UserMotif(docName.getBytes());
		return userMotifDao.save(motif);
	}
//    https://www.youtube.com/watch?v=znjhY71F-8I&ab_channel=ChargeAhead

	public void storeImage(MultipartFile file) throws IOException {
		UserMotif userMotif = new UserMotif(file.getBytes());
		userMotifDao.save(userMotif);
	}

	@Override
	public List<UserMotif> findByUser(Long id) {
		User user = new User();
		user.setId(id);
		return userMotifDao.findByUser(user);
	}
	@Override
    public List<UserMotif> findByImage(byte[] image) {
        // load the opencv lbrary
        OpenCV.loadShared();
        boolean fosIsNull = false;

        User user = userService.findByRole(Role.ADMIN);
        List<UserMotif> userMotifs = findByUser(user);
        List<UserMotif> foundedMotifs = new ArrayList<>();
        String loadedImagefile = "loadedImagefilePathname51";
        String imageFile = "imageFilePathname55";
        List<Double> doubles = new ArrayList<>();

        for (UserMotif currentUserMotif : userMotifs) {

            try {
                FileOutputStream fos = new FileOutputStream(loadedImagefile);
                if (fos != null) {
                    fos.write(currentUserMotif.getImage());
                    fos.close();
                }else {
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
                }else {
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

                //Converting the source image to binary
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
                Imgproc.findContours(binary, contours1, hierarchey, Imgproc.RETR_TREE,
                        Imgproc.CHAIN_APPROX_SIMPLE);
                Imgproc.findContours(imageBinary, contours2, hierarchey, Imgproc.RETR_TREE,
                        Imgproc.CHAIN_APPROX_SIMPLE);

                double result = Imgproc.matchShapes(contours1.get(0), contours2.get(0), 1, 0);
                doubles.add(result);

                if (result < 0.15) {
                    double pourcentage = 100 - (100 * result);
                    currentUserMotif.setUser(null);
                    currentUserMotif.getMotif().setPourcentage(pourcentage);
                    foundedMotifs.add(currentUserMotif);
                }
                System.out.println("serviceImpl.MotifServiceImpl.compareMotif() : " + currentUserMotif.getMotif().getUsermotifs().get(0).getId() + " => " + result + " = " + (result < 0.01));
            }
        }
        System.out.println("serviceImpl.MotifServiceImpl.compareMotif()" + foundedMotifs.size());
        return foundedMotifs;
    }

}
