package com.computermatcher.model;

public class Computer {
    private String name;
    private double price;
    private boolean dedicatedGPU;
    private boolean portable;
    private boolean highScreenQuality;
    private String specifications;

    public Computer(String name, double price, boolean dedicatedGPU, 
                   boolean portable, boolean highScreenQuality, String specifications) {
        this.name = name;
        this.price = price;
        this.dedicatedGPU = dedicatedGPU;
        this.portable = portable;
        this.highScreenQuality = highScreenQuality;
        this.specifications = specifications;
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean hasDedicatedGPU() { return dedicatedGPU; }
    public boolean isPortable() { return portable; }
    public boolean hasHighScreenQuality() { return highScreenQuality; }
    public String getSpecifications() { return specifications; }

    public int calculateMatchScore(boolean wantsDedicatedGPU, 
                                 boolean wantsPortable, 
                                 boolean wantsHighScreenQuality) {
        int score = 0;
        if (dedicatedGPU == wantsDedicatedGPU) score++;
        if (portable == wantsPortable) score++;
        if (highScreenQuality == wantsHighScreenQuality) score++;
        return score;
    }

    @Override
    public String toString() {
        return String.format("""
            Computer: %s
            Price: $%.2f
            Specifications: %s
            Features:
              - Dedicated GPU: %s
              - Portable: %s
              - High Screen Quality: %s
            """,
            name, price, specifications,
            dedicatedGPU ? "Yes" : "No",
            portable ? "Yes" : "No",
            highScreenQuality ? "Yes" : "No"
        );
    }
}
