package com.example.luthfihariz.footballclub.data

import com.example.luthfihariz.footballclub.data.model.FootballClub

class FootballClubRepository : FootballClubDataSource {
    override fun getClubs(): List<FootballClub> {

        // dummy data

        return arrayListOf(
                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/02/Manchester-United-logo-600x338.png",
                        "Manchester United",
                        "Manchester United was based as Newton Heath LYR Football Club in 1878. The club was found by the department of railway depots in the Manchester Newton Heath area. During this period, the team mainly consisted of workers of the railway company and played against the teams of other departments, and in 1888, the club joined the local league."),

                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/02/Arsenal-logo-600x338.png",
                        "Arsenal",
                        "A modern red color on the Arsenal logo and form appeared due to the club Nottingham Forest. Fred Beardsley, Bill Parr, and Charlie Bates are the three football players, who moved from the last club to the Dial Square, the former Arsenal name, taking red shirts with them. There was no money even for new equipment, so they were forced to leave the above-mentioned T-shirts, and bought the same ones to other players. In addition, they gave white shorts and blue and white golf."),

                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Chelsea-logo-600x338.png",
                        "Chelsea",
                        "Chelsea was based in 1905. The first emblem of the club was a collective image of the British army veterans with medals on their chests. In fact, it was the logo of the Royal Hospital of Chelsea. It was not put on the form but appeared on the first match programs."),

                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Manchester-City-logo-600x338.png",
                        "Manchester City",
                        "\"ManCity\" was founded in 1880 at St. Mark’s Church in one of the poorest districts of Manchester called West Gorton, where drunkenness, massacres, and robberies were the common things. From there the team moved to the neighboring region Ardwick, and the current name was given to the club only in 1894. Before that, it was called St. Marx, Gorton and Ardvik. For the last 122 years, the sign of “Manchester City” has not been changed."),

                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Liverpool-logo-600x338.png",
                        "Liverpool",
                        "For many years, Liverbird was the symbol of FC Liverpool. Taken from the army uniforms, it firstly appeared in the club at the beginning of the XX century, when in 1901 its image was engraved on the championship medals handed to the players. In 1922, the bird appeared on the club flag, created for celebrating the victories of the team in the league. Since 1935, its image has been printed on pre-match programs, and in the 30s, Liverbird appeared on the training suits worn by players at Anfield Stadium."),

                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Barcelona-logo-600x338.png",
                        "Barcelona",
                        "Barcelona football club, also known as Barça, was founded by Swedish businessman Hans Gamper. On October 22, 1899, he sent a note to a local newspaper in order to invite everyone to participate in the creation of a football club. 11 people responded and, one month later, on November 29, 1899, they formed a team named FC Ball (Foot Ball Club Barcelona)."),

                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/Real-Madrid-logo-600x338.png",
                        "Real Madrid",
                        "On March 6, 1902, a group of like-minded footballers formally founded the Madrid Football Club, which was preceded by another team Football Sky at the turn of the 19th and 20th centuries."),

                FootballClub("https://worldsportlogos.com/wp-content/uploads/2018/01/FC-Bayern-M%C3%BCnchen-logo.png",
                        "Bayern Munchen",
                        "It is not easy to accept but Bayern football team is 115 years old. However, these ‘old bones’ are quite strong to smash their enemies. Everything began with a split, back in the days of the German Empire, on February 27, 1900. 11 guys led by Franz John became too crowded under the same roof with the Munich gymnastics union MTV-1879. And they, draining glasses with a drink in a zucchini “Gizella”, wanted to create a football club FC Bayern München, named in honor of the land, where Munich stands.")

        )
    }

}