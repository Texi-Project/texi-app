package com.texi.app.claim.repository;

import com.texi.app.domain.Claim;
import com.texi.app.domain.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends CrudRepository<Claim, Long> {
    List<Claim> findByStatusOrderByClaimDateDesc(Status status);
}
