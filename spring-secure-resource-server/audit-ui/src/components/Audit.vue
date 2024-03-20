<script lang="ts">
import { ref, defineComponent, getCurrentInstance } from 'vue'
import axios from "axios";
import "leaflet/dist/leaflet.css";
import { LMap, LTileLayer } from "@vue-leaflet/vue-leaflet";

type AuditData = {
  name: string;
  val: string;
}

type AuditEvent = {
  context: string;
  name: string;
  principal: string; 
  result: string;
  eventTime: number;
  auditData: AuditData[];

}

type Pageable = {
  pageNumber: number;
  pageSize: number;
  offset: number;
}

type AuditResponse = {
  content: AuditEvent[];
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  numberOfElements: number;
  first: boolean;
}

type Data = {
  endTime: string;
  startTime: string;
  pageSize: number;
  pageNum: number;
  auditData: AuditEvent[];
  noAuditEvents: boolean;
  validData: boolean;
  errorCondition: boolean;
  fullDataResponse: AuditResponse;
}

export default defineComponent({
  name: 'Search',
  data(): Data {
    return {
      endTime: new Date().toISOString().substring(0,16),
      startTime: new Date(new Date().setDate(new Date().getDate()-1)).toISOString().substring(0,16),
      pageSize: 15,
      pageNum: 1,      
      auditData: [],
      noAuditEvents: false,
      validData: false,
      errorCondition: false,    
      fullDataResponse: <AuditResponse>{},
    };
  }, 
  methods: {
    stringToDateMills (str: string) {
      if (str === '')
       return 0;

       return Date.parse(str);
    },

    millsToDate (mills: number) {
      let dt  = new Date(mills);

       return dt.toLocaleString('en-US');
    },

    initialStartTime () {
       let dt = new Date()
       dt.toISOString().substring(0,16);
    },

    formatEventData (data: AuditData[]) {
      // hard code to single data for now
      return data[0].name + ": " + data[0].val;
    },

    isNavigatableData () {

      if (!this.auditData || this.auditData.length == 0)
      {
        return false;
      }

      return !(this.fullDataResponse.last && this.fullDataResponse.first);
    },

    formatItemsInfo () {
      if (!this.auditData || this.auditData.length == 0)
      {
        return "0 of 0 items";
      }

      return  (this.fullDataResponse.pageable.offset + 1) + " - " + (this.fullDataResponse.pageable.offset + this.fullDataResponse.numberOfElements) + " of " 
         + this.fullDataResponse.totalElements + " items"

    },

    formatPageInfo () {
      if (!this.auditData || this.auditData.length == 0)
      {
        return "";
      }

      return  (this.fullDataResponse.pageable.pageNumber + 1) + " / "  + (this.fullDataResponse.totalPages)

    },

    onSearchClick() {
      this.pageNum = 1;
      this.searchAuditEvent();
    },

    firstPage () {

      if (this.pageNum != 1)
      {
        this.pageNum = 1;
        this.searchAuditEvent();
      }
    },

    nextPage () {

      if (this.pageNum != this.fullDataResponse.totalPages)
      {
        ++this.pageNum;
        this.searchAuditEvent();
      }
    },

    previousPage () {
       if (this.pageNum != 1)
       {
          --this.pageNum;
          this.searchAuditEvent();
       }
    },

    lastPage () {

      if (this.pageNum != this.fullDataResponse.totalPages) 
      {
        this.pageNum = this.fullDataResponse.totalPages;
        this.searchAuditEvent();
      }
    },

    validateInput () {

      const start = this.stringToDateMills(this.startTime.valueOf());
      const end = this.stringToDateMills(this.endTime.valueOf());
      // ensure the end date time is past now
      if (start >= end)
      {
        alert("The end time must later than the start time.");
        return false;
      }

      return true;
    },

    searchAuditEvent() {

        if (!this.validateInput())
          return;

        const start = this.stringToDateMills(this.startTime.valueOf());
        const end = this.stringToDateMills(this.endTime.valueOf());

        axios.get('/api/audit/audit/eventTime/' + start + '/' + end + "?size=" + this.pageSize + "&page=" + (this.pageNum -1))
        .then(response => {
            this.errorCondition = false;
            this.auditData = response.data.content;
            this.fullDataResponse = response.data;
            if (response.data.content && response.data.content.length > 0)
            {
              console.log("Vadid data true");
               this.validData = true;
               this.noAuditEvents = false;
            }
            else
            {
               console.log("Vadid data false");
               this.validData = false;
               this.noAuditEvents = true;
            }
        })
        .catch(error => {
            this.auditData = [];
            this.noAuditEvents = false;
            this.validData = false;
            this.errorCondition = true;
            this.fullDataResponse = <AuditResponse>{};
        });   
    },
  },  
});
 
</script>

<template>
    <main>
      <div id="searchContainer">
        <h1>Enter audit time range and click search</h1>
          <div>
          <label for="name">Start Time</label> 
          <input v-model="startTime"  value="{{initialStartTime()}}" type="datetime-local" />
          </div>
          <div>
          <label for="name">End Time Time</label> 
          <input v-model="endTime" value="{{initialStartTime()}}" type="datetime-local" />          
          </div>
          <button @click="onSearchClick()">Search</button>
         
      </div> 
    </main>   
    <table class="audit-table">
      <thead>
        <tr>
          <th><div style="width: 150px" />Event Time</th>              
          <th><div style="width: 140px" />Context</th>
          <th><div style="width: 180px" />Name</th>
          <th><div style="width: 250px" />Principal</th>   
          <th><div style="width: 180px" />Data</th>     
          <th><div style="width: 100px" />Result</th>                   
        </tr>
      </thead>
      <tbody>
        <tr v-for="auditEvent in auditData" >
          <td>{{millsToDate(auditEvent.eventTime)}}</td>            
          <td>{{auditEvent.context}}</td>
          <td>{{auditEvent.name}}</td>
          <td>{{auditEvent.principal}}</td>
          <td>{{formatEventData(auditEvent.auditData)}}</td>
          <td>{{auditEvent.result}}</td>       
        </tr>
      </tbody>
    </table>   
    <div v-if="validData" id="pageInfo">
      <label style="width: 70px">Page Size </label>
      <select v-model="pageSize" @change="onSearchClick()" name="pageSize" id="pageSize" class="dropdown">
         <option value="5">5</option>
         <option value="15">15</option>
         <option value="25">25</option>
         <option value="50">50</option>
         <option value="100">100</option>
      </select>
        <label class="tableFooter" > 
          <label style="padding: 0px 20px 0px 20px">{{formatItemsInfo()}}</label>
          <div class="tableFooter" v-if="isNavigatableData()">
            <button @click="firstPage()">|&lt;</button>
            <button @click="previousPage()">&lt;</button> 
            <label>{{formatPageInfo()}}</label> 
            <button @click="nextPage()">&gt;</button>
            <button @click="lastPage()">&gt;|</button> 
          </div>
        </label>
    </div>
    <h1 v-if="noAuditEvents">No events found for this time range</h1>    
    <h1 v-if="errorCondition">Error occurred during search</h1>   
    <form action="/scg-logout" method="GET" id="logoutForm">
        <input type="hidden" id="logout" name="redirect" value="/"/>
        <button>Logout</button>
    </form>    
</template>