package com.calindra.desafio.controller;

import static org.hamcrest.Matchers.equalTo;
import static com.calindra.desafio.constants.DesafioConstants.*;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.calindra.desafio.builder.DistanciaEnderecoRequestDtoBuilder;
import com.calindra.desafio.dto.DistanciaEnderecoRequestDto;
import com.calindra.desafio.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class EnderecoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private EnderecoService enderecoService;
	
	@Test
	void deveriaRetornarStatusOkQuandoPeloMenos3EnderecosInformados() throws Exception {
		DistanciaEnderecoRequestDto requestDtoMock = new DistanciaEnderecoRequestDtoBuilder()
				.addEndereco(PRIMEIRO_ENDERECO)
				.addEndereco(SEGUNDO_ENDERECO)
				.addEndereco(TERCEIRO_ENDERECO)
				.build();
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(new URI(ENDERECO_DISTANCIA_ENDPOINT))
				.content(objectMapper.writeValueAsString(requestDtoMock))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isOk());
	}
	
	@Test
	void deveriaRetornarStatusBadRequestQuandoMenosDe3EnderecosInformados() throws Exception {
		DistanciaEnderecoRequestDto requestDtoMock = new DistanciaEnderecoRequestDtoBuilder()
				.addEndereco(PRIMEIRO_ENDERECO)
				.addEndereco(SEGUNDO_ENDERECO)
				.build();
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(new URI(ENDERECO_DISTANCIA_ENDPOINT))
				.content(objectMapper.writeValueAsString(requestDtoMock))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest())
		.andExpect(MockMvcResultMatchers
				.jsonPath("$[0].campo", equalTo("enderecos")))
		.andExpect(MockMvcResultMatchers
				.jsonPath("$[0].mensagem", equalTo("Necessário informar no mínimo 3 endereços.")));
	}
	
	@Test
	void deveriaRetornarStatusBadRequestQuandoEnderecoInformadoEmBranco() throws Exception {
		DistanciaEnderecoRequestDto requestDtoMock = new DistanciaEnderecoRequestDtoBuilder()
				.addEndereco(PRIMEIRO_ENDERECO)
				.addEndereco(SEGUNDO_ENDERECO)
				.addEndereco(" ")
				.build();
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(new URI(ENDERECO_DISTANCIA_ENDPOINT))
				.content(objectMapper.writeValueAsString(requestDtoMock))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.isBadRequest())
		.andExpect(MockMvcResultMatchers
				.jsonPath("$[0].campo", equalTo("enderecos[2]")))
		.andExpect(MockMvcResultMatchers
				.jsonPath("$[0].mensagem", equalTo("Endereço não pode estar em branco.")));
	}
	
}
