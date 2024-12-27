package com.example.a7minutesworkout

object Constants {
    fun defaultExerciseList():ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJucks = ExerciseModel(
            1,
            "jumping jacks",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        exerciseList.add(jumpingJucks)

        val abdominalCrunch = ExerciseModel(2,"abdominal Crunch",
            R.drawable.ic_abdominal_crunch,false,false)
        exerciseList.add(abdominalCrunch)

        val highKneeRunning = ExerciseModel(
            3,
            "high knee running in place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        exerciseList.add(highKneeRunning)

        val lunge = ExerciseModel(
            4,
            "lunge",
            R.drawable.ic_lunge,
            false,
            false
        )
        exerciseList.add(lunge)

        val plank = ExerciseModel(
            5,
            "plank",
            R.drawable.ic_plank,
            false,
            false
        )
        exerciseList.add(plank)

        val pushUp = ExerciseModel(
            6,
            "push up",
            R.drawable.ic_push_up,
            false,
            false
        )
        exerciseList.add(pushUp)

        val pushUpAndRotation = ExerciseModel(
            7,
            "push up and rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        exerciseList.add(pushUpAndRotation)

        val sidePlank = ExerciseModel(
            8,
            "side palnk",
            R.drawable.ic_side_plank,
            false,
            false
        )
        exerciseList.add(sidePlank)

        val squat = ExerciseModel(
            9,
            "squat",
            R.drawable.ic_squat,
            false,
            false
        )
        exerciseList.add(squat)

        val stepUpOnToChair = ExerciseModel(
            10,
            "step Up On To chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        exerciseList.add(stepUpOnToChair)

        val tricepsDipOnChair = ExerciseModel(
            11,
            "triceps dib on chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        exerciseList.add(tricepsDipOnChair)

        val wallSit = ExerciseModel(
            12,
            "wall sit",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        exerciseList.add(wallSit)


        return exerciseList
    }
}