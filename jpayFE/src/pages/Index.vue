<template>
  <q-page class="q-pa-md">
    <div class="q-py-md">
      <div class="row">
        <div class="col-sm-12 col-md-6 col-lg-6 q-pa-xs">
          <q-select
            filled
            label="Country"
            hint="select country"
            v-model="search.country"
            :options="countries"
            use-chips
            clearable
            @update:model-value="fetchData()"
          >
          </q-select>
        </div>

        <div class="col-sm-12 col-md-6 col-lg-6 q-pa-xs">
          <q-select
            filled
            label="State"
            hint="filter by state"
            v-model="search.state"
            :options="states"
            option-value="id"
            option-label="desc"
            clearable
            use-chips
            @update:model-value="fetchData()"
          >
          </q-select>
        </div>
      </div>
    </div>
    <q-table
      title="Phonebook"
      :loading="loading"
      :columns="columns"
      :rows="data"
      row-key="phoneNumber"
      :rows-per-page-options="[10, 20]"
    >
      <template v-slot:body="props">
        <q-tr :props="props">
          <q-td key="name" :props="props">
            {{ props.row.name }}
          </q-td>
          <q-td key="phoneNumber" :props="props">
            {{ props.row.phoneNumber }}
          </q-td>
          <q-td key="country" :props="props">
            {{ props.row.country }}
          </q-td>
          <q-td key="countryCode" :props="props">
            {{ "+" + props.row.countryCode }}
          </q-td>
          <q-td key="state" :props="props">
            {{ props.row.state === "VALID" ? "Valid" : "Invalid" }}
          </q-td>
        </q-tr>
      </template>
    </q-table>
  </q-page>
</template>

<script>
import { defineComponent } from "vue";

export default defineComponent({
  name: "PageIndex",
  data() {
    return {
      countries: ["CAMEROON", "ETHIOPIA", "MOROCCO", "MOZAMBIQUE", "UGANDA"],
      states: [
        { id: "VALID", desc: "valid" },
        { id: "NOT_VALID", desc: "Invalid" },
      ],
      loading: true,
      search: {},
      columns: [
        {
          name: "name",
          label: "Full Name",
          field: "name",
          sortable: false,
          align: "left",
        },
        {
          name: "phoneNumber",
          label: "Phone Number",
          sortable: true,
          align: "center",
          field: "phoneNumber",
        },
        {
          name: "country",
          label: "Country",
          sortable: true,
          align: "center",
          field: "country",
        },
        {
          name: "countryCode",
          label: "Country Code",
          sortable: "false",
          align: "center",
          field: "countryCode",
        },
        {
          name: "state",
          label: "State(valid/invalid)",
          sortable: true,
          align: "center",
          field: "state",
        },
      ],
      data: [],
      baseUrl: process.env.API_BASE_URL,
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      let url = `${this.baseUrl}/api/customer/phonebook?`;

      if (this.search.country && this.search.state) {
        url =
          url +
          "country=" +
          this.search.country +
          "&state=" +
          this.search.state.id;
      } else if (this.search.country) {
        url = url + "country=" + this.search.country;
      } else if (this.search.state) {
        url = url + "state=" + this.search.state.id;
      }

      this.$axios
        .get(url)
        .then((response) => {
          this.data = response.data.dataList;
          this.loading = false;
        })
        .catch((error) => {
          this.loading = false;
          return this.$q.notify({
            type: "negative",
            position: "bottom",
            progress: true,
            actions: [
              {
                label: "Dismiss",
                color: "white",
                handler: () => {
                  /* ... */
                },
              },
            ],
            message: "Failed to fetch records",
          });
        });
    },
  },
});
</script>
