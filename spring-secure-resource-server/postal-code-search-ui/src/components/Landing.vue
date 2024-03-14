<script setup lang="ts">
  const login = () => {
    let port = (window.location.port ? ':' + window.location.port : '');
    window.location.href = '//' + window.location.hostname + port + '/postalSearch';
  }
</script>

<template>
   <main>
            <div align="center" >
              <h1>Welcome to Location Search</h1>                    
            </div> 
            <div className="container welcomeContent">
                      The location search application takes a US postal code and loads a map of the area.
            </div>                  
            <div align="center" id="login">
                  <button @click="login" >Login</button>
            </div> 
    </main>           
</template>
