
class LocalStorageService {
    #TOKEN = 'token';
    #EMAIl = 'email';
    #ROLE = 'role';
    #FIRST_NAME = 'firstName';
    #LAST_NAME = 'lastName';
 

    setToken(token) {
        localStorage.setItem(this.#TOKEN, token);
    }
    getToken() {
        return localStorage.getItem(this.#TOKEN);
    }
    

    setEmail(email) {
        localStorage.setItem(this.#EMAIl, email);
    }
    getEmail() {
        return localStorage.getItem(this.#EMAIl) || null;
    }

    setRole(role) {
        localStorage.setItem(this.#ROLE, role);
    }
    getRole() {
        return localStorage.getItem(this.#ROLE) || null;
    }
    setFirstName(firstName) {
        localStorage.setItem(this.#FIRST_NAME, firstName);
    }
    getFirstName() {
        return localStorage.getItem(this.#FIRST_NAME) || null;
    }
    setLastName(lastName) {
        localStorage.setItem(this.#LAST_NAME, lastName);
    }
    getLastName() {
        return localStorage.getItem(this.#LAST_NAME) || null;
    }
    
}


export default LocalStorageService;