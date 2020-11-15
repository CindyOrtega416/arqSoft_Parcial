package ar.edu.ucc.arqSoft.baseService.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.ucc.arqSoft.baseService.dao.SocioDao;
import ar.edu.ucc.arqSoft.baseService.model.Socio;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath:/spring/applicationContext.xml" })
@Transactional
public class SocioDaoTest {
	
private static final Logger logger = LogManager.getLogger(SocioDaoTest.class);
	
	@Autowired
	private SocioDao socioDao;
	
	@Test
	public void testRegister() throws BadRequestException {

		logger.info("Test de Registro de Socio");
		
		Socio socio = new Socio();
		
		socio.setDni("40402552");
		socio.setNombre("Cindy");
		socio.setApellido("Ortega");
		socio.setEmail("cindyortega416@gmail.com");
		

		socioDao.insert(socio);
		//assertEquals(expected, actual) y return true if: expected.equals( actual ) returns true.
		Assert.assertEquals(2, socio.getId().longValue());
		return;
	}

}
