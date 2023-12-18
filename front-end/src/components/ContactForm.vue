<script setup>
// IMPORT LIBS
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

// DATA
const router = useRouter();
const loading = ref(false);
const contact = ref({
    name: '',
    email: '',
    message: ''
});

// FUNCTIONS
const sendMessage = async () => {
    loading.value = true;
    const response = await axios.post('http://localhost:8080/api/contact', contact.value);
    console.log(response);
    router.push({ name: 'index' });
};
</script>

<template>
    <div class="col-12 border rounded-5 shadow my-5">
        <div class="row px-5">
            <div class="col-12 py-3">
                <router-link :to="{ name: 'index' }">
                    <button class="btn btn-black mx-2">
                        <i class=" fa-solid fa-circle-arrow-left fa-xl"></i>
                    </button>
                </router-link>
            </div>
            <div class="col-12 text-center py-3">
                <form @submit.prevent="sendMessage">
                    <div class="input-group my-3">
                        <span class="input-group-text" id="basic-addon2">Nome</span>
                        <input type="text" id="name" class="form-control" v-model="contact.name" required>
                    </div>
                    <div class="input-group my-3">
                        <span class="input-group-text" id="basic-addon2">Email</span>
                        <input type="email" id="email" class="form-control" v-model="contact.email" required>
                    </div>
                    <div class="input-group my-3">
                        <span class="input-group-text" id="basic-addon2">Testo</span>
                        <textarea type="text" id="message" class="form-control" v-model="contact.message"
                            required></textarea>
                    </div>
                    <button type="submit" class="btn btn-success my-4" :disabled="loading">{{ loading ? 'Invio in corso' :
                        'Invia' }}</button>
                </form>
            </div>
        </div>
    </div>
</template>

  

<style scoped></style>