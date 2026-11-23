package coffeeshop;

import java.util.ArrayList;

// STEP 3 · Menu: the 8 things the shop sells.
//
// Open UML.md and find "Step 3 · Menu". The table there lists the 8 items, in order.
//   1 private field: items   (an ArrayList<MenuItem>)
//   1 constructor:   Menu(). It makes the list and adds the 8 items.
//   3 methods:       size(), getItem(int number), toString()
//
// Then drag check/coffeeshop/MenuTest.java into src/test/java/coffeeshop/ and run it.
// Commit message:  step 3: Menu

public class Menu {
    private ArrayList<MenuItem> items;

    // Creates the menu and populates it with the 8 shop items in order
    public Menu() {
        items = new ArrayList<>();
        items.add(new Drink("Coffee", 2.00, 'S'));
        items.add(new Drink("Latte", 3.25, 'S'));
        items.add(new Drink("Cappuccino", 3.25, 'S'));
        items.add(new Drink("Hot Chocolate", 2.50, 'S'));
        items.add(new Drink("Iced Tea", 2.25, 'S'));
        items.add(new Food("Bagel", 2.75, false));
        items.add(new Food("Blueberry Muffin", 2.50, false));
        items.add(new Food("Breakfast Sandwich", 4.50, false));
    }

    // Returns the number of items on the menu
    public int size() {
        return items.size();
    }

    // Returns the item for a 1-based menu number, or null if out of range
    public MenuItem getItem(int number) {
        if (number < 1 || number > items.size()) {
            return null;
        }
        return items.get(number - 1);
    }

    // Returns the formatted numbered menu with a newline after every item
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(i + 1).append(". ").append(items.get(i)).append("\n");
        }
        return sb.toString();
    }
}