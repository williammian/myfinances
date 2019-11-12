import axios from 'axios'

const httpClient = axios.create({
    baseURL: 'http://localhost:8080'
})

class ApiService {

    constructor(apiurl) {
        this.apiurl = apiurl;
    }

    post(url, objeto) {
        return httpClient.post(url, objeto);
    }

    put(url, objeto) {
        return httpClient.put(url, objeto);
    }
    
    delete(url) {
        return httpClient.delete(url);
    }

    get(url) {
        return httpClient.get(url);
    }
}

export default ApiService;