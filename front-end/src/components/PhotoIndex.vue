<script setup>
// IMPORT LIBS
import { onMounted, computed, ref } from 'vue';
import axios from 'axios';

// IMPORT COMPONENTS
import PhotoShow from './PhotoShow.vue';

// DATA
const photos = ref(null);
const photoActive = ref(null);
const nomeFilter = ref('');

// COMPUTED PROPERTY
const newPhotos = computed(() => {
    const searchTerm = nomeFilter.value.toLowerCase().trim();
    return searchTerm ? photos.value.filter(photo => photo.name.toLowerCase().includes(searchTerm)) : photos.value;
});

// FUNCTIONS
const getPhotos = async () => {
    const data = await axios.get("http://localhost:8080/api/photos");
    photos.value = data.data;
};
const openPhoto = (id) => {
    photos.value.forEach((photo) => {
        if (photo.id === id) {
            photoActive.value = photo;
        }
    });
};
const closePage = () => {
    photoActive.value = null;
    getPhotos();
};

// HOOKS
onMounted(getPhotos);
</script>

<template>
    <div class="col-12" v-if="photoActive == null">
        <div class="row">
            <div class="col-12 text-center py-5">
                <router-link :to="{ name: 'contact' }">
                    <button class="btn btn-primary">
                        <span>Contattaci</span>
                    </button>
                </router-link>
            </div>
            <div class="col-12 d-flex justify-content-center">
                <form class="index-filter-form py-5 w-50" @submit.prevent="">
                    <input type="text" placeholder="Filtra le foto per nome"
                        class="form-control d-inline-block border-2 border-black py-2" v-model="nomeFilter">
                </form>
            </div>
            <div class="col-12 py-3">
                <div class="row justify-content-center">
                    <div class="col-6 p-5" v-for="photo in newPhotos" :key="photo.id">
                        <div class="card text-center" style="width: 500px">
                            <img :src="photo.url" class="card-img-top" style="height: 400px">
                            <div class="card-body py-5">
                                <h5 class="card-title" v-text="photo.name"></h5>
                            </div>
                            <div class="card-footer py-3">
                                <button class="btn btn-info" @click="openPhoto(photo.id)">
                                    <i class="fas fa-eye"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <photo-show v-else :photo="photoActive" @close-page="closePage" />
</template>

<style lang="scss" scoped></style>