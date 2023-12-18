<script setup>
// IMPORT COMPONENTS
import AppHeader from './components/AppHeader.vue';
</script>

<template>
  <app-header />
  <main>
    <div class="container">
      <div class="row">
        <router-view></router-view>
      </div>
    </div>
  </main>
</template>

<style lang="scss">
@use './styles/generals.scss' as *;
</style>