package open.cklan.com.interviewlibrary.category.day1_service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.Priority;
import com.example.Schema;

import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;
@Schema(name = "service",priority = Priority.HIGH)
public class Day1_ServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day1__service);
    }
}
