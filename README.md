# 1675 Utils
Repository for game independent software utilities used by FRC Team 1675

## This project 
This project is a command-based program skeleton. The utilities are collected here, and the relevant files are named in the subsections of this readme. To see an example of the usage of the components, simply download and run this project.

## Uper Tuner
One the fly number tuning for rapid iteration of configuration values.

### Relevant Files
TODO

### Usage
TODO

## JSON Configurable Subsystems
Configure WPILib subsystems using JSON files rather than the typically `Constants.java` approach. This is a more standard, modern practice which keeps configuration options well-scoped and allows for simpler configuration. 

### Relevant Files
The file which new is `JsonConfigurableSubsystems.java` 

### Usage
Simply copy the source from `JsonConfigurableSubsystems.java` and include it in your project. Extend subsystems you would like to configure with JSON from the `JsonConfigurableSubsystems` class rather than `SubsystemBase`. Place any configurations in `deploy/config/name-of-your-subsystem.json`. Call the `getConfig()` method to access your properties. 

Jake Herrmann § Team 1675