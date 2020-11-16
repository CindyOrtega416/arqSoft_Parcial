package ar.edu.ucc.arqSoft.baseService.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.edu.ucc.arqSoft.baseService.dto.SocioRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.SocioResponseDto;
import ar.edu.ucc.arqSoft.baseService.service.SocioService;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath:/spring/applicationContext.xml" })
public class SocioServiceTest {
	
	@Autowired
	private SocioService socioService;
	
	@Test
	public void testInsert() throws BadRequestException {
		SocioRequestDto request = new SocioRequestDto();
		
		request.setApellido("Ortega");
		request.setEmail("cindyortega416@gmail.com");
		request.setDni("40402552");
		request.setNombre("Cindy");
		
		SocioResponseDto response =socioService.insertSocio(request);
		
		
		//Assert.assertEquals("Cordoba",response.getName());
		Assert.assertEquals(2, response.getId().longValue());
		return;
	}
}