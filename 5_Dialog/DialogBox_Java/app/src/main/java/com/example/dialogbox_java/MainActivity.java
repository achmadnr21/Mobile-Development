package com.example.dialogbox_java;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView hasilnya;
    private EditText txtinput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasilnya = (TextView) findViewById(R.id.result);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(op);
    }
    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button) {
                tampil_input();
            }
        }
    };
    DialogInterface.OnClickListener oknya = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //positif -1 negatif -2
            switch (which) {
                case -1: hasilnya.setText(txtinput.getText().toString());
                    break;
                case -2:
                    break;
            }
        }
    };
    private void tampil_input(){
        LayoutInflater li = LayoutInflater.from(this);
        View inputnya = li.inflate(R.layout.input_dialog,null);

        AlertDialog.Builder dialognya = new AlertDialog.Builder(this);
        dialognya.setView(inputnya);
//        final EditText txtinput = (EditText) inputnya.findViewById(R.id.edittext);
        txtinput = (EditText) inputnya.findViewById(R.id.edittext);

        dialognya
                .setCancelable(false)
                .setPositiveButton("Ok",oknya)
                .setNegativeButton("Batal",oknya);
        dialognya.show();
    }



}