package ar.edu.ucc.arqSoft.baseService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.TareaDao;
import ar.edu.ucc.arqSoft.baseService.dao.UsuarioDao;
import ar.edu.ucc.arqSoft.baseService.dao.ComentarioDao;
import ar.edu.ucc.arqSoft.baseService.dao.EstadoDao;
import ar.edu.ucc.arqSoft.baseService.dao.ProyectoDao;
import ar.edu.ucc.arqSoft.baseService.dto.ComentarioRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.ComentarioResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.UsuarioRequestDto;
import ar.edu.ucc.arqSoft.baseService.model.Comentario;
import ar.edu.ucc.arqSoft.baseService.model.Estado;
import ar.edu.ucc.arqSoft.baseService.model.Tarea;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
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


/*	public List<TareaResponseDto> GetByTarea(String nombre_tarea) throws EntityNotFoundException, BadRequestException {
		List<Tarea> tareas = tareaDao.findByTarea(nombre_tarea);
		
		List<TareaResponseDto> response = new ArrayList<TareaResponseDto>();
		for(Tarea tarea: tareas) 
		{
			if(tarea.getId()<=0)
			{
				throw new BadRequestException();
			}
		response.add((TareaResponseDto) new ModelDtoConverter().convertToDto(tarea,new TareaResponseDto()));
		}
		return response;
	}
*/	
	/*public List<TareaResponseDto> GetByProyecto(Proyecto proyecto) throws EntityNotFoundException, BadRequestException {
		List<Tarea> tareas = tareaDao.findByProyecto(proyecto);
		
		List<TareaResponseDto> response = new ArrayList<TareaResponseDto>();
		for(Tarea tarea: tareas) 
		{
			if(tarea.getId()<=0)
			{
				throw new BadRequestException();
			}
		response.add((TareaResponseDto) new ModelDtoConverter().convertToDto(proyecto,new TareaResponseDto()));
		}
		return response;
	}
	
*/	
/*	public TareaResponseDto getTareaById(Long id) throws EntityNotFoundException, BadRequestException {
		if (id <= 0) {
			throw new BadRequestException();
		}
		Tarea tarea = tareaDao.load(id);
		tareaDao.insert(tarea);

		TareaResponseDto response = new TareaResponseDto();
		
		return response;

	//	return taskResponseGenerator(response, task);
		
		if (id <= 0) {
			throw new BadRequestException();		//no aceptar id<=0
		}
		Comentario comentario=comentarioDao.load(id);
	
		ComentarioResponseDto dto=new ComentarioResponseDto();
		dto.setDescripcion(comentario.getDescripcion());
		dto.setFecha(comentario.getFecha());
		dto.setNombre(comentario.getNombre());
		dto.setTarea(comentario.getTarea());
		dto.setUsuario(comentario.getUsuario());
		
	
		return dto;
		
	}
*/

	
	
	public TareaResponseDto insertTarea (TareaRequestDto request) throws BadRequestException, EntityNotFoundException{
		
		Tarea tarea = new Tarea();
		
		tarea.setNombre(request.getNombre());
		tarea.setDescripcion(request.getDescripcion());
		tarea.setProyecto(proyectoDao.load(request.getId_proyecto()));
		tarea.setEstado(estadoDao.load(request.getId_estado()));

		
		tareaDao.insert(tarea);
		
		TareaResponseDto response = new TareaResponseDto();
		
		response.setNombre(tarea.getNombre());
		response.setDescripcion(tarea.getDescripcion());
		response.setProyecto(tarea.getProyecto());
		response.setEstado(tarea.getEstado());
		
		
		return response;
		
	}

public TareaResponseDto addUsuario(TareaRequestDto req, Long id_usuario) throws BadRequestException, EntityNotFoundException {
	
	Tarea tarea = tareaDao.load(req.getId());
	
    if(id_usuario<=0)
    {
        throw new BadRequestException();
    }
    
    tarea.setUsuarios(usuarioDao.load(id_usuario));
	
	tareaDao.update(tarea);
	
	Comentario comentario= new Comentario();
	
	comentario.setDescripcion("Nuevo usuario agregado");
	comentario.setUsuario(usuarioDao.load(null));
	comentario.setTarea(tareaDao.load(req.getId()));
	
	comentarioDao.insert(comentario);
	
    TareaResponseDto response = new TareaResponseDto();

    response = (TareaResponseDto) new ModelDtoConverter().convertToDto(tarea,new TareaResponseDto());

    return response;
}

public TareaResponseDto cambioEstado(TareaRequestDto request, Long id ) throws BadRequestException, EntityNotFoundException
{
	
	Tarea tarea = tareaDao.load(request.getId());
	
	tarea.setEstado(estadoDao.load(id));
	
	tareaDao.saveOrUpdate(tarea);
	
	Comentario comentario= new Comentario();
	
	comentario.setDescripcion("Hubo un cambio en el estado de la tarea");
	comentario.setUsuario(usuarioDao.load(null));
	comentario.setTarea(tareaDao.load(request.getId()));
	
	comentarioDao.insert(comentario);
	
	TareaResponseDto response = new TareaResponseDto();
	
	response = (TareaResponseDto) new ModelDtoConverter().convertToDto(tarea,new TareaResponseDto());
	
	return response;
	
}

/*public TareaResponseDto addComentario (TareaRequestDto request, Long id_comentario)throws BadRequestException, EntityNotFoundException {
    
    Tarea tarea = tareaDao.load(request.getId());
    
    if(id_comentario<=0)
    {
        throw new BadRequestException();
    }
 	tarea.setComentarios(comentarioDao.load(id_comentario));
	
	tareaDao.update(tarea);
	
    Tarea tarea = tareaDao.load(id_tarea);

    tarea.setUsuarios(usuarioDao.load(request.getId_tarea()));

    TareaResponseDto response = new TareaResponseDto();

    response = (TareaResponseDto) new ModelDtoConverter().convertToDto(tarea,new TareaResponseDto());

    return response;
	}*/
}
