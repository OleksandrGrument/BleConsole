# BleConsole


<h3>Release Note </h3>

<a data-flickr-embed="true"  href="https://www.flickr.com/photos/152012252@N06/38741580991/in/album-72157685903489972/" title="BleConsoleUi"><img src="https://farm5.staticflickr.com/4548/38741580991_83ff04678a_c.jpg" width="800" height="659" alt="BleConsoleUi"></a>
<h3>About </h3>

This app i have developed for IoT Developers, to help them in development of firmare for new smart devices.

The IoT is a concept where everyday devices can be accessed through the internet using well-known technologies such as URLs and HTTP requests.
The first step of IoT it's a "Smart Home". The automation of this is a main goal of developers.
Soon, IoT will offer consumers the ability to interact with nearly every appliance and device they own. For example, your refrigerator will let you know when you are running low on milk and your dishwasher will inform you when it is ready to be emptied. 


This app was developed to communicate with Bluetooth LE peripheral and should be useful in Bluetooth Low Energy peripheral equipment development. (Write commands and read state of LE peripheral)

It is based on an example in Android's SDK - 
https://developer.android.com/guide/topics/connectivity/bluetooth-le.html
<br>
The working example of the APP you can find - 
https://play.google.com/store/apps/details?id=com.grument.bleconsole


<h3>Architecture </h3>
I have write this project to  be compatible with the MVP architecture, MVP Architecture decoupled the View and logical data layer.

<a data-flickr-embed="true"  href="https://www.flickr.com/photos/152012252@N06/37853865955/in/album-72157685903489972/" title="MVP Architecture"><img src="https://farm5.staticflickr.com/4554/37853865955_151432dbb8_z.jpg" width="640" height="493" alt="MVP Architecture"></a>

<h3>Technology stack</h3>
Was use Dependency Injection(DI) implementation is Dagger 2, to enhace the MVP Architecture and make it more "clear".
The "Event bus" helped me create a good conversation between Activities and LeService, made it more easy.
Also was use the Butterknife library for view injection in Android.





