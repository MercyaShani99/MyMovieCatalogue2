package com.example.mymoviecatalogue2.utils

import com.example.mymoviecatalogue2.data.source.local.entity.Movie
import com.example.mymoviecatalogue2.data.source.local.entity.TvShow
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailResults
import com.example.mymoviecatalogue2.data.source.remote.response.detail.DetailTvResults
import com.example.mymoviecatalogue2.data.source.remote.response.movie.MovieGenres

object DetailDummy {
    fun getDetailMovie(movieId: Int?): Movie {
        return Movie(
            399566,
            "Godzilla vs Kong",
//            listOf("Action", "Science Fiction", "Drama"),
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "2021-03-24",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg"

        )
    }

    fun getDetailTv(tvId: Int?): TvShow {
        return TvShow(
            1416,
            "The Falcon and the Winter Soldier",
//            listOf("Sci-Fi & Fantasy", "Action & Adventure", "Drama", "War & Politics"),
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "2021-03-19",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg"
        )
    }

    fun getRemoteDetailMovie(): DetailResults {
        return DetailResults(
            moviesId = 632357,
            title = "The Unholy",
            genre = listOf(
                MovieGenres(
                    id = 123,
                    name = "Horror"
                )
            ),
            releaseDate = "2021-03-31",
            imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
            overview = "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister"
        )
    }

    fun getRemoteDetailTvShow(): DetailTvResults {
        return DetailTvResults(
            tvId = 1416,
            title = "Grey's Anatomy",
            genre = listOf(
                MovieGenres(
                    id = 112,
                    name = "Drama"
                )
            ),
            firstAirDate = "2005-03-27",
            imagePath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
        )
    }
}