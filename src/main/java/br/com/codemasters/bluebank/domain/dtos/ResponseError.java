package br.com.codemasters.bluebank.domain.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseError {
	
	private String exception;
	private String message;
	private String path;
	private LocalDateTime time;

}
