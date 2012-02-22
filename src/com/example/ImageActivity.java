package com.example;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageActivity extends Activity {
  private ImageView imageView;
  private Bitmap bitmap;
  private Button button;

  static {
    System.loadLibrary("imageprocessing");
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wallace);

    imageView = (ImageView) findViewById(R.id.imageView);
    button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        adjustBrightness();
      }
    });
  }

  private void adjustBrightness() {
    brightness(bitmap);
    imageView.setImageBitmap(bitmap);
  }

  public native void brightness(Bitmap bmp);
}