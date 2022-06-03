package sg.edu.rp.c346.id20042741.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvToDo;
    Spinner add_or_remove;
    EditText enterToDo;
    ArrayList<String> toDoArray;
    ArrayAdapter adapter;
    Button btnAdd;
    Button btnClear;
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvToDo = findViewById(R.id.lvID);
        add_or_remove = findViewById(R.id.spinnerid);
        enterToDo = findViewById(R.id.textEditNew);
        toDoArray = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toDoArray);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDel = findViewById(R.id.btnDel);
        lvToDo.setAdapter(adapter);

        add_or_remove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, String.format("%d", position),Toast.LENGTH_SHORT).show();
                switch(position){
                    case 0:
                        enterToDo.setHint("Type in a new task here");
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        enterToDo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }

        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoArray.add(enterToDo.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }
}