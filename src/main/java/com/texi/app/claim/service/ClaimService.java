package com.texi.app.claim.service;

import com.texi.app.domain.Claim;

public interface ClaimService {
    Claim resolveClaim(Long id);
}
