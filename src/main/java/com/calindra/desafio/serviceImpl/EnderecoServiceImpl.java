package com.calindra.desafio.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calindra.desafio.dto.DistanciaEnderecoDto;
import com.calindra.desafio.dto.DistanciaEnderecoRequestDto;
import com.calindra.desafio.dto.DistanciaEnderecoResponseDto;
import com.calindra.desafio.dto.GeoCoordinateDto;
import com.calindra.desafio.exception.CoordinateNotFoundException;
import com.calindra.desafio.exception.GeoApiServiceException;
import com.calindra.desafio.service.EnderecoService;
import com.calindra.desafio.service.GeoApiProxy;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private GeoApiProxy geoApiProxyImpl; 
	
	public DistanciaEnderecoResponseDto calcularDistanciaEntreEnderecos(DistanciaEnderecoRequestDto requestDto) throws GeoApiServiceException, CoordinateNotFoundException {
		DistanciaEnderecoResponseDto responseDto = new DistanciaEnderecoResponseDto();
		List<String> enderecos = requestDto.getEnderecos();
		
		for (int i = 0; i < enderecos.size(); i++) {
			GeoCoordinateDto coordenadaFrom = geoApiProxyImpl.recuperarCoordenadas(enderecos.get(i));

			for (int j = i + 1; j < enderecos.size(); j++) {
				GeoCoordinateDto coordenadaTo = geoApiProxyImpl.recuperarCoordenadas(enderecos.get(j));

				Double distanceInKm = coordenadaFrom.getDistanceInKm(coordenadaTo);

				responseDto.addDistanciaEnderecos(
						new DistanciaEnderecoDto(enderecos.get(i), enderecos.get(j), distanceInKm));
			}
		}
		
		geoApiProxyImpl.limparCache();
		
		return responseDto;
	}
}
