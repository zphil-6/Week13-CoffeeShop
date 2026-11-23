package coffeeshop;

import java.util.ArrayList;

// STEP 4 · Order: one customer's order.
//
// Open UML.md and find "Step 4 · Order". This is the biggest class. Build it one method at a
// time, and run the test after each one.
//   2 constants:      COUPON_CODE, COUPON_PERCENT   (public static final)
//   1 static field:   nextOrderNumber, starts at 1
//   3 private fields: orderNumber, items, discountPercent
//   1 constructor:    Order()
//   10 methods:       getOrderNumber(), addItem(), removeItem(), getItemCount(), isEmpty(),
//                     getSubtotal(), applyCoupon(), getDiscount(), getTotal(), toString()
//
// Then drag check/coffeeshop/OrderTest.java into src/test/java/coffeeshop/ and run it.
// Commit message:  step 4: Order

public class Order {
    public static final String COUPON_CODE = "HORNET10";
    public static final int COUPON_PERCENT = 10;

    private static int nextOrderNumber = 1;

    private int orderNumber;
    private ArrayList<MenuItem> items;
    private int discountPercent;

    // Creates a new order with the next available order number and an empty item list
    public Order() {
        this.orderNumber = nextOrderNumber++;
        this.items = new ArrayList<>();
        this.discountPercent = 0;
    }

    // Returns this order's number
    public int getOrderNumber() {
        return orderNumber;
    }

    // Adds a menu item to the order
    public void addItem(MenuItem item) {
        items.add(item);
    }

    // Removes the item at the 1-based number; returns false if out of range
    public boolean removeItem(int number) {
        if (number < 1 || number > items.size()) {
            return false;
        }
        items.remove(number - 1);
        return true;
    }

    // Returns the number of items in the order
    public int getItemCount() {
        return items.size();
    }

    // Returns true if the order has no items
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Returns the sum of prices for all items in the order
    public double getSubtotal() {
        double sum = 0.0;
        for (MenuItem item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    // Applies the coupon discount if the trimmed code matches COUPON_CODE (case-insensitive)
    public boolean applyCoupon(String code) {
        if (code != null && code.trim().equalsIgnoreCase(COUPON_CODE)) {
            discountPercent = COUPON_PERCENT;
            return true;
        }
        return false;
    }

    // Returns the dollar amount of the discount
    public double getDiscount() {
        return getSubtotal() * discountPercent / 100.0;
    }

    // Returns the final total after subtracting the discount and adding the tip percentage
    public double getTotal(int tipPercent) {
        double afterDiscount = getSubtotal() - getDiscount();
        int validTip = Math.max(0, tipPercent);
        return afterDiscount + (afterDiscount * validTip / 100.0);
    }

    // Returns a formatted summary of the order, items, subtotal, and optional discount
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderNumber).append("\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(i + 1).append(". ").append(items.get(i)).append("\n");
        }
        sb.append("  Subtotal: $").append(String.format("%.2f", getSubtotal()));
        if (discountPercent > 0) {
            sb.append("\n  Discount: -$").append(String.format("%.2f", getDiscount()));
        }
        return sb.toString();
    }
}
