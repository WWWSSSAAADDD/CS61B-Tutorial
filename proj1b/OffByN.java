public class OffByN implements CharacterComparator{
    private int offVal;
    public OffByN(int N) {
        offVal = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return x - y == offVal || x - y == -offVal;
    }
}