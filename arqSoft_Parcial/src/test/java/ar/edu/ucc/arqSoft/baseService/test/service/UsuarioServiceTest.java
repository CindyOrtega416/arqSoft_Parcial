package ar.edu.ucc.arqSoft.baseService.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.edu.ucc.arqSoft.baseService.dto.UsuarioRequestDto;
import ar.edu.ucc.arqSoft.baseService.dto.UsuarioResponseDto;
import ar.edu.ucc.arqSoft.baseService.service.UsuarioService;
import ar.edu.ucc.arqSoft.common.exception.BadRequestException;
import ar.edu.ucc.arqSoft.common.exception.EntityNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath:/spring/applicationContext.xml" })

public class UsuarioServiceTest {

	@Autowired
	private UsuarioService usuarioService;

	@Test
	public void testInsert() throws EntityNotFoundException, BadRequestException {
		UsuarioRequestDto request = new UsuarioRequestDto();
		request.setNombre("Ignacio");
		request.setApellido("Achaval");
		request.setEmail("Igna@gmail");

		UsuarioResponseDto response = usuarioService.insertUsuario(request);

		Assert.assertNotNull(response.getNombre());
		return;
	}
}
