package com.oracle.serviceImpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.MotifDao;
import com.oracle.models.Motif;
import com.oracle.models.Role;
import com.oracle.models.User;
import com.oracle.models.UserMotif;
import com.oracle.service.MotifService;
import com.oracle.service.UserService;

import nu.pattern.OpenCV;

@Service
public class MotifServiceImpl implements MotifService {

    @Autowired
    private MotifDao motifDao;
    @Autowired
    private UserMotifServiceIml userMotifServiceImpl;
    @Autowired
    private UserService userService;

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

    @Override
    public List<Motif> findByImage(byte[] image) {
        // load the opencv lbrary
        OpenCV.loadShared();
        // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        User user = userService.findByRole(Role.ADMIN);
        List<UserMotif> motifs = userMotifServiceImpl.findByUser(user);
        List<Motif> foundedMotifs = new ArrayList<>();
        String loadedImagefile = "loadedImagefilePathname";
        String imageFile = "imageFilePathname";
        List<Double> doubles = new ArrayList<>();

        for (UserMotif currentMotif : motifs) {

            try (FileOutputStream fos = new FileOutputStream("loadedImagefilePathname")) {
                fos.write(currentMotif.getImage());
                //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MotifServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MotifServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try (FileOutputStream fos = new FileOutputStream("imageFilePathname")) {
                fos.write(image);
                //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MotifServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MotifServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

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
                Motif motif = currentMotif.getMotif();
                motif.setPourcentage(pourcentage);
                foundedMotifs.add(motif);
            }
        }
        System.out.println("serviceImpl.MotifServiceImpl.compareMotif()" + doubles.toString());
        return foundedMotifs;
    }

}
