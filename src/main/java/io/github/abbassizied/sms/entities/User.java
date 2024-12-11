package io.github.abbassizied.sms.entities;
 
import jakarta.persistence.*; 
import java.util.HashSet;
import java.util.Set; 

@Entity
@Table(name = "users")  // "user" is a reserved keyword in some databases, so we use "users".
public class User extends BaseEntity { 

    @Column(nullable = false)
    private String firstName;
 
    @Column(nullable = false)
    private String lastName;
 
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    private String photoUrl;

    @Column(nullable = false)
    private Boolean active;
  
    // Many-to-many relationship between User and Role
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // Default constructor 
    public User() {
    	super(); 
    }
 
    // Getters and setters for each field
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }    
    public void setPassword(String password) { this.password = password; }
     
    public String getPhotoUrl() { return photoUrl; } 
	public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

	public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ ", photoUrl=" + photoUrl + ", active=" + active + ", roles=" + roles + "]";
	}

 
}
