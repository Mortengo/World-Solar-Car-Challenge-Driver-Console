# World-Solar-Car-Challenge-Driver-Console
This is the World Solar Car Challenge driver console repository. To run the code the following will need to be installed and configured.

## Requirements
- Android Studio
- Java 8 JDK/SDK
- NodeJS
- GIT
- Simulator

## How to Install Required Software & Setup

### Android Studio
Android Studio can be installed here: https://developer.android.com/studio. Download and install Android Studio.
For the simulator please follow the instructions from this repository to install the simulator: https://github.com/TonySony44/WSC-Simulator-API

### Java Installation
Go to the following link: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html. Under __Java SE Development Kit 8u211__ accept the license agreement and select the appropriate operating system and download the file. Once downloaded, locate the file and install it.

#### Note
You may have to create a free account in order to download the JDk.

### GIT Installation
Please follow the following instructions to install GIT: https://www.atlassian.com/git/tutorials/install-git.
Find your operating system in the instructions before following the instructions.

### Simulator
For the application to work the simulator needs to be installed and running before running the Android application. To install the simulator please follow the instructions in this repository: https://github.com/TonySony44/WSC-Simulator-API. This will also show you how to install NodeJS.

### Cloning The Repository
To clone the repository open up a terminal or command line and change to the directory you wish to store the repository. i.e. `cd Desktop`. From there issue the following command `git clone https://github.com/TonySony44/World-Solar-Car-Challenge-Driver-Console.git` to clone the repository. Once completed check that the repository was successfully cloned by going to the location of where the repository was cloned.

### Opening The Project in Android Studio
Once Android Studio is installed, open it up. You will be greeted with a screen with a list of options. Select the option to __Open an existing Android Studio project__.
Find the repository you just cloned and open it. It make take sometime for Android Studio to setup, this may take anywhere from __5-10 minutes__.

### Setting Up Android Studio & The Emulator
Once in Android Studio, on the top select __tools__ and then select __AVD Manager__. A new window will appear. On the bottom left of the window select __Create Virtual Device__, __Tablet__ and then __New Hardware Profile__. For the following items configure them as follows:
- __Device Name:__ 15 Inch Tablet
- __Device Type:__ Phone/Tablet
- __Screen --> Screen Size:__ 15

Once these settings have been configured click finish. In the search bar, search for the device you just created __(i.e. 15 Inch Tablet)__. Click on it and click next. You will be prompted to install a system image. Select __Oreo API Level 27__. Then click the download button next to the image to download the image. Once that is done click next and then next again and then finish The device should now pop up on your screen.

#### Note
This may take up some space on your disk drive. Ensure you have enough storage on your drive before downloading the image. The image may be as big as __6GB__.

### Setting Up activity_main.xml
Under __app --> main --> res --> layout__ select __activity_main.xml.__ From there on the bottom left you will see a button called design, click on it. On the top left you will see a symbol that has a screen that is half rotated, click on it and change it to landscape instead of portrait. After that is done click the button next to the half rotated screen and select the device you created above. The design should now fit the dimensions.

### Running The Application
To run the application click the play button on the top. You will be prompted to select a Virtual Device. Select the device you created and click ok. The application will begin to load. It make take __10-15 seconds__ to load. Once loaded the application should be running in the emulator.

The speed, battery level and distance traveled should automatically change. To show the weather click on the weather button. It will take about __2 seconds__ for it to retrieve the weather. The weather will then be shown in the middle of the screen. To change the ambient temperature, next to the emulator you will see a bar with buttons. Select the button that represents three dots __(i.e. ...)__. Once clicked a new window will popup. From there on the left click on __Virtual Sensors__ and then on the top click __Additional Sensors__. You will then see a field called __Ambient Temperature__ with a slider. You can slide the slider left or right to change the ambient temperature or you can input or own value in the text field next to the slider. If you check the emulator the temperature should update in real time.
