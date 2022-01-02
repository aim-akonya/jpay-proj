<template>
  <q-page class="q-pa-md">
    <q-table
     title="Phonebook"
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
      columns:[
        { name: "name", label:"Full Name", field:"name", sortable:false, align:"left"},
        { name: "phoneNumber", label:"Phone Number", sortable: true, align:"center", field:"phoneNumber"},
        { name: "country", label:"Country", sortable: true, align:"center", field:"country"},
        { name: "state", label:"State(valid/invalid)", sortable: true, align:"center", field:"state"}
      ],
      data:[{
        name: "Kelvin Akiya",
        phoneNumber: "(254) 702059654",
        country: "Kenya",
        state:"Valid"
      },
      {
        name: "Mike Akonya",
        phoneNumber: "(256) 7470596540",
        country: "Uganda",
        state:"Invalid"
      }],
    }
  }
})
</script>
