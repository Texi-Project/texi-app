package com.texi.app.claim.repository;

import com.texi.app.domain.Claim;
import org.springframework.data.repository.CrudRepository;

public interface ClaimRepository extends CrudRepository<Claim, Long> {
}
