// Built by: Philip
package coffeeshop;

import java.util.Scanner;

// STEP 6 · CoffeeShopApp: the game you play. This is the class with main.
//
// It already runs. Click the green arrow next to main and try it.
//
// In Step 6 you finish it. Open UML.md ("Step 6") and the README ("Step 6") for the
// screens and the exact sentences. You add:
//   2 more private fields: shop and order
//   the game loop in run()
//   printBanner(), printMainMenu(), and one method for each menu choice
//
// Then drag check/coffeeshop/CoffeeShopAppTest.java into src/test/java/coffeeshop/ and run it.
// Commit message:  step 6: game loop

public class CoffeeShopApp {

    private Scanner input;

    // YOUR CODE (Step 6): add the fields shop and order here.
    private CoffeeShop shop;
    private Order order;

    // Creates the app with the input scanner, the coffee shop, and the first order
    public CoffeeShopApp(Scanner input) {
        this.input = input;
        // YOUR CODE (Step 6): make the shop, new CoffeeShop("Hornet Coffee"), and the first new Order().
        this.shop = new CoffeeShop("Hornet Coffee");
        this.order = new Order();
    }

    // The game loop. It keeps going until the user picks 0.
    public void run() {
        printBanner();
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readNumber("Pick a number: ");
            switch (choice) {
                case 1:
                    System.out.println(shop.getMenu());
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    removeItem();
                    break;
                case 4:
                    viewOrder();
                    break;
                case 5:
                    useCoupon();
                    break;
                case 6:
                    checkout();
                    break;
                case 7:
                    System.out.println(shop.salesReport());
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("That is not on the list. Pick 0 to 7.");
                    break;
            }
        }
        System.out.println("Shop closed. Goodbye!");
    }

    // Prints the coffee cup banner and shop title
    private void printBanner() {
        System.out.println("     ( (");
        System.out.println("      ) )");
        System.out.println("   ........");
        System.out.println("   |      |]");
        System.out.println("   \\      /");
        System.out.println("    `----'");
        System.out.println("  HORNET COFFEE");
        System.out.println();
    }

    // Prints the main menu along with the current order number and item count
    private void printMainMenu() {
        System.out.println();
        System.out.println("Order #" + order.getOrderNumber() + " (" + order.getItemCount() + " items)");
        System.out.println("1. Show the menu");
        System.out.println("2. Add an item");
        System.out.println("3. Remove an item");
        System.out.println("4. View my order");
        System.out.println("5. Use a coupon");
        System.out.println("6. Check out");
        System.out.println("7. Today's sales");
        System.out.println("0. Close the shop");
    }

    // Lets the user pick an item from the menu, customize it, and add it to the order
    private void addItem() {
        System.out.println(shop.getMenu());
        int num = readNumber("Item number: ");
        MenuItem picked = shop.getMenu().getItem(num);
        if (picked == null) {
            System.out.println("That item is not on the menu.");
            return;
        }

        if (picked.isDrink()) {
            String answer = readText("Size (S, M, L): ");
            char size = answer.isEmpty() ? 'S' : answer.charAt(0);
            Drink drink = new Drink(picked.getName(), picked.getPrice(), size);
            order.addItem(drink);
            System.out.println("Added: " + drink);
        } else {
            String answer = readText("Heat it up? (y/n): ");
            boolean heated = answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes");
            Food food = new Food(picked.getName(), picked.getPrice(), heated);
            order.addItem(food);
            System.out.println("Added: " + food);
        }
    }

    // Removes a chosen item from the current order if it is not empty
    private void removeItem() {
        if (order.isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }
        System.out.println(order);
        int num = readNumber("Item number to remove: ");
        if (order.removeItem(num)) {
            System.out.println("Item removed.");
        } else {
            System.out.println("That item is not in your order.");
        }
    }

    // Displays the current order or a message if it is empty
    private void viewOrder() {
        if (order.isEmpty()) {
            System.out.println("Your order is empty.");
        } else {
            System.out.println(order);
        }
    }

    // Prompts for a coupon code and applies it to the current order
    private void useCoupon() {
        String code = readText("Coupon code: ");
        if (order.applyCoupon(code)) {
            System.out.println("Coupon applied!");
        } else {
            System.out.println("Invalid coupon code.");
        }
    }

    // Completes the current order with a tip, prints the total, and starts a new order
    private void checkout() {
        if (order.isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }
        System.out.println(order);
        int tip = readNumber("Tip percent (0, 10, 15, 20): ");
        double total = shop.checkout(order, tip);
        System.out.println("Total: $" + String.format("%.2f", total));
        order = new Order();
    }

    // GIVEN CODE. Do not change it. Use it for every number the user types.
    // It keeps asking until the user types a whole number, so letters can not crash your game.
    private int readNumber(String prompt) {
        System.out.print(prompt);
        while (!input.hasNextInt()) {
            input.nextLine();                    // throw away what they typed
            System.out.print("Please type a number: ");
        }
        int number = input.nextInt();
        input.nextLine();                        // throw away the Enter key
        return number;
    }

    // GIVEN CODE. Do not change it. Use it for every word the user types.
    // Reads one line of text, with the spaces at the ends cut off.
    private String readText(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    // Start here. Click the green arrow next to this line to run the game.
    public static void main(String[] args) {
        CoffeeShopApp app = new CoffeeShopApp(new Scanner(System.in));
        app.run();
    }
}