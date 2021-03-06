package com.jwhh.notekeeper

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private var notePosition = POSITION_NOT_SET
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Use an adapter to populate our spinner with courses from our DataManager class

        var adapterCourses = ArrayAdapter<CourseInfo>(this, android.R.layout.simple_spinner_item, DataManager.courses.values.toList())

        //DropDown for the spinner layout
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //Now associate the adapter with the spinner
        spinnerCourses.adapter = adapterCourses

        notePosition =savedInstanceState?.getInt(EXTRA_NOTE_POSITION, POSITION_NOT_SET) ?:
                intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)

        if (notePosition != POSITION_NOT_SET) {
            displayNoteInfo()
        }
        else{
           DataManager.notes.add(NoteInfo())
            notePosition=DataManager.notes.lastIndex
        }

    }

    private fun displayNoteInfo() {
        var note = DataManager.notes[notePosition]

        textNoteText.setText(note.text)
        textNoteTitle.setText(note.title)

        //Select appropriate note from the spinner
        var coursesPosition = DataManager.courses.values.indexOf(note.course)
        spinnerCourses.setSelection(coursesPosition)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveToNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveToNext() {
        notePosition++
        displayNoteInfo()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)

            if(menuItem != null){
                menuItem.icon = getDrawable(R.drawable.ic_block_white_24dp)
                menuItem.isEnabled = false
            }
        }


        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note=DataManager.notes[notePosition]
        note.title=textNoteTitle.text.toString()
                note.text=textNoteText.text.toString()
        note.course=spinnerCourses.selectedItem as CourseInfo
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt(EXTRA_NOTE_POSITION,notePosition)
    }

}
