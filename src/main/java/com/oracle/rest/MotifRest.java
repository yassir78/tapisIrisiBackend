package com.oracle.rest;

import java.util.List;


import com.oracle.models.UserMotif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
