package com.computermatcher.model;

public enum PriceRange { //wanted to use price ranges since someone within one hundred dollars or so will usually still look for products on tier
    BUDGET(0, 500),
    MID_RANGE(500, 1000),
    HIGH_END(1000, 1500),
    PREMIUM(1500, Double.MAX_VALUE);

    private final double minPrice;
    private final double maxPrice;

    PriceRange(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public boolean isInRange(double price) {
        return price >= minPrice && price < maxPrice;
    }

    public static PriceRange fromPrice(double price) {
        for (PriceRange range : values()) {
            if (range.isInRange(price)) {
                return range;
            }
        }
        return PREMIUM;
    }

    @Override
    public String toString() {
        if (this == PREMIUM) {
            return String.format("$%.0f+", minPrice);
        }
        return String.format("$%.0f - $%.0f", minPrice, maxPrice);
    }
}
