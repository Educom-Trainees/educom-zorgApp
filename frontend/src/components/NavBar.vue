<template>
    <div class="navbar navbar-expand zapp-gradient fixed-top">
        <RouterLink to="/"><img class="navbar-brand ms-2 me-5" src="../assets/small-logo.png" height="50"/></RouterLink>
        <div class="nav-item ms-2 zapp-text text-white">
            {{$route.meta.title}}
        </div>
        <ul class="navbar-nav flex-row ms-auto d-md-flex float-right me-5">
            <RouterLink class="nav-item nav-link text-white me-2" to="/">Medewerkers</RouterLink>
            <RouterLink class="nav-item nav-link text-white me-2" to="/">Klanten</RouterLink>
            <RouterLink class="nav-item nav-link text-white me-2" to="/login">Taken</RouterLink>
        </ul>
    </div>
</template>