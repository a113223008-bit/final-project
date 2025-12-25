import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {
    private List<Dish> menu = new ArrayList<>();
    private List<Order> allOrders = new ArrayList<>();

    /**
     * 新增菜品至菜單
     * [TC-R001] 正常新增菜品 [cite: 70, 72]
     * [TC-R002] 防止 ID 重複 [cite: 73, 76]
     */
    public void addDishToMenu(Dish dish) {
        if (findDishById(dish.getDishId()) != null) {
            // 若 ID 已存在，拋出 IllegalArgumentException
            throw new IllegalArgumentException("Dish with this ID already exists");
        }
        menu.add(dish); // 成功新增至菜單 [cite: 72]
    }

    /**
     * 依 ID 搜尋菜品
     * [TC-R003] 正常查詢 [cite: 77, 79, 80]
     * [TC-R004] 查詢無結果回傳 null [cite: 81, 82]
     */
    public Dish findDishById(String id) {
        return menu.stream()
                .filter(d -> d.getDishId().equals(id))
                .findFirst()
                .orElse(null); // 找不到時回傳 null [cite: 82]
    }

    /**
     * 關鍵字搜尋菜品 (不分大小寫)
     * [TC-L001] 包含關鍵字搜尋 [cite: 92, 94, 95]
     * [TC-L002] 無結果回傳空清單 [cite: 96, 98]
     */
    public List<Dish> findDishesByKeyword(String keyword) {
        String lowerKey = keyword.toLowerCase(); // 轉換為小寫進行比對
        List<Dish> results = new ArrayList<>();
        for (Dish d : menu) {
            if (d.getName().toLowerCase().contains(lowerKey)) {
                results.add(d); // 加入符合條件的菜品 [cite: 95]
            }
        }
        return results; // 回傳清單，無結果則為空
    }

    /**
     * 取得所有可訂購菜品
     * [TC-R005] 篩選 isAvailable 為 true 的項目 [cite: 83, 85, 86]
     */
    public List<Dish> getAvailableDishes() {
        return menu.stream()
                .filter(Dish::isAvailable)
                .collect(Collectors.toList()); // 回傳所有可訂購的菜品 [cite: 86]
    }

    /**
     * 顧客成功下訂單
     * [TC-R006] 將訂單新增至餐廳紀錄 [cite: 87, 89, 90]
     */
    public void placeOrder(Order order) {
        allOrders.add(order); // 訂單成功新增 [cite: 90]
    }
}