package edu.uw.uicomponentsdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoviesFragment frag = MoviesFragment.newInstance("Menu");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, frag)
                .commit();

    }

    public void handleButton(View v) {
        Log.v(TAG, "You clicked me!");
        ActionBar actionBar = getSupportActionBar();

        if(actionBar.isShowing()) {
            actionBar.hide();
        } else {
            actionBar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.hi_menu_item:
                showHelloDiaglog();
                return true;
            case R.id.bye_menu_item:
                Log.v(TAG, "Bye!");
                MoviesFragment.newInstance("Bye").show(getSupportFragmentManager(), null);
                return true;
            case R.id.third_menu_item:
                Log.v(TAG, "??");

//                Context context = this;
//                String message = "Question Marks";
//                int duration = Toast.LENGTH_LONG;
//
//                Toast toast = Toast.makeText(context, message, duration);
//                toast.show();
                Toast.makeText(this, "Question Marks ? ? ?", Toast.LENGTH_LONG).show();

                return true;
            default:
                Log.v(TAG, "Defaulted");
                return super.onOptionsItemSelected(item);
        }
    }

    public void showHelloDiaglog() {
        Log.v(TAG, "Hi!");
        HelloDiaglogFragment frag = HelloDiaglogFragment.newInstance();
        frag.show(getSupportFragmentManager(), null);
    }

    public static class HelloDiaglogFragment extends DialogFragment {

        public static HelloDiaglogFragment newInstance() {
            Bundle args = new Bundle();
            HelloDiaglogFragment fragment = new HelloDiaglogFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Hello!")
                    .setMessage("This is me saying hello. Hello.");

            builder.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.v(TAG, "They said hi back!");
                }
            });

            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Log.v(TAG, "Why you aint clicking my button");
                }
            });

            AlertDialog dialog = builder.create();
            return dialog;
        }
    }
}
