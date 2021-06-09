package com.example.mymoviecatalogue2.utils

import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieGenres
import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieResults
import com.example.mymoviecatalogue2.data.source.remote.response.tv.TvResults

object DataDummy {

    fun listGenreMovie() : List<MovieGenres> {
        val movieGenre = ArrayList<MovieGenres>()

        movieGenre.add(
            MovieGenres(12,"Advanture")
        )
        movieGenre.add(
            MovieGenres(28,"Action")
        )
        movieGenre.add(
            MovieGenres(16, "Animation")
        )

        return movieGenre
    }

    fun generateDummyMovie() : List<Movie> {
        val movie = ArrayList<Movie>()

        movie.add(
            Movie(399566,
                "Godzilla vs Kong",
//                listOf("Action", "Science Fiction", "Drama"),
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "2021-03-24",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg")
        )

        movie.add(
            Movie(2,
                "Mortal Combat",
//                listOf("Fantasy", "Action", "Adventure", "Science Fiction","Thriller"),
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg")
        )

        movie.add(
            Movie(3,
                "Zack Snyder's Justice League ",
//                listOf("Action", "Adventure", "Fantasy", "Science Fiction"),
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "2021-04-29",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg")
        )

        movie.add(
            Movie(4,
                "Wonder Woman 1984",
//                listOf("Fantasy", "Action", "Adventure"),
                "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.",
                "2020-12-16",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg")
        )

        movie.add(
            Movie(5,
                "The Unholy",
//                listOf("Horror"),
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "2021-03-31",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/b4gYVcl8pParX8AjkN90iQrWrWO.jpg")
        )

        movie.add(
            Movie(6,
                "Avengers: Endgame",
//                listOf("Adventure", "Science Fiction", "Action"),
                "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
                "2019-04-24",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ulzhLuWrPK07P1YkdWQLZnQh1JL.jpg")
        )

        movie.add(
            Movie(7,
                "John Wick: Chapter 3 - Parabellum",
//                listOf("Action", "Thriller", "Crime"),
                "Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.",
                "2019-05-15",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg")
        )

        movie.add(
            Movie(8,
                "The SpongeBob Movie: Sponge on the Run",
//                listOf("Animation", "Fantasy", "Adventure", "Comedy", "Family"),
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
                "2020-08-14",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg")
        )

        movie.add(
            Movie(9,
                "Captain Marvel",
//                listOf("Action", "Adventure", "Science Fiction"),
                "The story follows Carol Danvers as she becomes one of the universe’s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races. Set in the 1990s, Captain Marvel is an all-new adventure from a previously unseen period in the history of the Marvel Cinematic Universe.",
                "2019-03-06",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg")
        )

        movie.add(
            Movie(10,
                "Peninsula",
//                listOf("Action", "Horror", "Thriller"),
                "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                "2020-07-15",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3beTKqDnqWtUUvHusKFs1CACaU0.jpg")
        )

        return movie
    }

    fun generateDummyTvShow() : List<TvShow> {
        val tvShow = ArrayList<TvShow>()

        tvShow.add(
            TvShow(1416,
                "The Falcon and the Winter Soldier",
//                listOf("Sci-Fi & Fantasy", "Action & Adventure", "Drama", "War & Politics"),
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg")
        )

        tvShow.add(
            TvShow(2,
                "WandaVision",
//                listOf("Sci-Fi & Fantasy", "Mystery", "Drama"),
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "2021-01-15",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg")
        )

        tvShow.add(
            TvShow(3,
                "The Good Doctor",
//                listOf("Drama"),
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "2017-09-25",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg")
        )

        tvShow.add(
            TvShow(4,
                "S.W.A.T",
//                listOf("Action & Adventure", "Crime", "Drama"),
                "A locally born and bred S.W.A.T. lieutenant is torn between loyalty to the streets and duty to his fellow officers when he's tasked to run a highly-trained unit that's the last stop for solving crimes in Los Angeles.",
                "2017-10-02",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/am7NOjx56BpJOh1yFy6P70WmuTb.jpg")
        )

        tvShow.add(
            TvShow(5,
                "The Flash",
//                listOf("Drama", "Sci-Fi & Fantasy"),
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg")
        )

        tvShow.add(
            TvShow(6,
                "Marvel's Agents of S.H.I.E.L.D.",
//                listOf("Drama", "Sci-Fi & Fantasy", "Action & Adventure"),
                "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary.",
                "2013-09-24",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gHUCCMy1vvj58tzE3dZqeC9SXus.jpg")
        )

        tvShow.add(
            TvShow(7,
                "Game of Thrones",
//                listOf("Sci-Fi & Fantasy", "Drama", "Action & Adventure"),
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "2011-04-17",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg")
        )

        tvShow.add(
            TvShow(8,
                "Money Heist",
//                listOf("Crime", "Drama"),
                "To carry out the biggest heist in history, a mysterious man called The Professor recruits a band of eight robbers who have a single characteristic: none of them has anything to lose. Five months of seclusion - memorizing every step, every detail, every probability - culminate in eleven days locked up in the National Coinage and Stamp Factory of Spain, surrounded by police forces and with dozens of hostages in their power, to find out whether their suicide wager will lead to everything or nothing.",
                "2017-05-02",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kvp2I1cKQ8pTzhBIxIyAjscsfxL.jpg")
        )

        tvShow.add(
            TvShow(9,
                "9-1-1",
//                listOf("Drama", "Action & Adventure"),
                "Explore the high-pressure experiences of police officers, paramedics and firefighters who are thrust into the most frightening, shocking and heart-stopping situations. These emergency responders must try to balance saving those who are at their most vulnerable with solving the problems in their own lives.",
                "2018-01-03",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vHq6VlLijLppMPFXhWo3hqjYY8q.jpg")
        )

        tvShow.add(
            TvShow(10,
                "Chernobyl",
//                listOf("Drama"),
                "The true story of one of the worst man-made catastrophes in history: the catastrophic nuclear accident at Chernobyl. A tale of the brave men and women who sacrificed to save Europe from unimaginable disaster.",
                "2019-05-06",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hlLXt2tOPT6RRnjiUmoxyG1LTFi.jpg")
        )

        return tvShow
    }


    fun getRemoteMovie(): ArrayList<MovieResults> {
        val remotemov = ArrayList<MovieResults>()

        remotemov.add(
            MovieResults(
                moviesId = 527774,
                title = "Raya and the Last Dragon",
                listOf(16,
                    12,
                    14,
                    10751,
                    28),
                releaseDate = "2021-03-03",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                overview = "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people."
            )
        )

        remotemov.add(
            MovieResults(
                moviesId = 464052,
                title = "Wonder Woman 1984",
                listOf(14,
                    28,
                    12),
                releaseDate = "2020-12-16",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                overview = "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy."
            )
        )


        remotemov.add(
            MovieResults(
                moviesId = 791373,
                title = "Zack Snyder's Justice League",
                listOf(18),
                releaseDate = "2021-04-29",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                overview = "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions."
            )
        )


        remotemov.add(
            MovieResults(
                moviesId = 587807,
                title = "Tom & Jerry",
                listOf(35,
                    10751,
                    16),
                releaseDate = "2021-02-11",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yMoMlJo2msoVwmuf6k1C78MrB3H.jpg",
                overview = "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse."
            )
        )


        remotemov.add(
            MovieResults(
                moviesId = 632357,
                title = "The Unholy",
                listOf(27),
                releaseDate ="2021-03-31",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                overview = "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister."
            )
        )
        return remotemov
    }

    fun getRemoteTvShow(): ArrayList<TvResults> {

        val remoteTv = ArrayList<TvResults>()

        remoteTv.add(
            TvResults(
                tvId = 71712,
                title = "The Good Doctor",
                listOf(18),
                firstAirDate = "2017-09-25",
                overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"
            )
        )

        remoteTv.add(
            TvResults(
                tvId = 1416,
                title = "Grey's Anatomy",
                listOf(18),
                firstAirDate = "2005-03-27",
                overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"
            )
        )

        remoteTv.add(
            TvResults(
                tvId = 85271,
                title = "The Simpsons",
                listOf(10765,
                    9648,
                    18),
                firstAirDate = "1989-12-17",
                overview = "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg"
            )
        )

        remoteTv.add(
            TvResults(
                tvId = 88396,
                title = "The Falcon and the Winter Soldier",
                listOf(10765,
                    10759,
                    18,
                    10768),
                firstAirDate = "2021-03-19",
                overview = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                imagePath= "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg"
            )
        )

        remoteTv.add(
            TvResults(
                tvId = 95557,
                title = "Invincible",
                listOf(16,
                    10759,
                    18,
                    10765),
                firstAirDate = "2021-03-26",
                overview = "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg"
            )
        )
        return remoteTv
    }
}