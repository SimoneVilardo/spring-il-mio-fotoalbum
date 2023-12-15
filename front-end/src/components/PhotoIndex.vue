<script setup>
// IMPORT LIBS
import { defineProps, ref, computed } from 'vue'; 

// DATA
const nomeFilter = ref('');

// EMITS
const emits = defineEmits(["openPhoto"]);


// COMPUTED PROPERTY
const filteredPhotos = computed(() => {
    const searchTerm = nomeFilter.value.toLowerCase().trim();
    return searchTerm ? props.photos.filter(photo => photo.name.toLowerCase().includes(searchTerm)) : props.photos;
});

// PROPS
const props = defineProps({
    photos: {
        type: Array,
        required: true
    }
});
</script>
<template>
    <h1 class="text-center">LE MIE FOTO</h1>
    <form class="index-filter-form py-5">
        <input type="text" placeholder="Filtra le foto per nome" class="form-control d-inline-block" v-model="nomeFilter">
    </form>
    <table class="table table-dark table-hover index-table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">Strumenti</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="photo in filteredPhotos" :key="photo.id">
                <th scope="row" v-text="photo.id"></th>
                <td v-text="photo.name"></td>
                <td>
                    <button class="btn btn-info" @click="$emit('openPhoto', photo.id)">
                        <i class="fas fa-eye"></i>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>
</template>

<style lang="scss" scoped>
@use '../styles/generals.scss' as *;

.index-table {
    width: 50vw !important;
}
</style>