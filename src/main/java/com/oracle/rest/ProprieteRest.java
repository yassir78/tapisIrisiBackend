package com.oracle.rest;

import java.util.List;

import com.oracle.models.Motif;
import com.oracle.service.MotifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.oracle.models.Propriete;
import com.oracle.service.ProprieteService;

@RestController
@CrossOrigin
@RequestMapping("/tapis-irisi/propriete")
public class ProprieteRest {
    @Autowired
    private ProprieteService proprieteService;
    @Autowired
    private MotifService motifService;


    // done
    @GetMapping("/")
    public List<Propriete> findAll() {
        return proprieteService.findAll();
    }


    // done
    @GetMapping("/byMotif")
    public List<Propriete> findByMotif(@RequestBody Motif motif) {
        return proprieteService.findByMotif(motif);
    }
    // done
    @PostMapping("/{idmotif}")
    public Propriete save(@RequestBody Propriete propriete, @PathVariable long idmotif) {
        Motif m = motifService.findById(idmotif);
        propriete.setMotif(m);
        return proprieteService.save(propriete);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        proprieteService.delete(id);
    }

    @PutMapping("/{id}")
    public Propriete update(@RequestBody Propriete propriete, @PathVariable long id) {
        return proprieteService.updatepropriete(id, propriete);
    }

}
