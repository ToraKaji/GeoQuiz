package deepdive.cnm.edu.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.awt.font.TextAttribute;

public class Geoquiz extends AppCompatActivity {
  private Button mCheatButton;
  private Button mTrueButton;
  private Button mFalseButton;
  private ImageButton mNextButton;
  private ImageButton mPreviousButton;
  private TextView mQuestionTextView;
  private boolean mIsCheater;
  private int mCurrentIndex = 0;
  private static final String TAG = "GeoQuiz";
  private static final String KEY_INDEX = "index";
  private static final int REQUEST_CODE_CHEAT = 0;
  private Question[] mQuestionBank = new Question[]{
      new Question(R.string.question_australia, true),
      new Question(R.string.question_oceans, true),
      new Question(R.string.question_mideast, false),
      new Question(R.string.question_africa, true),
      new Question(R.string.question_americas, true),
      new Question(R.string.question_asia, true)
  };

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Log.i(TAG, "onSaveInstanceState");
    outState.putInt(KEY_INDEX, mCurrentIndex);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "onCreate() called");
    setContentView(R.layout.activity_geoquiz);
    if(savedInstanceState != null){
      mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0 );
    }

    mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
    int question = mQuestionBank[mCurrentIndex].getTextResID();
    mQuestionTextView.setText(question);

    mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
    mPreviousButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if(mCurrentIndex>0)
        mCurrentIndex = Math.abs(mCurrentIndex - 1) % mQuestionBank.length;
        updateQuestion();
      }
    });

    mNextButton = (ImageButton) findViewById(R.id.next_button);
    mNextButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        mCurrentIndex = Math.abs(mCurrentIndex + 1) % mQuestionBank.length;
        mIsCheater = false;
        updateQuestion();
      }
    });
    mCheatButton = (Button) findViewById(R.id.cheat_button);
    mCheatButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        Intent intent = CheatActivity.newIntent(Geoquiz.this, answerIsTrue);
        startActivityForResult(intent, REQUEST_CODE_CHEAT);
      }
    });
    updateQuestion();
    mTrueButton = (Button) findViewById(R.id.true_button);
    mTrueButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        checkAnswer(true);
      }
    });
    mFalseButton = (Button) findViewById(R.id.false_button);
    mFalseButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        checkAnswer(false);
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.d(TAG, "onStart() called");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d(TAG, "onStop() called");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "onDestroy() called");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(TAG, "onPause() called");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG, "onResume() called");

  }

  private void checkAnswer(boolean userPressedTrue) {
    boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
    Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    int messageResID = 0;
    if (mIsCheater) {
      messageResID = R.string.judgment_toast;
    } else {
      if (userPressedTrue == answerIsTrue) {
        messageResID = R.string.correct_toast;
        v.cancel();
      } else {
        messageResID = R.string.incorrect_toast;
        v.vibrate(2000);
      }
    }
    Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show();
  }
  private void updateQuestion(){
    int question = mQuestionBank[mCurrentIndex].getTextResID();
    mQuestionTextView.setText(question);
  }
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode != Activity.RESULT_OK) {
      return;
    }
    if (requestCode == REQUEST_CODE_CHEAT) {
      if (data == null) {
        return;
      }
      mIsCheater = CheatActivity.wasAnswerShown(data);
    }
  }
}
