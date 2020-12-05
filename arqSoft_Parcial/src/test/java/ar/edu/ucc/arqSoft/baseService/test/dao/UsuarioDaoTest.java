package ar.edu.ucc.arqSoft.baseService.test.dao;


import javax.transaction.Transactional;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.edu.ucc.arqSoft.baseService.dao.UsuarioDao;
import ar.edu.ucc.arqSoft.baseService.model.Usuario;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath:/spring/applicationContext.xml" })
@Transactional

public class UsuarioDaoTest{
	
	private static Logger logger = Logger.getLogger(UsuarioDaoTest.class);
	//private static final Logger logger = LogManager.getLogger(UsuarioDaoTest.class);
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Test
	public void testRegister() throws BadRequestException {

		logger.info("Test de Registro de user 1");
		
		Usuario socio = new Usuario();
		socio.setNombre("Ignacio");
		socio.setApellido("Achaval");
		socio.setEmail("igna@gmail");

		usuarioDao.insert(socio
				);
		Assert.assertNotNull(socio.getId());
		return;
	}
}

