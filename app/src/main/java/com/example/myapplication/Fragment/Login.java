package com.example.myapplication.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Helper.SQLiteHelper;
import com.example.myapplication.Model.Customer;
import com.example.myapplication.R;
import com.example.myapplication.SQLite.CustomerSQLite;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private CustomerSQLite helper;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private EditText email, password;
    private TextView messageInfo;
    private Button btnLogin;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView googleBtn;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        AnhXa(view); // Call AnhXa() with the inflated view
        ButtonActive();
        LoginWithGoogle();
        return view;
    }

    private void ButtonActive() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = helper.isPasswordCorrect(email.getText().toString().trim(), password.getText().toString().trim());
                if (customer == null) {
                    messageInfo.setText("Sai rồi");
                } else {
                    helper.addCustomer(customer);
                    Activity activity = getActivity();
                    if (activity != null) {
                        activity.finish();
                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                    }
                }
            }
        });
    }

    private void AnhXa(View view) {
        helper = new CustomerSQLite(getContext());
        email = view.findViewById(R.id.EmailLogin);
        password = view.findViewById(R.id.PasswrodLogin);
        btnLogin = view.findViewById(R.id.btnLogin);
        messageInfo = view.findViewById(R.id.messageInfo);
        googleBtn = view.findViewById(R.id.googleBtn);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        gsc = GoogleSignIn.getClient(getContext(), gso);
    }



    private void LoginWithGoogle() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(getContext());
                if (acc != null) {

                    String personName = acct.getDisplayName();
                    String personEmail = acct.getEmail();
                    Uri personPhoto = acct.getPhotoUrl();
                    String picture = "";
                    if (personPhoto != null) {
                         picture = personPhoto.toString();

                    }
                    Customer customer = new Customer(personName, personEmail,"LoginWithGoogle",picture,null,null,1);
                    helper.addCustomer(customer);
                    Activity activity = getActivity();
                    if (activity != null) {
                        activity.finish();
                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                    }



                } else {


                }

            }
        });

    }

    void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);

            } catch (ApiException e) {

            }
        }

    }

}