package ar.edu.ucc.arqSoft.baseService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.ucc.arqSoft.baseService.dto.EstadoRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.EstadoResponseDto;
import ar.edu.ucc.arqSoft.baseService.service.EstadoService;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;

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
    public @ResponseBody EstadoResponseDto lookupEstadoById(@PathVariable("id") Long id)
    {
        //return estadoService.getEstadoById(id);
    	return null;
    }
    
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EstadoResponseDto saveEstado(@RequestBody EstadoRequestDto request) throws BadRequestException 
    {
        return estadoService.insertEstado(request);
    }
}