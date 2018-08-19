# CarPool
Application list the available cars and also shows their locations on Google Map

**Base URI**
`https://s3-us-west-2.amazonaws.com/wunderbucket/`

**HTTP method**

GET

**Response formats**

JSON (`.json`, default)

Prerequisites
--------------

- minSdkVersion 19
- targetSdkVersion 28
- Latest Android Build Tools
- Android Support Repository
- Dagger (Depedency Injection)
- Retrofit
- RxAndroid

Getting started
---------------

This sample uses the Gradle build system.

Download the copy by cloning this repository or downloading an archived snapshot. (See the options at the top of the page.)
In Android Studio, create a new project and choose the "Import non-Android Studio project" or "Import Project" option.
Generate a Google Map API key form [Google Developers Console](https://developers.google.com/maps/documentation/android/start#get-key) and replace your key with API_KEY in Manifest file.

First view contains a two fragments 

1.CarListFragment list the available cars
2.CarMapFragment place the markers based on the car location. When we tap on a marker other markers get hidden and it shows car name as info window
 When we tap again info window get hidden and show all markers.
  
## Architecture Pattern
The application make use of MPV architecture pattern.

## License
Please see the [LICENSE.md](https://github.com/SinoKD/CarPool/blob/master/LICENSE) file for details
