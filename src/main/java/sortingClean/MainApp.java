package sortingClean;

public class MainApp {
    public static void main(String[] args) {
        AlgorithmRunner algorithmRunner = new AlgorithmRunner();
        algorithmRunner.runAlgorithms();

        SortAlgorithmAspect aspect = new SortAlgorithmAspect();
        aspect.printReport();
    }
}
