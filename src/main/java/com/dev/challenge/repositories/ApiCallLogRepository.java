package com.dev.challenge.repositories;

import com.dev.challenge.entities.ApiCallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiCallLogRepository extends JpaRepository<ApiCallLog, Long> {
}

