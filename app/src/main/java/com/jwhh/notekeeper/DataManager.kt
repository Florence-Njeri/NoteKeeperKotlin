package com.jwhh.notekeeper

/**Will manage NoteKeeperData classes instances
 * It will hold a collection of notes and courses
 */
class DataManager {
    //Will hold collection of courses
    val courses=HashMap<String,CourseInfo>()

    //Will hold collection of notes
    val notes= ArrayList<NoteInfo>()

    init {
        initializeCourses()
    }


    private fun initializeCourses(){
        var course=CourseInfo("android_intents","Android Programming with Intents")
        courses.set(course.courseId,course)

        course=CourseInfo(courseId = "android_async",title = "Android Async Programming with Services")
        courses.set(course.courseId,course)

       course=CourseInfo(title = "Java Fundamentals the Language",courseId = "java_lang")
        courses.set(course.courseId,course)

        course=CourseInfo("java_core","Java Fundamentals: The Core Platform")
        courses.set(course.courseId,course)

    }
}