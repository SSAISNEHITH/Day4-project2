import java.util.*;

class Product {
    int id;
    String name;
    String category;
    double price;
    int quantity;

    Product(int id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("--------------------");
    }
}

 class productInventory {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<Integer, Product> products = new HashMap<>();
        HashSet<String> categories = new HashSet<>();
        TreeSet<String> sortedCategories = new TreeSet<>();
        LinkedHashSet<String> recentProducts = new LinkedHashSet<>();

        int choice;

        do {
            System.out.println("\n===== PRODUCT INVENTORY SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Display Unique Categories");
            System.out.println("7. Display Sorted Categories");
            System.out.println("8. Display Recent Products");
            System.out.println("9. Inventory Report");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    if (products.containsKey(id)) {
                        System.out.println("Product ID already exists!");
                        break;
                    }

                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Price: ");
                    double price = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter Quantity: ");
                    int quantity = Integer.parseInt(sc.nextLine());

                    if (price < 0 || quantity < 0) {
                        System.out.println("Invalid price or quantity!");
                        break;
                    }

                    Product p = new Product(id, name, category, price, quantity);

                    products.put(id, p);
                    categories.add(category);
                    sortedCategories.add(category);
                    recentProducts.add(name);

                    System.out.println("Product Added Successfully!");
                    break;

                case 2:
                    if (products.isEmpty()) {
                        System.out.println("No Products Available");
                    } else {
                        for (Product product : products.values()) {
                            product.display();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int searchId = Integer.parseInt(sc.nextLine());

                    if (products.containsKey(searchId)) {
                        products.get(searchId).display();
                    } else {
                        System.out.println("Product Not Found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    int updateId = Integer.parseInt(sc.nextLine());

                    if (products.containsKey(updateId)) {

                        Product old = products.get(updateId);

                        recentProducts.remove(old.name);

                        System.out.print("Enter New Product Name: ");
                        old.name = sc.nextLine();

                        System.out.print("Enter New Category: ");
                        old.category = sc.nextLine();

                        System.out.print("Enter New Price: ");
                        double newPrice = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter New Quantity: ");
                        int newQuantity = Integer.parseInt(sc.nextLine());

                        if (newPrice < 0 || newQuantity < 0) {
                            System.out.println("Invalid price or quantity!");
                            break;
                        }

                        old.price = newPrice;
                        old.quantity = newQuantity;

                        categories.add(old.category);
                        sortedCategories.add(old.category);
                        recentProducts.add(old.name);

                        System.out.println("Product Updated Successfully!");

                    } else {
                        System.out.println("Product Not Found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Product ID: ");
                    int deleteId = Integer.parseInt(sc.nextLine());

                    if (products.containsKey(deleteId)) {
                        Product deleted = products.remove(deleteId);
                        recentProducts.remove(deleted.name);

                        System.out.println("Product Deleted Successfully!");
                    } else {
                        System.out.println("Product Not Found!");
                    }
                    break;

                case 6:
                    System.out.println("Unique Categories:");
                    System.out.println(categories);
                    break;

                case 7:
                    System.out.println("Categories in Alphabetical Order:");
                    System.out.println(sortedCategories);
                    break;

                case 8:
                    System.out.println("Recently Added Products:");
                    System.out.println(recentProducts);
                    break;

                case 9:
                    double totalValue = 0;
                    int totalQuantity = 0;

                    System.out.println("\n===== INVENTORY REPORT =====");

                    for (Product product : products.values()) {

                        product.display();

                        totalValue += product.price * product.quantity;
                        totalQuantity += product.quantity;
                    }

                    System.out.println("Total Products: " + products.size());
                    System.out.println("Total Quantity: " + totalQuantity);
                    System.out.println("Total Inventory Value: " + totalValue);
                    break;

                case 10:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 10);

        sc.close();
    }
}