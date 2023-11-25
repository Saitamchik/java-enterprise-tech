package com.skillbox.service;

import com.skillbox.exception.IncorrectPointsException;
import com.skillbox.model.League;
import com.skillbox.model.Player;
import com.skillbox.model.Race;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class LeagueManagerImpl implements LeagueManager{

    ConcurrentHashMap<String, Player> playerConcurrentHashMap = new ConcurrentHashMap<>();
    @Override
    public void addPlayer(Player player) {
        playerConcurrentHashMap.putIfAbsent(player.getNickName(), player);
    }

    @Override
    public void removePlayer(Player player) {
        playerConcurrentHashMap.remove(player.getNickName(), player);
    }

    @Override
    public Player getPlayer(String name) {
        return playerConcurrentHashMap.get(name);
    }

    @Override
    public Player[] getAllPlayers() {
        return playerConcurrentHashMap.values().toArray(Player[]::new);
    }

    @Override
    public Player[] getPlayers(League league) {
        return playerConcurrentHashMap.values().stream()
                .filter(player -> player.getRace().equals(league))
                .collect(Collectors.toList())
                .toArray(Player[]::new);
    }

    @Override
    public Player[] getPlayers(Race race) {
        return playerConcurrentHashMap.values().stream()
                .filter(player -> player.getRace().equals(race))
                .collect(Collectors.toList())
                .toArray(Player[]::new);
    }

    @Override
    public void addPoints(String name, int points) {
        Player player = getPlayer(name);
        if (player != null) {
            if (points > 0) {
                int userNewPoints = player.getPoints() + points;
                player.setPoints(userNewPoints);
                playerConcurrentHashMap.put(name, player);
            } else {
                throw new IncorrectPointsException("No change to the user's points has been made, " +
                        "an incorrect value of the specified value was transmitted.");
            }
        } else {
            throw new NullPointerException("User not found.");
        }
    }
}
