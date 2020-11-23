package ar.edu.ucc.arqSoft.baseService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import ar.edu.ucc.arqSoft.baseService.dto.EstadoResponseDto;

import ar.edu.ucc.arqSoft.baseService.service.EstadoService;
import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Controller
@RequestMapping("/estado")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@RequestMapping(value="/{nombre}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> lookupByNombre(@PathVariable("nombre") String nombre){
    
		try {
				List<EstadoResponseDto> dto = estadoService.GetByNombre(nombre);
				return new ResponseEntity<Object>(dto, HttpStatus.OK);
				
	}
		catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1001", "Estado no encontrado");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
			
		}	catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1002", "ID no valido");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}  
    }
}
