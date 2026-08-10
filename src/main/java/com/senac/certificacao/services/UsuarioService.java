package com.senac.certificacao.services;

import com.senac.certificacao.dto.UsuarioDTO;
import com.senac.certificacao.entities.Perfil;
import com.senac.certificacao.entities.Usuario;
import com.senac.certificacao.exceptions.DatabaseException;
import com.senac.certificacao.exceptions.ResourceNotFoundException;
import com.senac.certificacao.repositories.PerfilRepository;
import com.senac.certificacao.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;   // segundo repository: pra buscar o perfil

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new UsuarioDTO(usuario);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> result = repository.findAll(pageable);
        return result.map(UsuarioDTO::new);
    }

    @Transactional
    public UsuarioDTO insert(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        // campos simples
        entity.setNomeUsuario(dto.getNomeUsuario());
        entity.setEmailUsuario(dto.getEmailUsuario());
        entity.setLogin(dto.getLogin());
        entity.setAtivo(dto.isAtivo());

        // >>> tratamento do relacionamento: busca o Perfil e associa <
        Perfil perfil = perfilRepository.getReferenceById(dto.getPerfil().getId());
        entity.setPerfil(perfil);

        entity = repository.save(entity);
        return new UsuarioDTO(entity);
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        try {
            Usuario entity = repository.getReferenceById(id);
            entity.setNomeUsuario(dto.getNomeUsuario());
            entity.setEmailUsuario(dto.getEmailUsuario());
            entity.setLogin(dto.getLogin());
            entity.setAtivo(dto.isAtivo());

            // >>> relacionamento também no update <
            Perfil perfil = perfilRepository.getReferenceById(dto.getPerfil().getId());
            entity.setPerfil(perfil);

            entity = repository.save(entity);
            return new UsuarioDTO(entity);
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
