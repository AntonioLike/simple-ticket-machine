package com.cleverti.simpletrainticketmachine.repositories.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Station implements Serializable{
	
	private static final long serialVersionUID = 7807340895122421411L;
	
	@Id
	private String name;
}
