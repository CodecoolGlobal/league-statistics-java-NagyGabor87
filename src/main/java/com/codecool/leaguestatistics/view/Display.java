package com.codecool.leaguestatistics.view;

import com.codecool.leaguestatistics.model.LeagueStatistics;
import com.codecool.leaguestatistics.model.Team;

import java.util.List;

/**
 * Provides console view
 */
public class Display {

    public static String displayMatchResults(Team team1, Team team2, int team1Goals, int team2Goals) {
        StringBuilder string = new StringBuilder();
        string.append("The match result between ").append(team1.getName()).append(" ").append("and ").append(team2.getName()).append(" ")
                .append("is: ").append(team1Goals).append(" ").append("-").append(" ").append(team2Goals);
        return String.valueOf(string);
    }

    public static void displayLeagueTable(List<Team> teams) {
        List<Team>sortedTeams = LeagueStatistics.getAllTeamsSorted(teams);
        System.out.println("Final Standings: ");
        System.out.println("|--------------------------------------------------------------------------------------------------------|");
        System.out.printf("| %40s | %6s | %12s | %10s | %10s | %10s|\n", "Team Name", "Points", "Goals scored", "Wins", "Draws", "Loses");
        for (Team sortedTeam : sortedTeams) {
            System.out.println("|--------------------------------------------------------------------------------------------------------|");
            System.out.printf("| %40s | %6s | %12s | %10s | %10s | %10s|\n", sortedTeam.getName(), sortedTeam.getCurrentPoints(), sortedTeam.sumOfGoals(), sortedTeam.getWins(), sortedTeam.getDraws(), sortedTeam.getLoses());
        }
        System.out.println("|--------------------------------------------------------------------------------------------------------|");
    }

}
