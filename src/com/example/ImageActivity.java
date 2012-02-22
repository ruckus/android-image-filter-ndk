package com.example;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class ImageActivity extends Activity {
  private ImageView imageView;
  private Bitmap bitmap;
  private Button button;
  private SeekBar seekBar;
  private float brightness;
  private TextView label;
  private Bitmap original;

  static {
    System.loadLibrary("imageprocessing");
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    original = BitmapFactory.decodeResource(getResources(), R.drawable.wallace);

    imageView = (ImageView) findViewById(R.id.imageView);
    button = (Button) findViewById(R.id.button);
    seekBar = (SeekBar) findViewById(R.id.seekBar);
    label = (TextView) findViewById(R.id.label);

    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        adjustBrightness();
      }
    });

    seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        brightness = (float)(progress / 10.0f);
        label.setText("Brightness = " + brightness);
      }

      public void onStartTrackingTouch(SeekBar seekBar) {}
      public void onStopTrackingTouch(SeekBar seekBar){}
    });    
  }

  private void adjustBrightness() {
    Bitmap bitmap = original.copy(Bitmap.Config.ARGB_8888, true);
    brightness(bitmap, brightness);
    imageView.setImageBitmap(bitmap);
  }

  public native void brightness(Bitmap bmp, float brightness);
}