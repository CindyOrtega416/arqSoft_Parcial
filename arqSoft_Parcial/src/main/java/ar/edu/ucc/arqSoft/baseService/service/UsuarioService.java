package ar.edu.ucc.arqSoft.baseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.UsuarioDao;
import ar.edu.ucc.arqSoft.baseService.dto.UsuarioRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.UsuarioResponseDto;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Service
@Transactional
public class UsuarioService {


	@Autowired
	private UsuarioDao usuarioDao;
	
	public UsuarioResponseDto insertUsuario (UsuarioRequestDto dto) throws EntityNotFoundException, BadRequestException  {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre(dto.getNombre());
		usuario.setApellido(dto.getApellido());
		usuario.setEmail(dto.getEmail());
		
		usuarioDao.insert(usuario);
		
		UsuarioResponseDto response = new UsuarioResponseDto();
		
		response.setNombre(usuario.getNombre());
		response.setApellido(usuario.getApellido());
		
		return response;
	}
	
	public UsuarioResponseDto getUsuarioByID (Long id) throws EntityNotFoundException, BadRequestException {
		
		if (id <= 0) {
			throw new BadRequestException();
		}
		
		Usuario usuario = usuarioDao.load(id);
		UsuarioResponseDto dto = new UsuarioResponseDto();
		
		dto.setNombre(usuario.getNombre());
		dto.setApellido(usuario.getApellido());
		dto.setEmail(usuario.getEmail());
		dto.setProyectos(usuario.getProyectos());
		
		return dto;
	}
}
