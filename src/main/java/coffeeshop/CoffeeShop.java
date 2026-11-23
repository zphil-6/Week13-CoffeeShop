package coffeeshop;

import java.util.ArrayList;

// STEP 5 · CoffeeShop: the shop, its menu, and every order it finished today.
//
// Open UML.md and find "Step 5 · CoffeeShop".
//   4 private fields: name, menu, completedOrders, totalSales
//   1 constructor:    CoffeeShop(String name)
//   7 methods:        getName(), getMenu(), checkout(), getOrdersCompleted(),
//                     getTotalSales(), getItemsSold(), salesReport()
//
// Then drag check/coffeeshop/CoffeeShopTest.java into src/test/java/coffeeshop/ and run it.
// Commit message:  step 5: CoffeeShop

public class CoffeeShop {
    private String name;
    private Menu menu;
    private ArrayList<Order> completedOrders;
    private double totalSales;

    // Creates a coffee shop with a name, a new menu, an empty order list, and zero sales
    public CoffeeShop(String name) {
        this.name = name;
        this.menu = new Menu();
        this.completedOrders = new ArrayList<>();
        this.totalSales = 0.0;
    }

    // Returns the shop's name
    public String getName() {
        return name;
    }

    // Returns the shop's menu
    public Menu getMenu() {
        return menu;
    }

    // Processes an order with a tip percentage; returns 0 if empty, otherwise records the sale and returns the total
    public double checkout(Order order, int tipPercent) {
        if (order == null || order.isEmpty()) {
            return 0.0;
        }
        double total = order.getTotal(tipPercent);
        completedOrders.add(order);
        totalSales += total;
        return total;
    }

    // Returns the total number of completed orders
    public int getOrdersCompleted() {
        return completedOrders.size();
    }

    // Returns the total sales amount collected today
    public double getTotalSales() {
        return totalSales;
    }

    // Returns the total number of items sold across all completed orders
    public int getItemsSold() {
        int count = 0;
        for (Order order : completedOrders) {
            count += order.getItemCount();
        }
        return count;
    }

    // Returns a four-line formatted summary of today's sales
    public String salesReport() {
        return name + " - today's sales\n"
                + "  Orders: " + getOrdersCompleted() + "\n"
                + "  Items sold: " + getItemsSold() + "\n"
                + "  Total: $" + String.format("%.2f", totalSales);
    }
}