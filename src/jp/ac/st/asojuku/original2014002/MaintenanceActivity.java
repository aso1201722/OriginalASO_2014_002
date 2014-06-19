package jp.ac.st.asojuku.original2014002;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MaintenanceActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {




	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;
	int selectedID = -1;
	int lastPosition = -1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintenance_activity);
	}


	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		Button modorubt = (Button)findViewById(R.id.btn_mente);
		ListView lstHitokoto = (ListView)findViewById(R.id.btn_check);
		modorubt.setOnClickListener(this);
		lstHitokoto.setOnItemClickListener(this);


		this.setDBValuetoList(lstHitokoto);
	}


	public void onClick(View v){
		switch(v.getId()){
		  case R.id.btn_mente:
			  Intent intent = new Intent(MaintenanceActivity.this,MainActivity.class);
			  startActivity(intent);
			  break;
		}
	}


	private void setDBValuetoList(ListView lstHitokoto){
		SQLiteCursor cursor = null;
		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());


		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			Log.e("ERROR", e.toString());
		}


		cursor = this.helper.selectHitokotoList(sdb);
		int db_layout = android.R.layout.simple_list_item_activated_1;
		String[]from = {"phrase"};
		int[] to = new int[]{android.R.id.text1};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);
		lstHitokoto.setAdapter(adapter);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ


	}


}
