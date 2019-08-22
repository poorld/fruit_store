package me.teenyda.mvp_template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.teenyda.mvp_template.model.login.base.LoginAct;
import me.teenyda.mvp_template.model.test.StatusBarTestAct;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.to_test)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, LoginAct.class);
                        startActivity(intent);
                    }
                });
    }
}
