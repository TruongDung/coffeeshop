package edu.mum.coffee.adapters;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserAdapter implements UserDetails {
	private static final long serialVersionUID = -1360188483928178893L;
	private User user;
	private Person person;

	public UserAdapter(User user) {
		this.user = user;
	}

	public UserAdapter(User user, Person person) {
		this.user = user;
		this.person = person;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		user.getRoles().stream().forEach(authorities::add);
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public User getUser() {
		return user;
	}

	public Person getPerson() {
		return person;
	}
}
