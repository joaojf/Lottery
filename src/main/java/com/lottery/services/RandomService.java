package com.lottery.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.dtos.EmailDto;
import com.lottery.models.Game;
import com.lottery.models.User;
import com.lottery.repositories.GameRepository;
import com.lottery.repositories.UserRepository;

@Service
public class RandomService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GameRepository gameRepository;

	private User findOrCreate(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);

		return userOptional.orElseGet(() -> userRepository.save(new User(email)));

	}

	public Game generateGame(EmailDto emailDTO) {
		String email = emailDTO.getEmail();
		User user = findOrCreate(email);
		/////////////////////////////////////////////////////////////////////////////////////////////////
		Random random = new Random();

		Set<Integer> numbers = new TreeSet<>();

		do {
			Integer number = random.nextInt(60) + 1;
			numbers.add(number);
		} while (numbers.size() < 6);

		String numbersString = numbers.stream().map(String::valueOf).collect(Collectors.joining(" - "));
		/////////////////////////////////////////////////////////////////////////////////////////////////
		return gameRepository.save(new Game(user, numbersString));
	}

	public List<Game> listGames(String email) {
		User owner = findOrCreate(email);
		return gameRepository.findByOwner(owner);
	}

}
