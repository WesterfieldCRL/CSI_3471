
public class Product
{
    Double price;
    int ID;
    String name;
    public Product(int ID, double price, String name)
    {
        this.ID = ID;
        this.price = price;
        this.name = name;
    }
    public double getPrice()
    {
        return price;
    }
    public int getID()
    {
        return ID;
    }
    public String getName()
    {
        return name;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public void setID(int ID)
    {
        this.ID = ID;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass())
        {
            return false;
        }
        Product product = (Product)obj;
        return ID == product.getID()
                && name.equals(product.getName())
                && price.equals(product.getPrice());
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        result = prime * result + ID;
        result = prime * result + price.hashCode();
        return result;
    }
}
