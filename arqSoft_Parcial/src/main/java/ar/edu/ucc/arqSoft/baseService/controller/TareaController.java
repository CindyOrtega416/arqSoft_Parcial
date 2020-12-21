package ar.edu.ucc.arqSoft.baseService.controller;

import ar.edu.ucc.arqSoft.baseService.dto.AsignarUsuarioProyectoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.AsignarUsuarioProyectoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.AsignarUsuarioTareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.AsignarUsuarioTareaResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaResponseDto;
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

import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;

@Controller
@RequestMapping("/tarea")
public class TareaController {

	@Autowired
	private TareaService tareaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> lookupTareaById(@PathVariable("id") Long id) {
		try {
			TareaResponseDto dto = tareaService.getTareaById(id);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("404", "No se encontró la tarea buscada");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);

		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "El id ingresado no es válido");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)

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

	@RequestMapping(value = "/cambiarEstadoDeTarea/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> cambioEstado(@PathVariable("id") TareaRequestDto request,
			@RequestBody Long id) {
		try {
			TareaResponseDto dto = tareaService.cambioEstado(request, id);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("404", "La tarea no se encontró");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);

		} catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("400", "Error en la solicitud");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);

		}

	}

	@RequestMapping(value = "/addUsuario/",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AsignarUsuarioTareaResponseDto addUsuario(@RequestBody AsignarUsuarioTareaRequestDto request) {
		try {
			AsignarUsuarioTareaResponseDto dto = (AsignarUsuarioTareaResponseDto) tareaService.addUsuario(request);
			return dto;

		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la tarea", e);

		} catch (BadRequestException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido", e);
		}
	
	}

}