package com.oracle.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oracle.models.Motif;
import com.oracle.models.UserMotif;
import com.oracle.service.MotifService;

@RestController
@CrossOrigin("*")
@RequestMapping("/tapis-irisi/motif")
public class MotifRest {
    @Autowired
    private MotifService motifService;

    @PostMapping(value = "/save/")
    public Motif saveMotif2(@RequestParam("libelle") String libelle, @RequestParam("desc") String desc, @RequestParam("file") MultipartFile file, @RequestParam("userId") long userId) {
        Motif m = new Motif(libelle, desc);
        try {
            m = motifService.save(m, file, userId);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return m;
    }
	// done
    @GetMapping("/")
    public List<Motif> findAll() {
        return motifService.findAll();
    }

    // done
    @GetMapping("/{id}")
    public Motif findById(@PathVariable long id) {
        return motifService.findById(id);
    }

    // done
    @PostMapping(value = "")
    public Motif saveMotif(@RequestBody Motif motif) {
        return motifService.save(motif);
    }

    @PostMapping("/update/")
    public ResponseEntity<UserMotif> updateMotif(@RequestParam String libelle,
    		@RequestParam(name = "idMotif") long idMotif,MultipartFile file, @RequestParam(name = "idUserMotif") long idUserMotif) {
    	System.out.println("jkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        UserMotif um = motifService.updateMotif(idMotif,libelle,file, idUserMotif);
        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/tapis-irisi/user-motif/images/")
                .path("" + (int)(10.0 * Math.random()))
                .toUriString();

        um.setFileUrl(fileDownloadUri);
        return ResponseEntity.status(HttpStatus.OK).body(um);
    }

    @DeleteMapping("/delete/{id}")
    public int delteMotif(@PathVariable long id) {
       return  motifService.delete(id);
    }



}
