package com.example.nav

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nav.Constants.DEFAULT_TOKEN
import com.example.nav.Constants.ROOT_FRAG_TAG
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var tokenManager: LogInStateManager
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var navview: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerlayout)
        navview = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close)
        settingviews()
        val dao = ProfileDB.getDB(this.applicationContext).ProfileDAO()
        val repository = Repository(dao)
        sharedViewModel = ViewModelProvider(
            this,
            SharedViewModelFactory(repository)
        ).get(SharedViewModel::class.java)

        sharedViewModel =
            ViewModelProvider(this, SharedViewModelFactory(repository)).get(
                SharedViewModel::class.java
            )

        tokenManager = LogInStateManager(applicationContext)
        if (tokenManager.getToken() != DEFAULT_TOKEN) {

            sharedViewModel.iid.value = tokenManager.getToken()
            replacefragment(Fragment1(), "Home", 1)
        } else
           replacefragment(Introduction(), "Get Started", 0)

    }

    private fun settingviews() {
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navview.setNavigationItemSelectedListener {


            when (it.itemId) {
                R.id.nav_home -> Toast.makeText(applicationContext,"clicked home", Toast.LENGTH_SHORT).show()
                R.id.firstsegmemt -> replacefragment(Fragment1(), it.title.toString(),1)
                R.id.secondsegment -> replacefragment(Fragment2(), it.title.toString(),1)
                R.id.thirdsegment -> replacefragment(Fragment3(), it.title.toString(),1)
                R.id.fourthsegment -> replacefragment(Fragment3(), it.title.toString(),1)
                R.id.btAct2 -> switchActivity()
            }
            true}
    }

    private fun replacefragment(fragment: Fragment, title: String,flag:Int) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            if(flag==1){
                supportFragmentManager.popBackStack(ROOT_FRAG_TAG,0)
                addToBackStack(ROOT_FRAG_TAG)
            }else
                addToBackStack(null)
            commit()
        }
    drawerLayout.closeDrawers()
    setTitle(title)
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (toggle.onOptionsItemSelected(item)) {
        return true
    }

    return super.onOptionsItemSelected(item)
}
    private fun switchActivity() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }


}