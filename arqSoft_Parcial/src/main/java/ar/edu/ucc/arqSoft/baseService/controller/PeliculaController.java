package ar.edu.ucc.arqSoft.baseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import ar.edu.ucc.arqSoft.baseService.dto.PeliculaResponseDto;
import ar.edu.ucc.arqSoft.baseService.service.PeliculaService;
import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@Controller
@RequestMapping("/pelicula")
public class PeliculaController {
	
	
	@Autowired
	private PeliculaService peliculaService;
	
	 @RequestMapping(method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody List<PeliculaResponseDto> getAllPeliculas()
	    {
	        return peliculaService.getAllPeliculas();
	    }
	 
//		@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//		public @ResponseBody PeliculaResponseDto lookupPeliculaByIdAlt(@PathVariable("id") Long id) {
//			try {
//				PeliculaResponseDto dto = peliculaService.getPeliculaById(id);		
//				return dto;
//			} catch (EntityNotFoundException e) {
//				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film Not Found", e);
//			} catch (BadRequestException e) {
//				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request - ID = 0 o negativo", e);
//			}
//		}
	 
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> lookupPeliculaById(@PathVariable("id") Long id){
    
		try {
				PeliculaResponseDto dto = peliculaService.getPeliculaById(id);
				return new ResponseEntity<Object>(dto, HttpStatus.OK);
				
	}catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1001", "No se encontró la pelicula");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
			
		}	catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1002", "Mal ingresado");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		
		}
         
         
    }

}
