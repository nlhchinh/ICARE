package com.example.icare.logic.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // Thêm import cho Button
import android.widget.ImageView;
import android.widget.TextView; // Sử dụng TextView cho ô chọn ngày
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.icare.R;
import com.example.icare.logic.database.AppDatabase;
import com.example.icare.logic.database.AppDatabaseClient; // Thêm import này
import com.example.icare.logic.model.PeriodInfor;

import java.util.UUID;

public class PeriodInputAcivity extends AppCompatActivity {

    // Sửa lại kiểu cho đúng với layout (ô chọn ngày là TextView)
    TextView text_view_start_date;
    EditText edit_text_cycle_length, edit_text_period_length;
    Button btn_save; // Thêm biến cho nút Lưu

    ImageView icon_calendar;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.period_input_infor_layout);

        // FIX 1: KHỞI TẠO DATABASE - ĐÂY LÀ LỖI CHÍNH CỦA BẠN
        appDatabase = AppDatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

        // find view by id
        // Sửa lại ID và kiểu cho đúng với layout period_input_infor_layout
        text_view_start_date = findViewById(R.id.input_start_date);
        icon_calendar = findViewById(R.id.icon_calendar);
        edit_text_cycle_length = findViewById(R.id.input_cycle_length);
        edit_text_period_length = findViewById(R.id.input_period_length);
        btn_save = findViewById(R.id.btn_save); // Tìm nút Lưu

        // -----------------------------------------------------------------------------------------
        // show date picker when click on edit text start date
        View.OnClickListener showDatePicker = v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // show date picker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Update the TextView with the selected date
                        String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        text_view_start_date.setText(date);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        };

        // Gán sự kiện click cho ô chọn ngày
        text_view_start_date.setOnClickListener(showDatePicker);
        icon_calendar.setOnClickListener(showDatePicker);
        // -----------------------------------------------------------------------------------------

        // FIX 2: DI CHUYỂN LOGIC LƯU VÀO TRONG SỰ KIỆN ONCLICK CỦA NÚT "LƯU"
        btn_save.setOnClickListener(v -> {
            // Lấy dữ liệu từ các ô nhập liệu
            String startDate = text_view_start_date.getText().toString();
            String cycleLengthStr = edit_text_cycle_length.getText().toString();
            String periodLengthStr = edit_text_period_length.getText().toString();

            // get userId from SharedPreferences
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String userId = prefs.getString("userId", null);

            // Kiểm tra dữ liệu
            if (startDate.isEmpty() || startDate.equals("Chọn ngày") || cycleLengthStr.isEmpty() || periodLengthStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            if (userId == null) {
                Toast.makeText(this, "Lỗi: Không tìm thấy người dùng. Vui lòng đăng nhập lại.", Toast.LENGTH_LONG).show();
                return;
            }

            // insert period infor object to database
            try {
                // create new period infor object
                PeriodInfor periodInfor = new PeriodInfor();

                // Generate a unique ID for period infor
                String uniqueID = UUID.randomUUID().toString();

                // set period infor object
                periodInfor.setPeriodInforId(uniqueID);
                periodInfor.setStartDay(startDate); // Sửa: Dùng getText()
                periodInfor.setCycleLength(Integer.parseInt(cycleLengthStr)); // Sửa: Dùng getText()
                periodInfor.setPeriodLength(Integer.parseInt(periodLengthStr)); // Sửa: Dùng getText()
                periodInfor.setUserId(userId);

                // insert period infor object to database
                appDatabase.periodInforDao().insert(periodInfor);

                // show toast
                Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();

                // navigate to PeriodManageActivity
                Intent intent = new Intent(PeriodInputAcivity.this, PeriodManageActivity.class);
                startActivity(intent);
                finish(); // Đóng activity sau khi lưu thành công

            } catch (Exception e) {
                Toast.makeText(this, "Lỗi khi lưu dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace(); // In lỗi ra logcat để dễ debug
            }
        });
    }
}
