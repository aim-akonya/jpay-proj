<template>
  <q-page class="q-pa-md">
    <div class="q-py-md">
      <div class="row">
        <div class="col-sm-12 col-md-6 col-lg-6 q-pa-xs">
          <q-input
            filled
            label="Country"
            hint="filter by country"
            v-model="search.country"
          />
        </div>

        <div class="col-sm-12 col-md-6 col-lg-6 q-pa-xs">
            <q-input
              filled
              label="State"
              hint="filter by state"
              v-model="search.state"
            />
          </div>
      </div>
    </div>
    <q-table
     title="Phonebook"
     :loading="loading"
     :columns="columns"
     :rows="data"
     row-key="phoneNumber"
    />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'PageIndex',
  data(){
    return {
      loading: true,
      search:{},
      columns:[
        { name: "name", label:"Full Name", field:"name", sortable:false, align:"left"},
        { name: "phoneNumber", label:"Phone Number", sortable: true, align:"center", field:"phoneNumber"},
        { name: "country", label:"Country", sortable: true, align:"center", field:"country"},
        { name: "state", label:"State(valid/invalid)", sortable: true, align:"center", field:"state"}
      ],
      data:[],
    }
  },
  mounted(){
    this.fetchData()
  },
  methods:{
    fetchData(){
      let url = `http://localhost:8080/api/customer/phonebook?`;
      if(this.search.country){
        url = url+"country="+this.search.country;
      }
      if(this.search.state){
        url = url+"state="+this.search.state;
      }
      this.$axios.get(url)
      .then(response => {
        this.data = response.dataList
        this.loading = false;
      })
      .catch(error => {
        this.loading = false;
        return this.$q.notify({
            type: 'negative',
            position: 'bottom',
            progress: true,
            actions: [
              {
                label: 'Dismiss',
                color: 'white',
                handler: () => {
                  /* ... */
                }
              }
            ],
            message: "Failed to fetch records"
          });
      })
    },
  }
  
})
</script>
