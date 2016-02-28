package dotinc.nearby;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.facebook.appevents.AppEventsLogger;

import Fragments.StartLoginFragment;

public class StartScreen extends AppCompatActivity {

    private Context context;
    private FragmentManager manager;

    private void instantiate() {
        context = StartScreen.this;
        manager = getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        instantiate();

        StartLoginFragment fragment = new StartLoginFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.root, fragment, "Login_frag");
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(context);
    }


    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(context);
    }
}
