package com.oracle.rest;


import com.oracle.models.Motif;
import com.oracle.models.User;
import com.oracle.models.UserMotif;
import com.oracle.response.UserMotifResponse;
import com.oracle.service.MotifService;
import com.oracle.service.UserMotifService;
import com.oracle.service.UserService;
import com.oracle.serviceImpl.UserMotifServiceIml;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

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

    // done
    @GetMapping("/byUser")
    public List<UserMotif> findByUser(@RequestBody User user) {
        return userMotifService.findByUser(user);
    }

    // done
    @GetMapping("/byMotif")
    public List<UserMotif> findByMotif(@RequestBody Motif motif) {
        return userMotifService.findByMotif(motif);
    }

    // done
    @PostMapping(value = "/{user}/{motif}")
    public ResponseEntity<UserMotifResponse> save(@PathVariable long user, @PathVariable long motif, @RequestParam("file") MultipartFile file) throws IOException {
        User u = userService.findById(user);
        Motif m = motifService.findById(motif);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        UserMotif userMotif = new UserMotif(fileName, file.getContentType(), file.getBytes());
        if (u != null && m != null) {
            userMotif.setUser(u);
            userMotif.setMotif(m);
            userMotifService.save(userMotif);
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path("" + userMotif.getId())
                    .toUriString();
            UserMotifResponse userMotifResponse = new UserMotifResponse(userMotif.getName(), fileDownloadUri, userMotif.getType(), userMotif.getImage());
            return ResponseEntity.status(HttpStatus.OK).body(userMotifResponse);
        } else {
            System.out.println("xi moxkil");
            return null;
        }

    }

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            userMotifService.storeImage(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        }
    }
}
