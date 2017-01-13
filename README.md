# Popover Multi-OS Engine iOS UI

Implement a pop over. We have a problem with Auto Layout combined with popovers.

This project reproduces the minimal problem: using AutoLayout in PopOvers seems to remove all children from the view

List all simulators:

./gradlew moeListSimulators

Run on specific simulator:

./gradlew moeLaunch -Pmoe.launcher.simulators=BC9652EF-D701-4250-B6A3-AB35336E3CAD