package com.example.Acmeplex.dataGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.enums.Genre;
import com.example.Acmeplex.repositories.MovieRepository;
import com.example.Acmeplex.request.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieRequestGenerator {

    public static List<MovieRequest> generateRequests() {
        List<MovieRequest> requests = new ArrayList<>();

        // Movie1: Dune (2021)
        requests.add(new MovieRequest(
                "Dune",
                "Feature adaptation of Frank Herbert's science fiction novel about the son of a noble family entrusted with the protection of the most valuable asset and most vital element in the galaxy.",
                155,
                Genre.SCI_FI,
                LocalDate.of(2021, 10, 22),
                8.1,
                "Denis Villeneuve",
                Arrays.asList("Timothée Chalamet", "Rebecca Ferguson", "Zendaya"),
                "https://image.tmdb.org/t/p/original/jYEW5xZkZk2WTrdbMGAPFuBqbDc.jpg",      // bannerUrl
                "https://media.themoviedb.org/t/p/w440_and_h660_face/gDzOcq0pfeCeqMBwKIJlSmQpjkZ.jpg",    // carouselUrl
                "https://www.themoviedb.org/video/play?key=n9xhJrPXop4"                    // trailerUrl
        ));

        // Movie2: No Time to Die (2021)
        requests.add(new MovieRequest(
                "No Time to Die",
                "James Bond has left active service. His peace is short-lived when Felix Leiter, an old friend from the CIA, turns up asking for help.",
                163,
                Genre.ACTION,
                LocalDate.of(2021, 10, 8),
                7.3,
                "Cary Joji Fukunaga",
                Arrays.asList("Daniel Craig", "Ana de Armas", "Rami Malek"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/bz7pwNGCbV576COsDcYN9MbEACC.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/iUgygt3fscRoKWCV1d0C7FbM9TP.jpg",
                "https://www.themoviedb.org/video/play?key=BIhNsAtPbPI"
        ));

        // Movie3: Spider-Man: No Way Home (2021)
        requests.add(new MovieRequest(
                "Spider-Man: No Way Home",
                "With Spider-Man's identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear.",
                148,
                Genre.ACTION,
                LocalDate.of(2021, 12, 17),
                8.4,
                "Jon Watts",
                Arrays.asList("Tom Holland", "Zendaya", "Benedict Cumberbatch"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/zD5v1E4joAzFvmAEytt7fM3ivyT.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "https://www.themoviedb.org/video/play?key=WgU7P6o-GkM"
        ));

        // Movie4: The Batman (2022)
        requests.add(new MovieRequest(
                "The Batman",
                "When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption.",
                176,
                Genre.ACTION,
                LocalDate.of(2022, 3, 4),
                7.9,
                "Matt Reeves",
                Arrays.asList("Robert Pattinson", "Zoë Kravitz", "Paul Dano"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/b0PlSFdDwbyK0cf5RxwDpaOJQvQ.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/74xTEgt7R36Fpooo50r9T25onhq.jpg",
                "https://www.themoviedb.org/video/play?key=NLOp_6uPccQ"
        ));

        // Movie5: Top Gun: Maverick (2022)
        requests.add(new MovieRequest(
                "Top Gun: Maverick",
                "After more than thirty years of service, Maverick is still pushing the envelope as a top naval aviator but must confront ghosts of his past.",
                131,
                Genre.ACTION,
                LocalDate.of(2022, 5, 27),
                8.6,
                "Joseph Kosinski",
                Arrays.asList("Tom Cruise", "Jennifer Connelly", "Miles Teller"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/AaV1YIdWKnjAIAOe8UUKBFm327v.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/62HCnUTziyWcpDaBO2i1DX17ljH.jpg",
                "https://www.themoviedb.org/video/play?key=qSqVVswa420"
        ));

        // Movie6: Black Widow (2021)
        requests.add(new MovieRequest(
                "Black Widow",
                "Natasha Romanoff confronts the darker parts of her ledger when a dangerous conspiracy with ties to her past arises.",
                134,
                Genre.ACTION,
                LocalDate.of(2021, 7, 9),
                6.7,
                "Cate Shortland",
                Arrays.asList("Scarlett Johansson", "Florence Pugh", "David Harbour"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/keIxh0wPr2Ymj0Btjh4gW7JJ89e.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/qAZ0pzat24kLdO3o8ejmbLxyOac.jpg",
                "https://www.themoviedb.org/video/play?key=RxAtuMu_ph4"
        ));

        // Movie7: Shang-Chi and the Legend of the Ten Rings (2021)
        requests.add(new MovieRequest(
                "Shang-Chi and the Legend of the Ten Rings",
                "Shang-Chi, the master of weaponry-based Kung Fu, is forced to confront his past after being drawn into the Ten Rings organization.",
                132,
                Genre.ACTION,
                LocalDate.of(2021, 9, 3),
                7.4,
                "Destin Daniel Cretton",
                Arrays.asList("Simu Liu", "Awkwafina", "Tony Chiu-Wai Leung"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/r7K6Xt0RX4Mw0cAbZVw5cyb1Tux.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/9f2Q0U3IOsLgrI2HkvldwSABZy5.jpg",
                "https://www.themoviedb.org/video/play?key=8YjFbMbfXaQ"
        ));

        // Movie8: Eternals (2021)
        requests.add(new MovieRequest(
                "Eternals",
                "The saga of the Eternals, a race of immortal beings who lived on Earth and shaped its history and civilizations.",
                156,
                Genre.SCI_FI,
                LocalDate.of(2021, 11, 5),
                6.4,
                "Chloé Zhao",
                Arrays.asList("Gemma Chan", "Richard Madden", "Angelina Jolie"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/c6H7Z4u73ir3cIoCteuhJh7UCAR.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/lFByFSLV5WDJEv3KabbdAF959F2.jpg",
                "https://www.themoviedb.org/video/play?key=0WVDKZJkGlY"
        ));

        // Movie9: Doctor Strange in the Multiverse of Madness (2022)
        requests.add(new MovieRequest(
                "Doctor Strange in the Multiverse of Madness",
                "Dr. Stephen Strange casts a forbidden spell that opens the doorway to the multiverse, including alternate versions of himself.",
                126,
                Genre.SCI_FI,
                LocalDate.of(2022, 5, 6),
                7.0,
                "Sam Raimi",
                Arrays.asList("Benedict Cumberbatch", "Elizabeth Olsen", "Chiwetel Ejiofor"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/gUNRlH66yNDH3NQblYMIwgZXJ2u.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg",
                "https://www.themoviedb.org/video/play?key=aWzlQ2N6qqg"
        ));

        // Movie10: Jurassic World Dominion (2022)
        requests.add(new MovieRequest(
                "Jurassic World Dominion",
                "Four years after the destruction of Isla Nublar, dinosaurs now live alongside humans. The fragile balance will reshape the future and determine whether humans are to remain the apex predators.",
                146,
                Genre.SCI_FI,
                LocalDate.of(2022, 6, 10),
                5.7,
                "Colin Trevorrow",
                Arrays.asList("Chris Pratt", "Bryce Dallas Howard", "Sam Neill"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/698FjyzLdpgXmUSr63LaRwblTmx.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/kAVRgw7GgK1CfYEJq8ME6EvRIgU.jpg",
                "https://www.themoviedb.org/video/play?key=UtxAPXT4174"
        ));

        // Movie11: Encanto (2021)
        requests.add(new MovieRequest(
                "Encanto",
                "A young Colombian girl has to face the frustration of being the only member of her family without magical powers.",
                102,
                Genre.ANIMATION,
                LocalDate.of(2021, 11, 24),
                7.3,
                "Byron Howard, Jared Bush",
                Arrays.asList("Stephanie Beatriz", "María Cecilia Botero", "John Leguizamo"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/5i3ghCXVLNhewrBjTesMgy4FHT7.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                "https://www.youtube.com/watch?v=CaimKeDcudo"
        ));

        // Movie12: The Suicide Squad (2021)
        requests.add(new MovieRequest(
                "The Suicide Squad",
                "Supervillains Harley Quinn, Bloodsport, Peacemaker, and a collection of cons join the super-secret, super-shady Task Force X.",
                132,
                Genre.ACTION,
                LocalDate.of(2021, 8, 6),
                7.2,
                "James Gunn",
                Arrays.asList("Margot Robbie", "Idris Elba", "John Cena"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/jlGmlFOcfo8n5tURmhC7YVd4Iyy.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/kb4s0ML0iVZlG6wAKbbs9NAm6X.jpg",
                "https://www.youtube.com/watch?v=PDARAdc04oM"
        ));

        // Movie13: Dune: Part Two (Upcoming)
        requests.add(new MovieRequest(
                "Dune: Part Two",
                "The saga continues as Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
                0, // Duration unknown for upcoming films
                Genre.SCI_FI,
                LocalDate.of(2023, 11, 3),
                0.0, // Rating not available for upcoming films
                "Denis Villeneuve",
                Arrays.asList("Timothée Chalamet", "Zendaya", "Rebecca Ferguson"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
                "https://www.youtube.com/watch?v=Way9Dexny3w" // Placeholder until official trailer is released
        ));

        // Movie14: Lightyear (2022)
        requests.add(new MovieRequest(
                "Lightyear",
                "The story of Buzz Lightyear and his adventures to infinity and beyond.",
                105,
                Genre.ANIMATION,
                LocalDate.of(2022, 6, 17),
                6.0,
                "Angus MacLane",
                Arrays.asList("Chris Evans", "Keke Palmer", "Peter Sohn"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/hwUxHPkuUleJeoick4uZsrKDXxF.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/ox4goZd956BxqJH6iLwhWPL9ct4.jpg",
                "https://www.youtube.com/watch?v=BwPL0Md_QFQ"
        ));

        // Movie15: Thor: Love and Thunder (2022)
        requests.add(new MovieRequest(
                "Thor: Love and Thunder",
                "Thor enlists the help of Valkyrie, Korg, and ex-girlfriend Jane Foster to fight Gorr the God Butcher.",
                119,
                Genre.ACTION,
                LocalDate.of(2022, 7, 8),
                6.4,
                "Taika Waititi",
                Arrays.asList("Chris Hemsworth", "Natalie Portman", "Christian Bale"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/jsoz1HlxczSuTx0mDl2h0lxy36l.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/pIkRyD18kl4FhoCNQuWxWu5cBLM.jpg",
                "https://www.youtube.com/watch?v=Go8nTmfrQd8"
        ));

        // Movie16: Black Panther: Wakanda Forever (2022)
        requests.add(new MovieRequest(
                "Black Panther: Wakanda Forever",
                "The nation of Wakanda fights to protect their home from intervening world powers as they mourn the death of King T'Challa.",
                161,
                Genre.ACTION,
                LocalDate.of(2022, 11, 11),
                7.3,
                "Ryan Coogler",
                Arrays.asList("Letitia Wright", "Lupita Nyong'o", "Danai Gurira"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/b6ZJZHUdMEFECvGiDpJjlfUWela.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/uxzzxijgPIY7slzFvMotPv8wjKA.jpg",
                "https://www.youtube.com/watch?v=dxWvtMOGAhw"
        ));

        // Movie17: The Flash (Upcoming 2023)
        requests.add(new MovieRequest(
                "The Flash",
                "Barry Allen uses his super speed to change the past, but his attempt to save his family creates a world without superheroes.",
                0,
                Genre.ACTION,
                LocalDate.of(2023, 6, 16),
                0.0,
                "Andy Muschietti",
                Arrays.asList("Ezra Miller", "Michael Keaton", "Ben Affleck"),
                "https://media.themoviedb.org/t/p/w1000_and_h563_face/goqjRP9UBEf6ztinVt46FdoUwDC.jpg",
                "https://media.themoviedb.org/t/p/w440_and_h660_face/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "https://www.youtube.com/watch?v=IgVyroQjZbE"
        ));

        return requests;
    }

}