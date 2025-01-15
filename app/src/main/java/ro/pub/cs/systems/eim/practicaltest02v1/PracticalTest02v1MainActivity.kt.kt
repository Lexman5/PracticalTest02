package ro.pub.cs.systems.eim.practicaltest02v1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class PracticalTest02v1MainActivity : AppCompatActivity() {

    private lateinit var inputPrefix: EditText
    private lateinit var searchButton: Button
    private lateinit var resultsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Asigură-te că layout-ul corect este referit
        setContentView(R.layout.activity_practical_test02v1_main)


        // Leagă componentele din layout
        inputPrefix = findViewById(R.id.inputPrefix)
        searchButton = findViewById(R.id.searchButton)
        resultsTextView = findViewById(R.id.resultsTextView)

        // Setează logica pentru buton
        searchButton.setOnClickListener {
            val prefix = inputPrefix.text.toString().trim()

            if (prefix.isNotEmpty()) {
                // Simulează căutarea și afișează rezultatele
                val results = simulateAutocomplete(prefix)
                resultsTextView.text = results.joinToString("\n")
            } else {
                resultsTextView.text = "Te rog introdu un prefix!"
            }
        }
    }

    // Funcție care simulează o completare automată
    private fun simulateAutocomplete(prefix: String): List<String> {
        val possibleResults = listOf("Google", "GitHub", "GitLab", "Gmail", "Golang", "GraphQL")
        return possibleResults.filter { it.startsWith(prefix, ignoreCase = true) }
    }
}

