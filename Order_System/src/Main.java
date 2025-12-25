import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();
        List<Dish> currentOrderDishes = new ArrayList<>();

        // 初始化：預先新增 10 道菜品
        try {
            restaurant.addDishToMenu(new Dish("經典炒飯", "經典炒飯", 120.0)); // [TC-R001]
            restaurant.addDishToMenu(new Dish("牛肉麵", "牛肉麵", 150.0));
            restaurant.addDishToMenu(new Dish("排骨飯", "排骨飯", 110.0));
            restaurant.addDishToMenu(new Dish("小籠包", "小籠包", 100.0));
            restaurant.addDishToMenu(new Dish("酸辣湯", "酸辣湯", 50.0));
            restaurant.addDishToMenu(new Dish("珍珠奶茶", "珍珠奶茶", 60.0));
            restaurant.addDishToMenu(new Dish("紅豆餅", "紅豆餅", 30.0));
            restaurant.addDishToMenu(new Dish("炸雞排", "炸雞排", 85.0));
            restaurant.addDishToMenu(new Dish("燙青菜", "燙青菜", 40.0));
            restaurant.addDishToMenu(new Dish("滷味拼盤", "滷味拼盤", 90.0));
        } catch (IllegalArgumentException e) {
            System.out.println("初始化錯誤: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n========= 訂餐系統選單 =========");
            System.out.println("1. 瀏覽/搜尋菜單");
            System.out.println("2. 加入購物車 (ID)");
            System.out.println("3. 結帳並列印明細");
            System.out.println("4. 退出系統");
            System.out.print("請選擇操作: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("請輸入搜尋關鍵字 (直接按 Enter 顯示全部): ");
                    String key = scanner.nextLine();
                    List<Dish> results = restaurant.findDishesByKeyword(key); // [TC-L001]
                    System.out.println("\n----- 菜單清單 -----");
                    for (Dish d : results) {
                        System.out.printf("%-5s %-10s $%6.1f %s\n",
                                d.getDishId(), d.getName(), d.getPrice(), (d.isAvailable() ? "" : "[已售罄]"));
                    }
                    break;

                case "2":
                    System.out.print("請輸入欲加入的菜品 ID: ");
                    String id = scanner.nextLine();
                    Dish found = restaurant.findDishById(id); // [TC-R003]
                    if (found != null && found.isAvailable()) {
                        currentOrderDishes.add(found); // [TC-C001]
                        System.out.println("✅ 已加入: " + found.getName() + found.getPrice());
                    } else {
                        System.out.println("❌ 錯誤: 找不到該菜品或目前不可訂購。");
                    }
                    break;

                case "3":
                    // [TC-O001 / TC-R006] 提交訂單並產生明細
                    if (currentOrderDishes.isEmpty()) {
                        System.out.println("⚠️ 購物車目前是空的，無法結帳。");
                    } else {
                        Order order = new Order("ORD-" + System.currentTimeMillis(), new ArrayList<>(currentOrderDishes));
                        restaurant.placeOrder(order);

                        // --- 明細表列印區 ---
                        System.out.println("\n================================");
                        System.out.println("       🧾 訂餐結帳明細表");
                        System.out.println("================================");
                        System.out.printf("%-15s %10s\n", "品名", "價格");
                        System.out.println("--------------------------------");

                        for (Dish d : currentOrderDishes) {
                            System.out.printf("%-15s %10.1f\n", d.getName(), d.getPrice());
                        }

                        System.out.println("--------------------------------");
                        System.out.printf("%-15s %10.1f\n", "總計金額", order.calculateTotal()); // [TC-O001 驗證總價]
                        System.out.println("================================");
                        System.out.println("感謝您的光臨！餐點準備中...");

                        currentOrderDishes.clear(); // 結帳後清空購物車
                    }
                    break;

                case "4":
                    System.out.println("系統已關閉。");
                    return;

                default:
                    System.out.println("無效的輸入，請重新選擇。");
            }
        }
    }
}