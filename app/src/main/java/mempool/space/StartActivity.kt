package mempool.space

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import mempool.space.activity.HomeActivity

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
