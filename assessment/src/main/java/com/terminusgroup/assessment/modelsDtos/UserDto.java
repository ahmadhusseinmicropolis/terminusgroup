package com.terminusgroup.assessment.modelsDtos;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class UserDto {
	
	public UserDto() {
		
	}
	public UserDto(Long id, String firstName, String lastName, String occupation, int age){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.occupation = occupation;
		this.age = age;
	}
	private Long id;
	
    private String firstName;
	
    private String lastName;
	
    private String occupation;
	
    private int age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
    
}
