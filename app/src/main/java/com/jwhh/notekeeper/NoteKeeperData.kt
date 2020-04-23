package com.jwhh.notekeeper

class CourseInfo(val courseId:String, val title:String) {
    //So only title form this CourseInfo class will be displayed in the Spinner
    override fun toString(): String {
        return title

    }
}

class NoteInfo(var course:CourseInfo, var title:String, var text:String)

