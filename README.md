# Quiz Demo

# Database Design

![erdiagram](./readme_resource/erdiagram.png)

# Where is the databse initialization file
1. ./src/main/resources/static/initialization.sql
2. ./src/main/resources/static/addData.sql
3. I also provide a database dump you can find a file called `dump1.sql`(Please use this to generate the db)

# Basic requirement
All has been done



# Bonus requirements
1. Add CSS/BootStrap
1. Display different CSS Styles for answered and unanswered questions
1. A Countdown timer for the quiz
1. In the event that the admin changes the correct answer for a question, the corresponding quiz results will also be updated accordingly
5. Add  a "view" button at the end of each row to allow the admin to view the full contact message by opening a new page to avoid a message content being too long.



# Some Assumption
1. User's email cannot be updated
2. Category name should be unique and not null
3. Question choice cannot have empty description
4. When we are modifying question, we cannot add additional choice and change the category
