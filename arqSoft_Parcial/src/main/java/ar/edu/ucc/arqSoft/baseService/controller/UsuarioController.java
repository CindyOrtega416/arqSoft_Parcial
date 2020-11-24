package ar.edu.ucc.arqSoft.baseService.controller;

import java.util.List;

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

import ar.edu.ucc.arqSoft.baseService.dto.UsuarioRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.UsuarioResponseDto;
import ar.edu.ucc.arqSoft.baseService.service.UsuarioService;
import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {


	@Autowired
	private UsuarioService usuarioService;
	
	
	 
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code= HttpStatus.CREATED)
	public @ResponseBody UsuarioResponseDto insertUsuario(@RequestBody UsuarioRequestDto request) {
				try {
						UsuarioResponseDto dto = (UsuarioResponseDto) usuarioService.insertUsuario(request);
						return dto;
				} catch (EntityNotFoundException e) {
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado", e);
				} catch (BadRequestException e) {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID invalido", e);
				}
	}
	
	
	 
		@RequestMapping(value="/{nombre}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody ResponseEntity<Object> lookupByNombre(@PathVariable("nombre") String nombre){
	    
			try {
					List<UsuarioResponseDto> dto = usuarioService.GetByNombre(nombre);
					return new ResponseEntity<Object>(dto, HttpStatus.OK);
					
		}catch (EntityNotFoundException e) {
				GenericExceptionDto exDto = new GenericExceptionDto("1001", "Usuario no encontrado");
				return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
				
			}	catch (BadRequestException e) {
				GenericExceptionDto exDto = new GenericExceptionDto("1002", "ID invalido");
				return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
			
			}
	         
	         
	    }
}
