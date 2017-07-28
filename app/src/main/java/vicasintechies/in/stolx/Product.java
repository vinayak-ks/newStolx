package vicasintechies.in.stolx;

/**
 * Created by Chandan S R on 28-Jul-17.
 */

public class Product {
    private String image;
    private String id;

    public Product() {
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product(String image, String id) {

        this.image = image;
        this.id = id;
    }
}
