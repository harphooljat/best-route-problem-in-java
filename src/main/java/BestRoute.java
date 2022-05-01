public class BestRoute {
    private int cost;
    private int[] route;

    public BestRoute(int cost, int[] route) {
        this.cost = cost;
        this.route = route;
    }

    public int getCost() {
        return this.cost;
    }

    public int[] getRoute() {
        return route;
    }
}
