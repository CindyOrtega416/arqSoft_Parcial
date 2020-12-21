package ar.edu.ucc.arqSoft.baseService.controller;

import ar.edu.ucc.arqSoft.baseService.dto.AgregarTareaProyectoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.AgregarTareaProyectoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.AsignarUsuarioProyectoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.AsignarUsuarioProyectoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.ProyectoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.ProyectoResponseDto;
import ar.edu.ucc.arqSoft.baseService.dto.TareaRequestDto;
import ar.edu.ucc.arqSoft.baseService.service.ProyectoService;
import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;
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

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {

	@Autowired
	private ProyectoService proyectoService;

	@RequestMapping(value = "/getAll/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProyectoResponseDto> getAllProyectos() {

		return proyectoService.getAllProyectos(); // traer todos los proyectos
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public @ResponseBody List<ProyectoResponseDto> getbyName(@PathVariable("nombre") String nombre) {
		try {
			ProyectoResponseDto dto = (ProyectoResponseDto) proyectoService.GetByNombre(nombre);
			return (List<ProyectoResponseDto>) dto;
		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrado", e);
		} catch (BadRequestException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID no válido", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public @ResponseBody ProyectoResponseDto register(@RequestBody ProyectoRequestDto request) {

		try {
			ProyectoResponseDto dto = (ProyectoResponseDto) proyectoService.insertProyecto(request);
			return dto;

		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el proyecto", e);

		} catch (BadRequestException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido", e);
		}
	}

	@RequestMapping(value = "/addTarea/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AgregarTareaProyectoResponseDto addTarea(@RequestBody AgregarTareaProyectoRequestDto request) {

		try {
			AgregarTareaProyectoResponseDto dto = (AgregarTareaProyectoResponseDto) proyectoService.addTarea(request);
			return dto;

		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el proyecto", e);

		} catch (BadRequestException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido", e);
		}
	}

	@RequestMapping(value = "/addUsuario", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AsignarUsuarioProyectoResponseDto addUsuario(@RequestBody AsignarUsuarioProyectoRequestDto request) {
		try {
			AsignarUsuarioProyectoResponseDto dto = (AsignarUsuarioProyectoResponseDto) proyectoService.addUsuario(request);
			return dto;

		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el proyecto", e);

		} catch (BadRequestException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido", e);
		}
	}

	}
