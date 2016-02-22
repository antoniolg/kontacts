package com.antonioleiva.kontacts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = GridLayoutManager(ctx, 2)

        async() {
            val contacts = RetrieveContacts().execute(ctx)
            uiThread {
                recycler.adapter = ContactsAdapter(contacts) { navigateToDetail(it) }
            }
        }
    }

    private fun navigateToDetail(contact: Contact) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_NAME to contact.name,
                DetailActivity.EXTRA_IMAGE to contact.imageUrl)
    }
}
