package com.skillbox.service;

import com.skillbox.LeagueComparator;
import com.skillbox.model.League;
import com.skillbox.model.Player;
import com.skillbox.model.Race;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LeagueComparatorTest {
    private LeagueManager leagueManager;

    @Before
    public void setUp() throws Exception {
        Class<?> stackInterface = LeagueManager.class;
        Reflections reflections = new Reflections("com.skillbox.service", new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toSet());
        for (Class<?> clazz : classes) {
            HashSet<Class<?>> interfaces = new HashSet<>(Arrays.asList(clazz.getInterfaces()));
            for (Class<?> i : interfaces) {
                if (i.equals(stackInterface)) {
                    Object o = clazz.newInstance();
                    leagueManager = (LeagueManager) o;
                }
            }
        }
    }

    @Test
    public void testCompareLeague() {
        LeagueComparator leagueComparator = new LeagueComparator();
        Player player1 = generatePlayer("Chack", 0, League.PRO);
        Player player2 = generatePlayer("Max", 10, League.BRONZE);
        leagueManager.addPlayer(player1);
        leagueManager.addPlayer(player2);
        int expected = leagueComparator.compare(player2, player1);
        Assert.assertEquals(expected, 5);
    }

    @Test
    public void testComparePoint() {
        Player player1 = generatePlayer("Chack", 0, League.PRO);
        Player player2 = generatePlayer("Max", 10, League.BRONZE);
        leagueManager.addPlayer(player1);
        leagueManager.addPlayer(player2);
        int actual = Integer.compare(player2.getPoints(), player1.getPoints());
        int expected = player2.compareTo(player1);
        Assert.assertEquals(expected, actual);
    }

    Player generatePlayer(String name, int point, League league) {
        return new Player(name, point, league, Race.ELF);
    }
}
