package mlhm.mohammed.milhemfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import mlhm.mohammed.milhemfinalproject.Data.MyTask;
import mlhm.mohammed.milhemfinalproject.Data.MyTaskAdapte;
import mlhm.mohammed.milhemfinalproject.MyUI.AddTaskActivity;

public class MainActivity extends AppCompatActivity {
    private ImageButton ibtnAdd;
    //1 after building the arry adapter
    ListView lstTask;
    //adapter
    MyTaskAdapte taskAdapte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ibtnAdd=findViewById(R.id.btnAdd);
        //LISTNER
        ibtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(i);
            }
        });
        //3.
        lstTask=findViewById(R.id.listTask);
        //4.
        taskAdapte=new MyTaskAdapte(getBaseContext(),R.layout.item_task_view);
        //5. connect listview to the adaptor
        lstTask.setAdapter(taskAdapte);
        //7
        downloadFromFireBase();
    }
    //6.
    public void downloadFromFireBase(){
        //where we saved before we need to contact to download
        //1.
        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference= database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        //4.
        reference.child("All Task").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                taskAdapte.clear();
                for (DataSnapshot child : dataSnapshot.getChildren())
                {
                    MyTask task=child.getValue(MyTask.class);
                    taskAdapte.add(task);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseErrorHandler databaseError) {
                Toast.makeText(MainActivity.this,"Download Error",Toast.LENGTH_SHORT).show();

            }
        });
    }

}