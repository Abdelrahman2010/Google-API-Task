package com.abdo.hp.task;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    Button   EnterBT;
    EditText Email_ET;
    SignInButton sign_in_button;
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInClient googleSignInClient;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterBT  = (Button)findViewById(R.id.EnterBT);
        Email_ET = (EditText)findViewById(R.id.Email_ET);
        sign_in_button = findViewById(R.id.sign_in_button);

//        GoogleSignInOptions mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("832835292776-6016jts3gp73kokk3j77pkpcu84ouoca.apps.googleusercontent.com")
//                .requestEmail()
//                .build();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("832835292776-fr0quk6v0butqjt6b0gbq27p7tsokq82.apps.googleusercontent.com")
                .requestServerAuthCode("832835292776-fr0quk6v0butqjt6b0gbq27p7tsokq82.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        /* Setup the Google API object to allow Google+ logins */
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,  this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("832835292776-6016jts3gp73kokk3j77pkpcu84ouoca.apps.googleusercontent.com")
//                .requestEmail()
//                .build();

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignIn();

            }
        });



        EnterBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Text = Email_ET.getText().toString();
                Intent intent = new Intent(MainActivity.this,Home.class);
                intent.putExtra("Email",Text);
                startActivity(intent);
            }
        });


    }

    void GoogleSignIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) //google
        {
            GoogleSignInResult mGoogleSignInResult=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handelResult(mGoogleSignInResult);

//            try {
//                // The Task returned from this call is always completed, no need to attach
//                // a listener.
//                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                onLoggedIn(account);
//            } catch (ApiException e) {
//                // The ApiException status code indicates the detailed failure reason.
//                Log.w("a7a", "signInResult:failed code=" + e.getMessage());
//
//                Toast.makeText(getApplicationContext(), "Error" ,Toast.LENGTH_LONG).show();
//
//            }

        }


    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("Email", googleSignInAccount);

        startActivity(intent);
        finish();
    }

    void handelResult(GoogleSignInResult mGoogleSignInResult)
    {
        if(mGoogleSignInResult.isSuccess()) {
            GoogleSignInAccount mGoogleSignInAccount =mGoogleSignInResult.getSignInAccount();
            String emailGoogle=mGoogleSignInAccount.getEmail();
            String idGoogle=mGoogleSignInAccount.getId();


            if(emailGoogle.equals(""))
            {
                Toast.makeText(getApplicationContext(), "Can'it use this account ", Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intent = new Intent(MainActivity.this,Home.class);
                intent.putExtra("Email",emailGoogle);
                startActivity(intent);
            }

            // tv.setText(emailGoogle);
            Toast.makeText(getApplicationContext(), "User ID:  " + emailGoogle, Toast.LENGTH_LONG).show();

            //access mGoogleLogout Button
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            mGoogleLogoutButton.setLaoutParams(lp);
//            mGoogleLoginButton.setVisibility(View.INVISIBLE);
//            mGoogleLogoutButton.setVisibility(View.VISIBLE);
        }
        else{

            Toast.makeText(getApplicationContext(), mGoogleSignInResult.toString() ,Toast.LENGTH_LONG).show();
            Log.w("a7a", "signInResult:failed code=" + mGoogleSignInResult.getStatus());



        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
