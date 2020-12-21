package ar.edu.ucc.arqSoft.baseService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.ComentarioDao;
import ar.edu.ucc.arqSoft.baseService.dao.ProyectoDao;
import ar.edu.ucc.arqSoft.baseService.dao.TareaDao;
import ar.edu.ucc.arqSoft.baseService.dao.UsuarioDao;
import ar.edu.ucc.arqSoft.baseService.dto.AgregarTareaProyectoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.AgregarTareaProyectoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.AsignarUsuarioProyectoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.AsignarUsuarioProyectoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.ProyectoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.ProyectoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.model.Comentario;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Service
@Transactional
public class ProyectoService {

	@Autowired
	private ProyectoDao proyectoDao;

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private TareaDao tareaDao;

	@Autowired
	private ComentarioDao comentarioDao;

	public ProyectoResponseDto getProyectoById(Long id) throws EntityNotFoundException, BadRequestException {
		if (id <= 0) {
			throw new BadRequestException();
		}
		Proyecto proyecto = proyectoDao.load(id);

		ProyectoResponseDto response = (ProyectoResponseDto) new ModelDtoConverter().convertToDto(proyecto,
				new ProyectoResponseDto());
		return response;
	}

	public List<ProyectoResponseDto> GetByNombre(String nombre) throws BadRequestException, EntityNotFoundException {
		List<Proyecto> proyectos = proyectoDao.FindByName(nombre);

		List<ProyectoResponseDto> response = new ArrayList<ProyectoResponseDto>();
		for (Proyecto proyecto : proyectos) {
			if (proyecto.getNombre() == null) {
				throw new BadRequestException();
			}
			response.add(
					(ProyectoResponseDto) new ModelDtoConverter().convertToDto(proyecto, new ProyectoResponseDto()));
		}
		return response;
	}

	public List<ProyectoResponseDto> getAllProyectos() {
		List<Proyecto> proyectos = proyectoDao.getAll();

		List<ProyectoResponseDto> response = new ArrayList<ProyectoResponseDto>();

		for (Proyecto proyecto : proyectos) {
			response.add(
					(ProyectoResponseDto) new ModelDtoConverter().convertToDto(proyecto, new ProyectoResponseDto()));
		}

		return response;
	}

	public ProyectoResponseDto insertProyecto(ProyectoRequestDto request)
			throws BadRequestException, EntityNotFoundException {
		
		
		Proyecto proyecto = (Proyecto) new ModelDtoConverter().convertToEntity(new Proyecto(), request);
		
		try {
			proyectoDao.insert(proyecto);
		}
		catch (BadRequestException e) {
				throw new BadRequestException();
		}
		
		ProyectoResponseDto response = (ProyectoResponseDto) new ModelDtoConverter().convertToDto(proyecto, new ProyectoResponseDto());
		
		return response;

	/*	Proyecto proyecto = new Proyecto();

		proyecto.setNombre(request.getNombre());
		proyecto.setDescripcion(request.getDescripcion());
		proyecto.setFecha_inicio(request.getFecha_inicio());
		proyecto.setFecha_actualizacion(request.getFecha_actualizacion());
		proyecto.setUsuarios(usuarioDao.load(request.getId_usuario()));

		proyectoDao.insert(proyecto);

		ProyectoResponseDto response = new ProyectoResponseDto();

		response.setNombre(proyecto.getNombre());
		response.setDescripcion(proyecto.getDescripcion());
		response.setFecha_inicio(proyecto.getFecha_inicio());
		response.setFecha_actualizacion(proyecto.getFecha_actualizacion());

		return response;
*/
	}

	public AgregarTareaProyectoResponseDto addTarea(AgregarTareaProyectoRequestDto req)
			throws BadRequestException, EntityNotFoundException {
	
		Proyecto proyecto = proyectoDao.load(req.getId_proyecto());

		proyecto.setTareas(tareaDao.load(req.getId_tarea()));

/*		Comentario comentario = new Comentario();

		comentario.setDescripcion("Nuevo usuario agregado");
		comentario.setUsuario(usuarioDao.load(req.getId_usuario()));
		comentario.setTarea(null);

		comentarioDao.insert(comentario);
*/
		AgregarTareaProyectoResponseDto response = new AgregarTareaProyectoResponseDto();

		response = (AgregarTareaProyectoResponseDto) new ModelDtoConverter().convertToDto(proyecto, new AgregarTareaProyectoResponseDto());

		return response;
	}



	public AsignarUsuarioProyectoResponseDto addUsuario(AsignarUsuarioProyectoRequestDto req)
			throws BadRequestException, EntityNotFoundException {
	
		Proyecto proyecto = proyectoDao.load(req.getId_proyecto());

		proyecto.setUsuarios(usuarioDao.load(req.getId_usuario()));
		
		proyectoDao.update(proyecto);

/*		Comentario comentario = new Comentario();

		comentario.setDescripcion("Nuevo usuario agregado");
		comentario.setUsuario(usuarioDao.load(req.getId_usuario()));
		comentario.setTarea(null);

		comentarioDao.insert(comentario);
*/
		AsignarUsuarioProyectoResponseDto response = new AsignarUsuarioProyectoResponseDto();

		response = (AsignarUsuarioProyectoResponseDto) new ModelDtoConverter().convertToDto(proyecto, new AsignarUsuarioProyectoResponseDto());

		return response;
	}

}
