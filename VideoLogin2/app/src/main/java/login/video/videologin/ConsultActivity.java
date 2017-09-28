package login.video.videologin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class ConsultActivity extends AppCompatActivity implements OnItemSelectedListener {

    private Spinner cidade;
    private Spinner provedor;
    private Spinner estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        cidade = (Spinner) findViewById(R.id.city_spin);
        provedor = (Spinner) findViewById(R.id.serve_spin);
        estado = (Spinner) findViewById(R.id.country_spin);

        //carregamento dos estados a partir do bd
        //TODO implementar acesso ao servidor
        loadCountrySpinners();
    }

    public void loadCountrySpinners() {

        //TODO subistituir por lista do servidor
        ArrayList<String> estados = new ArrayList<>();
        estados.add("ES");
        estados.add("MG");
        estados.add("PE");
        estados.add("PN");
        estados.add("RJ");
        estados.add("SP");
        //------

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado.setAdapter(adapter);
        estado.setOnItemSelectedListener(this);
    }

    public ArrayAdapter<String> getCitySpinner(String country) {
        //TODO subistituir por lista do servidor
        ArrayList<String> cities = new ArrayList<>();
        cities.add("cidade 1");
        cities.add("cidade 2");
        cities.add("cidade 3");
        cities.add("cidade 4");
        cities.add("cidade 5");
        cities.add("cidade 6");
        //-------

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public ArrayAdapter<String> getProvedorSpinner(String city) {
        //TODO subistituir por lista do servidor
        ArrayList<String> provedores = new ArrayList<>();
        provedores.add("provedor 1");
        provedores.add("provedor 2");
        provedores.add("provedor 3");
        provedores.add("provedor 4");
        provedores.add("provedor 5");
        provedores.add("provedor 6");
        //--------

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provedores);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public void onClick(View v) {

        boolean validCpf = false;

        if (v.getId() == R.id.consult_btn) {
            //TODO validar cpf digitado com o bd
            EditText cpf = (EditText) findViewById(R.id.cpfText);
            if (cpf.getText().toString().equals("123")) {
                validCpf = true;
            }

            if (validCpf) {
                Intent intent = new Intent();
                intent = new Intent(this, SubscribeActivity.class);
                startActivity(intent);
            } else {
                //messagem para cpf inválido
                AlertDialog alert;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle(R.string.alertCpfTitle);
                builder.setMessage(R.string.alertCpfContent);
                builder.setNeutralButton(R.string.alertCpfButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });
                alert = builder.create();
                //Exibe
                alert.show();
            }
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if (parent.getId() == R.id.country_spin) {
            //carregamento das cidades no spinner referentes ao estado selecionado
            cidade.setClickable(true);
            cidade.setAdapter(this.getCitySpinner(parent.getSelectedItem().toString()));
            cidade.setOnItemSelectedListener(this);
        } else if (parent.getId() == R.id.city_spin) {
            //carregamento dos provedores no spinner referentes a cidade selecionada
            provedor.setClickable(true);
            provedor.setAdapter(this.getProvedorSpinner(parent.getSelectedItem().toString()));
            provedor.setOnItemSelectedListener(this);
        } else if (parent.getId() == R.id.serve_spin) {
            //TODO pegar lista de cpf's para comparar com entrada do usuário, ou submeter cpf digitado

        }
    }

}
