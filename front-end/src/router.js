import { createRouter, createWebHistory } from 'vue-router';

// IMPORTARE TUTTE LE PAGINE DALLA CARTELLA "PAGES"
import PhotoIndex from './components/PhotoIndex.vue';
import ContactForm from './components/ContactForm.vue';
import NotFound from './components/NotFound.vue';


// CREIAMO IL ROUTER CON LE SUE ROTTE
const router = createRouter({

    history: createWebHistory(),

    routes: [
        {
            path: '/',
            name: 'index',
            component: PhotoIndex,
        },
        {
            path: '/contact',
            name: 'contact',
            component: ContactForm,
        },
        {
            path: '/:catchAll(.*)',
            redirect: '/not-found',
        },
        {
            path: '/not-found',
            name: 'not-found',
            component: NotFound,
        },
    ]

})

// ESPORTIAMO IL ROUTER
export { router }