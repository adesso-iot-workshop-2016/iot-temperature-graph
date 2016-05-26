# IoT example project implementing a gui with a temperature diagram


## Introduction

This project is using a JavaFX frontend. It makes use of relayr Java API.
This API is leveraging RxJava. Details can be found on https://github.com/relayr/java-sdk.


## Prerequisites
* [git](https://git-scm.com/)
* [JDK](http://www.oracle.com/technetwork/java/javaee/downloads/index.html)
* [IntelliJ IDEA] (https://www.jetbrains.com/idea/)

## Configuration of the relayr cloud
Configure your Wunderbar on the relayr cloud. Retrieve the bearer token identifiying you and the device id of the gyroskop of your wunderbar. The bearer token can be retrieved from the [developer dashboard](https://developer.relayr.io/dashboard/account/general).

Update the token and the device id in the TemperatureGraph.java File.

## Installation
Import the project into the IntelliJ IDEA and run the TemperatureGraph.