package com.example.cifradocesar;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText inputText, inputShift;
    Button buttonExecute;
    TextView outputResult, outputHistorial;
    Spinner menuOpciones;

    StringBuilder historialCompleto = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        inputShift = findViewById(R.id.inputShift);
        buttonExecute = findViewById(R.id.buttonExecute);
        outputResult = findViewById(R.id.outputResult);
        outputHistorial = findViewById(R.id.outputHistorial);
        menuOpciones = findViewById(R.id.menuOpciones);

        // Cargar opciones del Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opciones_menu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuOpciones.setAdapter(adapter);

        buttonExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opcionSeleccionada = menuOpciones.getSelectedItem().toString();

                if (opcionSeleccionada.equals("Leer desde archivo (.txt)")) {
                    outputResult.setText("Funcionalidad aún en desarrollo.");
                    return;
                }

                String text = inputText.getText().toString();
                String shiftStr = inputShift.getText().toString();

                if (text.isEmpty() || shiftStr.isEmpty()) {
                    outputResult.setText("Por favor, completa todos los campos.");
                    return;
                }

                int shift = Integer.parseInt(shiftStr);
                String result;
                String tipo;

                if (opcionSeleccionada.equals("Cifrar texto")) {
                    result = CifradoCesar.encrypt(text, shift);
                    tipo = "Cifrado";
                } else if (opcionSeleccionada.equals("Descifrar texto")) {
                    result = CifradoCesar.decrypt(text, shift);
                    tipo = "Descifrado";
                } else {
                    outputResult.setText("Selecciona una opción válida.");
                    return;
                }

                outputResult.setText("Resultado: " + result);

                String fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

                historialCompleto.append(fechaHora)
                        .append(" - ").append(tipo)
                        .append(" '").append(text).append("' ➝ ")
                        .append(result).append("\n");

                outputHistorial.setText(historialCompleto.toString());
            }
        });
    }
}
