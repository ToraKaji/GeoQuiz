package deepdive.cnm.edu.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Geoquiz extends AppCompatActivity {
private Button mTrueButton;
private Button mFalseButton;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_geoquiz);

    mTrueButton = (Button) findViewById(R.id.true_button);
    mTrueButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(Geoquiz.this,
            R.string.correct_toast,
            Toast.LENGTH_SHORT).show();
      }
    });
    mFalseButton = (Button) findViewById(R.id.false_button);
    mFalseButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(Geoquiz.this,
            R.string.incorrect_toast,
            Toast.LENGTH_SHORT).show();
      }
    });
  }
}