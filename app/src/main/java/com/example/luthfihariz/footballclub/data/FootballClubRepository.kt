package com.example.luthfihariz.footballclub.data

import com.example.luthfihariz.footballclub.data.model.FootballClub

class FootballClubRepository : FootballClubDataSource{
    override fun getClubs(): List<FootballClub> {

        // dummy data

        return arrayListOf(
                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Chelsea-logo-600x338.png",
                        "Chelsea",
                        "Chelsea was based in 1905. The first emblem of the club was a collective image of the British army veterans with medals on their chests. In fact, it was the logo of the Royal Hospital of Chelsea. It was not put on the form but appeared on the first match programs."),
                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Chelsea-logo-600x338.png",
                        "Chelsea",
                        "Chelsea was based in 1905. The first emblem of the club was a collective image of the British army veterans with medals on their chests. In fact, it was the logo of the Royal Hospital of Chelsea. It was not put on the form but appeared on the first match programs."),
                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Chelsea-logo-600x338.png",
                        "Chelsea",
                        "Chelsea was based in 1905. The first emblem of the club was a collective image of the British army veterans with medals on their chests. In fact, it was the logo of the Royal Hospital of Chelsea. It was not put on the form but appeared on the first match programs."),
                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Chelsea-logo-600x338.png",
                        "Chelsea",
                        "Chelsea was based in 1905. The first emblem of the club was a collective image of the British army veterans with medals on their chests. In fact, it was the logo of the Royal Hospital of Chelsea. It was not put on the form but appeared on the first match programs."),
                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Chelsea-logo-600x338.png",
                        "Chelsea",
                        "Chelsea was based in 1905. The first emblem of the club was a collective image of the British army veterans with medals on their chests. In fact, it was the logo of the Royal Hospital of Chelsea. It was not put on the form but appeared on the first match programs."),
                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Chelsea-logo-600x338.png",
                        "Chelsea",
                        "Chelsea was based in 1905. The first emblem of the club was a collective image of the British army veterans with medals on their chests. In fact, it was the logo of the Royal Hospital of Chelsea. It was not put on the form but appeared on the first match programs."))
    }

}