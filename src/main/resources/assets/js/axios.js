import axios from 'axios';

const query = `
     query {
       getUsers {
         id
         name
       }
     }
   `;

axios.post('http://localhost:8080/graphql', {query})
    .then(response => {
        console.log(response.data.data);
    })
    .catch(error => {
        console.error(error);
    });