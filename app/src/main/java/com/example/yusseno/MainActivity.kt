package com.example.yusseno

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yusseno.ui.theme.YussenoTheme

class MainActivity : ComponentActivity() {
    // Mendefinisikan MainActivity sebagai turunan dari ComponentActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        // Fungsi yang dipanggil ketika aktivitas dibuat
        super.onCreate(savedInstanceState)
        // Memanggil fungsi superclass onCreate

        setContent {
            // Menetapkan konten tampilan dengan Compose
            YussenoTheme {
                // Menerapkan tema Yusseno
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Menambahkan permukaan yang mengisi seluruh ukuran layar
                    Conversation(SampleData.conversationSample)
                    // Menampilkan komponen Conversation dengan data percakapan sampel
                }
            }
        }
    }
}

// Data class untuk merepresentasikan pesan dalam percakapan
data class Message(val author: String, val body: String)

// Objek yang berisi contoh data percakapan
object SampleData {
    // Contoh data percakapan
    val conversationSample = listOf(
        // Daftar pesan dalam percakapan
        Message(
            "Lexi",
            "Test...Test...Test..."
        ),
        Message(
            "Lexi",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trimMargin()
        ),
        Message(
            "Lexi",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trimMargin()
        ),
        Message(
            "Lexi",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Lexi",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trimMargin()
        ),
        Message(
            "Lexi",
            "It's available from API 21+ :)"
        ),
        Message(
            "Lexi",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Lexi",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Lexi",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Lexi",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Lexi",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Lexi",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Lexi",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}

// Fungsi komposisi untuk menampilkan kartu pesan
@Composable
fun MessageCard(msg: Message) {
    // Mengatur tata letak kartu pesan
    Row(modifier = Modifier.padding(all = 8.dp)) {
        // Menampilkan gambar profil pengirim pesan
        Image(
            painter = painterResource(R.drawable.kucing),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        // Memberikan spasi horizontal antara gambar profil dan teks
        Spacer(modifier = Modifier.width(8.dp))

        // Variabel untuk melacak apakah pesan diperluas atau tidak
        var isExpanded by remember { mutableStateOf(false) }

        // Mengganti nilai isExpanded ketika baris diklik
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            // Menampilkan nama pengirim pesan
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            // Menambahkan spasi vertikal antara nama pengirim dan isi pesan
            Spacer(modifier = Modifier.height(4.dp))

            // Menampilkan isi pesan dengan potongan maksimum
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

// Fungsi komposisi untuk menampilkan percakapan
@Composable
fun Conversation(messages: List<Message>) {
    // Menampilkan daftar pesan dalam percakapan dengan lazy column
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

// Fungsi untuk pratinjau percakapan dalam mode terang dan gelap
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewConversation() {
    // Menampilkan percakapan dalam pratinjau dengan tema Yusseno
    YussenoTheme {
        Conversation(SampleData.conversationSample)
    }
}
