import java.util.*;

public class Order {
    private String orderId;
    private List<Dish> dishes = new ArrayList<>();
    private String status = "未處理";

    public Order(String orderId, List<Dish> dishes) {
        this.orderId = orderId;
        for (Dish d : dishes) {
            if (!d.isAvailable()) throw new IllegalArgumentException("Some dishes are unavailable"); //
            this.dishes.add(d);
        }
    }

    public double calculateTotal() {
        return dishes.stream().mapToDouble(Dish::getPrice).sum(); // [cite: 52]
    }

    public void addDish(Dish dish) {
        if (!dish.isAvailable()) throw new IllegalArgumentException("Dish is unavailable"); // [cite: 53, 56]
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        if (!dishes.contains(dish)) throw new IllegalArgumentException("Dish is not in the order"); // [cite: 57, 60]
        dishes.remove(dish);
    }

    public void markAsDelivered() {
        if (status.equals("已送達")) throw new IllegalStateException("Order cannot be marked as delivered"); // [cite: 65, 68]
        this.status = "已送達"; // [cite: 61, 64]
    }
}