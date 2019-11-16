import React from 'react'
import { withRouter } from 'react-router-dom'

import Card from '../../components/card'
import FormGroup from '../../components/form-group'
import SelectMenu from '../../components/selectMenu'
import * as messages from '../../components/toastr'

import LancamentoService from '../../app/service/lancamentoService'
import LocalStorageService from '../../app/service/localstorageService'

class CadastroLancamentos extends React.Component {

    state = {
        id: null,
        descricao: '',
        valor: '',
        mes: '',
        ano: '',
        tipo: '',
        status: ''
    }

    constructor() {
        super();
        this.service = new LancamentoService();
    }

    componentDidMount() {
        const params = this.props.match.params;
        console.log('params', params);
    }

    submit = () => {
        const usuarioLogado = LocalStorageService.obterItem('_usuario_logado');

        const { descricao, valor, mes, ano, tipo } = this.state;
        const lancamento = { descricao, valor, mes, ano, tipo, usuario: usuarioLogado.id };

        this.service
            .salvar(lancamento)
            .then( response => {
                this.props.history.push('/consulta-lancamentos');
                messages.mensagemSucesso('Lançamento cadastrado com sucesso!');
            }).catch( error => {
                messages.mensagemErro(error.response.data);
            });
    }

    handleChange = (event) => {
        const value = event.target.value;
        const name = event.target.name;

        this.setState({ [name] : value });
    }

    render(){
        const tipos = this.service.obterListaTipos();

        const meses = this.service.obterListaMeses();

        return(
            <Card title="Cadastro de Lançamento">
                <div className="row">
                    <div className="col-md-12">
                        <FormGroup id="inputDescricao" label="Descrição: *">
                            <input id="inputDescricao" 
                                   type="text" 
                                   className="form-control" 
                                   name="descricao" 
                                   value={this.state.descricao}
                                   onChange={this.handleChange} />
                        </FormGroup>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <FormGroup id="inputAno" label="Ano: *">
                            <input id="inputAno" 
                                   type="text" 
                                   className="form-control" 
                                   name="ano" 
                                   value={this.state.ano}
                                   onChange={this.handleChange} />
                        </FormGroup>
                    </div>
                    <div className="col-md-6">
                        <FormGroup id="inputMes" label="Mês: *">
                            <SelectMenu id="inputMes" 
                                        className="form-control" 
                                        lista={meses}
                                        name="mes" 
                                        value={this.state.mes}
                                        onChange={this.handleChange} />
                        </FormGroup>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-4">
                        <FormGroup id="inputValor" label="Valor: *">
                            <input id="inputValor" 
                                   type="text" 
                                   className="form-control" 
                                   name="valor" 
                                   value={this.state.valor}
                                   onChange={this.handleChange} />
                        </FormGroup>
                    </div>
                    <div className="col-md-4">
                        <FormGroup id="inputTipo" label="Tipo: *">
                            <SelectMenu id="inputTipo" 
                                        className="form-control" 
                                        lista={tipos}
                                        name="tipo" 
                                        value={this.state.tipo}
                                        onChange={this.handleChange}/>
                        </FormGroup>
                    </div>
                    <div className="col-md-4">
                        <FormGroup id="inputStatus" label="Status: *">
                            <input id="inputStatus" 
                                   type="text" 
                                   className="form-control" disabled
                                   name="status" 
                                   value={this.state.status}/>
                        </FormGroup>
                    </div>
                </div>
                <div>
                    <div className="row">
                        <div className="col-md-6">
                            <button onClick={this.submit} className="btn btn-success">Salvar</button>
                            <button onClick={e => this.props.history.push('/consulta-lancamentos')} className="btn btn-danger">Cancelar</button>
                        </div>
                    </div>
                </div>
            </Card>
        )
    }

}

export default withRouter( CadastroLancamentos )