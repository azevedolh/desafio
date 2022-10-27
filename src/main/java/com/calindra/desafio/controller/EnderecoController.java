package com.calindra.desafio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calindra.desafio.dto.DistanciaEnderecoRequestDto;
import com.calindra.desafio.dto.DistanciaEnderecoResponseDto;
import com.calindra.desafio.exception.CoordinateNotFoundException;
import com.calindra.desafio.exception.GeoApiServiceException;
import com.calindra.desafio.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping("/distancia")
	public ResponseEntity<DistanciaEnderecoResponseDto> listarDistancias(@RequestBody @Valid DistanciaEnderecoRequestDto requestDto) throws GeoApiServiceException, CoordinateNotFoundException {
		return ResponseEntity.ok(enderecoService.calcularDistanciaEntreEnderecos(requestDto));
	}
}
