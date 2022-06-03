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
    EditText enterToDoOrIndex;
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
        enterToDoOrIndex = findViewById(R.id.textAddOrRemove);
        toDoArray = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toDoArray);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDel = findViewById(R.id.btnDel);
        lvToDo.setAdapter(adapter);

        add_or_remove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        enterToDoOrIndex.setHint("Type in a new task here");
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        enterToDoOrIndex.setHint("Type in the index of the task to be removed");
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
                if(!enterToDoOrIndex.getText().toString().isEmpty()){
                    toDoArray.add(enterToDoOrIndex.getText().toString());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "Enter something", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // first condition; check if the array got nothing.
                if(toDoArray.size() != 0){

                    // second condition; check if the input is not empty
                    if(!enterToDoOrIndex.getText().toString().isEmpty()){
                        int getIndex = Integer.parseInt(enterToDoOrIndex.getText().toString());

                        // third condition; check if getIndex is a number that is a valid index
                        if(getIndex >= 0 && getIndex <= toDoArray.size()){

                            // fourth condition; check if index of toDoArray is null
                            if(toDoArray.get(getIndex-1) == null){
                                Toast.makeText(MainActivity.this, "Please enter a valid index", Toast.LENGTH_SHORT).show();
                            }

                            // if not empty, remove
                            else{
                                toDoArray.remove(getIndex-1);
                                adapter.notifyDataSetChanged();
                            }
                        }

                        // else for third condition
                        else{
                            Toast.makeText(MainActivity.this, "Please enter a valid index", Toast.LENGTH_SHORT).show();
                        }
                    }

                    // else for second condition
                    else{
                        Toast.makeText(MainActivity.this, "Enter the index to remove", Toast.LENGTH_SHORT).show();
                    }
                }

                // else for first condition
                else{
                    Toast.makeText(MainActivity.this, "Currently have no task. Add in some to remove", Toast.LENGTH_SHORT).show();
                }

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