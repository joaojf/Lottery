package com.lottery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lottery.models.Game;
import com.lottery.models.User;

public interface GameRepository extends JpaRepository<Game, Long> {

	List<Game> findByOwner(User owner);

}