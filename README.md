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
   Copyright (c) 2019 daisyarusey

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

