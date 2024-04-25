<script setup>
    import translations from '../config/nl-NL'


</script>

<template class="m-0">
    <div class="zapp-gradient vh-100 vw-100">
        <RouterLink to="/"><img class="ms-2 mt-2" src="../assets/small-logo.png" height="10%" /></RouterLink>
        <div class="container vw-100 vertical-center">
            <div class="row">
                <div class="col-2 col-md-3 col-lg-4"></div>
                <form class="col-8 col-md-6 col-lg-4">
                    <div class="mb-3">
                        <input class="form-control" type="text" :placeholder="translations.username" tabindex="1" />
                    </div>
                    <div class="mb-3">
                        <input class="form-control" type="password" :placeholder="translations.password" tabindex="2" />
                    </div>
                    <button class="login-button w-50" tabindex="4" >{{translations.forgot_password}}?</button>
                    <button class="login-button w-50" type="submit" tabindex="3" >{{translations.login_button}}</button>
                </form>
            </div>
        </div>
        <img class="position-bottom-right mb-2 me-2" src="../assets/logo-text.png" height="15%" />
    </div>
</template>