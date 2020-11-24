package ar.edu.ucc.arqSoft.baseService.controller;


import ar.edu.ucc.arqSoft.baseService.dto.ProyectoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.ProyectoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.UsuarioRequestDto;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.baseService.service.ProyectoService;
import ar.edu.ucc.arqSoft.baseService.service.TareaService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;


@Controller
	@RequestMapping("/tarea")
public class ProyectoController {
	
	
	@Autowired
	private TareaService tareaService;
	
	@Autowired
	private ProyectoService proyectoService;
	 
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
         
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code= HttpStatus.CREATED)
		public @ResponseBody ProyectoResponseDto insertProyecto(@RequestBody ProyectoRequestDto request) {
		
					try {
							ProyectoResponseDto dto = (ProyectoResponseDto) proyectoService.insertProyecto(request);
							return dto;
							
					} catch (EntityNotFoundException e) {
							throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el proyecto", e);
							
					} catch (BadRequestException e) {
							throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido", e);
					}
	}
	
	
	@RequestMapping(value="/addTarea/{id]}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code= HttpStatus.CREATED)
	public @ResponseBody ProyectoResponseDto addTarea(@RequestBody TareaRequestDto request, @PathVariable("id") Long id){
		
			try {
				ProyectoResponseDto dto =(ProyectoResponseDto) proyectoService.addTarea(request, id);		
				return dto;
				
			} catch (EntityNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la tarea", e);
				
			} catch (BadRequestException e) { 
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido", e);
			}
	}
	
	
	@RequestMapping(value="/addUsuario/{id]}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code= HttpStatus.CREATED)
	public @ResponseBody ProyectoResponseDto addUsuario(@RequestBody UsuarioRequestDto request, @PathVariable("id") Long id){
			try {
				ProyectoResponseDto dto =(ProyectoResponseDto) proyectoService.addUsuario(request, id);		
				return dto;
				
			} catch (EntityNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la tarea", e);
			} catch (BadRequestException e) { 
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido", e);
			}
	}
	
}