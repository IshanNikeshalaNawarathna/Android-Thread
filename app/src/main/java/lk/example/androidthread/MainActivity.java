package lk.example.androidthread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button = findViewById(R.id.button);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Log.i("Thread","Button Case 1");

//               for (int i =0;i<=10;i++){ // UI thread block
//                   TextView textView = findViewById(R.id.textView);
//                   textView.setText(String.valueOf(i));
//
//                   try {
//                       Thread.sleep(1000);
//                   } catch (InterruptedException e) {
//                       throw new RuntimeException(e);
//                   }
//
//               }

//               Thread t = new Thread(){ // block the Android Tool kit
//                   @Override
//                   public void run() {
//                       for (int i =0;i<=10;i++){
//                           TextView textView = findViewById(R.id.textView);
//                           textView.setText(String.valueOf(i));
//
//                           try {
//                               Thread.sleep(1000);
//                           } catch (InterruptedException e) {
//                               throw new RuntimeException(e);
//                           }
//
//                       }
//                   }
//
//               };t.start();

               Thread t = new Thread(){ // solutions
                   @Override
                   public void run() {
                       for (int i =0;i<=10;i++){
                           final int x = i;
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   TextView textView = findViewById(R.id.textView);
                                   textView.setText(String.valueOf(x));
                               }
                           });
                           try {
                               Thread.sleep(1000);
                           } catch (InterruptedException e) {
                               throw new RuntimeException(e);
                           }

                       }
                   }

               };t.start();

           }
       });

    }
}