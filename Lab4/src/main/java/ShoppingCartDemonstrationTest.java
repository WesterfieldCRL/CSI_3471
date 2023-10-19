import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ShoppingCartDemonstrationTest
{
    static double salesTax = 7.5;
    enum productComparator implements Comparator<Product>
    {
        ASCENDING
        {
            @Override
            public int compare(Product a, Product b)
            {
                return a.getName().compareTo(b.getName());
            }
        },
        DESCENDING
        {
            @Override
            public int compare(Product a, Product b)
            {
                return b.getName().compareTo(a.getName());
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException
    {

        Scanner scanner = new Scanner(new File(args[0]));
        ArrayList<Product> productList = new ArrayList<>();
        HashMap<Product, Double> saleMap = new HashMap<>();

        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            productList.add(new Product(Integer.parseInt(values[0]), Double.parseDouble(values[1]), values[2]));
        }

        ShoppingCart cart = new ShoppingCart(productList);

        scanner.close();
        scanner = new Scanner(new File(args[1]));
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] values = line.split(",");

            //filter reductions
            double reduction = Double.parseDouble(values[3]);
            if (reduction >= 0 && reduction <= 100)
            {
                saleMap.put(new Product(Integer.parseInt(values[0]), Double.parseDouble(values[1]), values[2]), reduction);
            }
        }
        System.out.println("Total = " + cart.calculateTotal(saleMap, salesTax));
        System.out.println("Items on sale:");
        Set<Product> salesList= cart.searchSaleItems(saleMap.keySet());

        for (Product product : salesList)
        {
            System.out.println(product.getName());
        }

        System.out.println("\nPrinting in Ascending order:\n");

        productList.sort(productComparator.ASCENDING);

        for (Product thing : productList)
        {
            System.out.println(thing.name);
        }

        System.out.println("\nPrinting in Descending order:\n");

        productList.sort(productComparator.DESCENDING);

        for (Product thing : productList)
        {
            System.out.println(thing.name);
        }

    }

}
