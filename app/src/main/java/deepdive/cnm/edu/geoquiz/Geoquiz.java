package deepdive.cnm.edu.geoquiz;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.awt.font.TextAttribute;

public class Geoquiz extends AppCompatActivity {

  private Button mTrueButton;
  private Button mFalseButton;
  private ImageButton mNextButton;
  private ImageButton mPreviousButton;
  private TextView mQuestionTextView;
  private int mCurrentIndex = 0;

  private Question[] mQuestionBank = new Question[]{
      new Question(R.string.question_australia, true),
      new Question(R.string.question_oceans, true),
      new Question(R.string.question_mideast, false),
      new Question(R.string.question_africa, true),
      new Question(R.string.question_americas, true),
      new Question(R.string.question_asia, true)
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_geoquiz);
    mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
    int question = mQuestionBank[mCurrentIndex].getTextResID();
    mQuestionTextView.setText(question);
    mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
    mPreviousButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
        updateQuestion();
      }
    });
    mNextButton = (ImageButton) findViewById(R.id.next_button);
    mNextButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
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

  private void checkAnswer(boolean userPressedTrue) {
    boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
    Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    int messageResID = 0;

    if (userPressedTrue == answerIsTrue){
      messageResID = R.string.correct_toast;
      v.cancel();
    }else{
      messageResID = R.string.incorrect_toast;
      v.vibrate(9000);
    }
    Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show();
  }
  private void updateQuestion(){
    int question = mQuestionBank[mCurrentIndex].getTextResID();
    mQuestionTextView.setText(question);
  }
}
