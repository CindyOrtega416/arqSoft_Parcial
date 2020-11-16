package ar.edu.ucc.arqSoft.baseService.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.edu.ucc.arqSoft.baseService.dto.PeliculaResponseDto;
import ar.edu.ucc.arqSoft.baseService.service.PeliculaService;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath:/spring/applicationContext.xml" })
public class PeliculaServiceTest{
	
	@Autowired
	private PeliculaService peliculaService;
	
	@Test
	public void testFindById() throws EntityNotFoundException, BadRequestException {
		PeliculaResponseDto response = peliculaService.getPeliculaById((long) 1);
		
		Assert.assertEquals("Resident Evil", response.getTitulo());
		return;
	}

}