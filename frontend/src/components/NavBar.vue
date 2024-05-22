<script setup>
    import translations from '../config/nl-NL'
</script>

<template>
    <div class="navbar navbar-dark navbar-expand-md zapp-gradient">
        <RouterLink to="/"><img class="navbar-brand ms-2 me-5" src="../assets/small-logo.png" height="50" /></RouterLink>
        <div class="nav-item ms-2 zapp-text text-white">
            {{$route.meta.title}}
        </div>
        <button class="navbar-toggler flex-row ms-auto float-right me-2" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon" style = "color:white;"></span>
        </button>
        <div class="dropdown order-md-last order-0 user-icon text-center">
            <i class="fs-2 bi bi-person py-0 text-white h-100" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i>
            <div class="dropdown-menu dropdown-menu-end user-menu zapp-gradient-continue" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item text-white" href="#">Action</a>
                <a class="dropdown-item text-white" href="#">Another action</a>
                <a class="dropdown-item text-white" href="#">Something else here</a>
            </div>
        </div>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav ms-auto d-md-flex float-right me-3">
                <RouterLink class="nav-item nav-link text-white ms-1 me-auto" :to="'/' + translations.employees">{{translations.employees}}</RouterLink>
                <RouterLink class="nav-item nav-link text-white ms-1 me-auto" :to="'/' + translations.customers">{{translations.customers}}</RouterLink>
                <RouterLink class="nav-item nav-link text-white ms-1 me-auto" :to="'/' + translations.tasks">{{translations.tasks}}</RouterLink>
            </div>
        </div>
    </div>
</template>

<style scoped>
    .user-menu {
        top: 0;
        margin-top: 0.5rem !important;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border: 1px solid white;
        border-top: none;
        border-right: none;

    }

    .user-icon {
        height: 50px;
        width: 50px;
    }
</style>