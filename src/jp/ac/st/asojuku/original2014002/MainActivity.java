package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements View.OnClickListener{


	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		//メンテナンスへ
		Button mentebt = (Button)findViewById(R.id.btn_mente);
		mentebt.setOnClickListener(this);
		//ヒトコトチェックへ
		Button checkbt = (Button)findViewById(R.id.btn_touroku);
		checkbt.setOnClickListener(this);
		//登録ボタン変数にリスナーを登録する
		Button tourokubt = (Button)findViewById(R.id.btn_check);
		tourokubt.setOnClickListener(this);


		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			return;
		}
	}


	public void onClick(View v){
		switch(v.getId()){
		case R.id.btn_mente: //btnMsgが押された
			  Intent intent = new Intent(MainActivity.this,MaintenanceActivity.class);
			  startActivity(intent);
			  break;


			  //ヒトコトチェックボタンが押下された
		case R.id.btn_touroku:


			  String strHitokoto = helper.selectRandomHitokoto(sdb);
			  Intent intent1 = new Intent(MainActivity.this,HitokotoActivity.class);
			  intent1.putExtra("hitokoto", strHitokoto);
			  startActivity(intent1);
			  break;


			  //登録ボタンが押下された
		case R.id.btn_check: //btnMsgが押された
			  EditText etv = (EditText)findViewById(R.id.edtMsg);
			  String inputMsg = etv.getText().toString();


			  if(inputMsg!=null && !inputMsg.isEmpty()){
				  helper.insertHitokoto(sdb, inputMsg);
			  }
			  etv.setText("");
			  break;




		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
//checkbt
