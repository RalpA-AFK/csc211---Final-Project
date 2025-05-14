public abstract class Product {
    String name;
    double price;
    String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - $" + String.format("%.2f", price);
    }

    public static class Electronics extends Product {
        public Electronics(String name, double price) {
            super(name, price, "Electronics");
        }
    }

    public static class Clothing extends Product {
        public Clothing(String name, double price) {
            super(name, price, "Clothing");
        }
    }

    public static class Grocery extends Product {
        public Grocery(String name, double price) {
            super(name, price, "Grocery");
        }
    }
}
