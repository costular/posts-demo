#!/bin/sh
./disable_device_amnimations.sh
./gradlew connectedAndroidTest
./enable_disable_device_amnimations.sh