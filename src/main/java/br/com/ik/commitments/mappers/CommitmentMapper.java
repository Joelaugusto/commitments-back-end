package br.com.ik.commitments.mappers;


import br.com.ik.commitments.domain.Commitment;
import br.com.ik.commitments.domain.CommitmentRequest;
import br.com.ik.commitments.domain.CommitmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class CommitmentMapper {

    public static final CommitmentMapper commitmentMapper = Mappers.getMapper(CommitmentMapper.class);
    public abstract CommitmentResponse toResponse(Commitment commitment);
    public abstract Commitment toEntity(CommitmentRequest request);
    public abstract Commitment updateEntity(CommitmentRequest request,@MappingTarget Commitment commitment);
    public Page<CommitmentResponse> toResponse(Page<Commitment> commitments){
        return commitments.map(this::toResponse);
    }
    public List<CommitmentResponse> toResponse(List<Commitment> commitments){
        return commitments.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
