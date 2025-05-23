package com.example.CrudProject.service;

import com.example.CrudProject.entity.Nominee;
import com.example.CrudProject.repository.NomineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NomineeService {

    private final NomineeRepository nomineeRepository;

    public NomineeService(NomineeRepository nomineeRepository) {
        this.nomineeRepository = nomineeRepository;
    }


    public Nominee addNominee(Nominee nominee) {
        return nomineeRepository.save(nominee);
    }


    public List<Nominee> getAllNominees() {
        return nomineeRepository.findAll();
    }


    public Nominee getNomineeById(Long id) {
        return nomineeRepository.findById(id).orElse(null);
    }


    public void deleteNominee(Long id) {
        nomineeRepository.deleteById(id);
    }

    public Optional<Nominee> updateNominee(Long id, Nominee nominee) {
        return nomineeRepository.findById(id)
                .map(existingNominee -> {
                    nominee.setNomineeId(id); // Ensure ID is set for update
                    return nomineeRepository.save(nominee);
                });
    }
}