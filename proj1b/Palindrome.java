public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> returnDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            returnDeque.addLast(word.charAt(i));
        }
        return returnDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);

        if (d.size() <= 1) {
            return true;
        } else {
            while (!d.isEmpty()) {
                Character firstChar = (Character) d.removeFirst();
                Character lastChar = (Character) d.removeLast();
//                System.out.println("firstChar: " + firstChar);
//                System.out.println("lastChar: " + lastChar);
                if (lastChar != null && firstChar != lastChar) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        } else {
            int begin = 0;
            int end = word.length() - 1;
            while (begin < end) {
                if (!cc.equalChars(word.charAt(begin), word.charAt(end))) {
                    return false;
                }
                ++begin;
                --end;
            }
        }
        return true;
    }
}
