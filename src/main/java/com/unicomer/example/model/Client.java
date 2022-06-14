package com.unicomer.example.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
@Data
@AllArgsConstructor
@ToString
public class Client implements Serializable {

	private static final long serialVersionUID = -5025618734709927492L;
	
    @NotNull(message = "Se debe incoporar el id")
    @Min(value = 1, message = "El valor debe tener un valor minimo de 1")
    @Digits(integer=19,fraction=0, message = "El valor debe ser numerico, de 1 a 19 Enteros")
	@ApiModelProperty(position = 1, required = true, example = "12345", notes="Identificador del cliente")
	private int id;
    
    @NotNull(message = "Se debe incoporar el nombre")
	@ApiModelProperty(position = 2, required = true, example = "Francisco", notes="Nombre del cliente")
	private String firstName;
    
    @NotNull(message = "Se debe incoporar el apellido")
	@ApiModelProperty(position = 3, required = true, example = "Graf", notes="Apellido del cliente")
	private String lastName;
    
	@JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Se debe incoporar la fecha de nacimiento")
	@ApiModelProperty(position = 4, required = true , example = "1983-04-07", notes="Fecha de nacimiento del cliente")
	private Date birthday;
	
    @Size(min = 0, max = 20, message = "El genero debe constar entre {min} and {max} caracteres")
	@ApiModelProperty(position = 5, allowEmptyValue = true, example = "Masculino", notes="Genero del cliente")
	private String gender;
	
	@Digits(integer = 19, fraction = 0, message = "El Teléfono movil debe ser numerico")
	@ApiModelProperty(position = 6, allowEmptyValue = true, example = "1234567", notes="Teléfono movil del Cliente")
	private int cellphone;
	
	@Digits(integer = 19, fraction = 0, message = "El Teléfono residencial  debe ser numerico")
	@ApiModelProperty(position = 7, allowEmptyValue = true, example = "1234", notes="Teléfono residencial del Cliente")
	private int homephone;
	
    @Size(min = 0, max = 500, message = "La Direccion debe constar entre {min} and {max} caracteres")
	@ApiModelProperty(position = 8, allowEmptyValue = true, example = "P. Sherman Calle Wallaby 42", notes="Dirección del Cliente")
	private String addressHome;
	
    @Size(min = 0, max = 500, message = "La Profesión debe constar entre {min} and {max} caracteres")
	@ApiModelProperty(position = 9, allowEmptyValue = true, example = "Profesor", notes="Profesión del cliente")
	private String profession;
	
	@Digits(integer = 19, fraction = 0, message = "El ingreso de item debe ser numerico")
	@ApiModelProperty(position = 10, allowEmptyValue = true, example = "1000", notes="Ingresos")
    private int income;
	

}
