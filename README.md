# MegaDroidSDK

[![Join the chat at https://gitter.im/GusWard/MegaDroidSDK](https://badges.gitter.im/GusWard/MegaDroidSDK.svg)](https://gitter.im/GusWard/MegaDroidSDK?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
Android library for handy and commonly used code across projects, please contribute anything you think may be useful for other developers and remember to add as many tests as you can think of!

## Features

## Activities

### ActivityMega
 
#### An Activity class with the following extras built in:

* TAG with name of class
* State aware
* Visibility aware
* Set decor mode
* Finish intent action

## Threading

### PeriodicTask 

#### Handler wrapper for periodic tasks:

* Set interval, max loops and UI enabled
* Stop / start at any time
* State aware

```java
PeriodicTask periodicTask = new PeriodicTask(
                runnable,
                period,
                loops,
                uiThread
);
periodicTask.start();
```
