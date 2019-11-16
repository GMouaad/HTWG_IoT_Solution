<template>
    <v-card>
        <v-card v-for="project in projects" :key="project.title">
            <v-card-title>
                {{project.title}}
            </v-card-title>
            <v-card-text>
                By {{project.owner}}
            </v-card-text>
        </v-card>
        <v-card v-for="employee in employees" :key="employee.id">
            <v-card-title>
                {{employee.employee_name}}
            </v-card-title>
            <v-card-text>
                Salary {{employee.employee_salary}}
            </v-card-text>
        </v-card>
    </v-card>
</template>
<script>
export default {
    name: 'Projects',
    data: () => ({
        projects: [
          { title: 'Mobile App Dev', owner: 'Mouaad Gssair' },
          { title: 'PoC Microservices', owner: 'Mouaad Gssair' },
          { title: 'IoT Infrastructur', owner: 'Mouaad Gssair' },
          { title: 'BoW Control System', owner: 'Daniel Grotz & M. Gssair' }
        ],
        employees: []
    }),
    created() {
        this.$http.get("http://localhost:8080/api/project-mgmt/projects").then(result => {
                this.projects.push(result.body);
            }, error => {
                console.error(error);
            });
        // this.$http.get("http://dummy.restapiexample.com/api/v1/employees").then(result => {
        //         this.employees = result.body;
        //     }, error => {
        //         console.error(error);
        //     });
    }
}
</script>