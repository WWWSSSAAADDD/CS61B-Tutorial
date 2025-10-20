import org.junit.Test;

public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (Character.isLetter(x) && Character.isLetter(y)) {
            int diff = Character.toLowerCase(x) - Character.toLowerCase(y);
            return diff == 1 || diff == -1;
        }
        return false;
    }
}