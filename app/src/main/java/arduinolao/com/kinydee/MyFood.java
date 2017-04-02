package arduinolao.com.kinydee;

/**
 * Created by K'Phai on 01/14/2017.
 */

public class MyFood {
    String foodName;
    int imageResId;

    public MyFood() {
    }

    public MyFood(String foodName, int imageResId) {
        this.foodName = foodName;
        this.imageResId = imageResId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String setFoodName(String foodName) {
        this.foodName = foodName;
        return foodName;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
