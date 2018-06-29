package tw.idv.ezbins.coffeewater;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText ed_water_ratio;
    private EditText ed_coffee_weight;
    private EditText ed_times;
    private Button calcu;
    private Button clear;
    private CoffeePour coffee_pour;
    private int total_weight;
    private double first_pour;
    private double second_pour;
    private double third_pour;
    private double water_ratio[];
    private double result [];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coffee_pour = new CoffeePour();
        findviews();
        calcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //記算第一次第二次第三次加水的量
                String str_coffee_weight = ed_coffee_weight.getText().toString();
                String str_water_ratio = ed_water_ratio.getText().toString();
                String str_times = ed_times.getText().toString();
                water_ratio = caclueate_ratio(str_times, str_coffee_weight, str_water_ratio);

                switch ((int)(water_ratio[0])) {
                    case 3:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("咖啡分次沖泡水量")
                                .setMessage("總水量 "+water_ratio[1]+"\n"+
                                        "第一次水量 "+ water_ratio[2]+"\n"+
                                        "第二次水量" + water_ratio[3])
                                .setPositiveButton("OK",null)
                                .show();
                        break;

                    case 4:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("咖啡分次沖泡水量")
                                .setMessage("總水量 "+water_ratio[1]+"\n"+
                                        "第一次水量 "+ water_ratio[2]+"\n"+
                                        "第二次水量" + water_ratio[3]+"\n"+
                                        "第三次水量" + water_ratio[4])
                                .setPositiveButton("OK",null)
                                .show();
                        break;

                }
            }
        });
        //清除輸入的內容
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_coffee_weight.getText().clear();
                ed_water_ratio.getText().clear();
                ed_times.getText().clear();
            }
        });
    }

    private void findviews() {
        ed_water_ratio = (EditText) findViewById(R.id.ed_water_ratio);
        ed_coffee_weight = (EditText)findViewById(R.id.ed_cofee_weight);
        ed_times = (EditText) findViewById(R.id.ed_times);
        calcu = (Button) findViewById(R.id.calcu);
        clear = (Button) findViewById(R.id.clear);
    }

    private double[] caclueate_ratio(String str_times, String str_coffee_weight,  String str_water_ratio) {

        coffee_pour.setCoffee_weight(Integer.parseInt(str_coffee_weight));
        coffee_pour.setWater_ratio(Integer.parseInt(str_water_ratio));
        coffee_pour.setPour_times(Integer.parseInt(str_times));

        total_weight = coffee_pour.getCoffee_weight() * coffee_pour.getWater_ratio();

        try{
            //計算比例
            switch (coffee_pour.getPour_times()) {
                //Case pour time =3
                case 3:
                    result = new double[4];
                    first_pour = Math.floor(total_weight * 0.3);
                    second_pour = Math.floor(total_weight *0.4) + first_pour;

                    result[0] = coffee_pour.getPour_times();
                    result[1] = total_weight;
                    result[2] = first_pour;
                    result[3] = second_pour;
                    break;

                // Case pour time =4
                case 4:
                    result = new double[5];
                    first_pour = Math.floor(total_weight * 0.3);
                    second_pour = Math.floor((total_weight * 0.4)*0.5) + first_pour;
                    third_pour = Math.floor((total_weight * 0.4)*0.5) + second_pour;;

                    result[0] = coffee_pour.getPour_times();
                    result[1] = total_weight;
                    result[2] = first_pour;
                    result[3] = second_pour;
                    result[4] = third_pour;
                    break;
            }

        }catch(Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return result;
    }

}
