<template class="m-0">
    <div class="zapp-gradient vh-100 vw-100">
        <RouterLink to="/"><img class="ms-2 mt-2" src="../assets/small-logo.png" width="5%" /></RouterLink>
        <div class="container">
            <form class="row vw-100 vertical-center">
                <div class="col-4 offset-4 mb-3 pe-5 ps-5">
                    <input class="form-control" type="text" placeholder="Gebruikersnaam" />
                </div>
                <div class="col-4 offset-4 mb-3 pe-5 ps-5">
                    <input class="form-control" type="password" placeholder="Wachtwoord" />
                </div>
                <button class="login-button col-2 offset-4">Vergeten?</button>
                <button class="login-button col-2" type="submit">Login</button>
            </form>
        </div>
        <img class="img-bottom-right mb-2 me-2" src="../assets/logo-text.png" width="25%" />
</div>
</template>