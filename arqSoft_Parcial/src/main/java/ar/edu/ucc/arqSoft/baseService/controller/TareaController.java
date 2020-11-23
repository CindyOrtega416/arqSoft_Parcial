package ar.edu.ucc.arqSoft.baseService.controller;

import ar.edu.ucc.arqSoft.baseService.dao.TareaDao;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaResponseDto;
import ar.edu.ucc.arqSoft.baseService.model.Tarea;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.baseService.service.TareaService;
import ar.edu.ucc.arqSoft.common.dto.ModelDtoConverter;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;


@Controller
@RequestMapping("/tarea")
public class TareaController {
	
	
	@Autowired
	private TareaService tareaService;
	 
	@RequestMapping(value="/{nombre}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> lookupByNombre(@PathVariable("nombre") String nombre){
    
		try {
				List<TareaResponseDto> dto = tareaService.GetByNombre(nombre);
				return new ResponseEntity<Object>(dto, HttpStatus.OK);
				
	}catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1001", "No se encontró la tarea");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
			
		}	catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1002", "Mal ingresado");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		
		}
         
         
    }
	
	@RequestMapping(value="/{nombre_tarea}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> lookupByTarea(@PathVariable("nombre_tarea") String nombre_tarea){
    
		try {
				List<TareaResponseDto> dto = tareaService.GetByTarea(nombre_tarea);
				return new ResponseEntity<Object>(dto, HttpStatus.OK);
				
	}
		catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1001", "No se encontró la tarea");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
			
		}	catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1002", "Mal ingresado");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}  
    }
	
	@RequestMapping(value="/{proyecto}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> lookupByProyecto(@PathVariable("proyecto") Proyecto proyecto){
    
		try {
				List<TareaResponseDto> dto = tareaService.GetByProyecto(proyecto);
				return new ResponseEntity<Object>(dto, HttpStatus.OK);
				
	}catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1001", "No se encontró la tarea");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
			
		}	catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1002", "Mal ingresado");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		
		}
         
         
    }


}