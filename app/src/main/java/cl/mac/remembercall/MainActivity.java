package cl.mac.remembercall;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import cl.mac.remembercall.models.Reminder;

import static android.R.id.edit;
import static cl.mac.remembercall.R.id.fab;

public class MainActivity extends AppCompatActivity {

    /**
     * Una aplicacion que te permita añadir recordatorios de a quien debes llamar
     * Puedes añadir el motivo de la llamada
     *
     * Si es posible averiguar:
     * Al momento de escribir a quien llamar este debe sugerirte tu lista de contactos
     * Debe permitirte ingresar a que hora te recuerde hacer esta llamada, que te envie una notificacion push
     *
     * @param savedInstanceState
     */

    private RemindersListFragment remindersListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        remindersListFragment = (RemindersListFragment) getSupportFragmentManager().findFragmentById(R.id.reminderListFragment);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_reminder);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);


                ImageButton button = (ImageButton) dialog.findViewById(R.id.saveBtn);
                final EditText editText = (EditText) dialog.findViewById(R.id.nameEt);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = editText.getText().toString();
                        if (name.trim().length() > 0 ){
                            Reminder reminder = new Reminder();
                            reminder.setName(name);
                            reminder.setDone(false);
                            reminder.save();

                            remindersListFragment.addReminder(reminder);
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
    }


}
