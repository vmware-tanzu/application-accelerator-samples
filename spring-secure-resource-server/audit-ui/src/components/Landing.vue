<script setup lang="ts">
  const login = () => {
    let port = (window.location.port ? ':' + window.location.port : '');
    window.location.href = '//' + window.location.hostname + port + '/auditViewer';
  }
</script>

<template>
   <main align="center">
            <div  >
              <h1 align="center">Location Audit Viewer</h1>                    
            </div> <br>
            <div align="center" className="container welcomeContent">
                      The audit viewer list events occuring within the location system.
            </div>                  
            <div align="center" id="login">
                  <button @click="login" >Login</button>
            </div> 
    </main>           
</template>
