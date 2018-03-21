package edu.mum.coffee.service;

import java.util.List;

import edu.mum.coffee.domain.Role;
import edu.mum.coffee.domain.User;
import edu.mum.coffee.repository.RoleRepository;
import edu.mum.coffee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public Person savePerson(Person person) {
		User user = userRepository.findByEmail(person.getEmail());
		user.setEnabled(person.isEnabled());
		userRepository.save(user);
		return personRepository.save(person);
	}

	public List<Person> findByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	public Person findById(Long id) {
		return personRepository.findOne(id);
	}

	public void removePerson(Person person) {
		personRepository.delete(person);
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public void signup(Person person) {
		person.setEnabled(true);

		User user = new User();
		user.setEmail(person.getEmail());
		user.setEnabled(true);
		Role customerRole = roleRepository.findByRole("ROLE_CUSTOMER");
		user.addRole(customerRole);
		user.setPassword(passwordEncoder.encode(person.getPassword()));

		person = personRepository.save(person);
		userRepository.save(user);
	}
}
