package deepdive.cnm.edu.geoquiz;

import android.widget.Button;
import android.widget.TextView;

public class Question {

  private int mTextResID;
  private boolean mAnswerTrue;


  public int getTextResID() {
    return mTextResID;
  }

  public void setmTextResID(int mTextResID) {
    this.mTextResID = mTextResID;
  }

  public boolean isAnswerTrue() {
    return mAnswerTrue;
  }

  public void setmAnswerTrue(boolean mAnswerTrue) {
    this.mAnswerTrue = mAnswerTrue;
  }

  public Question(int textResID, boolean AnswerTrue){
    mAnswerTrue = AnswerTrue;
    mTextResID = textResID;

  }
}
