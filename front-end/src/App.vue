<script setup>
// IMPORT LIBS
import { onMounted, ref } from 'vue';
import axios from 'axios';

// IMPORT COMPONENTS
import PhotoIndex from './components/PhotoIndex.vue';
import PhotoShow from './components/PhotoShow.vue';

// DATA
const photos = ref(null);
const photoActive = ref(null);

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
const closePhoto = () => {
  photoActive.value = null;
  getPhotos();
};

// HOOKS
onMounted(getPhotos);
</script>

<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-12">
        <photo-index v-if="photoActive == null" :photos="photos" @open-photo="openPhoto" />
        <photo-show v-else :photo="photoActive" @close-photo="closePhoto" />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use './styles/generals.scss' as *;
</style>