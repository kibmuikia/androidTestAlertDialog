package kib.dev.testalertdialog;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import kib.dev.testalertdialog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private int[] avatarMan = {R.drawable.m001, R.drawable.m002, R.drawable.m003, R.drawable.m004, R.drawable.m005, R.drawable.m006, R.drawable.m007};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
                showAvatarDialog();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void showAvatarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Avatar");
        final View dialogView = getLayoutInflater().inflate(R.layout.avatar_dialog, null);

        GridView gridView = dialogView.findViewById(R.id.avatarGridView);
        AvatarAdapter adapter = new AvatarAdapter(this, avatarMan);
        gridView.setAdapter(adapter);

        builder.setView(dialogView);
        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

        final AlertDialog dialog = builder.create();

        // Get dialog's layout params
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        // Get screen height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        // Calculate 60% of screen height
        int dialogHeight = (int) (screenHeight * 0.6);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            int selectedAvatarId = avatarMan[position];
            showToast(this, "Selected: " + selectedAvatarId);
            dialog.dismiss();
        });

        dialog.show();
        // dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, 1000);

        /*
        * Please note that the height is cast to an integer, so there might be a small loss of precision.
        * Also, make sure to call dialog.getWindow().setAttributes(layoutParams); after dialog.show();
        * to ensure the dialog has been fully created before attempting to modify its parameters.
        * */
        // Set dialog's height
        layoutParams.height = dialogHeight;
        dialog.getWindow().setAttributes(layoutParams);
    }

    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}