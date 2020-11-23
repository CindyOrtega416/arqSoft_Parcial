package ar.edu.ucc.arqSoft.baseService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.TareaDao;
import ar.edu.ucc.arqSoft.baseService.dao.UsuarioDao;
import ar.edu.ucc.arqSoft.baseService.dao.EstadoDao;
import ar.edu.ucc.arqSoft.baseService.dao.ProyectoDao;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaResponseDto;
import ar.edu.ucc.arqSoft.baseService.model.Estado;
import ar.edu.ucc.arqSoft.baseService.model.Tarea;
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
	private ProyectoDao proyectoDado;
	
	@Autowired
	private EstadoDao estadoDao;

	public List<TareaResponseDto> GetByNombre(String nombre) throws EntityNotFoundException, BadRequestException {
		List<Tarea> tareas = tareaDao.FindByName(nombre);
		
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
	
	public List<TareaResponseDto> GetByTarea(String nombre_tarea) throws EntityNotFoundException, BadRequestException {
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
	
	public List<TareaResponseDto> GetByProyecto(Proyecto proyecto) throws EntityNotFoundException, BadRequestException {
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
	
	
	
public TareaResponseDto insertTarea (TareaRequestDto request) throws BadRequestException{
		
		Tarea tarea = new Tarea();
		
		tarea.setNombre_tarea(request.getNombre_tarea());
		tarea.setDescripcion(request.getDescripcion());
		//tarea.setUsuarios(request.getId_usuario());
		tarea.setUsuarios(usuarioDao.load(request.getId_usuario()));
		tarea.setProyecto(proyectoDado.load(request.getId_proyecto()));
		tarea.setEstado(estadoDao.load(request.getId_estado()));

		
		tareaDao.insert(tarea);
		
		TareaResponseDto response = new TareaResponseDto();
		
		response.setNombre_tarea(tarea.getNombre_tarea());
		response.setDescripcion(tarea.getDescripcion());
		response.setUsuario(tarea.getUsuarios());
		response.setProyecto(tarea.getProyecto());
		response.setEstado(tarea.getEstado());
		
		
		return response;
		
	}
	
}