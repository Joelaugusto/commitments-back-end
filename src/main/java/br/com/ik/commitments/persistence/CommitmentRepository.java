package br.com.ik.commitments.persistence;

import br.com.ik.commitments.domain.Commitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitmentRepository extends JpaRepository<Commitment, Long> {
}
