# Flood_Warning_App

This repository includes all of the .java files for the floodwarning system android app.
For a complete set of code including xml design files and build files (and more), follow this link: 
https://drive.google.com/file/d/0B58bd97ag0AyLUxLVHVsaWo4Y2M/view?usp=sharing

In order to run the code on your own android device, go to the above link and find app-debug.apk in /app/build/outputs/apk directory.
It was only tested on a OnePlus 3 phone, so mileage may vary on any other device. The phone must be using at least sdk version 23 (Marshmellow). 
Additionally, in order to modify or recompile any of the code, Android Studio (or IntelliJ Ultimate) is required, 
and modifying some of the build.gradle files may be necessary for it to compile, using any errors recieved as a guideline. Additionally,
a google maps api key will be necessary for the code to work properly (only relevant if recompiling).

The primary feature of this app is to get data regarding flood monitoring stations from a UK database, as well as risks calculated
on a Flask Server and delivered via RESTful, and then display them on a map. Clicking update will retrieve the list of all stations,
and then tapping any will display it on the map. Currently updating takes a very long time because the server is hosted on a low end
machine and caching has not been implemented yet (although in theory it should be fairly straightforward to add in the future).

The code handling all of the stationdata computations can be found here: https://github.com/MaxVigdorchik/partia-flood-warning-system/.

Some screenshots of the app's functionality can be found here: https://drive.google.com/file/d/0B58bd97ag0AyYnJOc0JKaTFKbG8/view?usp=sharing

Development is currently stopped while the code is being marked.

Created by Max Vigdorchik
