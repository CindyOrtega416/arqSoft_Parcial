package ar.edu.ucc.arqSoft.baseService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.TareaDao;
import ar.edu.ucc.arqSoft.baseService.dao.UsuarioDao;
import ar.edu.ucc.arqSoft.baseService.dao.ComentarioDao;
import ar.edu.ucc.arqSoft.baseService.dao.EstadoDao;
import ar.edu.ucc.arqSoft.baseService.dao.ProyectoDao;
import ar.edu.ucc.arqSoft.baseService.dto.AsignarUsuarioTareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.AsignarUsuarioTareaResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaResponseDto;
import ar.edu.ucc.arqSoft.baseService.model.Comentario;
import ar.edu.ucc.arqSoft.baseService.model.Tarea;

import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Service
@Transactional
public class TareaService {

	@Autowired
	private TareaDao tareaDao;

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private ProyectoDao proyectoDao;

	@Autowired
	private EstadoDao estadoDao;

	@Autowired
	private ComentarioDao comentarioDao;

	/*
	 * public List<TareaResponseDto> GetByTarea(String nombre_tarea) throws
	 * EntityNotFoundException, BadRequestException { List<Tarea> tareas =
	 * tareaDao.findByTarea(nombre_tarea);
	 * 
	 * List<TareaResponseDto> response = new ArrayList<TareaResponseDto>();
	 * for(Tarea tarea: tareas) { if(tarea.getId()<=0) { throw new
	 * BadRequestException(); } response.add((TareaResponseDto) new
	 * ModelDtoConverter().convertToDto(tarea,new TareaResponseDto())); } return
	 * response; }
	 */
	/*
	 * public List<TareaResponseDto> GetByProyecto(Proyecto proyecto) throws
	 * EntityNotFoundException, BadRequestException { List<Tarea> tareas =
	 * tareaDao.findByProyecto(proyecto);
	 * 
	 * List<TareaResponseDto> response = new ArrayList<TareaResponseDto>();
	 * for(Tarea tarea: tareas) { if(tarea.getId()<=0) { throw new
	 * BadRequestException(); } response.add((TareaResponseDto) new
	 * ModelDtoConverter().convertToDto(proyecto,new TareaResponseDto())); } return
	 * response; }
	 * 
	 */
	public TareaResponseDto getTareaById(Long id) throws EntityNotFoundException, BadRequestException {

		if (id <= 0) {
			throw new BadRequestException();
		}
		Tarea tarea = tareaDao.load(id);

		TareaResponseDto response = new TareaResponseDto();

		response.setNombre(tarea.getNombre());
		response.setDescripcion(tarea.getDescripcion());
		response.setProyecto(tarea.getProyecto());
		response.setEstado(tarea.getEstado());

		return response;
	}

	public List<TareaResponseDto> getAllTareas() {

		List<Tarea> tareas = tareaDao.getAll();

		List<TareaResponseDto> response = new ArrayList<TareaResponseDto>();

		for (Tarea Tarea : tareas) {

			response.add((TareaResponseDto) new ModelDtoConverter().convertToDto(Tarea, new TareaResponseDto()));
		}
		return response;

	}

	public TareaResponseDto insertTarea(TareaRequestDto request) throws BadRequestException, EntityNotFoundException {

		Tarea tarea = new Tarea();

		tarea.setNombre(request.getNombre());
		tarea.setDescripcion(request.getDescripcion());
		tarea.setProyecto(proyectoDao.load(request.getId_proyecto()));
		tarea.setEstado(estadoDao.load(request.getId_estado()));
		tarea.setFecha_inicio(request.getFecha_inicio());
		tarea.setUltima_actualizacion(request.getUltima_actualizacion());

		tareaDao.insert(tarea);

		TareaResponseDto response = new TareaResponseDto();

		response.setNombre(tarea.getNombre());
		response.setDescripcion(tarea.getDescripcion());
		response.setProyecto(tarea.getProyecto());
		response.setEstado(tarea.getEstado());
		response.setFecha_inicio(tarea.getFecha_inicio());
		response.setUltima_actualizacion(tarea.getUltima_actualizacion());

		return response;

	}

	public AsignarUsuarioTareaResponseDto addUsuario(AsignarUsuarioTareaRequestDto req)
			throws BadRequestException, EntityNotFoundException {

		Tarea tarea = tareaDao.load(req.getId_tarea());



		tarea.setUsuarios(usuarioDao.load(req.getId_usuario()));

		tareaDao.update(tarea);

/*		Comentario comentario = new Comentario();

		comentario.setDescripcion("Nuevo usuario agregado");
		comentario.setUsuario(usuarioDao.load(null));
		comentario.setTarea(tareaDao.load(req.getId()));

		comentarioDao.insert(comentario);
*/

		AsignarUsuarioTareaResponseDto response = new AsignarUsuarioTareaResponseDto();

		response = (AsignarUsuarioTareaResponseDto) new ModelDtoConverter().convertToDto(tarea, new AsignarUsuarioTareaResponseDto());

		return response;
	}

	public TareaResponseDto cambioEstado(TareaRequestDto request, Long id)
			throws BadRequestException, EntityNotFoundException {

		Tarea tarea = tareaDao.load(request.getId());

		tarea.setEstado(estadoDao.load(id));

		tareaDao.saveOrUpdate(tarea);

		Comentario comentario = new Comentario();

		comentario.setDescripcion("Hubo un cambio en el estado de la tarea");
		comentario.setUsuario(usuarioDao.load(null));
		comentario.setTarea(tareaDao.load(request.getId()));

		comentarioDao.insert(comentario);

		TareaResponseDto response = new TareaResponseDto();

		response = (TareaResponseDto) new ModelDtoConverter().convertToDto(tarea, new TareaResponseDto());

		return response;

	}

	/*
	 * public TareaResponseDto addComentario (TareaRequestDto request, Long
	 * id_comentario)throws BadRequestException, EntityNotFoundException {
	 * 
	 * Tarea tarea = tareaDao.load(request.getId());
	 * 
	 * if(id_comentario<=0) { throw new BadRequestException(); }
	 * tarea.setComentarios(comentarioDao.load(id_comentario));
	 * 
	 * tareaDao.update(tarea);
	 * 
	 * Tarea tarea = tareaDao.load(id_tarea);
	 * 
	 * tarea.setUsuarios(usuarioDao.load(request.getId_tarea()));
	 * 
	 * TareaResponseDto response = new TareaResponseDto();
	 * 
	 * response = (TareaResponseDto) new ModelDtoConverter().convertToDto(tarea,new
	 * TareaResponseDto());
	 * 
	 * return response; }
	 */
}
