package com.example.autocount.vehicleparking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;

import org.w3c.dom.Text;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    Button scanbtn;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    //mysql details for testing
//    private static final String url = "jdbc:mysql://192.168.0.198/test";
//    private static final String user = "test123";
//    private static final String pass = "12345678";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanbtn = (Button) findViewById(R.id.scan_btn);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST );
        }
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
        //testDb();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if(data!= null) {
                final Barcode barcode = data.getParcelableExtra("barcode");
                Intent intent = new Intent(MainActivity.this, GoToWebpage.class);
                String webAddress = barcode.displayValue;
                intent.putExtra("Web Address",webAddress);
                startActivity(intent);
            }
        }
    }

    // this is just for testing can comment it if errors occur
//    public void testDb() {
//        TextView textView = (TextView) findViewById(R.id.slot_numbers);
//        try {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = (Connection) DriverManager.getConnection(url,user,pass);
//
//            String result = "";
//            Statement st = con.createStatement();
//            //get data from table
//            ResultSet rs = st.executeQuery("select * from test");
//            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
//            while(rs.next()) {
//                //show the data
//                result +=  rs.getString(1) + "\n";
//            }
//            textView.setText(result);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            textView.setText(e.toString());
//        }
//    }
}
