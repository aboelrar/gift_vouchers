package www.gift_vouchers.com.main_screen.ui.myorders.model;

public class my_order_list {
    String id;
    String image;
    String name;
    String code;
    String price;

    public my_order_list(String id, String image, String name, String code, String price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
