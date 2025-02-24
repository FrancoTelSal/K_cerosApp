
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.kcerosapp.ui.theme.KcerosAppTheme
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

@Composable
fun FirebaseCon() {
    val context = LocalContext.current
    val db = Firebase.database
    var data by remember { mutableStateOf("") }

    // Lectura de datos
    DisposableEffect(Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data = snapshot.getValue(String::class.java) ?: ""
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de errores
            }
        }
        db.getReference("https://k-ceros-default-rtdb.firebaseio.com/").addValueEventListener(listener)
        onDispose {
            db.getReference("https://k-ceros-default-rtdb.firebaseio.com/").removeEventListener(listener)
        }
    }
    Column {
        Button(onClick = {
            db.getReference("https://k-ceros-default-rtdb.firebaseio.com/").setValue("Nuevo valor")
        }) {
            Text("Escribir en Firebase")
        }

        Text("Datos desde Firebase: $data")
    }
    }
    // Escritura de datos (ejemplo)


@Preview
@Composable
private fun FirebaseConPreview() {
    KcerosAppTheme {
        FirebaseCon()
    }
}