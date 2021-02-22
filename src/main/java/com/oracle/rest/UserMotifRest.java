package com.oracle.rest;


import com.oracle.models.Motif;
import com.oracle.models.User;
import com.oracle.models.UserMotif;
import com.oracle.response.UserMotifResponse;
import com.oracle.service.MotifService;
import com.oracle.service.UserMotifService;
import com.oracle.service.UserService;
import com.oracle.serviceImpl.UserMotifServiceIml;
import org.springframework.beans.BeanUtils;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @ResponseBody
    public ResponseEntity<UserMotifResponse> save(@PathVariable long user, @PathVariable long motif, @RequestParam("file") MultipartFile file) throws IOException {
        User u = userService.findById(user);
        Motif m = motifService.findById(motif);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        UserMotif userMotif = new UserMotif(fileName, file.getContentType(), file.getBytes());
        if (u != null && m != null) {
            userMotif.setUser(u);
            userMotif.setMotif(m);
            UserMotif userms = userMotifService.save(userMotif);
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/tapis-irisi/user-motif/images/")
                    .path("" + userMotif.getId())
                    .toUriString();

            UserMotifResponse userMotifResponse = new UserMotifResponse(userMotif.getName(), fileDownloadUri, userMotif.getType());
            BeanUtils.copyProperties(userms, userMotifResponse);
            return ResponseEntity.status(HttpStatus.OK).body(userMotifResponse);
        } else {
            System.out.println("xi moxkil");
            return null;
        }

    }

    // done
    @PutMapping("/{idUserM}")
    @ResponseBody
    public ResponseEntity<UserMotifResponse> updateImageUserMotif(@PathVariable long idUserM, @RequestParam("file") MultipartFile file) throws IOException {
        UserMotif um = userMotifService.findById(idUserM).get();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        um.setImage(file.getBytes());
        um.setType(file.getContentType());
        um.setName(fileName);
        UserMotif userms = userMotifService.save(um);
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/tapis-irisi/user-motif/images/")
                .path("" + um.getId())
                .toUriString();

        UserMotifResponse userMotifResponse = new UserMotifResponse(um.getName(), fileDownloadUri, um.getType());
        BeanUtils.copyProperties(userms, userMotifResponse);
        return ResponseEntity.status(HttpStatus.OK).body(userMotifResponse);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImageUserMotif(@PathVariable long id) {
        Optional<UserMotif> um = userMotifService.findById(id);
        if (um != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + um.get().getName() + "\"")
                    .body(um.get().getImage());
        } else {
            return null;
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userMotifService.delete(id);
    }

}
