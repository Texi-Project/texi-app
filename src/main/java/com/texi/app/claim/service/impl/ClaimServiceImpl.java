package com.texi.app.claim.service.impl;

import com.texi.app.claim.repository.ClaimRepository;
import com.texi.app.claim.service.ClaimService;
import com.texi.app.domain.Claim;
import com.texi.app.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    ClaimRepository repository;

    @Override
    public Claim resolveClaim(Long id) {
        Optional<Claim> optional = repository.findById(id);
        if(!optional.isPresent()){
            return null;
        }
        Claim claim = optional.get();
        claim.setStatus(Status.DEACTIVATED);
        repository.save(claim);
        return claim;
    }
}
