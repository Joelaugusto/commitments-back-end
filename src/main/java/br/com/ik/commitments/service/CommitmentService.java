package br.com.ik.commitments.service;

import br.com.ik.commitments.domain.Commitment;
import br.com.ik.commitments.domain.CommitmentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommitmentService {

    Commitment findById(Long id);
    List<Commitment> findAll();
    Page<Commitment> findAll(Pageable pageable);
    Commitment update(Long id, CommitmentRequest request);
    Commitment store(CommitmentRequest request);
    void deleteById(Long id);
}
