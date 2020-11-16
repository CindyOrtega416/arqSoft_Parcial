package ar.edu.ucc.arqSoft.baseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.SocioDao;
import ar.edu.ucc.arqSoft.baseService.dto.SocioRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.SocioResponseDto;
import ar.edu.ucc.arqSoft.baseService.model.Socio;
import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;


@Service
@Transactional
public class SocioService {
	
	@Autowired
	private SocioDao socioDao;

	
	public SocioResponseDto insertSocio(SocioRequestDto request) throws BadRequestException{
		
		Socio socio = (Socio) new ModelDtoConverter().convertToEntity(new Socio(), request);
		
		socioDao.insert(socio);
		
		SocioResponseDto response = (SocioResponseDto) new ModelDtoConverter().convertToDto(socio, new SocioResponseDto());	
		
		return response;
	}

}