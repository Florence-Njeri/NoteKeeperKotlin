package com.jwhh.notekeeper

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_notes_list.*
import kotlinx.android.synthetic.main.content_note_list2.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this,MainActivity::class.java))
        }

        listNotes.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1, DataManager.notes)

        listNotes.setOnItemClickListener { parent,view,position,id ->
        val activityIntent=Intent(this,MainActivity::class.java)
            activityIntent.putExtra(EXTRA_NOTE_POSITION,position)
            startActivity(activityIntent)
        }
    }




}
