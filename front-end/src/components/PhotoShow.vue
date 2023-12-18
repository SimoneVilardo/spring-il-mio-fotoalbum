<script setup>
// PROPS
const props = defineProps({
    photo: {
        type: Object,
        required: true
    }
});

// EMITS
const emits = defineEmits(["closePage"]);
</script>

<template>
    <div class="col-12 d-flex justify-content-center border rounded-5 shadow my-5">
        <div class="row">
            <div class="col-12 py-3">
                <button class="btn btn-black mx-2" @click="$emit('closePage')">
                    <i class=" fa-solid fa-circle-arrow-left fa-xl"></i>
                </button>
            </div>
            <div class="col-6 px-3 py-5">
                <img :src="photo.url" class="img-fluid">
            </div>
            <div class="col-6 px-3 py-5">
                <h3 v-text="photo.name" class="card-title my-4"></h3>
                <p v-text="photo.description" class="card-text my-4"></p>
                <div v-for="category in photo.categories" :key="category.id" class="d-flex flex-row">
                    <div class="border rounded-5 px-3 py-1 m-1 badge bg-info">
                        <span v-text="category.name" class="fs-6"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
@use '../styles/generals.scss' as *;
</style>