package com.codecool.leaguestatistics;

import com.codecool.leaguestatistics.controller.Season;
import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.model.Player;

public class Program {

    public static void main( String[] args ) {
        Season season = new Season();
        season.run();
    }
}
