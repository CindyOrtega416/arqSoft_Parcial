package ar.edu.ucc.arqSoft.baseService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.EstadoDao;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoResponseDto;
import ar.edu.ucc.arqSoft.baseService.model.Estado;
import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Service
@Transactional
public class EstadoService {
	
	@Autowired
	private EstadoDao estadoDao;

	public EstadoResponseDto getEstadoById(Long id) throws EntityNotFoundException {
		Estado estado = estadoDao.load(id);
				
		EstadoResponseDto response = (EstadoResponseDto) new ModelDtoConverter().convertToDto(estado, new EstadoResponseDto());	
		return response;
	}
	
	public List<EstadoResponseDto> getAllEstados() {
		List<Estado> estados = estadoDao.getAll();
		
		List<EstadoResponseDto> response = new ArrayList<EstadoResponseDto>();
		 
		for (Estado estado : estados) {
			response.add((EstadoResponseDto) new ModelDtoConverter().convertToDto(estado, new EstadoResponseDto()));
		}
		
		return response;
	}
	
	
	public EstadoResponseDto insertEstado(EstadoRequestDto request) throws BadRequestException {
		
		Estado estado = (Estado) new ModelDtoConverter().convertToEntity(new Estado(), request);
		
		estadoDao.insert(estado);
		
		EstadoResponseDto response = (EstadoResponseDto) new ModelDtoConverter().convertToDto(estado, new EstadoResponseDto());	
		
		return response;
	}
	
}