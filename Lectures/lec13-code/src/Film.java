public class Film {

    /**
     * The name of the film. Must not be empty.
     */
    String name;

    /**
     * The year that the film was released. Must be greater than or equal to 1980.
     */
    int releaseYear;

    /**
     * The month that the film was released. Must be between 1 and 12 (inclusive).
     */
    int releaseMonth;

    /**
     * Assert that this object satisfies its class invariants.
     */
    private void assertInv() {
        assert name != null && !name.isEmpty();
        assert releaseYear >= 1980;
        assert 1 <= releaseMonth && releaseMonth <= 12;
    }

    /**
     * Constructs a new Film with name `filmName` released at `month` month of
     * `year` year. Requires `filmName` is not empty, and `month` is between
     * 1 and 12, and `year` is on or after 1980.
     */
    public Film(String filmName, int month, int year) {
        // assert check for preconditions
        assert filmName != null && !filmName.isEmpty();
        assert 1 <= month && month <= 12;
        assert 1980 <= year;

        this.name = filmName;
        this.releaseYear = year;
        this.releaseMonth = month;
        assertInv();
    }

    /**
     * TODO: Compare two films based on chronological order (if this film released
     * before `otherFilm`, this method should return -1).
     *
     * Once you have implemented `compareTo()`, you can test your implementation
     * by running the unit tests in FilmTest.java.
     */

}
