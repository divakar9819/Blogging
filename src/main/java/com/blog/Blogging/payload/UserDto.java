package com.blog.Blogging.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	private Integer userId;
	
	@NotEmpty(message = "name can't be empty")
	private String name;
	
	@Email(message = "Email is not valid!Please enter valid email")
	private String userName;
	
	@NotEmpty(message="Password can't be empty")
	@Min(message = "Minimum Password lenght should be 4", value = 4)
	@Max(message = "Maximum Password lenght should be 32" ,value = 32)
	private String password;
	
	@NotEmpty(message = "About can't be empty")
	private String about;

}
