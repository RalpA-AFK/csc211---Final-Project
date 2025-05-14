import java.util.*;

public class User {
    static class Customer {
        String username;
        List<Product> cart;

        public Customer(String username) {
            this.username = username;
            this.cart = new ArrayList<>();
        }

        public void addToCart(Product product) {
            cart.add(product);
        }

        public Order checkout() {
            double total = 0;
            for (Product product : cart) {
                total += product.price;
            }
            return new Order(this, cart, total);
        }
    }

    static class Admin {
        List<Product> inventory;

        public Admin() {
            inventory = new ArrayList<>();
        }

        public void addProduct(Product product) {
            inventory.add(product);
        }

        public void removeProduct(Product product) {
            inventory.remove(product);
        }

        public void displayInventory() {
            System.out.println("\nAvailable Products in Inventory:");
            for (Product product : inventory) {
                System.out.println(product);
            }
        }
    }

    static class Order {
        Customer customer;
        List<Product> products;
        double total;

        public Order(Customer customer, List<Product> products, double total) {
            this.customer = customer;
            this.products = products;
            this.total = total;
        }

        @Override
        public String toString() {
            StringBuilder orderSummary = new StringBuilder("Order:\n");
            for (Product product : products) {
                orderSummary.append("- ").append(product).append("\n");
            }
            orderSummary.append("Total: $").append(String.format("%.2f", total));
            return orderSummary.toString();
        }
    }
}
