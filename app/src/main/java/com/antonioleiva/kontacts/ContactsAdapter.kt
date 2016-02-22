package com.antonioleiva.kontacts

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_item.view.*

class ContactsAdapter(val contacts: List<Contact>, val listener: (Contact) -> Unit) :
        RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position], listener)
    }

    override fun getItemCount() = contacts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contact: Contact, listener: (Contact) -> Unit) = with(itemView) {
            contactText.text = contact.name
            contactImage.loadUrl(contact.imageUrl)
            setOnClickListener { listener(contact) }
        }
    }
}