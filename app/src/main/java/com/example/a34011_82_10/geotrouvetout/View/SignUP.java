package com.example.a34011_82_10.geotrouvetout.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a34011_82_10.geotrouvetout.Controller.JsonParsing;
import com.example.a34011_82_10.geotrouvetout.Controller.RestConnexion;
import com.example.a34011_82_10.geotrouvetout.Model.NewUser;
import com.example.a34011_82_10.geotrouvetout.R;

import org.json.JSONObject;

public class SignUP extends Activity {

    EditText mPseudo;
    EditText mPassword;
    String mMyPseudo;
    String mMyPassword;
    EditText mPasswordConf;
    String mMyPasswordConf;
    Intent intent;

    NewUser newuser;
    JsonParsing jsonparsing;
    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);

        mPseudo = (EditText) findViewById(R.id.pseudo);
        mPassword = (EditText) findViewById(R.id.password);
        mPasswordConf = (EditText) findViewById(R.id.password2);


        // When you want to confirm your registration

        Button bSignUp = (Button) findViewById(R.id.signup);

        bSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //***************** SAVING DATAS ***************** //

                mMyPseudo = mPseudo.getText().toString();
                mMyPassword = mPassword.getText().toString();
                Log.i("info", mMyPassword);

                mMyPasswordConf = mPassword.getText().toString();
                Log.i("info", mMyPasswordConf);

                // Check the pseudo and confirm double password
                // If password ain't matching, show an error message


                //***************** CHECK PASSWORDS ***************** //

                if (mMyPassword.equals(mMyPasswordConf)) {

                    Log.i("info", "Dans boucle if");


                    //***************** SERVER CONNEXION ***************** //
                    // TODO: Check with server REST if login / password okay



                    //***************** NEXT STEP ***************** //

                    //intent.putExtra("pseudo", mMyPseudo);
                    intent = new Intent(SignUP.this, SignUpConfirm.class);
                    intent.putExtra("pseudo", mMyPseudo);
                    startActivity(intent);


                } else {

                    Log.i("info", "Dans boucle else");

                    TextView myTextView = (TextView) findViewById(R.id.confirmpassword);
                    String mString = "Your passwords ain't matching. Please, try again.";
                    myTextView.setText(mString);

                }
            }
        });

        // End of the SignUp OnClick instructions

    }
}