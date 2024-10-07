package com.app.ebanking.repositories;

import com.app.ebanking.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {
    Page<Operation> findByAccountId(UUID accountId, PageRequest pageRequest);
    List<Operation> findByAccountId(UUID accountId);
}
