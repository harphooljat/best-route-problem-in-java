public class Distance implements Comparable<Distance> {

    private int node;
    private int value;

    public Distance(int n, int v) {
        this.node = n;
        this.value = v;
    }

    public int getNode() {
        return this.node;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int compareTo(Distance o) {
        if (this.value > o.value) {
            return 1;
        } else if (this.value < o.value) {
            return -1;
        } else {
            return 0;
        }
    }
}