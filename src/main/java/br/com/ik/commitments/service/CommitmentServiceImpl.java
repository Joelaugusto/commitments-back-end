package br.com.ik.commitments.service;

import br.com.ik.commitments.domain.Commitment;
import br.com.ik.commitments.domain.CommitmentRequest;
import br.com.ik.commitments.persistence.CommitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import static br.com.ik.commitments.mappers.CommitmentMapper.commitmentMapper;
import java.util.List;


//@RequiredArgsConstructor
@Service
public class CommitmentServiceImpl implements CommitmentService {

    @Autowired
    private CommitmentRepository repository;

    private final ResponseStatusException notFoundException
            = new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o compromisso");

    @Override
    public Commitment findById(Long id) {
        return repository.findById(id).orElseThrow(() -> notFoundException);
    }

    @Override
    public List<Commitment> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Commitment> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Commitment update(Long id, CommitmentRequest request) {
        Commitment commitment = findById(id);
        commitmentMapper.updateEntity(request, commitment);
        return repository.save(commitment);
    }

    @Override
    public Commitment store(CommitmentRequest request) {
        return repository.save(commitmentMapper.toEntity(request));
    }

    @Override
    public void deleteById(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw notFoundException;
        }
    }
}
