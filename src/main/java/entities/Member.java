package entities;

import enums.Role;

public class Member {
	private Long Id;
	private String firstName;
	private String secondName;
	private String email;
	private Role userRole;


	public Member(){}

	public Member(String firstName , String secondName,String email , Role usRole){
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.userRole = userRole;
	}	

	public Member(Long Id,String firstName , String secondName,String email , Role usRole){
		this.Id = Id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.userRole = userRole;

	}


	public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

	
	
}
