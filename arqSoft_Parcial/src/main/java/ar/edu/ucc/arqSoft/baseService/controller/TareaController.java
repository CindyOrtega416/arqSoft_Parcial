package ar.edu.ucc.arqSoft.baseService.controller;


import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaResponseDto;
import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
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
public class TareaController {
	
	
	@Autowired
	private TareaService tareaService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code= HttpStatus.CREATED)
		public @ResponseBody TareaResponseDto register(@RequestBody TareaRequestDto request) {
					try {
							TareaResponseDto dto = (TareaResponseDto) tareaService.insertTarea(request);
							return dto;
					} catch (EntityNotFoundException e) {
							throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada", e);
					} catch (BadRequestException e) {
							throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID no válido", e);
					}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code= HttpStatus.CREATED)
	public @ResponseBody TareaResponseDto cambioEstado(@RequestBody TareaRequestDto request, @PathVariable("id") Long id){
			try {
				TareaResponseDto dto =(TareaResponseDto) tareaService.cambioEstado(request,id);		
				return dto;
			} catch (EntityNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada", e);
			} catch (BadRequestException e) { 
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID no válido", e);
			}
	}
	
	@RequestMapping(value="/addUsuario/{id]}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code= HttpStatus.CREATED)
	public @ResponseBody TareaResponseDto addUsuario(@RequestBody TareaRequestDto request, @PathVariable("id") Long id){
			try {
				TareaResponseDto dto =(TareaResponseDto) tareaService.addUsuario(request,id);		
				return dto;
			} catch (EntityNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada", e);
			} catch (BadRequestException e) { 
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID no válido", e);
			}
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{nombre}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code= HttpStatus.CREATED)
	public @ResponseBody List<TareaResponseDto> getbyName(@PathVariable("nombre") String nombre){
			try {
				TareaResponseDto dto =(TareaResponseDto) tareaService.GetByNombre(nombre);		
				return (List<TareaResponseDto>) dto;
			} catch (EntityNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada", e);
			} catch (BadRequestException e) { 
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID no válido", e);
			}
	}
}