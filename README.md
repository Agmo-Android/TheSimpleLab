# The Simple App

### Require item to do
This app require Firebase setup by yourself. Please go thougth the setup step by step, refer to [Setup Firebase in Android Studio!](https://github.com/Agmo-Android/TheSimpleLab/blob/master/CodeLab/Setup%20Firebase%20in%20Android%20Studio.md) and here [Configure Firebase Console!](
https://github.com/Agmo-Android/TheSimpleLab/blob/master/CodeLab/Configure%20Firebase%20Console.md).


### App Example
Here is a simple look for this App

 <img src="https://github.com/Agmo-Android/TheSimpleLab/blob/master/device-2017-04-09-021442.png" width="220"/>


## Some tips 

### Setup Firebase in Android Studio

To setup Firebase in Android studio, Go to this link [Setup Firebase in Android Studio!](https://github.com/Agmo-Android/TheSimpleLab/blob/master/CodeLab/Setup%20Firebase%20in%20Android%20Studio.md)

### Configure Firebase Console

After setup firebase complete, start to create your data. Go to this link 
[Configure Firebase Console!](
https://github.com/Agmo-Android/TheSimpleLab/blob/master/CodeLab/Configure%20Firebase%20Console.md).


### How to use Millis Second in database for Event
- Thing you need to know first:
  - 1000 mills second is 1 Second
  - To convert mills into second, you need to divide by 1000
  - Example : 20000 / 1000 = 20 Seconds
  - Second is for display use, but in the app is use as Mills second
- Website to get millis second “https://currentmillis.com”
- How to use, Copy the millis, show as down below

![Image of Millis to copy](https://github.com/Agmo-Android/TheSimpleLab/blob/master/ScreenShot/ScreenShot_Millis_to_Copy.png)

- If you want to add 20 second after the event start, copy the millis + 20,000, this became your event start time.
- Pass the result to database under event > eventStartTime
