package com.senac.certificacao.services;

import com.senac.certificacao.dto.VisitaDTO;
import com.senac.certificacao.entities.Area;
import com.senac.certificacao.entities.Processo;
import com.senac.certificacao.entities.Usuario;
import com.senac.certificacao.entities.Visita;
import com.senac.certificacao.exceptions.DatabaseException;
import com.senac.certificacao.exceptions.ResourceNotFoundException;
import com.senac.certificacao.repositories.AreaRepository;
import com.senac.certificacao.repositories.ProcessoRepository;
import com.senac.certificacao.repositories.UsuarioRepository;
import com.senac.certificacao.repositories.VisitaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository repository;

    // três repositories dos relacionamentos
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private ProcessoRepository processoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public VisitaDTO findById(Long id) {
        Visita visita = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new VisitaDTO(visita);
    }

    @Transactional(readOnly = true)
    public Page<VisitaDTO> findAll(Pageable pageable) {
        Page<Visita> result = repository.findAll(pageable);
        return result.map(VisitaDTO::new);
    }

    @Transactional
    public VisitaDTO insert(VisitaDTO dto) {
        Visita entity = new Visita();
        entity.setDataVisita(dto.getDataVisita());   // campo simples

        // >>> três relacionamentos: busca cada um e associa <
        Area area = areaRepository.getReferenceById(dto.getArea().getId());
        Processo processo = processoRepository.getReferenceById(dto.getProcesso().getId());
        Usuario usuario = usuarioRepository.getReferenceById(dto.getUsuario().getId());
        entity.setArea(area);
        entity.setProcesso(processo);
        entity.setUsuario(usuario);

        entity = repository.save(entity);
        return new VisitaDTO(entity);
    }

    @Transactional
    public VisitaDTO update(Long id, VisitaDTO dto) {
        try {
            Visita entity = repository.getReferenceById(id);
            entity.setDataVisita(dto.getDataVisita());

            Area area = areaRepository.getReferenceById(dto.getArea().getId());
            Processo processo = processoRepository.getReferenceById(dto.getProcesso().getId());
            Usuario usuario = usuarioRepository.getReferenceById(dto.getUsuario().getId());
            entity.setArea(area);
            entity.setProcesso(processo);
            entity.setUsuario(usuario);

            entity = repository.save(entity);
            return new VisitaDTO(entity);
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
