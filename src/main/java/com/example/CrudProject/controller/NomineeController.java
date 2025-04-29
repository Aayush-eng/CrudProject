package com.example.CrudProject.controller;

import com.example.CrudProject.entity.Nominee;
import com.example.CrudProject.service.NomineeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nominees")
public class NomineeController {

    private final NomineeService nomineeService;

    public NomineeController(NomineeService nomineeService) {
        this.nomineeService = nomineeService;
    }

    @PostMapping("/add-nominee")
    public ResponseEntity<Nominee> addNominee(@RequestBody Nominee nominee) {
        Nominee savedNominee = nomineeService.addNominee(nominee);
        return ResponseEntity.ok(savedNominee);
    }

    @GetMapping("/all-nominee-list")
    public ResponseEntity<List<Nominee>> getAllNominees() {
        return ResponseEntity.ok(nomineeService.getAllNominees());
    }

    @GetMapping("/get-nominee/{id}")
    public ResponseEntity<Nominee> getNomineeById(@PathVariable Long id) {
        Nominee nominee = nomineeService.getNomineeById(id);
        if (nominee != null) {
            return ResponseEntity.ok(nominee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteNominee(@PathVariable Long id) {
        nomineeService.deleteNominee(id);
        return ResponseEntity.noContent().build();
    }
}
