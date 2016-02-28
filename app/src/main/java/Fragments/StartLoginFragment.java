package Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import dotinc.nearby.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartLoginFragment extends Fragment {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView text;
    private ImageView imageView;
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken token = loginResult.getAccessToken();
            Toast.makeText(getActivity(), token.getUserId(), Toast.LENGTH_SHORT).show();
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                text.setText("Welcome " + profile.getName() + ".");
                Glide.with(getActivity())
                        .load(profile.getProfilePictureUri(250, 250))
                        .into(imageView);
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    public StartLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        text = (TextView) view.findViewById(R.id.text_detail);
        imageView = (ImageView) view.findViewById(R.id.profile_pic);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
