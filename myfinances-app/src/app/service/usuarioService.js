import ApiService from '../apiservice'

class UsuarioService extends ApiService {

    constructor() {
        super('/api/usuarios')
    }

}

export default UsuarioService;