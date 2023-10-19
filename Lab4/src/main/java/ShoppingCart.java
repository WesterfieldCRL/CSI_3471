import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCart
{
    Set<Product> cart = new HashSet<>();
    public ShoppingCart(ArrayList<Product> input)
    {
        cart.addAll(input);
    }
    public double calculateTotal(HashMap<Product, Double> sale, double salesTax)
    {
        double totalCost = 0;
        for (Product product : cart)
        {
            totalCost += product.getPrice();
        }
        Set<Product> onSale = sale.keySet();
        for (Product product : searchSaleItems(onSale))
        {
            totalCost -= product.getPrice() * (sale.get(product)/100); //find percent off and subtract from cost
        }

        totalCost += totalCost * (salesTax/100);

        return totalCost;
    }
    public Set<Product> searchSaleItems(Set<Product> onSale)
    {
        Product[] cartArr = cart.toArray(new Product[0]);
        Product[] saleArr = onSale.toArray(new Product[0]);
        Set<Product> salesInCart = new HashSet<>();
        for (int i = 0; i < cart.size(); i++)
        {
            for (int j = 0; j < onSale.size(); j++)
            {
                if (cartArr[i].equals(saleArr[j]))
                {
                    salesInCart.add(cartArr[i]);
                }
            }
        }
        return salesInCart;
    }
}
