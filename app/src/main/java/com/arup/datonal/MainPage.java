package com.arup.datonal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.arup.datonal.data.StringData;

import java.util.Arrays;

public class MainPage extends AppCompatActivity {
    public static String[][] data2D;
    public static String dataString;


    ScrollView scrollView;
    TextView noDataText;



    public String[][] getData() {
        return data2D;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void LoadRecycleView() {
        RecyclerViewActivity recyclerViewActivity = new RecyclerViewActivity(ViewData());
        RecyclerView recyclerView = this.findViewById(R.id.mainPageRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewActivity);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i = new Intent(getApplicationContext(), ViewPage.class);
                        ViewPage.data = data2D[position];
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(), "Long Click", Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

/*    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_app_bar, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();
        //Handle item selection
/*        switch (item.getItemId()) {
            case R.id.i1:
                //perform any action;
                return true;
            case R.id.a:
                //perform any action;
                return true;
            case R.id.b:
                //perform any action;
                return true;
            default:
                return super.onOptionsItemSelected(item);*/
        //}
        return true;
    }

    public void onGroupItemClick(MenuItem item) {
        Toast.makeText(this, String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();
/*        findViewById(R.id.menu_changePIN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Hello World", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);


        scrollView = findViewById(R.id.ScrollMainPage);
        noDataText = findViewById(R.id.noDataWarn);

        Intent intent = getIntent();
        String previousActivity = intent.getStringExtra("previousActivity");
        if(previousActivity.equals("login")) {
            data2D = StringData.StringTo2DArray(dataString).clone();
        }
        else if(previousActivity.equals("signup")){
            dataString = "";
            data2D = new String[0][0];
        }

        loadData();

        findViewById(R.id.addIcon).setOnClickListener(v -> showDialog());

    }

    public String[][] ViewData() {
        try {
            String[][] viewArr = new String[data2D.length][2];
            int[] SubTitlePositions = {1, 4, 6, 9, 10};
            for(int i = 0 ; i < data2D.length ; i++) {
                viewArr[i][0] = data2D[i][0];
                viewArr[i][1] = "No Data";
                for(int pos :SubTitlePositions) {
                    if(!data2D[i][pos].equals("-")) {
                        viewArr[i][1] = data2D[i][pos];
                        break;
                    }
                }
            }
            Toast.makeText(this, Arrays.deepToString(viewArr), Toast.LENGTH_SHORT).show();
            return viewArr;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new String[0][0];
        }

    }

    public void loadData() {
        if(data2D.length != 0) {
            noDataText.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            LoadRecycleView();
        }
        else {
            noDataText.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
    }

    private void showDialog() {
        AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_add_list, null);
        alert.setView(view);
        alert.setCancelable(false);
        AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

        view.findViewById(R.id.userpasswordbutton).setOnClickListener(v -> {
            Toast.makeText(MainPage.this, "Coming Soon", Toast.LENGTH_SHORT).show();
        });
        view.findViewById(R.id.bankdetailsbutton).setOnClickListener(v -> {
            Toast.makeText(MainPage.this, "Coming Soon", Toast.LENGTH_SHORT).show();
        });
        view.findViewById(R.id.carddetailsbutton).setOnClickListener(v -> {
            Toast.makeText(MainPage.this, "Coming Soon", Toast.LENGTH_SHORT).show();
        });
        view.findViewById(R.id.notesbutton).setOnClickListener(v -> {
            Toast.makeText(MainPage.this, "Coming Soon", Toast.LENGTH_SHORT).show();
        });
        view.findViewById(R.id.allfieldbutton).setOnClickListener(v -> {
            openPasswordAdd();
        });
        view.findViewById(R.id.othersbutton).setOnClickListener(v -> {
            Toast.makeText(MainPage.this, "Coming Soon", Toast.LENGTH_SHORT).show();
        });

    }

    private void openPasswordAdd() {
        Intent openAddPage = new Intent(getApplicationContext() ,  passwordAdd.class);
        startActivity(openAddPage);
    }

}