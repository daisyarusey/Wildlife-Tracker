# Wildlife-tracker
This is an application that allows rangers to sighting animals and even endangered animals.


## Setup and Installation requirements
* Go to the projects [repository]
* Clone the project
```
git clone
```
* Install gradle
```
sdk install gradle 5.3.1
```
* Install java
```
sdk install java
```
* Open the directory in terminal
* move to main
```
cd build/classes/java/main
```
* Run the following command to execute the Terminal-java application
```
java App
```
* In PSQL:
```
CREATE DATABASE wildlife;
CREATE TABLE sighting (id serial PRIMARY KEY, animal_id,location varchar,ranger_id,time_recorded timestamp);
CREATE TABLE animals (id serial PRIMARY KEY, name varchar,age varchar,health varchar, type varchar);
```

## Technologies used
   * Java.
   * Gradle(for unit testing).
   * Bootstrap.
   * Spark.
  

## Contact details
   For more information reach out to daisyarusey.dc@gmail.com

## Licence
   MIT License
