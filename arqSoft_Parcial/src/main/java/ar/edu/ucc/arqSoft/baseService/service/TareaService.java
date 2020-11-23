package ar.edu.ucc.arqSoft.baseService.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.ComentarioDao;
import ar.edu.ucc.arqSoft.baseService.dao.EstadoDao;
import ar.edu.ucc.arqSoft.baseService.dao.ProyectoDao;
import ar.edu.ucc.arqSoft.baseService.dao.TareaDao;
import ar.edu.ucc.arqSoft.baseService.dao.UsuarioDao;
import ar.edu.ucc.arqSoft.baseService.dto.CambioTareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaCambioEstadoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaResponseDto;
import ar.edu.ucc.arqSoft.baseService.exception.TareaCerradaException;
import ar.edu.ucc.arqSoft.baseService.model.Comentario;
import ar.edu.ucc.arqSoft.baseService.model.Tarea;
import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Service
@Transactional
public class TareaService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private TareaDao tareaDao;
	
	@Autowired
	private ProyectoDao proyectoDao;
	
	@Autowired
	private EstadoDao estadoDao;
	
	@Autowired
	private ComentarioDao comentarioDao;
	

	public TareaResponseDto insertTarea (TareaRequestDto request) throws BadRequestException, EntityNotFoundException{
		
		Tarea tarea = new Tarea();
		
		tarea.setNombre_tarea(request.getNombre_tarea());
		tarea.setDescripcion(request.getDescripcion());
		tarea.setProyecto(proyectoDao.load(request.getId_proyecto()));
		tarea.setEstado(estadoDao.load(request.getId_estado()));
		
		tareaDao.insert(tarea);
		
		TareaResponseDto response = new TareaResponseDto();
		
		response.setNombre_tarea(tarea.getNombre_tarea());
		response.setDescripcion(tarea.getDescripcion());
		response.setProyecto(tarea.getProyecto());
		response.setEstado(tarea.getEstado());
		
		return response;	
			
	}
	
	public TareaResponseDto getTareaById (Long id) throws EntityNotFoundException, BadRequestException {
		
		if (id <= 0) {
			throw new BadRequestException();
		}
		
		Tarea tarea = tareaDao.load(id);
		TareaResponseDto dto=new TareaResponseDto();
		dto.setNombre_tarea(tarea.getNombre_tarea());
		dto.setDescripcion(tarea.getDescripcion());
		dto.setProyecto(tarea.getProyecto());
		dto.setEstado(tarea.getEstado());
		for(Comentario comentario : tarea.getComentarios())
		{
			dto.addComentarios(comentario);
		}
		return dto;
		
	}
	
	public List<TareaResponseDto> getAllTareasByNombre(String nombre) {
		List<Tarea> tareas = tareaDao.getAll();
		
		List<TareaResponseDto> response = new ArrayList<TareaResponseDto>();
		 
		for (Tarea tarea : tareas) {
			if(tarea.getNombre_tarea()==nombre)
			response.add((TareaResponseDto) new ModelDtoConverter().convertToDto(tarea, new TareaResponseDto()));
		}
		
		return response;
	}
	
	
	public List<TareaResponseDto> getAllTareas() {
		List<Tarea> tareas = tareaDao.getAll();
		
		List<TareaResponseDto> response = new ArrayList<TareaResponseDto>();
		 
		for (Tarea tarea : tareas) {
			response.add((TareaResponseDto) new ModelDtoConverter().convertToDto(tarea, new TareaResponseDto()));
		}
		
		return response;
	}
	
	public TareaResponseDto changeUsuario (CambioTareaRequestDto dto) throws EntityNotFoundException,TareaCerradaException {
		{
			Tarea tarea=tareaDao.load(dto.getId_tarea());
			
			if(tareaDao.load(dto.getId_tarea()).getEstado().getNombre()=="Cerrado")
			{
				throw new TareaCerradaException();
			}
			
			Comentario comentario=new Comentario();
			comentario.setDescripcion("Se cambio el usuario de "+tarea.getUsuarios()+" a "+usuarioDao.load(dto.getId_usuario())+".");
			comentario.setTarea(tarea);
			comentario.setNombre("Cambio de Usuario");
			tarea.setUsuarios(usuarioDao.load(dto.getId_usuario()));
			tarea.addComentario(comentario);
			tareaDao.update(tarea);
			TareaResponseDto response=new TareaResponseDto();
			
			response.setNombre_tarea(tarea.getNombre_tarea());
			response.setUsuario(tarea.getUsuarios());
			response.setProyecto(tarea.getProyecto());
			
			
			return response;
		}
	}
	
	public TareaResponseDto changeEstado (TareaCambioEstadoRequestDto dto) throws EntityNotFoundException,TareaCerradaException {
		{
			Tarea tarea=tareaDao.load(dto.getId_tarea());
			
			if(tarea.getEstado().getNombre()=="Cerrado")
			{
				if(estadoDao.load(dto.getId_estado()).getNombre()=="Abierto")
				{
					tarea.setUltima_actualizacion(null);
				}
				else
				{
					throw new TareaCerradaException();
				}
			}
			
			Comentario comentario=new Comentario();
			comentario.setFecha(Calendar.getInstance().getTime());
			comentario.setDescripcion("Se cambio el estado de "+tarea.getEstado().getNombre()+" a "+estadoDao.load(dto.getId_estado()).getNombre()+".");
			comentario.setTarea(tarea);
			comentario.setNombre("Cambio de Estado");
			tarea.setEstado(estadoDao.load(dto.getId_estado()));
			tarea.addComentario(comentario);
			if(tarea.getEstado().getNombre()=="Cerrado")
			{
				tarea.setUltima_actualizacion(Calendar.getInstance().getTime());
			}
			tareaDao.update(tarea);
			TareaResponseDto response=new TareaResponseDto();
			
			response.setNombre_tarea(tarea.getNombre_tarea());
			response.setUsuario(tarea.getUsuarios());
			response.setProyecto(tarea.getProyecto());
			response.setEstado(tarea.getEstado());
			
			
			return response;
		}
	}
}
