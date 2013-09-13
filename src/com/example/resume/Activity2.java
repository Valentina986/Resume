package com.example.resume;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;

public class Activity2 extends Activity {

	String fio,date,sex,post,pay,phone,mail;
	
	private void func() {
		TextView tvfio= (TextView) findViewById(R.id.tvfio);
		TextView tvdate= (TextView) findViewById(R.id.tvdate);
		TextView tvsex= (TextView) findViewById(R.id.tvsex);
		TextView tvpost= (TextView) findViewById(R.id.tvpost);
		TextView tvpay= (TextView) findViewById(R.id.tvpay);
		TextView tvphone= (TextView) findViewById(R.id.tvphone);
		TextView tvmail= (TextView) findViewById(R.id.tvmail);
		
		tvfio.setText(fio);
		tvdate.setText(date);
		tvsex.setText(sex);
		tvpost.setText(post);
		tvpay.setText(pay);
		tvphone.setText(phone);
		tvmail.setText(mail);
		
		EditText etanswer = (EditText) findViewById(R.id.etanswer);
		InputFilter spaceFilter = new InputFilter() {
			
			@Override
			public CharSequence filter(CharSequence source, int start, int end,	Spanned dest, int dstart, int dend) {
				if (dstart==dend) {
				if (source.charAt(start)==' '&&(dstart==0||dest.charAt(dstart-1)==' ')) return "";
				}
				return null;
			}
		};
		InputFilter answfilter = new InputFilter() {
			
			@Override
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
				if (dstart==dend) {
					String s;
					if (Character.isLetter(source.charAt(start))&&(dstart==0||dstart>1&&
						(dest.charAt(dstart-2)=='.'&&dest.charAt(dstart-1)==' '||dest.charAt(dstart-1)=='.'||
						dest.charAt(dstart-2)=='!'&&dest.charAt(dstart-1)==' '||dest.charAt(dstart-1)=='!'||
						dest.charAt(dstart-2)=='?'&&dest.charAt(dstart-1)==' '||dest.charAt(dstart-1)=='?'))) 
						s=Character.toUpperCase(source.charAt(start))+source.subSequence(start+1, end).toString();
					else s=source.toString();
					return s;
				}
				return null;
			}
		};
		etanswer.setFilters(new InputFilter[]{answfilter,spaceFilter});
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);
				
		fio = getIntent().getExtras().getString("fio");
		getIntent().removeExtra("fio");
		date = getIntent().getExtras().getString("date");
		getIntent().removeExtra("date");
		sex = getIntent().getExtras().getString("sex");
		getIntent().removeExtra("sex");
		post = getIntent().getExtras().getString("post");
		getIntent().removeExtra("post");
		pay = getIntent().getExtras().getString("pay");
		getIntent().removeExtra("pay");
		phone = getIntent().getExtras().getString("phone");
		getIntent().removeExtra("phone");
		mail = getIntent().getExtras().getString("mail");
		getIntent().removeExtra("mail");
		func();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.activity2);
		func();
	};
	
	public void sendanswer(View v) {
		EditText etanswer = (EditText) findViewById(R.id.etanswer);
		Intent answer = new Intent();
		answer.putExtra("answer", etanswer.getText().toString());
		setResult(RESULT_OK,answer);
		finish();
	}

}
