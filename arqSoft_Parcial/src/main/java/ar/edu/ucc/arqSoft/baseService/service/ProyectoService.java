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
import ar.edu.ucc.arqSoft.baseService.dto.ProyectoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.ProyectoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.UsuarioRequestDto;
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
	
	public ProyectoResponseDto insertProyecto (ProyectoRequestDto request) throws BadRequestException, EntityNotFoundException {
		
		Proyecto proyecto= new Proyecto();
		
		proyecto.setNombre(request.getNombre());
		proyecto.setDescripcion(request.getDescripcion());
		proyecto.setFecha_inicio(request.getFecha_inicio());
		proyecto.setFecha_actualizacion(request.getFecha_actualizacion());
		
		proyectoDao.insert(proyecto);
		
		
	
		ProyectoResponseDto response = new ProyectoResponseDto();
		
		response.setNombre(proyecto.getNombre());
		response.setDescripcion(proyecto.getDescripcion());
		response.setFecha_inicio(proyecto.getFecha_inicio());
		response.setFecha_actualizacion(proyecto.getFecha_actualizacion());
		
		return response; 
		
	}

	
	
	public ProyectoResponseDto addTarea (TareaRequestDto req, Long id_proyecto) throws BadRequestException, EntityNotFoundException{
		if(id_proyecto<=0)
		{
			throw new BadRequestException();
		}
		Proyecto proyecto = proyectoDao.load(id_proyecto);
		
		proyecto.setTareas(tareaDao.load(req.getId()));
		
		Comentario comentario= new Comentario();
		
		comentario.setDescripcion("Nueva tarea agregada al proyecto");
		comentario.setUsuario(usuarioDao.load(null));
		comentario.setTarea(tareaDao.load(req.getId()));
		
		comentarioDao.insert(comentario);
		
		ProyectoResponseDto response = new ProyectoResponseDto();
		
		response = (ProyectoResponseDto) new ModelDtoConverter().convertToDto(proyecto,new ProyectoResponseDto());
		
		return response;
	}
	
	
	
	public List<ProyectoResponseDto> GetByNombre(String nombre) throws BadRequestException, EntityNotFoundException{
		List<Proyecto> proyectos = proyectoDao.FindByName(nombre);
		
		List<ProyectoResponseDto> response = new ArrayList<ProyectoResponseDto>();
		for(Proyecto proyecto: proyectos) {
			if(proyecto.getNombre()== null)
			{
				throw new BadRequestException();
			}
		response.add((ProyectoResponseDto) new ModelDtoConverter().convertToDto(proyecto,new ProyectoResponseDto()));
		}
		return response;
	}
	
	
	
	public ProyectoResponseDto addUsuario (UsuarioRequestDto req, Long id_proyecto)throws BadRequestException, EntityNotFoundException {
		if(id_proyecto<=0)
		{
			throw new BadRequestException();
		}
	
		Proyecto proyecto = proyectoDao.load(id_proyecto);
		
		proyecto.setUsuarios(usuarioDao.load(req.getId()));
		
		Comentario comentario= new Comentario();
		
		comentario.setDescripcion("Nuevo usuario agregado");
		comentario.setUsuario(usuarioDao.load(null));
		comentario.setTarea(null);
		
		comentarioDao.insert(comentario);
		
		ProyectoResponseDto response = new ProyectoResponseDto();
		
		response = (ProyectoResponseDto) new ModelDtoConverter().convertToDto(proyecto,new ProyectoResponseDto());
		
		return response;
	}

	
	
}
