package com.codecool.leaguestatistics.model;

import com.codecool.leaguestatistics.controller.Season;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides all necessary statistics of played season.
 */
public class LeagueStatistics {

    /**
     * Gets all teams with highest points order, if points are equal next deciding parameter is sum of goals of the team.
     */
    public static List<Team> getAllTeamsSorted(List<Team> teams) {
        Stream<Team> stream = teams.stream();
        Comparator<Team> compareByPointsThenSumOfGoals = Comparator.comparing(Team::getCurrentPoints)
                .thenComparing(Team::sumOfGoals).reversed();
        return stream.sorted(compareByPointsThenSumOfGoals).collect(Collectors.toList());
    }

    /**
     * Gets all players from each team in one collection.
     */
    public static List<Player> getAllPlayers(List<Team> teams) {
        return teams.stream().flatMap(Team -> Team.getPlayers().stream()).collect(Collectors.toList());
    }

    /**
     * Gets team with the longest name
     */
    public static Team getTeamWithTheLongestName(List<Team> teams) {
        return teams.stream().max(Comparator.comparingInt(Team::nameLength)).get();
    }

    /**
     * Gets top teams with least number of lost matches.
     * If the amount of lost matches is equal, next deciding parameter is team's current points value.
     * @param teamsNumber The number of Teams to select.
     * @return Collection of selected Teams.
     */
    public static List<Team> getTopTeamsWithLeastLoses(List<Team> teams, int teamsNumber) {
        return teams.stream().sorted(Comparator.comparing(Team::getLoses)
                .thenComparing(Team::getCurrentPoints, Comparator.reverseOrder()))
                .limit(teamsNumber).collect(Collectors.toList());
    }

    /**
     * Gets a player with the biggest goals number from each team.
     */
    public static List<Player> getTopPlayersFromEachTeam(List<Team> teams) {
        return teams.stream().map(Team::getBestPlayer).collect(Collectors.toList());
    }

    /**
     * Gets all teams, where there are players with no scored goals.
     */
    public static List<Team> getTeamsWithPlayersWithoutGoals(List<Team> teams){
        return teams.stream().filter(Team -> Team.getPlayers().stream().anyMatch(player -> player.getGoals() == 0))
                .collect(Collectors.toList());
    }

    /**
     * Gets players with given or higher number of goals scored.
     * @param goals The minimal number of goals scored.
     * @return Collection of Players with given or higher number of goals scored.
     */
    public static List<Player> getPlayersWithAtLeastXGoals(List<Team> teams, int goals) {
        return teams.stream().flatMap(Team -> Team.getPlayers().stream()).filter(Player -> Player.getGoals() >= goals)
                .collect(Collectors.toList());
    }

    /**
     * Gets the player with the highest skill rate for given Division.
     */
    public static Player getMostTalentedPlayerInDivision(List<Team> teams, Division division) {
       return teams.stream().filter(Team -> Team.getDivision() == division).flatMap(Team -> Team.getPlayers().stream())
               .max(Comparator.comparing(Player::getSkillRate)).get();
    }

    /**
     * OPTIONAL
     * Returns the division with greatest amount of points.
     * If there is more than one division with the same amount current points, then check the amounts of wins.
     */
    public static Division getStrongestDivision(List<Team> teams) {
        return teams.stream().sorted(Comparator.comparing(Team::getCurrentPoints, Comparator.reverseOrder())
                .thenComparing(Team::getWins, Comparator.reverseOrder())).collect(Collectors.toList()).get(0).getDivision();
    }
}
