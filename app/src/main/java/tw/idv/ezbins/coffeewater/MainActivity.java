package tw.idv.ezbins.coffeewater;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText ed_water_ratio;
    private EditText ed_coffee_weight;
    private Button calcu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        calcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //記算第一次第二次第三次加水的量
                String str_coffee_weight = ed_coffee_weight.getText().toString();
                String str_water_ratio = ed_water_ratio.getText().toString();
                double water_raio[] = caclueate_ratio(str_coffee_weight, str_water_ratio);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("咖啡分次沖泡水量")
                        .setMessage("總水量 "+water_raio[0]+"\n"+
                                                  "第一次水量 "+ water_raio[1]+"\n"+
                                                  "第二次水量" + water_raio[2])
                        .setPositiveButton("OK",null)
                        .show();

            }
        });
    }

    private void findviews() {
        ed_water_ratio = (EditText) findViewById(R.id.ed_water_ratio);
        ed_coffee_weight = (EditText)findViewById(R.id.ed_cofee_weight);
        calcu = (Button) findViewById(R.id.calcu);
    }

    private double[] caclueate_ratio(String str_coffee_weight,  String str_water_ratio) {
        double result [] = new double[3];
        int coffee_weight = Integer.parseInt(str_coffee_weight);
        int water_ratio = Integer.parseInt(str_water_ratio);
        int total_weight = coffee_weight * water_ratio;

        double first_pour = total_weight * 0.3;
        double second_pour = (total_weight * 0.4) + first_pour;
        result[0] = total_weight;
        result[1] = first_pour;
        result[2] = second_pour;

        return result;
    }

}
