public class Item {
    private String name;
    private double price;
    private double taxRate;
    private double discountPrice;

    Item(String name, double price, double taxRate){
        this.name=name;
        this.price=price;
        this.taxRate=taxRate;
        this.discountPrice=price;
    }

    public double getTotalPrice() {
        return this.discountPrice+this.price*this.taxRate;
    }

    public void applyDiscount(double discount){
        this.discountPrice=this.price-this.price*discount;
        System.out.println("Discount Applied");
    }

    public void displayInfo(){
        System.out.println("Name : "+this.name);
        System.out.println("Price : "+this.price);
        System.out.println("Tax : "+this.taxRate*100+" %");
        System.out.println("Price after discount : "+this.discountPrice);

    }


}
