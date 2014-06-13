package jp.ac.st.asojuku.original2014002;



import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
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
		Button btn_mente = (Button)findViewById(R.id.btn_mente);
		btn_mente.setOnClickListener(this);

		Button btn_touroku = (Button)findViewById(R.id.btn_touroku);
		btn_touroku.setOnClickListener(this);

		Button btn_check = (Button)findViewById(R.id.btn_check);
		btn_check.setOnClickListener(this);



		if(sdb == null) {
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			Log.e("ERROR", e.toString());
			// 異常終了
		}
	}





	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	// 生成して代入用のIntentインスタンス変数を用意
	Intent intent = null;

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){ //どのボタンが押されたか判定
			case R.id.btn_mente: //btnMsgが押された
				intent = new Intent(MainActivity.this, MaintenanceActivity.class);

				startActivity(intent);
				break;
			case R.id.btn_touroku:
				// 次画面のアクティビティ起動
				EditText etv = (EditText)findViewById(R.id.edtMsg);
				String inputMsg = etv.getText().toString();
				if(inputMsg!=null && !inputMsg.isEmpty()){
					helper.insertHitokoto(sdb, inputMsg);
				}
				etv.setText("");
				break;


			case R.id.btn_check: //btnMsgが押された
				String strHitokoto = helper.selectRamdomHitokoto(sdb);
				intent = new Intent(MainActivity.this, HitokotoActivity.class);

				intent.putExtra("hitokoto", strHitokoto);
				// 次画面のアクティビティ起動
				startActivity(intent);
				break;



		}
	}

}