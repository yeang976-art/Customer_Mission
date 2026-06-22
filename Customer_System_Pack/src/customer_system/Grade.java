package customer_system;

public enum Grade {
    SIENNA, COBALT, DANDELION, CYAN, SCARLET, CORAL, ARGENTO, ELDORA, CRIMSON, IMPERIAL;

    public double discount() {
        return switch (this) {
            case SIENNA -> 0.0;
            case COBALT -> 0.005;
            case DANDELION -> 0.01;
            case CYAN -> 0.02;
            case SCARLET -> 0.04;
            case CORAL -> 0.07;
            case ARGENTO -> 0.15;
            case ELDORA -> 0.3;
            case CRIMSON -> 0.5;
            case IMPERIAL -> 0.8;
        };
    }
}
