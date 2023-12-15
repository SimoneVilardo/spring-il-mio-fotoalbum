<script setup>
// PROPS
const props = defineProps({
    photo: {
        type: Object,
        required: true
    }
});

// EMITS
const emits = defineEmits(["closePhoto"]);
</script>

<template>
    <div class="show-background p-5">
        <div class="text-center  pb-5">
            <button class="link btn btn-danger" @click="$emit('closePhoto')">
                X
            </button>
        </div>
        <img :src="photo.url" class="rounded">
        <div class="p-3">
            <h5 v-text="photo.name" class="card-title my-3"></h5>
            <p v-text="photo.description" class="card-text my-3"></p>
        </div>
    </div>
</template>

<style lang="scss" scoped>
@use '../styles/generals.scss' as *;

.show-background {
    background-color: white;
    color: black;
    border: 1px solid black;
    border-radius: 10px !important;
}


</style>