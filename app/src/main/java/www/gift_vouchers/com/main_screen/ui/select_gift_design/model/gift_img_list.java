package www.gift_vouchers.com.main_screen.ui.select_gift_design.model;

public class gift_img_list {
    String id;
    int gift_img;
    boolean status = false;

    public gift_img_list(String id, int gift_img) {
        this.id = id;
        this.gift_img = gift_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGift_img() {
        return gift_img;
    }

    public void setGift_img(int gift_img) {
        this.gift_img = gift_img;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
