package com.example.myapp.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@EqualsAndHashCode
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The unique id of the User")
    private Long userId;

	public User() {

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@NotBlank
	@Column(name="first_name")
    private String firstName;



	@Column(name="last_name")
    private String lastName;

	public User(Long userId, String firstName, String lastName) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
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




	@Override
	public String toString() {
		return super.toString();
	}
/*	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
		return lastName != null ? lastName.equals(user.lastName) : user.lastName == null;
	}

	@Override
	public int hashCode() {
		int result = userId != null ? userId.hashCode() : 0;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		return result;
	}*/
}

