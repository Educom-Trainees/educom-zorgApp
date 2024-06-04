# educom-zorgApp
Dit is een private repository voor Educom trainees om een groepsopdracht te doen.
/
/
Hierbij wordt een zorg applicatie gebouwd gebaseerd op [deze opdracht](https://e-learning.educom.nu/cases/ZAPP/intro)

## Goal
The goal of this project is to create an application which can be used by employees (healthcare workers), planners, and admins. Planners and admins need a webpage where they can see and manage appointments, customers, employees, and tasks. The healthcare workers need a mobile-friendly page, where they can view their appointments and check off any tasks they need to complete.

## 1. Scope
This project consists of two major parts, the backend database, and the frontend web application.

### 1.1 Frontend
The website is a Single Page Application (SPA) made with Vue 3 Composition API and Vite. URL routing is achieved with vue-router and queries are managed using vue-query.

### 1.2 Backend
Springboot and Maven

## 2. Software Maintenance Resources

### 2.1 Frontend

#### Structure
All files are contained in the ```/src``` folder.

##### api directory
Contains js file for functions handling fetch requests

##### assets directory
Contains images, icons, fonts, and CSS files. Anything to do with styling.

##### components directory
Contains all generic components which could be used in multiple pages/views.

##### config directory
Contains translation file so all non-english words are contained there. Allows all other files to stay more readable.

##### pages directory
Contains directories for multiple components with a similar goal (i.e. Employees). Any components used exclusively by these views can also be placed in these folders (e.g. WorkSchedule.vue is located in the ```pages/employees``` folder).

##### router directory
Contains the ```index.js``` file which defines routes used by vue-router.

##### utils directory
Contains any js files with functions used by multiple Vue files.

##### views directory
Contains any Vue files for top-level pages (e.g. LoginView, HomeView).

#### Tools/Packages
The frontend application is written in Vue 3 using the composition API, meaning most vue files will have the following layout:
```
<script setup>
  ...
</setup>

<template>
  ...
</template>
```

Vue Router is used to manage URL routing. Routes are defined in the ```src/router/index.js``` file. Routes specific to certain pages, are placed in their own folder, and then imported to the main ```index.js``` file.

Vue Query (TanStackQuery) is used to manage queries to the database. Vue query automatically caches responses, and will use any cached data when possible. This allows for quicker load times.

The vue-datepicker package is used for both calendar and time inputs. Calendar inputs use the built-in Date object. Time inputs require an object with the following format:
```
{
  hours: 10,
  minutes: 14,
  seconds: 53,
}
```
Most functions relating to these time objects can be found in the ```utils/time.js``` file

### 2.2 Backend
