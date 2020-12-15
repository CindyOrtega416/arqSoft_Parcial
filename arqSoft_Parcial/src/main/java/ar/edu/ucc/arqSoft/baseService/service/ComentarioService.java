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

	public ComentarioResponseDto getComentarioById(Long id) throws EntityNotFoundException, BadRequestException {

		if (id <= 0) {
			throw new BadRequestException(); // no aceptar id<=0
		}
		Comentario comentario = comentarioDao.load(id);

		ComentarioResponseDto response = new ComentarioResponseDto();

		response.setDescripcion(comentario.getDescripcion());
		response.setFecha(comentario.getFecha());
		response.setNombre(comentario.getNombre());
		response.setTarea(comentario.getTarea());
		response.setUsuario(comentario.getUsuario());

		return response;

	}

	public ComentarioResponseDto insertComentario(ComentarioRequestDto request)
			throws EntityNotFoundException, BadRequestException, TareaCerradaException {

		if (tareaDao.load(request.getId_tarea()).getEstado().getNombre() == "Cerrado") {
			throw new TareaCerradaException(); // no aceptar comentarios si tarea=cerrada
		}

		Comentario comentario = new Comentario();

		comentario.setNombre(request.getNombre());
		comentario.setDescripcion(request.getDescripcion());
		comentario.setUsuario(usuarioDao.load(request.getId_usuario()));
		comentario.setTarea(tareaDao.load(request.getId_tarea()));
		comentario.setFecha(Calendar.getInstance().getTime());

		comentarioDao.insert(comentario);

		ComentarioResponseDto response = new ComentarioResponseDto();

		response.setNombre(comentario.getNombre());
		response.setUsuario(comentario.getUsuario());
		response.setDescripcion(comentario.getDescripcion());
		response.setFecha(comentario.getFecha());
		response.setTarea(comentario.getTarea());

		return response;

	}

	public List<ComentarioResponseDto> getAllComentariosFromTarea(Long Id_tarea) { // mostrar todos los comentarios en
																					// tarea
		List<Comentario> comentarios = comentarioDao.getAll();

		List<ComentarioResponseDto> response = new ArrayList<ComentarioResponseDto>();

		for (Comentario comentario : comentarios) {
			if (comentario.getTarea().getId() == Id_tarea) {
				response.add((ComentarioResponseDto) new ModelDtoConverter().convertToDto(comentario,
						new ComentarioResponseDto()));

			}

		}

		return response;
	}

	public List<ComentarioResponseDto> getAllComentarios() { // mostrar todos los comentarios
		List<Comentario> comentarios = comentarioDao.getAll();

		List<ComentarioResponseDto> response = new ArrayList<ComentarioResponseDto>();

		for (Comentario comentario : comentarios) {

			response.add((ComentarioResponseDto) new ModelDtoConverter().convertToDto(comentario,
					new ComentarioResponseDto()));

		}

		return response;
	}

}