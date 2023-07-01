package com.nrecinos.preparcial.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nrecinos.preparcial.models.dtos.RegisterDTO;
import com.nrecinos.preparcial.models.dtos.UpdatePasswordDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.Token;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repositories.TokenRepository;
import com.nrecinos.preparcial.repositories.UserRepository;
import com.nrecinos.preparcial.services.UserService;
import com.nrecinos.preparcial.utils.JWTTools;

import jakarta.transaction.Transactional;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTTools jwtTools;
	
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(RegisterDTO user) throws Exception {
		User newUser = new User(
				user.getUsername(),
				user.getEmail(),
				passwordEncoder.encode(user.getPassword())
				);
		
		userRepository.save(newUser);
	}
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteById(UUID code) throws Exception {
		userRepository.deleteById(code);
	}

	@Override
	public User findOneById(UUID code) {
		return userRepository.findById(code).orElse(null);
	}

	@Override
	public void updatePassword(UpdatePasswordDTO password, User user) {
		User modifiedPassword = user;
		modifiedPassword.setPassword(password.getNewPassword());
		userRepository.save(modifiedPassword);
		
	}

	@Override
	public User findByUsernameOrEmail(String username, String email) {
		return userRepository.findByUsernameOrEmail(username, email);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Token registerToken(User user) throws Exception {
		cleanTokens(user);
		
		String tokenString = jwtTools.generateToken(user);
		Token token = new Token(tokenString, user);
		
		tokenRepository.save(token);
		
		return token;
	}

	@Override
	public Boolean isTokenValid(User user, String token) {
		try {
			cleanTokens(user);
			List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
			
			tokens.stream()
				.filter(tk -> tk.getContent().equals(token))
				.findAny()
				.orElseThrow(() -> new Exception());
			
			return true;
		} catch (Exception e) {
			return false;
		}		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void cleanTokens(User user) throws Exception {
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
		
		tokens.forEach(token -> {
			if(!jwtTools.verifyToken(token.getContent())) {
				token.setActive(false);
				tokenRepository.save(token);
			}
		});
		
	}
	
	@Override
	public User findUserAuthenticated() {
		String username = SecurityContextHolder
			.getContext()
			.getAuthentication()
			.getName();
		
		return userRepository.findByUsernameOrEmail(username, username);
	}
}
