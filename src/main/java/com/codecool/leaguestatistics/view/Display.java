package com.codecool.leaguestatistics.view;

import com.codecool.leaguestatistics.model.Team;

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

}
