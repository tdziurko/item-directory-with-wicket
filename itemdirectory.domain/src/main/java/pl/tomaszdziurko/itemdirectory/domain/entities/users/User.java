package pl.tomaszdziurko.itemdirectory.domain.entities.users;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.tomaszdziurko.itemdirectory.domain.entities.AbstractEntity;

@javax.persistence.Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=255, nullable=false)
    private String login;

    @Column(length=255, nullable=false)
    private String password;
    
	protected User() {
		super();
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    
}
