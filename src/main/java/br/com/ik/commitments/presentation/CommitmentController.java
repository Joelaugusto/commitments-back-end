package br.com.ik.commitments.presentation;
import br.com.ik.commitments.domain.CommitmentRequest;
import br.com.ik.commitments.domain.CommitmentResponse;
import br.com.ik.commitments.service.CommitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static br.com.ik.commitments.mappers.CommitmentMapper.commitmentMapper;


@RestController
@RequestMapping(path = "/api/v1/commitments")
@CrossOrigin
public class CommitmentController{

    @Autowired
    private CommitmentService commitmentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommitmentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(commitmentMapper.toResponse(commitmentService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Iterable<CommitmentResponse>> findAll(Pageable pageable, Boolean unpaged) {
        return ResponseEntity.ok(Boolean.TRUE.equals(unpaged) ?
                commitmentMapper.toResponse(commitmentService.findAll()) :
                commitmentMapper.toResponse(commitmentService.findAll(pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommitmentResponse> update(@PathVariable Long id, @RequestBody CommitmentRequest request) {
        return ResponseEntity.ok(commitmentMapper.toResponse(commitmentService.update(id, request)));
    }

    @PostMapping
    public ResponseEntity<CommitmentResponse> store(@RequestBody CommitmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commitmentMapper.toResponse(commitmentService.store(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commitmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
