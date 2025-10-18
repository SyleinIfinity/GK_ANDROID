package ltdd.th.vankhanh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class inputActivity extends AppCompatActivity {

    private TextView Lai;

    private EditText soTienGui;
    private Spinner kyhan;

    private Button btnTinhLai;
    private double currentRate = 0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input);
        init();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.input), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        kyhan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int month = laythang(parent.getItemAtPosition(position).toString());
                currentRate = laihangnam(month);

                Lai.setText("Áp dụng lãi suất" + currentRate + " %/nam");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnTinhLai.setOnClickListener(v->{
            String soTien = soTienGui.getText().toString();
            if (soTien.isEmpty() || currentRate == 0.0)
            {
                Toast.makeText(this, "Nhap hop le", Toast.LENGTH_SHORT).show();
                return;
            }
            double sotien = Double.parseDouble(soTien);
            int month = laythang(kyhan.getSelectedItem().toString());

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("sotien", sotien);
            intent.putExtra("month", month);
            intent.putExtra("currentRate", currentRate);
            startActivity(intent);
        });

    }

    public int laythang(String kyhan)
    {
        if (kyhan.contains("6")) return 6;
        if (kyhan.contains("12")) return 12;
        if (kyhan.contains("24")) return 24;
        return 0;
    }

    public double laihangnam(int month){
        if (month == 6) return 5.0;
        if (month == 12) return 5.5;
        if (month == 24) return 6.0;
        return 0.0;
    }

    public void init(){
        Lai = findViewById(R.id.LaiNhanDuoc);
        soTienGui = findViewById(R.id.soTienGui);
        kyhan = findViewById(R.id.spinnerKyHan);
        btnTinhLai = findViewById(R.id.btnTinhLai);

    }
}
