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
	
	  @RequestMapping(method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody List<EstadoResponseDto> getAllEstados()
	    {
	        return estadoService.getAllEstados();
	    }

	  
	  
	    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody ResponseEntity<Object> lookupEstadoById(@PathVariable("id") Long id) throws EntityNotFoundException
	    {
	        try {
				EstadoResponseDto dto = estadoService.getEstadoById(id);
				return new ResponseEntity<Object>(dto, HttpStatus.OK);
				
			} catch (EntityNotFoundException e) {
				GenericExceptionDto exDto = new GenericExceptionDto("404", "No se encontró el estado");
				return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
				
			} catch (BadRequestException e) {
				GenericExceptionDto exDto = new GenericExceptionDto("400", "El id ingresado no es válido");
				return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
				
			}catch (Exception e) {
				GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
				return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
			}
	    }
	    
}
