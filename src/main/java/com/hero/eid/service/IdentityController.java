package com.hero.eid.service;

import com.hero.eid.model.Identity;
import com.hero.eid.model.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("identity")
public class IdentityController {

    private final IdentityRepository repo;

    @Autowired
    public IdentityController(IdentityRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Identity persist(@RequestBody Identity value){
        return repo.persist(value);
    }
}
