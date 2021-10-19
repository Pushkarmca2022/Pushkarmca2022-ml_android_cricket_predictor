package student.android.cricket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String url = "https://192.168.43.226/predict";
    String a,b,c;
    String d,e,f,g;
    EditText t,u,v,w;

    TextView result;
    String[] items =  {"Australia",
            "India",
            "Bangladesh",
            "New Zealand",
            "South Africa",
            "England",
            "West Indies",
            "Afghanistan",
            "Pakistan",
            "Sri Lanka"};
    String[] city ={"Colombo",
            "Mirpur",
            "Johannesburg",
            "Dubai",
            "Auckland",
            "Cape Town",
            "London",
            "Pallekele",
            "Barbados",
            "Sydney",
            "Melbourne",
            "Durban",
            "St Lucia",
            "Wellington",
            "Lauderhill",
            "Hamilton",
            "Centurion",
            "Manchester",
            "Abu Dhabi",
            "Mumbai",
            "Nottingham",
            "Southampton",
            "Mount Maunganui",
            "Chittagong",
            "Kolkata",
            "Lahore",
            "Delhi",
            "Nagpur",
            "Chandigarh",
            "Adelaide",
            "Bangalore",
            "St Kitts",
            "Cardiff",
            "Christchurch",
            "Trinidad"};
    AutoCompleteTextView autoCompleteTxt;
    AutoCompleteTextView autoCompleteTxt1;
    AutoCompleteTextView autoCompleteTxt2;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems1;
    Button predict ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



         t=findViewById(R.id.inputUsername);
        u=findViewById(R.id.inputEmail);
        w=findViewById(R.id.inputConformPassword);
        v=findViewById(R.id.inputPassword);
        result=findViewById(R.id.resul);
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        autoCompleteTxt1 = findViewById(R.id.auto_complete_txt1);
        autoCompleteTxt2 = findViewById(R.id.auto_complete_txt2);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        adapterItems1 = new ArrayAdapter<String>(this,R.layout.list_item,city);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt1.setAdapter(adapterItems);
        autoCompleteTxt2.setAdapter(adapterItems1);

        predict = findViewById(R.id.btnRegister);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                a = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+a, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTxt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                b = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+b, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               c = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+c, Toast.LENGTH_SHORT).show();
            }
        });
        //Button

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hit the API -> Volley


                d=t.getText().toString();
                e=u.getText().toString();
                f=v.getText().toString();
                g=w.getText().toString();
                System.out.println("   //////  "+a+b+c+d+e+f+g);



                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("Run");

                                        result.setText(data.toString());


                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){

                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();

                        params.put("a",a);
                        params.put("b",b);
                        params.put("c",c);
                        params.put("d",d);
                        params.put("e",e);
                        params.put("f",f);params.put("g",g);



                        return params;
                    }

                };
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(stringRequest);
            }
        });




    }
}