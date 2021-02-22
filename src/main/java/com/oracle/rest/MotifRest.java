package com.oracle.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.oracle.models.Motif;
import com.oracle.service.MotifService;
import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

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
    
    // done
    @PostMapping(value = "/findByImage")
    public List<Motif> findByImage(@RequestParam("file") MultipartFile file) throws IOException {
        return motifService.findByImage(file.getBytes());
    }
}
