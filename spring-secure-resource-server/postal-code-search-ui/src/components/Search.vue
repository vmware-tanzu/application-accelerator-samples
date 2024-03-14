<script lang="ts">
import { ref, defineComponent, getCurrentInstance } from 'vue'
import axios from "axios";
import "leaflet/dist/leaflet.css";
import { LMap, LTileLayer } from "@vue-leaflet/vue-leaflet";

export default defineComponent({
  name: 'Search',
  data() {
    return {
      postalCode: '',
      zoom: 14,
      geoloc: [30,-94],  
      validData: false,
      codeNotFound: false,
      errorCondition: false,     
    };
  },
  components: {
    LMap,
    LTileLayer,
  },  
  methods: {
    searchPostalCode() {
        axios.get('/api/search/geolookup?postalCode=' + this.postalCode)
        .then(response => {
            this.errorCondition = false;
            if (response.data)
            {
               this.geoloc = [response.data.latitude , response.data.longitude];
               this.validData = true;
               this.codeNotFound = false;
            }
            else
            {
               this.validData = false;
               this.codeNotFound = true;
            }
        })
        .catch(error => {
            this.validData = false;
            this.codeNotFound = false;
            this.errorCondition = true;
        });   
    },
  },  
});
 
</script>

<template>
    <main>
      <div>
        <h1>Enter a postal code and click search</h1>
          <label for="name">Postal Code:
          <input v-on:keyup.enter="searchPostalCode" v-model="postalCode" type="text" placeholder="US Postal Code" />
          <button @click="searchPostalCode">Search</button>
      </label>          
      </div> 
    </main>  
    <h1 v-if="codeNotFound">Postal code not found</h1>    
    <h1 v-if="errorCondition">Error occurred during search</h1>        
    <div style="height: 75vh; width: 50vw;">
      <l-map  v-if="validData" :use-global-leaflet="false" ref="map" v-model:zoom="zoom" v-model:center="geoloc">
      <l-tile-layer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        layer-type="base"
        name="OpenStreetMap"
      ></l-tile-layer>
    </l-map>         
    </div>    
    <form action="/scg-logout" method="GET" id="form">
        <input type="hidden" id="logout" name="redirect" value="/"/>
        <button>Logout</button>
    </form>    
</template>