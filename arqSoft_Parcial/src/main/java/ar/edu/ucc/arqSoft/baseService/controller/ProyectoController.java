package ar.edu.ucc.arqSoft.baseService.controller;

import ar.edu.ucc.arqSoft.baseService.dao.TareaDao;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.ProyectoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.ProyectoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaResponseDto;
import ar.edu.ucc.arqSoft.baseService.model.Tarea;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.baseService.service.ProyectoService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;


@Controller
@RequestMapping("/tarea")
public class ProyectoController {
	
	
	@Autowired
	private ProyectoService proyectoService;
	 
	@RequestMapping(value="/{nombre}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> lookupByNombre(@PathVariable("nombre") String nombre){
    
		try {
				List<ProyectoResponseDto> dto = proyectoService.GetByNombre(nombre);
				return new ResponseEntity<Object>(dto, HttpStatus.OK);
				
	}		catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1001", "No se encontró la tarea");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
			
		}	catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1002", "Mal ingresado");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);		
		}
    }
	
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ProyectoResponseDto saveProyecto(@RequestBody ProyectoRequestDto request) {
		
		return proyectoService.insertProyecto(request);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ProyectoResponseDto addUsuario2Proyecto(@RequestBody TareaRequestDto request, Long id_proyecto ) {
		
		return proyectoService.addTarea(request, id_proyecto);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ProyectoResponseDto addTarea2Proyecto(@RequestBody TareaRequestDto request, Long id_proyecto ) {
		
		return proyectoService.addUsuario(request, id_proyecto);
	}
	
}



