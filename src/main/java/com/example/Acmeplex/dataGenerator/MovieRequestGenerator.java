// package com.example.Acmeplex.dataGenerator;

// public class MovieRequestGenerator {

// public static List<MovieRequest> generateRequests() {
// List<MovieRequest> requests = new ArrayList<>();

// requests.add(new MovieRequest("Inception", "A thief who steals corporate
// secrets through the use of dream-sharing technology is given the inverse task
// of planting an idea into the mind of a CEO.", 148, Genre.SCI_FI,
// MovieType.FEATURE_FILM, LocalDate.of(2010, 7, 16), 8.8, "Christopher Nolan",
// Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"),
// "https://example.com/inception", "https://example.com/inception/trailer"));
// requests.add(new MovieRequest("The Matrix", "A computer hacker learns from
// mysterious rebels about the true nature of his reality and his role in the
// war against its controllers.", 136, Genre.SCI_FI, MovieType.FEATURE_FILM,
// LocalDate.of(1999, 3, 31), 8.7, "Lana Wachowski, Lilly Wachowski",
// Arrays.asList("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss"),
// "https://example.com/thematrix", "https://example.com/thematrix/trailer"));
// requests.add(new MovieRequest("Interstellar", "A team of explorers travel
// through a wormhole in space in an attempt to ensure humanity's survival.",
// 169, Genre.SCI_FI, MovieType.FEATURE_FILM, LocalDate.of(2014, 11, 7), 8.6,
// "Christopher Nolan", Arrays.asList("Matthew McConaughey", "Anne Hathaway",
// "Jessica Chastain"), "https://example.com/interstellar",
// "https://example.com/interstellar/trailer"));
// requests.add(new MovieRequest("The Godfather", "The aging patriarch of an
// organized crime dynasty transfers control of his clandestine empire to his
// reluctant son.", 175, Genre.DRAMA, MovieType.FEATURE_FILM, LocalDate.of(1972,
// 3, 24), 9.2, "Francis Ford Coppola", Arrays.asList("Marlon Brando", "Al
// Pacino", "James Caan"), "https://example.com/thegodfather",
// "https://example.com/thegodfather/trailer"));
// requests.add(new MovieRequest("Pulp Fiction", "The lives of two mob hitmen, a
// boxer, a gangster's wife, and a pair of diner bandits intertwine in four
// tales of violence and redemption.", 154, Genre.CRIME, MovieType.FEATURE_FILM,
// LocalDate.of(1994, 10, 14), 8.9, "Quentin Tarantino", Arrays.asList("John
// Travolta", "Uma Thurman", "Samuel L. Jackson"),
// "https://example.com/pulpfiction",
// "https://example.com/pulpfiction/trailer"));
// requests.add(new MovieRequest("The Dark Knight", "When the menace known as
// The Joker emerges from his mysterious past, he wreaks havoc and chaos on the
// people of Gotham.", 152, Genre.ACTION, MovieType.FEATURE_FILM,
// LocalDate.of(2008, 7, 18), 9.0, "Christopher Nolan", Arrays.asList("Christian
// Bale", "Heath Ledger", "Aaron Eckhart"), "https://example.com/thedarkknight",
// "https://example.com/thedarkknight/trailer"));
// requests.add(new MovieRequest("Fight Club", "An insomniac office worker and a
// devil-may-care soap maker form an underground fight club that evolves into
// something much more.", 139, Genre.DRAMA, MovieType.FEATURE_FILM,
// LocalDate.of(1999, 10, 15), 8.8, "David Fincher", Arrays.asList("Brad Pitt",
// "Edward Norton", "Meat Loaf"), "https://example.com/fightclub",
// "https://example.com/fightclub/trailer"));
// requests.add(new MovieRequest("Forrest Gump", "The presidencies of Kennedy
// and Johnson, the events of Vietnam, Watergate, and other historical events
// unfold from the perspective of an Alabama man with an IQ of 75.", 142,
// Genre.DRAMA, MovieType.FEATURE_FILM, LocalDate.of(1994, 7, 6), 8.8, "Robert
// Zemeckis", Arrays.asList("Tom Hanks", "Robin Wright", "Gary Sinise"),
// "https://example.com/forrestgump",
// "https://example.com/forrestgump/trailer"));
// requests.add(new MovieRequest("The Shawshank Redemption", "Two imprisoned men
// bond over a number of years, finding solace and eventual redemption through
// acts of common decency.", 142, Genre.DRAMA, MovieType.FEATURE_FILM,
// LocalDate.of(1994, 9, 23), 9.3, "Frank Darabont", Arrays.asList("Tim
// Robbins", "Morgan Freeman", "Bob Gunton"), "https://example.com/shawshank",
// "https://example.com/shawshank/trailer"));
// requests.add(new MovieRequest("Gladiator", "A former Roman General sets out
// to exact vengeance against the corrupt emperor who murdered his family and
// sent him into slavery.", 155, Genre.ACTION, MovieType.FEATURE_FILM,
// LocalDate.of(2000, 5, 5), 8.5, "Ridley Scott", Arrays.asList("Russell Crowe",
// "Joaquin Phoenix", "Connie Nielsen"), "https://example.com/gladiator",
// "https://example.com/gladiator/trailer"));
// requests.add(new MovieRequest("Titanic", "A seventeen-year-old aristocrat
// falls in love with a kind but poor artist aboard the luxurious, ill-fated
// R.M.S. Titanic.", 195, Genre.ROMANCE, MovieType.FEATURE_FILM,
// LocalDate.of(1997, 12, 19), 7.8, "James Cameron", Arrays.asList("Leonardo
// DiCaprio", "Kate Winslet", "Billy Zane"), "https://example.com/titanic",
// "https://example.com/titanic/trailer"));
// requests.add(new MovieRequest("Saving Private Ryan", "Following the Normandy
// Landings, a group of U.S. soldiers go behind enemy lines to retrieve a
// paratrooper whose brothers have been killed in action.", 169, Genre.WAR,
// MovieType.FEATURE_FILM, LocalDate.of(1998, 7, 24), 8.6, "Steven Spielberg",
// Arrays.asList("Tom Hanks", "Matt Damon", "Tom Sizemore"),
// "https://example.com/savingprivateryan",
// "https://example.com/savingprivateryan/trailer"));
// requests.add(new MovieRequest("Schindler's List", "In German-occupied Poland
// during World War II, Oskar Schindler gradually becomes concerned for his
// Jewish workforce after witnessing their persecution by the Nazis.", 195,
// Genre.BIOGRAPHY, MovieType.FEATURE_FILM, LocalDate.of(1993, 12, 15), 8.9,
// "Steven Spielberg", Arrays.asList("Liam Neeson", "Ralph Fiennes", "Ben
// Kingsley"), "https://example.com/schindlerslist",
// "https://example.com/schindlerslist/trailer"));
// requests.add(new MovieRequest("The Lion King", "Lion prince Simba and his
// father are targeted by his bitter uncle, who wants to ascend the throne
// himself.", 88, Genre.ANIMATION, MovieType.FEATURE_FILM, LocalDate.of(1994, 6,
// 24), 8.5, "Roger Allers, Rob Minkoff", Arrays.asList("Matthew Broderick",
// "Jeremy Irons", "James Earl Jones"), "https://example.com/thelionking",
// "https://example.com/thelionking/trailer"));
// requests.add(new MovieRequest("The Avengers", "Earth's mightiest heroes must
// come together and learn to fight as a team if they are to stop the
// mischievous Loki and his alien army from enslaving humanity.", 143,
// Genre.ACTION, MovieType.FEATURE_FILM, LocalDate.of(2012, 5, 4), 8.0, "Joss
// Whedon", Arrays.asList("Robert Downey Jr.", "Chris Evans", "Scarlett
// Johansson"), "https://example.com/theavengers",
// "https://example.com/theavengers/trailer"));
// requests.add(new MovieRequest("Avatar", "A paraplegic Marine dispatched to
// the moon Pandora on a unique mission becomes torn between following his
// orders and protecting the world he feels is his home.", 162, Genre.SCI_FI,
// MovieType.FEATURE_FILM, LocalDate.of(2009, 12, 18), 7.8, "James Cameron",
// Arrays.asList("Sam Worthington", "Zoe Saldana", "Sigourney Weaver"),
// "https://example.com/avatar", "https://example.com/avatar/trailer"));
// requests.add(new MovieRequest("Jurassic Park", "During a preview tour, a
// theme park suffers a major power breakdown that allows its cloned dinosaur
// exhibits to run amok.", 127, Genre.SCI_FI, MovieType.FEATURE_FILM,
// LocalDate.of(1993, 6, 11), 8.1, "Steven Spielberg", Arrays.asList("Sam
// Neill", "Laura Dern", "Jeff Goldblum"), "https://example.com/jurassicpark",
// "https://example.com/jurassicpark/trailer"));
// }