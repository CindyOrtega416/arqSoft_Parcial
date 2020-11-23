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

import ar.edu.ucc.arqSoft.baseService.dto.ComentarioRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.ComentarioResponseDto;
import ar.edu.ucc.arqSoft.baseService.exception.TareaCerradaException;
import ar.edu.ucc.arqSoft.baseService.service.ComentarioService;
import ar.edu.ucc.arqSoft.common.dto.GenericExceptionDto;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;


@Controller
@RequestMapping("/comentario")
public class ComentarioController {
	
	@Autowired
	private ComentarioService comentarioService;
	
    @RequestMapping(method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ComentarioResponseDto> getAllComentarios()
    {
        return comentarioService.getAllComentarios();					//traer todos los comentarios
    }
    

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)		//traer comentarios por id
    public @ResponseBody ResponseEntity<Object> lookupComentarioById(@PathVariable("id") Long id){
    
		try {
				ComentarioResponseDto dto = comentarioService.getComentarioById(id);
				return new ResponseEntity<Object>(dto, HttpStatus.OK);
				
	}catch (EntityNotFoundException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1001", "No se encontró el comentario");
			return new ResponseEntity<Object>(exDto, HttpStatus.NOT_FOUND);
			
		}	catch (BadRequestException e) {
			GenericExceptionDto exDto = new GenericExceptionDto("1002", "Mal ingresado");
			return new ResponseEntity<Object>(exDto, HttpStatus.BAD_REQUEST);
		
		}
		
		}
    @RequestMapping(value="/tarea/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ComentarioResponseDto> getAllComentariosByTareaId(@PathVariable("id") Long id) throws EntityNotFoundException, BadRequestException {
    	return comentarioService.getAllComentariosFromTarea(id);		//traer todos los comentarios de la tarea
		
    }
    
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ComentarioResponseDto saveComentario(@RequestBody ComentarioRequestDto request) throws EntityNotFoundException, BadRequestException, TareaCerradaException
    {
        return comentarioService.insertComentario(request);			//insertar comentario
    }
    

}


