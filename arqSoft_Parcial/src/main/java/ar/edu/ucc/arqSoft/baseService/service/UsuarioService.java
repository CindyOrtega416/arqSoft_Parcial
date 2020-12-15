package ar.edu.ucc.arqSoft.baseService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.ProyectoDao;
import ar.edu.ucc.arqSoft.baseService.dao.UsuarioDao;
import ar.edu.ucc.arqSoft.baseService.dto.UsuarioRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.UsuarioResponseDto;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private ProyectoDao proyectoDao;

	public UsuarioResponseDto insertUsuario(UsuarioRequestDto request) throws BadRequestException {

		Usuario usuario = (Usuario) new ModelDtoConverter().convertToEntity(new Usuario(), request);

		try {

			usuarioDao.insert(usuario);
		}

		catch (BadRequestException e) {

			throw new BadRequestException();
		}
		UsuarioResponseDto response = (UsuarioResponseDto) new ModelDtoConverter().convertToDto(usuario,
				new UsuarioResponseDto());
		return response;
	}

	/*
	 * public UsuarioResponseDto addTarea(TareaRequestDto req, Long usuarioid) {
	 * Usuario usuario = usuarioDao.load(usuarioid);
	 * 
	 * //usuario.setTareas(tareaDao.load(req.getId()));
	 * 
	 * UsuarioResponseDto response = new UsuarioResponseDto();
	 * 
	 * response = (UsuarioResponseDto) new
	 * ModelDtoConverter().convertToDto(usuario,new UsuarioResponseDto());
	 * 
	 * return response;
	 * 
	 * }
	 */

	public UsuarioResponseDto getUsuarioById(Long id) throws EntityNotFoundException, BadRequestException {

		if (id <= 0) {
			throw new BadRequestException();
		}
		Usuario usuario = usuarioDao.load(id);

		UsuarioResponseDto response = new UsuarioResponseDto();

		response.setId(usuario.getId());
		response.setNombre(usuario.getNombre());
		response.setApellido(usuario.getApellido());
		response.setEmail(usuario.getEmail());

		return response;
	}

	public List<UsuarioResponseDto> getUsuariosFromProyecto(Long id)
			throws EntityNotFoundException, BadRequestException {

		if (id <= 0) {
			throw new BadRequestException();
		}
		Proyecto proyecto = proyectoDao.load(id);

		Set<Usuario> usuarios = proyecto.getUsuarios();

		List<UsuarioResponseDto> response = new ArrayList<UsuarioResponseDto>();

		for (Usuario usuario : usuarios) {
			response.add((UsuarioResponseDto) new ModelDtoConverter().convertToDto(usuario, new UsuarioResponseDto()));
		}

		return response;
	}

	public List<UsuarioResponseDto> getAllUsuarios() {

		List<Usuario> usuarios = usuarioDao.getAll();

		List<UsuarioResponseDto> response = new ArrayList<UsuarioResponseDto>();

		for (Usuario usuario : usuarios) {

			response.add((UsuarioResponseDto) new ModelDtoConverter().convertToDto(usuario, new UsuarioResponseDto()));
		}
		return response;

	}

}
