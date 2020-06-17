package www.gift_vouchers.com.main_screen.ui;


/**
 * Created by poliveira on 24/10/2014.
 */
public class NavigationItem {
    private String mText;
    private int img;

    public NavigationItem(String mText, int img) {
        this.mText = mText;
        this.img = img;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
