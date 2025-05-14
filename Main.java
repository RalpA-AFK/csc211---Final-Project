import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        User.Admin admin = new User.Admin();

        admin.addProduct(new Product.Electronics("CPU (Intel i7)", 320.00));
        admin.addProduct(new Product.Electronics("RAM (16GB DDR4)", 75.00));
        admin.addProduct(new Product.Electronics("SSD (1TB NVMe)", 110.00));
        admin.addProduct(new Product.Clothing("Empire Jeans", 65.00));
        admin.addProduct(new Product.Clothing("Nike Air Jordans", 150.00));
        admin.addProduct(new Product.Grocery("Eggs (Dozen)", 4.00));
        admin.addProduct(new Product.Grocery("Kosher Butter (500g)", 6.50));

        admin.displayInventory();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to search for a product by name? (yes/no): ");
        String searchResponse = scanner.nextLine().trim().toLowerCase();

        List<Product> searchResults = new ArrayList<>();

        if (searchResponse.equals("yes")) {
            System.out.print("Enter product name to search: ");
            String searchQuery = scanner.nextLine().trim().toLowerCase();

            System.out.println("\nSearch Results for: " + searchQuery);
            
            searchResults = admin.inventory.stream()
                .filter(p -> p.name.toLowerCase().contains(searchQuery))
                .collect(Collectors.toList());
            
            if (searchResults.isEmpty()) {
                System.out.println("No products found matching your search.");
            } else {
                searchResults.forEach(System.out::println);
            }

        } else {
            System.out.println("\nWould you like to filter by category or price range? (category/price): ");
            String filterChoice = scanner.nextLine().trim().toLowerCase();

            if (filterChoice.equals("category")) {
                System.out.println("Enter category to filter by (Electronics, Clothing, Grocery): ");
                String categoryFilter = scanner.nextLine().trim().toLowerCase();

                searchResults = admin.inventory.stream()
                    .filter(p -> p.category.toLowerCase().contains(categoryFilter))
                    .collect(Collectors.toList());

                if (searchResults.isEmpty()) {
                    System.out.println("No products found in the category: " + categoryFilter);
                } else {
                    searchResults.forEach(System.out::println);
                }
            } else if (filterChoice.equals("price")) {
                System.out.print("Enter minimum price: ");
                double minPrice = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Enter maximum price: ");
                double maxPrice = Double.parseDouble(scanner.nextLine().trim());

                searchResults = admin.inventory.stream()
                    .filter(p -> p.price >= minPrice && p.price <= maxPrice)
                    .collect(Collectors.toList());

                if (searchResults.isEmpty()) {
                    System.out.println("No products found in the price range: $" + minPrice + " - $" + maxPrice);
                } else {
                    searchResults.forEach(System.out::println);
                }
            } else {
                System.out.println("Invalid choice. Please restart the program and choose either 'category' or 'price'.");
            }
        }

        if (!searchResults.isEmpty()) {
            System.out.println("\nWould you like to add any of these products to your cart? (yes/no): ");
            String addToCartResponse = scanner.nextLine().trim().toLowerCase();

            if (addToCartResponse.equals("yes")) {
                User.Customer customer = new User.Customer("pc_builder123");
                boolean addingItems = true;

                while (addingItems) {
                    System.out.println("\nEnter the index of the product you want to add to your cart (1-" + searchResults.size() + "), or 'done' to finish:");
                    String choice = scanner.nextLine().trim().toLowerCase();

                    if (choice.equals("done")) {
                        addingItems = false;
                    } else {
                        try {
                            int productIndex = Integer.parseInt(choice) - 1;
                            if (productIndex >= 0 && productIndex < searchResults.size()) {
                                customer.addToCart(searchResults.get(productIndex));
                                System.out.println(searchResults.get(productIndex).name + " added to your cart.");
                            } else {
                                System.out.println("Invalid choice. Please choose a valid product index.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid product index or 'done' to finish.");
                        }
                    }
                }

                User.Order order = customer.checkout();
                System.out.println("\nYour order has been placed:");
                System.out.println(order);
            }
        }
    }
}
