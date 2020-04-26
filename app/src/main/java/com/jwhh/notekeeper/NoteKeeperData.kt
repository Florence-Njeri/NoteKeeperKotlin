package com.jwhh.notekeeper

data class CourseInfo(val courseId:String, val title:String) {
    //So only title form this CourseInfo class will be displayed in the Spinner
    override fun toString(): String {
        return title

    }
}

data class NoteInfo(var course:CourseInfo, var title:String, var text:String)

