package com.example.resume;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity1 extends Activity {

	static final int RE_CODE=13;
	String[] months,years,days28,days29,days30,days31,sexs;
	
	private void func() {
		Spinner spmonth = (Spinner) findViewById(R.id.spmonth);
		Spinner spday = (Spinner) findViewById(R.id.spday);
		Spinner spyear = (Spinner) findViewById(R.id.spyear);
		int x=spday.getSelectedItemPosition();
		switch (spmonth.getSelectedItemPosition()) {
			case 3: case 5: case 8: case 10:
				spday.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,days30));
				if (x>29) spday.setSelection(29);
				else spday.setSelection(x);
				break;
			case 1:
				if (Integer.valueOf(spyear.getSelectedItem().toString())%4==0) {
					spday.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,days29));
					if (x>28) spday.setSelection(28);
					else spday.setSelection(x);
				} else {
					spday.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,days28));
					if (x>27) spday.setSelection(27);
					else spday.setSelection(x);
				}
				break;
			default: spday.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,days31));
					if (x>30) spday.setSelection(30);
					else spday.setSelection(x);
		}
	}
	
	private void funct() {
		Spinner spsex = (Spinner) findViewById(R.id.spsex);
		Spinner spmonth = (Spinner) findViewById(R.id.spmonth);
		Spinner spday = (Spinner) findViewById(R.id.spday);
		Spinner spyear = (Spinner) findViewById(R.id.spyear);
		EditText etfio= (EditText) findViewById(R.id.etfio);
		EditText etpost= (EditText) findViewById(R.id.etpost);
		EditText etphone= (EditText) findViewById(R.id.etphone);
		EditText etmail= (EditText) findViewById(R.id.etmail);
		String [] sexs = new String[] {"Мужской", "Женский"};
		String[] months = new String[] {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
		days31 = new String[31];
		for (int i=0;i<31;i++) {
			days31[i] = String.valueOf(i+1);
		}
		days30 = new String[30];
		for (int i=0;i<30;i++) {
			days30[i] = String.valueOf(i+1);
		}
		days29 = new String[29];
		for (int i=0;i<29;i++) {
			days29[i] = String.valueOf(i+1);
		}
		days28 = new String[28];
		for (int i=0;i<28;i++) {
			days28[i] = String.valueOf(i+1);
		}
		years = new String[80];
		for (int i=0;i<80;i++) {
			years[i] = String.valueOf(i+1920);
		}
		ArrayAdapter<String> aasex = new  ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sexs);
		ArrayAdapter<String> aamonth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,months);
		ArrayAdapter<String> aaday = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,days31);
		ArrayAdapter<String> aayear = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,years);
		spsex.setAdapter(aasex);
		spmonth.setAdapter(aamonth);
		spday.setAdapter(aaday);
		spyear.setAdapter(aayear);
		
		spmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
				func();
			}
			public void onNothingSelected(AdapterView<?> parent) {
				func();
			}
		});
		spyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
				func();
			}
			public void onNothingSelected(AdapterView<?> parent) {
				func();
			}
		});
		InputFilter fiofilter = new InputFilter() {
			
			@Override
			public CharSequence filter(CharSequence source, int start, int end,	Spanned dest, int dstart, int dend) {
				String s="";
				if (dstart==dend)
				{
					if (source.charAt(start)==' '&&(dstart==0||dest.charAt(dstart-1)==' ')) return "";
					for (int i=start;i<end;i++) {
						char c=source.charAt(i);
						if (!Character.isLetter(c)&&!Character.isSpaceChar(c)&&c!='-') return "";
						if (source.charAt(i)=='-') {
							if (i>start) {if (!Character.isLetter(source.charAt(i-1))) return "";}
							else if (dstart==0||!Character.isLetter(dest.charAt(dstart-1))) return "";
						}
					}
					if (Character.isLetter(source.charAt(start))&&(dstart==0||dstart>0&&(Character.isSpaceChar(dest.charAt(dstart-1))||dest.charAt(dstart-1)=='-'))) 
						s=Character.toUpperCase(source.charAt(start))+source.subSequence(start+1, end).toString();
					else s=source.toString();
					return s;
				}
				return null;
			}
		};
		etfio.setFilters(new InputFilter[]{fiofilter});
		InputFilter postfilter = new InputFilter() {
			
			@Override
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
				if (dstart==dend&&source.charAt(start)==' '&&(dstart==0||dest.charAt(dstart-1)==' ')) return "";
				String ch = source.toString();
				if (dstart==dend&&dstart==0) ch = Character.toUpperCase(ch.charAt(0))+ch.substring(1);
				return ch;
			}
		};
		etpost.setFilters(new InputFilter[]{postfilter});
		InputFilter phonefilter = new InputFilter() {
			
			@Override
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
				if (dstart==dend)
				{
					String check = dest.subSequence(0, dstart).toString()+source.subSequence(start, end)+dest.subSequence(dend,dest.length()).toString();
					if (check.charAt(0)=='+'&&check.length()>12||check.charAt(0)!='+'&&check.length()>11) return "";
					for (int i=0;i<check.length();i++) {
						if (i>0) {
							if (!Character.isDigit(check.charAt(i))) return "";
						} else if (check.charAt(i)!='+'&&!Character.isDigit(check.charAt(i))) return "";
					}
					return null;
				}
				return null;
			}
		};
		etphone.setFilters(new InputFilter[]{phonefilter});
		InputFilter mailfilter = new InputFilter() {
			
			@Override
			public CharSequence filter(CharSequence source, int start, int end,	Spanned dest, int dstart, int dend) {
				if (dend==dstart) {
					Pattern pattern = Pattern.compile("[[a-z][A-Z][0-9][.][@][-][_]]");//regular exp
					Matcher matcher = pattern.matcher(source);//input exp
					if (!matcher.matches()) return "";
				}
				return null;
			}
		};
		etmail.setFilters(new InputFilter[]{mailfilter});
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
		funct();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.activity1);
		funct();
	}
	
	private void failure(String errorText) {
		Context contxt = getApplicationContext();
		Toast.makeText(contxt, errorText, Toast.LENGTH_SHORT).show();
	}
	
	private boolean proverka() {
		EditText etfio= (EditText) findViewById(R.id.etfio);
		EditText etpost= (EditText) findViewById(R.id.etpost);
		EditText etphone= (EditText) findViewById(R.id.etphone);
		EditText etmail= (EditText) findViewById(R.id.etmail);
		EditText etpay = (EditText) findViewById(R.id.etpay);
		Pattern pattern = Pattern.compile("[[a-z][A-Z]]{1}[[a-z][A-Z][0-9][.][_][-]]*@[[a-z][A-Z]]+.[[a-z][A-Z]]{2,}");//regular exp
		Matcher matcher = pattern.matcher(etmail.getText().toString());//input exp
		if (etfio.getText().toString().length()<1) {
			failure("Не введены Фамилия Имя Отчество"); 
			return false;
		} else if (etpost.getText().toString().length()<1) {
			failure("Не введена должность");
			return false;
		} else if (etpay.getText().toString().length()<1) {
			failure("Не введена зарплата");
			return false;
		} else if (etphone.getText().toString().length()<11||etphone.getText().toString().charAt(0)=='+'&&etphone.getText().toString().length()<12) {
			failure("Ошибка ввода номера телефона");
			return false;
		} else if (!matcher.matches()) {
			failure("Неверно указан E-mail");
			return false;
		} else return true;
	}
	
	public void sendresume (View v) {
		if (proverka()) {		
			Spinner spsex = (Spinner) findViewById(R.id.spsex);
			Spinner spmonth = (Spinner) findViewById(R.id.spmonth);
			Spinner spday = (Spinner) findViewById(R.id.spday);
			Spinner spyear = (Spinner) findViewById(R.id.spyear);
			EditText etfio= (EditText) findViewById(R.id.etfio);
			EditText etpost= (EditText) findViewById(R.id.etpost);
			EditText etphone= (EditText) findViewById(R.id.etphone);
			EditText etmail= (EditText) findViewById(R.id.etmail);
			EditText etpay = (EditText) findViewById(R.id.etpay);
			Intent intent = new Intent(Activity1.this,Activity2.class);
			intent.putExtra("fio", etfio.getText().toString());
			intent.putExtra("date", spday.getSelectedItem().toString()+" "+spmonth.getSelectedItem().toString()+" "+spyear.getSelectedItem().toString());
			intent.putExtra("sex", spsex.getSelectedItem().toString());
			intent.putExtra("post", etpost.getText().toString());
			intent.putExtra("pay", etpay.getText().toString());
			intent.putExtra("phone", etphone.getText().toString());
			intent.putExtra("mail", etmail.getText().toString());
			startActivityForResult(intent, RE_CODE);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==RE_CODE)
			if (resultCode==RESULT_OK) {
				Context cntxt = Activity1.this;
				AlertDialog.Builder adanswer = new AlertDialog.Builder(cntxt);
				adanswer.setTitle("Ответ работодателя");
				adanswer.setMessage(data.getExtras().getString("answer"));
				adanswer.setPositiveButton("OK", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				adanswer.setCancelable(true);
				adanswer.show();
			}
	}
	


}
