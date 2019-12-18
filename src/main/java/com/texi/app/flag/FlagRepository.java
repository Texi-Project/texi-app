package com.texi.app.flag;

import com.texi.app.domain.Flag;
import com.texi.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlagRepository extends JpaRepository<Flag,Long> {
    int countAllByUser(User user);
}
