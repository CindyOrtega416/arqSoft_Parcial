package ar.edu.ucc.arqSoft.baseService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ar.edu.ucc.arqSoft.baseService.dao.UsuarioDao;

import ar.edu.ucc.arqSoft.baseService.dto.UsuarioRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.UsuarioResponseDto;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Service
@Transactional
public class UsuarioService {


	@Autowired
	private UsuarioDao usuarioDao;
	
	
	public UsuarioResponseDto insertUsuario (UsuarioRequestDto request) throws EntityNotFoundException, BadRequestException  {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre(request.getNombre());
		usuario.setApellido(request.getApellido());
		usuario.setEmail(request.getEmail());
		
		usuarioDao.insert(usuario);
		
		UsuarioResponseDto response = new UsuarioResponseDto();
		
		response.setNombre(usuario.getNombre());
		response.setApellido(usuario.getApellido());
		response.setEmail(usuario.getEmail());
		
		return response;
	}
	
/*	public UsuarioResponseDto addTarea(TareaRequestDto req, Long usuarioid) {
		Usuario usuario = usuarioDao.load(usuarioid);
		
		//usuario.setTareas(tareaDao.load(req.getId()));
		
		UsuarioResponseDto response = new UsuarioResponseDto();
		
		response = (UsuarioResponseDto) new ModelDtoConverter().convertToDto(usuario,new UsuarioResponseDto());
		
		return response;
		
	}*/
	
	public List<UsuarioResponseDto> GetByNombre(String nombre) throws EntityNotFoundException, BadRequestException {
		List<Usuario> usuarios = usuarioDao.FindByName(nombre);
		
		List<UsuarioResponseDto> response = new ArrayList<UsuarioResponseDto>();
		for(Usuario usuario: usuarios) 
		{
			if(usuario.getId()<=0)
			{
				throw new BadRequestException();
			}
		response.add((UsuarioResponseDto) new ModelDtoConverter().convertToDto(usuario,new UsuarioResponseDto()));
		}
		return response;
	
	}
	
}
