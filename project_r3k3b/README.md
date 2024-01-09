# Project: Fitness Journal

## Rationale

A lot of students in UBC have been trying to exercise or start their 
workout jounrey while in college. In fact, there is an increasing 
proportion of people who exercise regularly over the last 5 years.
One important part about exercising is to make plans or keep track 
of the exercise that we have done before. Hence, the goal of this 
project is to create a fitness journal that can make people put in
their exercise plans in order to program their progress.

### What will the application do?
The application will save a person's workout journey about 
the information of the workout. Such as name, training area, 
and the sets, reps that they have done. In the end, 
it should be an useful app to plan ahead their workout so that 
people can stick to the plans. 

## Who will use it?
Anyone who is interested in tracking gym progress or want to 
customize an exercise program, such as student or coaches.

## Why is this project of interest to you?
As someone who goes to gym regularly, this is a small but 
essetial tool to boost performance consistently. By scheduling workouts
, we can be more systematic and motivated by the plans we created

As a user, I want to be able to:

- Able to select to a specifc day that 
  I want to add, remove or view the workout 

- Can choose between adding different type of workout Cardio/Strength exercise

- Add several workout for a specific day

- Remove the workout if wrongly added

- Look at the current workout I have put into my plan

- Save workout schedule 

- Load workout schedule 

- Reset the workout schedule if user wants to make a new one.

## "Phase 4: Task 2": Example of logging event

# Sample event from logging of adding 3 workout and removing a workout then save schedule 
- Fri Dec 01 14:04:41 PST 2023
Modify Day1
- Fri Dec 01 14:04:59 PST 2023
Add Strength exercise Bench
- Fri Dec 01 14:05:12 PST 2023
Add Strength exercise Pull ups
- Fri Dec 01 14:05:18 PST 2023
Modify Day2
- Fri Dec 01 14:05:23 PST 2023
Add Cardio exercise Running
- Fri Dec 01 14:05:36 PST 2023
Removed exercise Running
- Fri Dec 01 14:05:41 PST 2023
Save schedule



## Phase 4: Task 3": Reflection on the UML diagram

If I could do this project again, I would utilize more abstract classes to help me 
remove unnecessary duplication of classes. For example I could create a abstract class 
exercise with some command thing between Cardio and Strength Exercise. Same with 
window frame. A lot of setting and labeling is duplicated hence it will be a place to 
implement abstract class too.


