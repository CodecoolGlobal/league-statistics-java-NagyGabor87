package com.codecool.leaguestatistics.controller;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.model.LeagueStatistics;
import com.codecool.leaguestatistics.model.Player;
import com.codecool.leaguestatistics.model.Team;
import com.codecool.leaguestatistics.view.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides all necessary methods for season simulation
 */
public class Season {

    private List<Team> league;

    public Season() {
        league = new ArrayList<>();
    }

    /**
     * Fills league with new teams and simulates all games in season.
     * After all games played calls table to be displayed.
     */
    public void run() {
        this.league = LeagueFactory.createLeague(6);
        playAllGames();
        Display.displayLeagueTable(league);

    }

    /**
     * Playing whole round. Everyone with everyone one time. Number of teams in league should be even.
     * Following solution represents the robin-round tournament.
     */
    private void playAllGames() {
        int numberOfTeams = league.size();
        List<Team> otherTeams;
        otherTeams = new ArrayList<>(league);
        otherTeams.remove(0);
        int teamSize = otherTeams.size();
        int halfSize = numberOfTeams / 2;
        for (int i = 0; i < numberOfTeams - 1; i++) {
            int teamIndex = i % teamSize;
            playMatch(otherTeams.get(teamIndex), league.get(0));
            for (int j =1; j < halfSize;  j++) {
                int firstTeam = (i+j) % teamSize;
                int secondTeam = ( i + teamSize - j) % teamSize;
                playMatch(otherTeams.get(firstTeam), otherTeams.get(secondTeam));
            }
        }
    }

    /**
     * Plays single game between two teams and displays result after.
     */
    private void playMatch(Team team1, Team team2) {
        int team1Goals = getScoredGoals(team1);
        int team2Goals = getScoredGoals(team2);
        if (team2Goals > team1Goals) {
            team2.setWins(team2.getWins() + 1);
            team1.setLoses(team1.getLoses() + 1);
        }
        if (team1Goals > team2Goals) {
            team1.setWins(team1.getWins() + 1);
            team2.setLoses(team2.getLoses() + 1);
        }
        if (team1Goals == team2Goals) {
            team1.setDraws(team1.getDraws() + 1);
            team2.setDraws(team2.getDraws() + 1);
        }
        System.out.println(Display.displayMatchResults(team1,team2,team1Goals,team2Goals));
    }

    /**
     * Checks for each player of given team chance to score based on skillrate.
     * Adds scored goals to player's and team's statistics.
     * @return All goals scored by the team in current game
     */
    private int getScoredGoals(Team team) {
        int currentGoals = 0;
        List<Player> players = team.getPlayers();
        for (Player player : players) {
            if (Utils.goalScoringChance(player.getSkillRate())) {
                player.setGoals(player.getGoals() + 1);
                currentGoals += 1;
            }

        }
        return currentGoals;
    }

}
