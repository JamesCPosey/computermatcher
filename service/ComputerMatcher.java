package com.computermatcher.service;

import com.computermatcher.model.Computer;
import com.computermatcher.model.PriceRange;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComputerMatcher { //creates matching system and preloads devices, as this tool is to get a general idea instead of a final decision
    private final List<Computer> computers;

    public ComputerMatcher() {
        computers = new ArrayList<>();
        initializeComputers();
    }

    private void initializeComputers() {
        // Budget computers
        computers.add(new Computer(
            "Basic Home PC", 449.99, false, false, false,
            "Intel Core i3, 8GB RAM, 256GB SSD"
        ));
        computers.add(new Computer(
            "Budget Laptop", 399.99, false, true, false,
            "AMD Ryzen 3, 8GB RAM, 128GB SSD"
        ));

        // Mid-range computers
        computers.add(new Computer(
            "Gaming Desktop", 899.99, true, false, false,
            "AMD Ryzen 5, 16GB RAM, RTX 3060, 512GB SSD"
        ));
        computers.add(new Computer(
            "Professional Laptop", 799.99, false, true, true,
            "Intel Core i5, 16GB RAM, 512GB SSD, 1440p Display"
        ));
        computers.add(new Computer(
            "Powerful Mini PC", 767.00, true, true, false,
            "AMD Ryzen 7 7840H, 32GB RAM, 1TB SSD"
        ));
        
        // High-end computers
        computers.add(new Computer(
            "Creator Workstation", 1299.99, true, false, true,
            "AMD Ryzen 7, 32GB RAM, RTX 3070, 1TB SSD"
        ));
        computers.add(new Computer(
            "Premium Ultrabook", 1399.99, false, true, true,
            "Intel Core i7, 16GB RAM, 1TB SSD, 4K Display"
        ));
        computers.add(new Computer(
            "Premium Tablet", 1199.99, false, true, true,
            "Qualcomm Snapdragon X, 16GB RAM, 512GB SSD, 4k Display"
        ));
        // Professional/Enthusiast computers, for those who put a lot of money into these
        computers.add(new Computer(
            "Ultimate Gaming Rig", 1999.99, true, false, true,
            "AMD Ryzen 9, 64GB RAM, RTX 4080, 2TB SSD"
        ));
        computers.add(new Computer(
            "Professional Workstation", 2499.99, true, false, true,
            "Intel Core i9, 128GB RAM, RTX 4090, 4TB SSD"
        ));
    }

    public List<Computer> findMatches(double budget, 
                                    boolean needsDedicatedGPU,
                                    boolean needsPortable, 
                                    boolean needsHighScreenQuality) {
        PriceRange targetRange = PriceRange.fromPrice(budget);
        
        return computers.stream()
            .filter(computer -> computer.getPrice() <= budget)
            .sorted((c1, c2) -> {
                int score1 = c1.calculateMatchScore(needsDedicatedGPU, needsPortable, needsHighScreenQuality);
                int score2 = c2.calculateMatchScore(needsDedicatedGPU, needsPortable, needsHighScreenQuality);
                return Integer.compare(score2, score1); // Higher scores first
            })
            .collect(Collectors.toList());
    }
}
