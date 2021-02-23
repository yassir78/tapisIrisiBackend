package com.oracle.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.oracle.models.Motif;
import com.oracle.service.MotifService;

@RestController
@CrossOrigin("*")
@RequestMapping("/tapis-irisi/motif")
public class MotifRest {
    @Autowired
    private MotifService motifService;

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

    @PutMapping("/update/{id}")
    public Motif updateMotif(@RequestBody Motif motif, @PathVariable long id) {
        return motifService.updateMotif(motif, id);
    }

    @DeleteMapping("/delete/{id}")
    public void delteMotif(@PathVariable long id) {
        motifService.delete(id);
    }

    // done
    @PostMapping(value = "/findByImage")
    public List<Motif> findByImage(@RequestParam("file") MultipartFile file) throws IOException {
        return motifService.findByImage(file.getBytes());
    }


}
