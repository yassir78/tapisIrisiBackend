package com.oracle.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oracle.models.Motif;
import com.oracle.models.User;
import com.oracle.models.UserMotif;
import com.oracle.response.UserMotifResponse;
import com.oracle.service.MotifService;
import com.oracle.service.UserMotifService;
import com.oracle.service.UserService;



@CrossOrigin("*")
@RestController
@RequestMapping("/tapis-irisi/user-motif")
public class UserMotifRest {

    @Autowired
    private UserMotifService userMotifService;
    @Autowired
    private UserService userService;
    @Autowired
    private MotifService motifService;

    @GetMapping("/")
    public ResponseEntity<List<UserMotifResponse>> findAll() {
        List<UserMotifResponse> userMotifResponses = new ArrayList<>();
        List<UserMotif> usermotifs = userMotifService.findAll();
        for (UserMotif um : usermotifs) {
            UserMotifResponse umr = new UserMotifResponse();
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/tapis-irisi/user-motif/images/")
                    .path("" + um.getId())
                    .toUriString();
            umr.setFileDownloadUri(fileDownloadUri);
            BeanUtils.copyProperties(um, umr);
            userMotifResponses.add(umr);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userMotifResponses);
    }

    // done
//    @GetMapping("/byUser")
//    public List<UserMotif> findByUser(@RequestBody User user) {
//        return userMotifService.findByUser(user);
//    }

//	@Autowired
//	private UserMotifService userMotifService;
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private MotifService motifService;


    // done
    @GetMapping("/byUser")
    public List<UserMotif> findByUser(@RequestBody User user) {
        return userMotifService.findByUser(user);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImageUserMotif(@PathVariable long id) {
        Optional<UserMotif> um = userMotifService.findById(id);
        if (um != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "\"")
                    .body(um.get().getImage());
        } else {
            return null;
        }
    }

    // done
    @GetMapping("/byMotif")
    public List<UserMotif> findByMotif(@RequestBody Motif motif) {
        return userMotifService.findByMotif(motif);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userMotifService.delete(id);
    }

//    @PostMapping("/upload")
//    public void uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//        try {
//            userMotifService.storeImage(file);
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//        }
//    }

    // done
    @PutMapping("/{idUserM}")
    @ResponseBody
    public ResponseEntity<UserMotifResponse> updateImageUserMotif(@PathVariable long idUserM,
                                                                  @RequestParam("file") MultipartFile file) throws IOException {
        UserMotif um = userMotifService.findById(idUserM).get();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        um.setImage(file.getBytes());
        UserMotif userms = userMotifService.save(um);
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/tapis-irisi/user-motif/images/")
                .path("" + um.getId())
                .toUriString();

        UserMotifResponse userMotifResponse = new UserMotifResponse(fileDownloadUri);
        BeanUtils.copyProperties(userms, userMotifResponse);
        return ResponseEntity.status(HttpStatus.OK).body(userMotifResponse);
    }


    // done
    @PostMapping(value = "/{user}/{motif}")
    @ResponseBody
    public ResponseEntity<UserMotifResponse> save(@PathVariable long user, @PathVariable long motif, @RequestParam("file") MultipartFile file) throws IOException {
        User u = userService.findById(user);
        Motif m = motifService.findById(motif);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        UserMotif userMotif = new UserMotif(file.getBytes());
        if (u != null && m != null) {
            userMotif.setUser(u);
            userMotif.setMotif(m);
            UserMotif userms = userMotifService.save(userMotif);
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/tapis-irisi/user-motif/images/")
                    .path("" + userMotif.getId())
                    .toUriString();

            UserMotifResponse userMotifResponse = new UserMotifResponse(fileDownloadUri);
            BeanUtils.copyProperties(userms, userMotifResponse);
            return ResponseEntity.status(HttpStatus.OK).body(userMotifResponse);
        } else {
            System.out.println("xi moxkil");
            return null;
        }
    }
}


// done


//	@GetMapping("/images/{id}")
//	public ResponseEntity<byte[]> findUserMotifById(@PathVariable long id) {
//		Optional<UserMotif> um = userMotifService.findById(id);
//		if (um != null) {
//			return ResponseEntity.ok()
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + um.get().getName() + "\"")
//					.body(um.get().getImage());
//		} else {
//			return null;
//		}
//
//	}
