package com.example.a34011_82_10.geotrouvetout.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a34011_82_10.geotrouvetout.R;

/**
 * Created by 34011-82-10 on 02/11/2016.
 */

public class SignUpConfirm extends Activity {

    Intent i;
    Intent intent;
    String monText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupconfirmation);

        monText = getIntent().getExtras().getString("pseudo");

        TextView mTextView = (TextView) findViewById(R.id.hello);
        String mString = "Hello " + monText + ", \n \n Let's register you to his amazing app !";
        mTextView.setText(mString);

        Button bplay = (Button)findViewById(R.id.button);

        bplay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                intent = new Intent(SignUpConfirm.this, MainView.class);
                intent.putExtra("pseudo", monText);
                startActivity(intent);
            }
        });
    }
}


