package ar.edu.ucc.arqSoft.baseService.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TareaCerradaException extends Exception {

	private static final long serialVersionUID = 2L;

}