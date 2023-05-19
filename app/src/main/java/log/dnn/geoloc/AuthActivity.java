package log.dnn.geoloc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

public class AuthActivity extends AppCompatActivity {

    public NavHostController navHostController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_auth);
        navHostController = (NavHostController) navHostFragment.getNavController();
    }
}