package com.senac.certificacao.services;


import com.senac.certificacao.dto.ProcessoDTO;
import com.senac.certificacao.entities.Processo;
import com.senac.certificacao.exceptions.DatabaseException;
import com.senac.certificacao.exceptions.ResourceNotFoundException;
import com.senac.certificacao.repositories.ProcessoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProcessoService {
    @Autowired
    private ProcessoRepository repository;

    @Transactional(readOnly = true)
    public ProcessoDTO findById(Long id) {
        Processo processo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProcessoDTO(processo);
    }

    @Transactional(readOnly = true)
    public Page<ProcessoDTO> findAll(Pageable pageable) {
        Page<Processo> result = repository.findAll(pageable);
        return result.map(ProcessoDTO::new);
    }

    @Transactional
    public ProcessoDTO insert(ProcessoDTO dto) {
        Processo entity = new Processo();
        entity.setNomeProcesso(dto.getNomeProcesso());
        entity = repository.save(entity);
        return new ProcessoDTO(entity);
    }

    @Transactional
    public ProcessoDTO update(Long id, ProcessoDTO dto) {
        try {
            Processo entity = repository.getReferenceById(id);
            entity.setNomeProcesso(dto.getNomeProcesso());
            entity = repository.save(entity);
            return new ProcessoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
