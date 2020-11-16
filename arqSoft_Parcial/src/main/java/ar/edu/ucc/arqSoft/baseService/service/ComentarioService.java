package ar.edu.ucc.arqSoft.baseService.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.ComentarioDao;
import ar.edu.ucc.arqSoft.baseService.dao.TareaDao;
import ar.edu.ucc.arqSoft.baseService.dao.UsuarioDao;
import ar.edu.ucc.arqSoft.baseService.dto.ComentarioRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.ComentarioResponseDto;
import ar.edu.ucc.arqSoft.baseService.exception.TareaCerradaException;
import ar.edu.ucc.arqSoft.baseService.model.Comentario;
import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Service
@Transactional
public class ComentarioService {
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private TareaDao tareaDao;
		
	@Autowired
	private ComentarioDao comentarioDao;
	
	public ComentarioResponseDto insertComentario (ComentarioRequestDto dto) throws EntityNotFoundException, BadRequestException, TareaCerradaException {
		if(tareaDao.load(dto.getId_tarea()).getEstado().getNombre()=="Cerrado")
		{
			throw new TareaCerradaException();		//no aceptar comentarios si tarea=cerrada
		}
		
		Comentario comentario = new Comentario();
		comentario.setNombre(dto.getNombre());
		comentario.setDescripcion(dto.getDescripcion());
		comentario.setUsuario(usuarioDao.load(dto.getId_usuario()));
		comentario.setTarea(tareaDao.load(dto.getId_tarea()));
		comentario.setFecha(Calendar.getInstance().getTime());
		
		comentarioDao.insert(comentario);
		
		ComentarioResponseDto response=new ComentarioResponseDto();
		
		response.setNombre(comentario.getNombre());
		response.setId_usuario(comentario.getUsuario().getId());
		response.setDescripcion(comentario.getDescripcion());
		response.setFecha(comentario.getFecha());
		
		return response;
		
	}
	
	public ComentarioResponseDto getComentarioById (Long id) throws EntityNotFoundException, BadRequestException {
		
		if (id <= 0) {
			throw new BadRequestException();		//no aceptar id<=0
		}
		Comentario comentario=comentarioDao.load(id);
	
		ComentarioResponseDto dto=new ComentarioResponseDto();
		dto.setDescripcion(comentario.getDescripcion());
		dto.setFecha(comentario.getFecha());
		dto.setNombre(comentario.getNombre());
		dto.setId_tarea(comentario.getTarea().getId());
		dto.setId_usuario(comentario.getUsuario().getId());
		
	
		return dto;
		
	}
	public List<ComentarioResponseDto> getAllComentariosFromTarea(Long Id_tarea) {		//mostrar todos los comentarios en tarea
		List<Comentario> comentarios = comentarioDao.getAll();
		
		List<ComentarioResponseDto> response = new ArrayList<ComentarioResponseDto>();
		 
		for (Comentario comentario : comentarios) {
			if(comentario.getTarea().getId()==Id_tarea)
			{
				response.add((ComentarioResponseDto) new ModelDtoConverter().convertToDto(comentario, new ComentarioResponseDto()));
	
			}
			
		}
		
		return response;
	}
	
	public List<ComentarioResponseDto> getAllComentarios() {		//mostrar todos los comentarios
		List<Comentario> comentarios = comentarioDao.getAll();
		
		List<ComentarioResponseDto> response = new ArrayList<ComentarioResponseDto>();
		 
		for (Comentario comentario : comentarios) {
			
			
				response.add((ComentarioResponseDto) new ModelDtoConverter().convertToDto(comentario, new ComentarioResponseDto()));
	
			
			
		}
		
		return response;
	}
	
	
	
}