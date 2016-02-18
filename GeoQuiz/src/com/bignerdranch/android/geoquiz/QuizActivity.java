package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private Button mPreviousButton;
	private TextView mQuestionTextView;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[] {
			new TrueFalse(R.string.question_africa, true),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, false),
			new TrueFalse(R.string.question_midest,false),
			new TrueFalse(R.string.question_oceans, true)
	};

	private int mCurrentIndex = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		// 创建试图
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		mQuestionTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				updateQuestion();
			}
		});
		updateQuestion();
		
		// 创建button
		mTrueButton = (Button)findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checkAnswer(true);
			}
		});
		
		mFalseButton = (Button)findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checkAnswer(false);
			}
		});
		
		mNextButton = (Button)findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				updateQuestion();
			}
		});
		
		mPreviousButton = (Button)findViewById(R.id.previous_button);
		mPreviousButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				updateQuestion();
			}
		});
	}

	private void updateQuestion() {
		mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressdTrue) {
		
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		
		int messageResId = 0;
		
		if (userPressdTrue == answerIsTrue) {
			messageResId = R.string.correct_toast;
		} else {
			messageResId = R.string.incorrect_toast;
		}
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
