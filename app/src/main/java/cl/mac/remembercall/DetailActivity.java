package cl.mac.remembercall;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cl.mac.remembercall.models.Reminder;

import static android.os.Build.VERSION_CODES.N;

public class DetailActivity extends AppCompatActivity {

    private Reminder reminder;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        long id = getIntent().getLongExtra(RemindersListFragment.REMINDER_ID, 0);
        reminder = Reminder.findById(Reminder.class, id);
        getSupportActionBar().setTitle(reminder.getName());

        editText = (EditText) findViewById(R.id.descriptionEt);

        final EditText phone = (EditText) findViewById(R.id.phoneEt);
        Button button = (Button) findViewById(R.id.callBtn);

        String description = reminder.getDescription();
        if (description != null) {
            editText.setText(description);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reminder.setDone(true);
                Intent callIntent = new Intent(Intent.ACTION_CALL);

                callIntent.setData(Uri.parse("tel:" + phone.getText().toString()));
                if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);


            }


        });


    }


    @Override
    protected void onPause() {
        super.onPause();
        reminder.setDescription(editText.getText().toString());
        reminder.save();
    }


    @Override
    protected void onResume() {
        super.onResume();


    }
}
