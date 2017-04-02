package arduinolao.com.kinydee;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KinYDeeActivity extends AppCompatActivity implements View.OnClickListener {


    private SensorManager mSensorManager;
    private ShakeEventListener mShakeEventListener;
    TextView tvResult;
    ImageView imvFood;
    Button btnRandom;
    ProgressBar proBar;
    RadioGroup rgType;
    DotProgressBar dotProgressBar;

    //Add List Food
    List<MyFood> foodList = Arrays.asList(
            new MyFood("ກະເພົາໝູ", R.drawable.image_001),
            new MyFood("ແກງຂຽວຫວານ", R.drawable.image_002),
            new MyFood("ເຂົ້າຜັດ", R.drawable.image_003),
            new MyFood("ເຂົ້າມັນໄກ່", R.drawable.image_004),
            new MyFood("ເຂົ້າໄຂ່ຈຽວ", R.drawable.image_005)
    );

    //Add List Drink
    List<MyFood> drinkList = Arrays.asList(
            new MyFood("Pepsi", R.drawable.image_001),
            new MyFood("Milk", R.drawable.image_002),
            new MyFood("3", R.drawable.image_003),
            new MyFood("4", R.drawable.image_004),
            new MyFood("5", R.drawable.image_005)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kin_y_dee);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mShakeEventListener = new ShakeEventListener();
        bindView();
        setUpView();

        mShakeEventListener.setOnShakeListener(
                new ShakeEventListener.OnShakeListener() {
            @Override
            public void onShake() {
                EventHandler();
            }
        });

    }// OnCreate----------------------------------------------------

    private void setUpView() {
        //imvFood.setVisibility(View.GONE);
        dotProgressBar.setVisibility(View.GONE);
        proBar.setVisibility(View.GONE);
        btnRandom.setOnClickListener(this);
    }

    private void bindView() {
        tvResult = (TextView) findViewById(R.id.textViewResult);
        btnRandom = (Button) findViewById(R.id.buttonRandom);
        imvFood = (ImageView) findViewById(R.id.imageViewFood);
        proBar = (ProgressBar) findViewById(R.id.progress);
        rgType = (RadioGroup) findViewById(R.id.rgTypeMenu);
        dotProgressBar = (DotProgressBar) findViewById(R.id.dot_progress_bar);
    }

    @Override
    public void onClick(View view) {
        if (view == btnRandom) {
            //TODO : Handle Click
            EventHandler();
        }
    }

    private void EventHandler() {
        dotProgressBar.setVisibility(View.VISIBLE);
        //proBar.setVisibility(View.VISIBLE);
        tvResult.setVisibility(View.GONE);
        imvFood.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO : When delayed
                dotProgressBar.setVisibility(View.GONE);
                //proBar.setVisibility(View.GONE);
                switch (rgType.getCheckedRadioButtonId()) {

                    case R.id.rdFood:
                        int foodIndex = randomMyFoodIndex(foodList.size());
                        MyFood myFood = foodList.get(foodIndex);
                        String nameFood = myFood.getFoodName();
                        int imageResIdFood = myFood.getImageResId();
                        ShowResult(nameFood, imageResIdFood);
                        break;

                    case R.id.rdDrink:
                        int drinkIndex = randomMyFoodIndex(drinkList.size());
                        MyFood myDrink = drinkList.get(drinkIndex);
                        String nameDrink = myDrink.getFoodName();
                        int imageResIdDrink = myDrink.getImageResId();
                        ShowResult(nameDrink, imageResIdDrink);
                        break;
                }
            }
        }, 2000);
    }

    private void ShowResult(String name, int imageResId) {
        tvResult.setText(name);
        imvFood.setImageResource(imageResId);
        tvResult.setVisibility(View.VISIBLE);
        imvFood.setVisibility(View.VISIBLE);
    }

    private int randomMyFoodIndex(int max) {
        Random random = new Random();
        int index = random.nextInt(max);
        return index;
    }


    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeEventListener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeEventListener,
                mSensorManager.getDefaultSensor(
                        Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }



}// Main Class------------------------------------------------------
