## Configure Firebase Console
After setup your Firebase in your Android Studio, go to "https://console.firebase.google.com",
1. Select the project you had create in Android Studio
2. Beside the Navigation bar, select "Database"

   <img src="https://github.com/Agmo-Android/TheSimpleLab/blob/master/ScreenShot/ScreenShot_Firebase_database.png" width="350">

3. Before setup your data, Go to "Rule" tab.

   <img src="https://github.com/Agmo-Android/TheSimpleLab/blob/master/ScreenShot/ScreenShot_Firebase_select_rule_tab.png" width="350">


4. Make sure you update your rule to this, this is remove the authentication, to let everyone can read and write.

   <img src="https://github.com/Agmo-Android/TheSimpleLab/blob/master/ScreenShot/ScreenShot_Firebase_rule_update.png" width="350">

5. After rule has update, select "Data" tab, time to create and update your data.
  - 1, move your mouse to my "timesimplelab"(in my case is show as timesimplelab, in your case it will be other name), it will show "+" icon and to click on "+" icon.
  
    <img src="https://github.com/Agmo-Android/TheSimpleLab/blob/master/ScreenShot/ScreenShot_Firebase_data_update_add_new_row_0.png" width="250">
  
  - 2, It will create a row, type in "event" into name section, after that click on "+" icon again.
  
    <img src="https://github.com/Agmo-Android/TheSimpleLab/blob/master/ScreenShot/ScreenShot_Firebase_data_update_add_new_row_1.png" width="300">
  
  - 3, It will create another row for you, from there type your "duration" into your name and value "10000". Same thing for "eventStartTime". At the end result should look like this. 
  
    <img src="https://github.com/Agmo-Android/TheSimpleLab/blob/master/ScreenShot/ScreenShot_Firebase_data_update_part1.png" width="350">

  - 4, After that click "Add" button. You have finish configuration Firebase.
