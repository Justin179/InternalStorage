package sairamkrishna.example.com.internalstorage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/*
 Internal storage is the storage of the private data on the device memory.
 私有的資料，存在裝置的記憶體
 By default these files are private and are accessed by only your application and get deleted,
 when user delete your application.
 此資料只能被此應用程式存取，此應用程式刪除時，該資料也會一併刪除
*/
public class MainActivity extends Activity  {

    EditText editText;
    TextView tv;
    Button btnSave,btnLoad;

    String data;
    private String file = "mydata";

    @Override
    protected void onCreate(Bundle btnSavedInstanceState) {
        super.onCreate(btnSavedInstanceState);
        setContentView(R.layout.activity_main);

        // bind 畫面元件
        editText=(EditText)findViewById(R.id.editText);
        tv=(TextView)findViewById(R.id.textView2);
        btnSave=(Button)findViewById(R.id.button);
        btnLoad=(Button)findViewById(R.id.button2);

        // 點Save - Writing file
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 拿到畫面上輸入的字串
                data = editText.getText().toString();
                try {

                    // 把資料寫到File
                    // Use internal storage to write some data in the file,
                    // call the openFileOutput() method with the name of the file and the mode.
                    // After that you can call write method to write data on the file.
                    FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);
                    fOut.write(data.getBytes());
                    fOut.close();

                    Toast.makeText(getBaseContext(),"file btnSaved",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        // 點Load - Reading file
        btnLoad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    /*
                        In order to read from the file you just created,
                        call the openFileInput() method with the name of the file.
                        It returns an instance of FileInputStream.
                     */
                    FileInputStream fin = openFileInput(file);

                    int c;
                    String temp="";
                    // After that, you can call read method to read one character at a time from the file and then you can print it.
                    // 一個字元一個字元讀進來
                    while( (c = fin.read()) != -1){
                        temp = temp + Character.toString((char)c);
                    }
                    // 讀到的字顯示於畫面上
                    tv.setText(temp);

                    Toast.makeText(getBaseContext(),"file read",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                }
            }
        });
    }
}
