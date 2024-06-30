package com.example.practicecameraserverstorage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class FotoActivity extends AppCompatActivity {

    private ImageView imageViewFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extra = this.getIntent().getExtras();
        String serverAddress = extra.getString("server address");
        String filePath = extra.getString("filepath");

        if (serverAddress == null || filePath == null) {
            this.finish();
        }

        this.imageViewFoto = (ImageView) this.findViewById(R.id.imageViewFoto);

        this.loadImage(serverAddress, filePath);
    }

    private void loadImage(String serverURL, String filePath) {
        String fullPath = serverURL + "/" + filePath;

        new Thread(() -> {
            InputStream inputStream = null;

            try {
                URL url = new URL(fullPath);
                inputStream = url.openConnection().getInputStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                runOnUiThread(() -> {
                    FotoActivity.this.imageViewFoto.setImageBitmap(bitmap);
                });
            }
            catch (Exception ignored) {}
            finally {
                try {
                    Objects.requireNonNull(inputStream).close();
                }
                catch (Exception ignored) {}
            }
        }).start();
    }
}