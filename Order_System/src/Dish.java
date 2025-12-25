public class Dish {
    private String dishId;
    private String name;
    private double price;
    private boolean isAvailable = true;

    public Dish(String dishId, String name, double price) {
        if (dishId == null || dishId.isEmpty())
            throw new IllegalArgumentException("dishId cannot be null or empty"); // [cite: 11, 13]
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("name cannot be null or empty"); // [cite: 14, 16]
        if (price <= 0)
            throw new IllegalArgumentException("Price must be greater than 0"); // [cite: 17, 19]

        this.dishId = dishId;
        this.name = name;
        this.price = price;
    }

    public void markAsUnavailable() {
        if (!isAvailable) throw new IllegalStateException("Dish is already unavailable"); // [cite: 24, 27]
        this.isAvailable = false; // [cite: 20, 23]
    }

    public void markAsAvailable() {
        if (isAvailable) throw new IllegalStateException("Dish is already available"); // [cite: 32, 35]
        this.isAvailable = true; // [cite: 28, 31]
    }

    // Getters
    public String getDishId() { return dishId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }
}
