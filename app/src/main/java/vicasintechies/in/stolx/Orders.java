package vicasintechies.in.stolx;

/**
 * Created by Chandan S R on 28-Jul-17.
 */

public class Orders {
    String product;
    String from_uid;
    String status;
    String qty;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String price;
    public Orders(){

       // Log.d("inside",product);
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product_name) {
        this.product = product_name;
    }

    public String getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(String from_uid) {
        this.from_uid = from_uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
