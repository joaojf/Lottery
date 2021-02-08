package com.lottery.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lottery.dtos.EmailDto;
import com.lottery.models.Game;
import com.lottery.services.RandomService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("random")
public class RandomController {

	@Autowired
	private RandomService RandomService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Game create(@RequestBody EmailDto emailDTO) {
		return RandomService.generateGame(emailDTO);
	}

	@GetMapping("list")
	public List<Game> list(@RequestParam String email) {
		return RandomService.listGames(email);
	}

}