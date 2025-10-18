package ltdd.th.vankhanh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tongTienGui;
    private TextView kyhanGui;
    private TextView LaiNhanDuocHangThang;
    private TextView TongLai;

    private Button btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        init();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DecimalFormat formatter = new DecimalFormat("#,###.## vi_VN");

        Intent intent = getIntent();

        double tongtien = intent.getDoubleExtra("sotien", 0.0);
        int month = intent.getIntExtra("month", 0);

        tongTienGui.setText(formatter.format(tongtien) + "đ");

        kyhanGui.setText(month + " tháng");


        double lai = intent.getDoubleExtra("currentRate", 0.0);

        LaiNhanDuocHangThang.setText(formatter.format(lai) + "đ");


        double tonglai = tongtien * lai * month / 100;
        TongLai.setText(formatter.format(tonglai) + "đ");

        btnQuayLai.setOnClickListener(v->{
            Intent intent1 = new Intent(this, inputActivity.class);
            startActivity(intent1);
        });

    }


    public void init(){
        tongTienGui = findViewById(R.id.tongTienGui);
        kyhanGui = findViewById(R.id.kyhanGui);
        LaiNhanDuocHangThang = findViewById(R.id.LaiNhanDuocHangThang);

        TongLai = findViewById(R.id.TongLai);

        btnQuayLai = findViewById(R.id.btnQuayLai);
    }
}