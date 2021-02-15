package br.com.br.proposta.customerErros;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrosDTO>>listErros(MethodArgumentNotValidException error){
		List<ErrosDTO> listDeErros = new ArrayList<>();
		for(FieldError fieldErro:error.getBindingResult().getFieldErrors()) {
			listDeErros.add(new ErrosDTO(fieldErro.getField(), fieldErro.getDefaultMessage()));
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listDeErros);
	}

}
