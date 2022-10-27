package com.calindra.desafio.config.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.calindra.desafio.exception.CoordinateNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@Autowired
	MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationHandlerDto> handleValidation(MethodArgumentNotValidException exception) {
		List<ValidationHandlerDto> listaDeErros = new ArrayList<ValidationHandlerDto>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ValidationHandlerDto dto = new ValidationHandlerDto(e.getField(), mensagem);
			listaDeErros.add(dto);
		});

		return listaDeErros;
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ExceptionHandlerDto handleExceptions(Exception exception) {
		return new ExceptionHandlerDto(exception.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(CoordinateNotFoundException.class)
	public ExceptionHandlerDto handleExceptionsNotFound(CoordinateNotFoundException exception) {
		return new ExceptionHandlerDto(exception.getMessage());
	}
}
