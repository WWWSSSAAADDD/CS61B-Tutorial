import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = (Deque) palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String s1 = "abcddcba";
        assertTrue(palindrome.isPalindrome(s1));

        String s2 = "abcd";
        assertFalse(palindrome.isPalindrome(s2));

        String empty = "";
        assertTrue(palindrome.isPalindrome(empty));

        String single = "a";
        assertTrue(palindrome.isPalindrome(single));

        /** test for method 2*/
        CharacterComparator cc = new OffByOne();

        assertTrue(palindrome.isPalindrome("aabb", cc));

        assertFalse(palindrome.isPalindrome(s1, cc));

        assertFalse(palindrome.isPalindrome(s2, cc));

        assertTrue(palindrome.isPalindrome(empty, cc));

        assertTrue(palindrome.isPalindrome(single, cc));
    }
}
