package ro.pub.cs.systems.eim.practicaltest02v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PracticalTest02v1MainActivity : AppCompatActivity() {

    private lateinit var inputKey: EditText
    private lateinit var inputValue: EditText
    private lateinit var fetchButton: Button
    private lateinit var addButton: Button
    private lateinit var deleteButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test02v1_main)

        inputKey = findViewById(R.id.inputKey)
        inputValue = findViewById(R.id.inputValue)
        fetchButton = findViewById(R.id.fetchButton)
        addButton = findViewById(R.id.addButton)
        deleteButton = findViewById(R.id.deleteButton)
        resultTextView = findViewById(R.id.resultTextView)

        fetchButton.setOnClickListener {
            val key = inputKey.text.toString()
            if (key.isNotEmpty()) {
                handleGet(key)
            } else {
                resultTextView.text = "Introdu o cheie pentru a căuta!"
            }
        }

        addButton.setOnClickListener {
            val key = inputKey.text.toString()
            val value = inputValue.text.toString()
            if (key.isNotEmpty() && value.isNotEmpty()) {
                handlePost(key, value)
            } else {
                resultTextView.text = "Cheia și valoarea nu pot fi goale!"
            }
        }

        deleteButton.setOnClickListener {
            val key = inputKey.text.toString()
            if (key.isNotEmpty()) {
                handleDelete(key)
            } else {
                resultTextView.text = "Introdu o cheie pentru a șterge!"
            }
        }
    }

    private fun handleGet(key: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = Server.handleGet(key)
            withContext(Dispatchers.Main) {
                resultTextView.text = response
            }
        }
    }

    private fun handlePost(key: String, value: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = Server.handlePost(key, value)
            withContext(Dispatchers.Main) {
                resultTextView.text = response
            }
        }
    }

    private fun handleDelete(key: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = Server.handleDelete(key)
            withContext(Dispatchers.Main) {
                resultTextView.text = response
            }
        }
    }
}
