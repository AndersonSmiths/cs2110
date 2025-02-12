package discussion9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;


@DisplayName("A HashDict...")
public class HashDictTest {

    @Test
    @DisplayName("should be empty when first constructed")
    void testConstructor() {
        HashDict<String, Object> dict = new HashDict<>();
        assertEquals(0, dict.size());
        assertEquals(0, dict.loadFactor());
    }

    @Nested
    @DisplayName("should increase in size after associating values with new keys...")
    class PutTest {

        @Test
        @DisplayName("when empty")
        void testPutEmpty() {
            HashDict<String, Object> dict = new HashDict<>();
            dict.put("key1", "value1");
            assertEquals(1, dict.size());
        }

        @Test
        @DisplayName("when other keys are also present")
        void testPutMulti() {
            HashDict<String, Object> dict = new HashDict<>();
            // Add enough entries to trigger a resize (assumes initial capacity < 85)
            for (int i = 1; i <= 64; ++i) {
                dict.put("key" + i, "value" + i);
                assertEquals(i, dict.size());
            }
        }
    }

    @Test
    @DisplayName("should not change size when replacing previously associated values")
    void testReplaceSize() {
        HashDict<String, Object> dict = new HashDict<>();
        dict.put("key", "value");
        assertEquals(1, dict.size());
        // Add enough entries to trigger a resize (assumes initial capacity < 85)
        for (int i = 1; i <= 64; ++i) {
            dict.put("key", "value" + i);
            assertEquals(1, dict.size());
        }
    }

    @Nested
    @DisplayName("should fail to get associated values...")
    class GetThrowsTest {

        @Test
        @DisplayName("when empty")
        void testGetEmpty() {
            HashDict<String, Object> dict = new HashDict<>();
            assertThrows(NoSuchElementException.class, () -> dict.get("key"));
        }

        @Test
        @DisplayName("when it only contains other keys")
        void testGetOtherKey() {
            HashDict<String, Object> dict = new HashDict<>();
            // Add enough entries to trigger a resize (assumes initial capacity < 85)
            for (int i = 1; i <= 64; ++i) {
                dict.put("key" + i, "value" + i);
                String badKey = i + "key";
                assertThrows(NoSuchElementException.class, () -> dict.get(badKey));
            }
        }
    }

    @Nested
    @DisplayName("should get associated values...")
    class GetReturnsTest {

        @Test
        @DisplayName("when only one key is present")
        void testGetOne() {
            HashDict<String, Object> dict = new HashDict<>();
            dict.put("key1", "value1");
            assertEquals("value1", dict.get(new String("key1")));
        }

        @Test
        @DisplayName("when other keys are also present")
        void testGetMulti() {
            HashDict<String, Object> dict = new HashDict<>();
            // Add enough entries to trigger a resize (assumes initial capacity < 85)
            for (int i = 1; i <= 64; ++i) {
                dict.put("key" + i, "value" + i);
                assertEquals(i, dict.size());
            }
            for (int i = 1; i <= 64; ++i) {
                assertEquals("value" + i, dict.get("key" + i));
            }
        }
    }

    @Test
    @DisplayName("should get new associated values after replacing previously associated values")
    void testReplaceGet() {
        HashDict<String, Object> dict = new HashDict<>();
        // Add enough entries to trigger a resize (assumes initial capacity < 85)
        for (int i = 1; i <= 64; ++i) {
            dict.put("key", "value" + i);
            assertEquals("value" + i, dict.get("key"));
        }
    }

    @Test
    @DisplayName("should not exceed its load factor limit when entries are added")
    void testLoadFactorLimit() {
        double maxLoadFactor = 0.75;
        HashDict<String, Object> dict = new HashDict<>();
        // Add enough entries to trigger a resize (assumes initial capacity < 85)
        for (int i = 1; i <= 64; ++i) {
            dict.put("key" + i, "value" + i);
            assertTrue(dict.loadFactor() <= maxLoadFactor);
        }
    }
}
